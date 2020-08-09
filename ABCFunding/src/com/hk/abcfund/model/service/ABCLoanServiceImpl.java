package com.hk.abcfund.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCInvestTransactionDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCLoanTransactionDao;
import com.hk.abcfund.model.dao.ABCMemberDao;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * 대출 신청에 대한 서비스 클래스
 * @author 9age
 *
 */
@Service
public class ABCLoanServiceImpl implements ABCLoanService {
	/** slf4j 로깅 */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanServiceImpl.class);

	/** 대출신청 DAO */
	@Autowired
	private ABCLoanDao ldao;
	
	/** 회원 DAO */
	@Autowired
	private ABCMemberDao memberDao;
	
	/** 투자 DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** 심사 DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** 계좌 DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** 대출내역 DAO */
	@Autowired
	private ABCLoanTransactionDao loanTranDao;
	
	/** 투자내역 DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/**
	 * 대출 신청 등록 메서드
	 * @param ldto 대출 신청 정보를 담은 객체
	 */
	@Override
	@Transactional
	public void addLoan(ABCLoanDto ldto) {
		// 대출 신청 정보 등록
		ldao.addLoan(ldto);
		
		// 신청자의 대출건수 증가
		memberDao.incLoan(ldto.getEmail());
		
		// 대출 신청 정보 등록 후 대출코드를 받아와 심사 테이블에 심사대기건으로 등록
		int lcode = ldao.getLoanCode();
		ldao.addJudge(lcode);
		
	}

	@Override
	public List<ABCLoanDto> getLoanList() {
		return ldao.getLoanList();
	}

	@Override
	public ABCLoanDto getLoan(int loanCode) {
		return ldao.getLoan(loanCode);
	}

	@Override
	public List<ABCLoanDto> getLoanListAll(String email) {
		return ldao.getLoanListAll(email);
	}
	
	/**
	 * 대출취소하는 메서드.
	 * 미승인 대출은 관련 데이터와 함께 바로 삭제하고
	 * 펀딩진행중인 대출은 투자금을 환급하고 삭제한다.
	 * @param loanCode 취소할 대출의 대출코드
	 */
	@Override
	@Transactional
	public void loanCancel(int loanCode) {
		// 1. 투자금 환급
		// 해당 대출코드를 가진 투자리스트 가져오기
		List<ABCInvestDto> list = investDao.getInvestListByLoan(loanCode);
		
		/* 
		 * 투자자가 있는 경우에만 수행
		 * 투자리스트로 계좌잔액 변경 및 투자 데이터 삭제
		 */
		if(!list.isEmpty()) {
			accountDao.updateForPayback(list);
			investDao.deleteByLoan(loanCode);
		}
		
		// 2. 해당 대출의 가상계좌 삭제
		accountDao.deleteByLoan(loanCode);
		
		// 3. 해당 대출의 심사데이터 삭제
		adminDao.deleteJudgeByLoan(loanCode);
		
		// 4. 해당 대출데이터 삭제
		ldao.deleteLoan(loanCode);
	}
	
	/**
	 * 대출신청자가 상환했는지 매분 확인하여 상환작업을 수행하는 메서드
	 */
	@Override
	@Transactional
	@Scheduled(cron="0 0/1 * 1,5,10,15,20,25 * ?")
	//@Scheduled(cron="0/10 * * * * ?")
	public void checkRepay() {
		// 1. 대출 리스트 가져오기
		List<ABCLoanDto> loanList = ldao.getLoanList();
		logger.info("대출리스트:\n"+loanList.toString());
		
		// 2. 상환일 확인
		for(ABCLoanDto loan : loanList){
			// 상환일이고 아직 상환완료가 되지 않은 대출인 경우 상환작업 수행
			if(ABCUtility.isSameDate(loan.getRequestDate()) &&
					!loan.getProgress().equals("상환완료")){
				logger.info("진행중인 대출: " + loan);
				
				// 필요한 값을 담을 맵 생성
				Map<String, Object>map = new HashMap<String, Object>();
				
				// 상환금 구하기
				int monthlyPay = ABCUtility.getRepayMoney(
					loan.getLoanMoney(), loan.getLoanDate(),loan.getInterestRate()/100.0);
				logger.info("상환금: " + monthlyPay);
				
				// 대출신청자의 잔액확인
				int balanceOfLoan = accountDao.getBalance(loan.getEmail());
				logger.info("대출신청자의 잔액: " + balanceOfLoan);
				
				// 잔액이 부족한 경우
				if(balanceOfLoan <= 0){
					logger.info("잔액부족으로 상환 스킵");
					continue;
				}
				
				// 대출신청자의 상환금을 대출상품의 계좌로 이체
				map.put("email", loan.getEmail());
				map.put("investMoney", monthlyPay);
				accountDao.withdraw(map);
				logger.info("대출신청자의 상환금 출금완료");
				
				// 대출 상품에 입금
				map.put("loanCode", loan.getLoanCode());
				map.put("investMoney", monthlyPay);
				accountDao.deposit(map);
				logger.info("대출상품의 계좌로 입금 완료");
				
				// 대출상품의 계좌에서 투자자 및 관리자에게 상환
				// 해당 대출의 투자자 리스트 얻어오기
				List<ABCInvestDto> investList = 
						investDao.getInvestListByLoan(loan.getLoanCode());
				
				// 투자자에게 상환
				int totalRepay = monthlyPay;
				for(ABCInvestDto invest : investList){
					// 해당 투자자에게 상환해야 할 금액
					int repayMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(),0.06);
					logger.info("투자자 상환금: " + repayMoney);
					
					// 대출계좌의 예치금 차감
					if(totalRepay <= 0) break;	// 상환금액이 부족한 경우 
					map.put("loanCode", loan.getLoanCode());
					map.put("money", repayMoney);
					accountDao.withdrawForInvest(map);
					totalRepay -= repayMoney;
					logger.info("남은 예치금: " + totalRepay);
					
					// 투자자의 계좌에 입금
					map.put("email", invest.getEmail());
					map.put("money", repayMoney);
					accountDao.depositForRepay(map);
				}
				logger.info("투자자의 입금완료");
				
				// 관리자에게 상환
				accountDao.depositForAdmin(totalRepay);
				logger.info("관리자 입금 완료:" + totalRepay);
				
				// 해당 대출의 회차 수 증가
				loan.setRound(loan.getRound()+1);
				ldao.updateRound(loan);
				logger.info("대출의 회차 수 증가완료");
				
				
				/* 대출신청자의 대출내역 추가하기 */
				// 최근 대출내역 가져오기
				ABCLoanTransactionDto preLoanTran = 
					loanTranDao.getRecentTransaction(loan.getLoanCode());
				logger.info("최근 대출내역: " + preLoanTran);
				
				int stackRepayRate = 0;
				int stackRepayOrigin = 0;
				int collectRate = 0;
				float interestRate = loan.getInterestRate() / 100F;
				int balance = 0;
				
				// 누적상환원금 및 누적상환이자금 구하기
				// 첫 상환내역인 경우(null인 경우)
				if(preLoanTran == null) {
					logger.info("첫 상환내역입니다.");
					
					// 진행상황을 상환중으로 변경
					loan.setProgress("상환중");
					ldao.updateProgress(loan);
					logger.info("진행상황을 상환중으로 변경완료");
					
					// 누적금 계산
					stackRepayRate = (int)(ABCUtility.getInterest(loan.getLoanMoney(), interestRate));
					stackRepayOrigin = monthlyPay - stackRepayRate;
					logger.info("상환이자금: " + stackRepayRate);
					logger.info("상환원금: " + stackRepayOrigin);
				} else{ // 이전 내역을 참고하여 누적
					// 잔금구하기
					balance = loan.getLoanMoney() - preLoanTran.getStackRepayOrigin();
					stackRepayRate = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
					stackRepayOrigin = (int)Math.round(
							ABCUtility.getEqualPrincipalPayment(loan.getLoanMoney(), 
																loan.getLoanDate(), 
																interestRate)
									- ABCUtility.getInterest(balance, interestRate));
					logger.info("상환이자금: " + stackRepayRate);
					logger.info("상환원금: " + stackRepayOrigin);
					
					// 누적금 계산
					stackRepayRate += preLoanTran.getStackRepayRate();
					stackRepayOrigin += preLoanTran.getStackRepayOrigin();
					
					logger.info("누적상환이자금: " + stackRepayRate);
					logger.info("누적상환원금: " + stackRepayOrigin);
					logger.info("대출내역 생성 중 잔금: " + balance);
				}
				
				// 회수율 구하기
				collectRate = (int)((double)stackRepayOrigin / loan.getLoanMoney() * 100);
				logger.info("회수율: " + collectRate);
				
				// 상환완료인지 확인
				// 완료이면 진행상황을 상환완료로 변경 및 해당 상품의 계좌 삭제
				if(stackRepayOrigin >= loan.getLoanMoney()){
					loan.setProgress("상환완료");
					ldao.updateProgress(loan);
					accountDao.deleteByLoan(loan.getLoanCode());
					logger.info("##상환완료작업 완료##");
				} else{ // 완료가 아니면 다음 달 상환일 잡기. 대출기간을 초과한 경우 연장
					String requestDate = ABCUtility.calcRepayDate(loan.getRepay());
					loan.setRequestDate(requestDate);
					if(loan.getRound() >= loan.getLoanDate()){ // 회차 수가 대출기간보다 큰 경우 
						// 대출기간 연장
						loan.setLoanDate(loan.getLoanDate()+1);
						ldao.extendLoanDate(loan);
						logger.info("대출기간 연장 완료");
					}
					ldao.nextRepayDate(loan);
					logger.info("다음 달 상환일 잡기 완료:"+requestDate);
				}
								
				// 대출내역 객체 생성
				ABCLoanTransactionDto loanTran = 
					new ABCLoanTransactionDto(
						0,					// loanSeq
						loan.getLoanCode(), // loanCode
						stackRepayRate,		// stackRepayRate
						stackRepayOrigin,	// stackReapyOrigin
						loan.getProgress(),	// progress
						collectRate,		// collectRate
						loan.getRound()		// round
					);
				
				// 대출내역 추가
				loanTranDao.addTransaction(loanTran);
				logger.info("대출내역 추가됨:" + loanTranDao.getRecentTransaction(loan.getLoanCode()));
				
				
				/* 대출투자자들의 투자내역 추가	 */
				for(ABCInvestDto invest : investList){
					// 최근 투자내역 가져오기
					ABCInvestTransactionDto preInvestTran = 
						investTranDao.getRecentTransaction(invest.getInvestSeq());
					logger.info("최근 투자내역: " + preInvestTran);
					
					// 금액 계산할 변수 초기화
					interestRate = 0.06F;
					int intendProfit = 0; // 예정수익금=>이자
					int stackCollect = 0; // 누적 회수금
					int origMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(), interestRate);
					logger.info("투자금에 따른 상환금: " + origMoney);
					
					// 첫 상환인 경우(null인 경우)
					if(preInvestTran == null){
						logger.info("첫 상환내역입니다.");
						
						intendProfit = (int)ABCUtility.getInterest(invest.getInvestMoney(), interestRate);
						stackCollect = origMoney - intendProfit;
						logger.info("예정수익금: " + intendProfit);
						logger.info("회수금: " + stackCollect);
					} else{
						balance = invest.getInvestMoney() - preInvestTran.getStackCollect();
						intendProfit = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
						stackCollect = (int)Math.round(
								ABCUtility.getEqualPrincipalPayment(invest.getInvestMoney(), 
																	loan.getLoanDate(), 
																	interestRate)
								- ABCUtility.getInterest(balance, interestRate)
								);
						logger.info("예정수익금: " + intendProfit);
						logger.info("회수금: " + stackCollect);
						
						// 회수금 누적
						stackCollect += preInvestTran.getStackCollect();
						logger.info("누적회수금: " + stackCollect);
					}
					
					// 투자내역 생성 및 등록
					ABCInvestTransactionDto investTran = 
						new ABCInvestTransactionDto(
							0,						// investTransactionSeq
							invest.getInvestSeq(),	// investSeq
							intendProfit,			// intendProfit
							stackCollect,			// stackCollect
							loan.getProgress(),		// progress
							collectRate,			// collectRate
							loan.getRound()			// round
						);
					investTranDao.addTransaction(investTran);
					logger.info("투자내역 추가됨: " + investTranDao.getRecentTransaction(invest.getInvestSeq()));
				}
				logger.info("#####################################################################################################");
			} // if문
		} // for문
	}

	@Override
	public List<ABCLoanDto> getRemainPayment(String email) {
		return ldao.getRemainPayment(email);
	}

	@Override
	public List<ABCLoanDto> getLoanListByInvest(String email) {
		return ldao.getLoanListByInvest(email);
	}

	@Override
	public List<ABCLoanTransactionDto> getTransactionBySorted(String email) {
		return loanTranDao.getTransactionBySorted(email);
	}

	@Override
	public int getPayingCount(String email) {
		return ldao.getPayingCount(email);
	}
}
