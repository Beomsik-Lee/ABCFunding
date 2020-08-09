/**
 * 
 */
package com.hk.abcfund.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
 * ������ ȭ���� �����ϱ� ���� ���� ���� Ŭ����
 * @author 9age
 *
 */
@Service
public class ABCAdminSerivceImpl implements ABCAdminSerivce {
	/** ������ DAO */
	@Autowired
	private ABCAdminDao adao;
	
	/** ���� DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** ���� DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** ���系�� DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/** ���⳻�� DAO */
	@Autowired
	private ABCLoanTransactionDao loanTranDao;
	
	/** slf4j �α� */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanServiceImpl.class);
	
	/**
	 * �ɻ��� ����Ʈ�� ȣ���ϴ� �޼���
	 */
	@Override
	public List<ABCJudgeDto> getJudgeList() {
		return adao.getJudgeList();
	}

	/**
	 * �ɻ��� �󼼳����� ȣ���ϴ� �޼���
	 * @param loanCode �����ڵ�
	 */
	@Override
	public ABCJudgeDto getJudgeDetail(int loanCode) {
		return adao.getJudgeDetail(loanCode);
	}

	/**
	 * �ɻ����� ó���ϴ� �޼��� ȣ��
	 * @param ABCJudgeResultDto �ɻ��� ������ ���� DTO ��ü
	 */
	@Override
	@Transactional
	public void addJudgeResult(ABCJudgeResultDto jrdto) {
		// �ɻ����� ���
		adao.addJudgeResult(jrdto);
		
		// �ɻ����� �����̸� ������ �����Ȳ�� '�ݵ�������'���� ��Ÿ����.
		if(jrdto.getResult() == 1) {
			ABCLoanDto loan = new ABCLoanDto(jrdto.getLoanCode(), "�ݵ�������");
			loanDao.updateProgress(loan);
		}
	}
	
	/**
	 * �ش� �����ڵ带 ���� �ɻ緹�ڵ� ���� 
	 */	
	@Override
	public void deleteJudgeByLoan(int loanCode) {
		adao.deleteJudgeByLoan(loanCode);
	}
	
	/**
	 * ���ڱ׷����� ���õ� �����͸� �����ϴ� �޼���
	 * @param email ȸ���� �̸���
	 * @return JSON���� ������ ������
	 */
	@Override
	public List<ABCInvestChartDto> getInvestChartData(String email) {
		// ����Ʈ ��������
		List<ABCInvestDto> investList = investDao.getInvestList(email);
		List<ABCInvestTransactionDto> investTranList = investTranDao.getTransactionReserve(email);
		
		// ����Ʈ ����
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
					
					// ���� ���� ���
					stackInterest += investTran.getIntendProfit();
					
					// �ִ� ���� ������ ���� ���� ã��
					if(investTran.getRound() > investTranList.get(max).getRound())
						max = j;
				}
			}
			if(max != -999)
				stackOrigin = investTranList.get(max).getStackCollect();
			
			// ����Ʈ�� ���
			chartList.add(new ABCInvestChartDto(investSeq, investMoney, stackOrigin, stackInterest));
		}
		
		return chartList;
	}
	
	/**
	 * ���� ��Ʈ �����͸� �����ϴ� �޼���
	 * @param email ȸ���� �̸���
	 */
	@Override
	public List<ABCLoanChartDto> getLoanChartData(String email) {
		// ����Ʈ ����
		List<ABCLoanChartDto> chartList = new ArrayList<ABCLoanChartDto>();
		
		// ���⳻�� ����Ʈ ��������
		List<ABCLoanTransactionDto> loanTranList = loanTranDao.getTransactionBySorted(email);
		
		if(loanTranList.isEmpty()) return chartList;
		
		// �ִ� ���� ���� �� ���� ���ڱ� ���ϱ�
		int stackRepayRate = 0;	// ���� ���ڱ�
		int stackRepayOrigin = 0; // ���� ����
		int loanCode = loanTranList.get(0).getLoanCode();	// ���� �ڵ�
		for(int idx = 0; idx < loanTranList.size(); idx++){
			// ���� ��������
			ABCLoanTransactionDto loanTran = loanTranList.get(idx);
			
			// �ٸ� �������� �Ѿ ���
			if(loanCode != loanTran.getLoanCode()){
				// ��Ʈ ������ ����Ʈ�� �߰��ϰ� �� ���� �ʱ�ȭ
				chartList.add(new ABCLoanChartDto(loanCode, stackRepayOrigin, stackRepayRate));
				loanCode = loanTran.getLoanCode();
				stackRepayRate = 0;
				stackRepayOrigin = 0;
			} else if(idx+1 == loanTranList.size()){ // ������ ������ ���
				// ���� ���� ���ϱ�
				stackRepayRate += loanTran.getStackRepayRate();
				
				// ���� ���� ���
				stackRepayOrigin = loanTran.getStackRepayOrigin();
				chartList.add(new ABCLoanChartDto(loanCode, stackRepayOrigin, stackRepayRate));
				
				break;
			}
			
			// ���� ���� ���ϱ�
			stackRepayRate += loanTran.getStackRepayRate();
			
			// ���� ���� ���
			stackRepayOrigin = loanTran.getStackRepayOrigin();
		}
		logger.info("���� ��Ʈ ������: " + chartList);
		
		return chartList;
	}
	
	/** 
	 * ���ڰ����� �����͸� �����ϴ� �޼���
	 * @return ���ڰ��� ������ ����Ʈ
	  */
	@Override
	public List<ABCInvestManageDto> getInvestManageData() {
		// ����Ʈ ����
		List<ABCInvestManageDto> chartList = new ArrayList<ABCInvestManageDto>();
		
		// ��� ���ڸ���Ʈ �� ���ڳ��� ��������
		List<ABCInvestDto> investList = investDao.getInvestListAll();
		List<ABCInvestTransactionDto> investTranList = investTranDao.getTransactionAll();
				
		// ������ ����
		for(int i = 0; i < investList.size(); i++){
			ABCInvestDto invest = investList.get(i);
			int investMoney = invest.getInvestMoney(); // ���ڱ�
			int stackProfit = 0;  // ���� ���ͱ�
			String progress = "";
			for(int j = 0; j < investTranList.size(); j++){
				ABCInvestTransactionDto investTran = investTranList.get(j);
				if(invest.getInvestSeq() == investTran.getInvestSeq()){
					// ���� ���ͱ� ���ϱ�
					stackProfit += investTran.getIntendProfit();
					
					// ������� ���ϱ�
					progress = investTran.getProgress();
				}
			}
			chartList.add(new ABCInvestManageDto(
				invest.getInvestSeq(), investMoney, stackProfit, progress));
		}
		logger.info("������ ����Ʈ: "+chartList);
		
		return chartList;
	}
	
	/**
	 * ������� ���� ��� 
	 */	
	@Override
	@Transactional
	public ABCAdminLoanManageDto getLoanManage() {
		
		logger.info("getLoanManage() ȣ�� ����!" + new Date());
		ABCAdminLoanManageDto dto =  new ABCAdminLoanManageDto();
		
		dto.setStackLoanNum((adao.getAdminLoanInfo1()).getStackLoanNum()); //�������Ǽ�
		dto.setStackLoanMoney((adao.getAdminLoanInfo1()).getStackLoanMoney()); //���������
		dto.setStackLoanRepayMoney((adao.getAdminLoanInfo2()).getStackLoanRepayMoney()); //���������ȯ��
		dto.setLoanEndNum((adao.getAdminLoanInfo3()).getLoanEndNum()); //��ȯ�Ϸ� �Ǽ�
		dto.setLoanMiddleNum((adao.getAdminLoanInfo4()).getLoanMiddleNum()); //��ȯ���ΰǼ�
		
		return dto;
	}

}