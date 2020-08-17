/**
 * 
 */
package com.hk.abcfund.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCInvestTransactionDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCLoanTransactionDao;
import com.hk.abcfund.model.dto.ABCAdminLoanManageDto;
import com.hk.abcfund.model.dto.ABCInvestChartDto;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestManageDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanChartDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;

/**
 * 관리자 화면을 제어하기 위한 서비스 구현 클래스
 * @author 9age
 *
 */
@Service
public class ABCAdminSerivceImpl implements ABCAdminSerivce {
	/** 관리자 DAO */
	@Autowired
	private ABCAdminDao adao;
	
	/** 대출 DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** 투자 DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** 투재내역 DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/** 대출내역 DAO */
	@Autowired
	private ABCLoanTransactionDao loanTranDao;
	
	/** slf4j 로깅 */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanServiceImpl.class);
	
	/**
	 * 심사대기 리스트를 호출하는 메서드
	 */
	@Override
	public List<ABCJudgeDto> getJudgeList() {
		return adao.getJudgeList();
	}

	/**
	 * 심사대기 상세내용을 호출하는 메서드
	 * @param loanCode 대출코드
	 */
	@Override
	public ABCJudgeDto getJudgeDetail(int loanCode) {
		return adao.getJudgeDetail(loanCode);
	}

	/**
	 * 심사결과를 처리하는 메서드 호출
	 * @param ABCJudgeResultDto 심사결과 정보를 담은 DTO 객체
	 */
	@Override
	@Transactional
	public void addJudgeResult(ABCJudgeResultDto jrdto) {
		// 심사결과를 등록
		adao.addJudgeResult(jrdto);
		
		// 심사결과가 승인이면 대출의 진행상황을 '펀딩진행중'으로 나타낸다.
		if(jrdto.getResult() == 1) {
			ABCLoanDto loan = new ABCLoanDto(jrdto.getLoanCode(), "펀딩진행중");
			loanDao.updateProgress(loan);
		}
	}
	
	/**
	 * 해당 대출코드를 가진 심사레코드 삭제 
	 */	
	@Override
	public void deleteJudgeByLoan(int loanCode) {
		adao.deleteJudgeByLoan(loanCode);
	}
	
	/**
	 * 투자그래프에 관련된 데이터를 생성하는 메서드
	 * @param email 회원의 이메일
	 * @return JSON으로 생성된 데이터
	 */
	@Override
	public List<ABCInvestChartDto> getInvestChartData(String email) {
		// 리스트 가져오기
		List<ABCInvestDto> investList = investDao.getInvestList(email);
		List<ABCInvestTransactionDto> investTranList = investTranDao.getTransactionReserve(email);
		
		// 리스트 생성
		List<ABCInvestChartDto> chartList = new ArrayList<ABCInvestChartDto>();
		
		for(int i = 0; i < investList.size(); i++){
			ABCInvestDto invest = investList.get(i);
			int investSeq = invest.getInvestSeq();
			int investMoney = invest.getInvestMoney();
			int stackInterest = 0;
			int stackOrigin = 0;
			int max = -999;
			for(int j = 0; j < investTranList.size(); j++){
				ABCInvestTransactionDto investTran = investTranList.get(j);
				if(investSeq == investTran.getInvestSeq()){
					if(max == -999) max = 0;
					
					// 누적 이자 계산
					stackInterest += investTran.getIntendProfit();
					
					// 최대 누적 원금을 가진 내역 찾기
					if(investTran.getRound() > investTranList.get(max).getRound())
						max = j;
				}
			}
			if(max != -999)
				stackOrigin = investTranList.get(max).getStackCollect();
			
			// 리스트에 등록
			chartList.add(new ABCInvestChartDto(investSeq, investMoney, stackOrigin, stackInterest));
		}
		
		return chartList;
	}
	
	/**
	 * 대출 차트 데이터를 추출하는 메서드
	 * @param email 회원의 이메일
	 */
	@Override
	public List<ABCLoanChartDto> getLoanChartData(String email) {
		// 리스트 생성
		List<ABCLoanChartDto> chartList = new ArrayList<ABCLoanChartDto>();
		
		// 대출내역 리스트 가져오기
		List<ABCLoanTransactionDto> loanTranList = loanTranDao.getTransactionBySorted(email);
		
		if(loanTranList.isEmpty()) return chartList;
		
		// 최대 누적 원금 및 누적 이자금 구하기
		int stackRepayRate = 0;	// 누적 이자금
		int stackRepayOrigin = 0; // 누적 원금
		int loanCode = loanTranList.get(0).getLoanCode();	// 대출 코드
		for(int idx = 0; idx < loanTranList.size(); idx++){
			// 내역 가져오기
			ABCLoanTransactionDto loanTran = loanTranList.get(idx);
			
			// 다른 내역으로 넘어간 경우
			if(loanCode != loanTran.getLoanCode()){
				// 차트 데이터 리스트에 추가하고 각 변수 초기화
				chartList.add(new ABCLoanChartDto(loanCode, stackRepayOrigin, stackRepayRate));
				loanCode = loanTran.getLoanCode();
				stackRepayRate = 0;
				stackRepayOrigin = 0;
			} else if(idx+1 == loanTranList.size()){ // 마지막 내역인 경우
				// 누적 이자 구하기
				stackRepayRate += loanTran.getStackRepayRate();
				
				// 누적 원금 담기
				stackRepayOrigin = loanTran.getStackRepayOrigin();
				chartList.add(new ABCLoanChartDto(loanCode, stackRepayOrigin, stackRepayRate));
				
				break;
			}
			
			// 누적 이자 구하기
			stackRepayRate += loanTran.getStackRepayRate();
			
			// 누적 원금 담기
			stackRepayOrigin = loanTran.getStackRepayOrigin();
		}
		logger.info("대출 차트 데이터: " + chartList);
		
		return chartList;
	}
	
	/** 
	 * 투자관리의 데이터를 추출하는 메서드
	 * @return 투자관리 데이터 리스트
	  */
	@Override
	public List<ABCInvestManageDto> getInvestManageData() {
		// 리스트 생성
		List<ABCInvestManageDto> chartList = new ArrayList<ABCInvestManageDto>();
		
		// 모든 투자리스트 및 투자내역 가져오기
		List<ABCInvestDto> investList = investDao.getInvestListAll();
		List<ABCInvestTransactionDto> investTranList = investTranDao.getTransactionAll();
				
		// 데이터 추출
		for(int i = 0; i < investList.size(); i++){
			ABCInvestDto invest = investList.get(i);
			int investMoney = invest.getInvestMoney(); // 투자금
			int stackProfit = 0;  // 누적 수익금
			String progress = "";
			for(int j = 0; j < investTranList.size(); j++){
				ABCInvestTransactionDto investTran = investTranList.get(j);
				if(invest.getInvestSeq() == investTran.getInvestSeq()){
					// 누적 수익금 구하기
					stackProfit += investTran.getIntendProfit();
					
					// 진행상태 구하기
					progress = investTran.getProgress();
				}
			}
			chartList.add(new ABCInvestManageDto(
				invest.getInvestSeq(), investMoney, stackProfit, progress));
		}
		logger.info("생성된 리스트: "+chartList);
		
		return chartList;
	}
	
	/**
	 * 대출관리 정보 얻기 
	 */	
	@Override
	@Transactional
	public ABCAdminLoanManageDto getLoanManage() {
		
		logger.info("getLoanManage() 호출 성공!" + new Date());
		ABCAdminLoanManageDto dto =  new ABCAdminLoanManageDto();
		
		dto.setStackLoanNum((adao.getAdminLoanInfo1()).getStackLoanNum()); //대출실행건수
		dto.setStackLoanMoney((adao.getAdminLoanInfo1()).getStackLoanMoney()); //누적대출금
		dto.setStackLoanRepayMoney((adao.getAdminLoanInfo2()).getStackLoanRepayMoney()); //누적대출상환금
		dto.setLoanEndNum((adao.getAdminLoanInfo3()).getLoanEndNum()); //상환완료 건수
		dto.setLoanMiddleNum((adao.getAdminLoanInfo4()).getLoanMiddleNum()); //상환중인건수
		
		return dto;
	}

}