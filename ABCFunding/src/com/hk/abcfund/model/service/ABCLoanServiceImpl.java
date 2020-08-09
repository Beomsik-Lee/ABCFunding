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
 * ���� ��û�� ���� ���� Ŭ����
 * @author 9age
 *
 */
@Service
public class ABCLoanServiceImpl implements ABCLoanService {
	/** slf4j �α� */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanServiceImpl.class);

	/** �����û DAO */
	@Autowired
	private ABCLoanDao ldao;
	
	/** ȸ�� DAO */
	@Autowired
	private ABCMemberDao memberDao;
	
	/** ���� DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** �ɻ� DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** ���� DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** ���⳻�� DAO */
	@Autowired
	private ABCLoanTransactionDao loanTranDao;
	
	/** ���ڳ��� DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/**
	 * ���� ��û ��� �޼���
	 * @param ldto ���� ��û ������ ���� ��ü
	 */
	@Override
	@Transactional
	public void addLoan(ABCLoanDto ldto) {
		// ���� ��û ���� ���
		ldao.addLoan(ldto);
		
		// ��û���� ����Ǽ� ����
		memberDao.incLoan(ldto.getEmail());
		
		// ���� ��û ���� ��� �� �����ڵ带 �޾ƿ� �ɻ� ���̺� �ɻ�������� ���
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
	 * ��������ϴ� �޼���.
	 * �̽��� ������ ���� �����Ϳ� �Բ� �ٷ� �����ϰ�
	 * �ݵ��������� ������ ���ڱ��� ȯ���ϰ� �����Ѵ�.
	 * @param loanCode ����� ������ �����ڵ�
	 */
	@Override
	@Transactional
	public void loanCancel(int loanCode) {
		// 1. ���ڱ� ȯ��
		// �ش� �����ڵ带 ���� ���ڸ���Ʈ ��������
		List<ABCInvestDto> list = investDao.getInvestListByLoan(loanCode);
		
		/* 
		 * �����ڰ� �ִ� ��쿡�� ����
		 * ���ڸ���Ʈ�� �����ܾ� ���� �� ���� ������ ����
		 */
		if(!list.isEmpty()) {
			accountDao.updateForPayback(list);
			investDao.deleteByLoan(loanCode);
		}
		
		// 2. �ش� ������ ������� ����
		accountDao.deleteByLoan(loanCode);
		
		// 3. �ش� ������ �ɻ絥���� ����
		adminDao.deleteJudgeByLoan(loanCode);
		
		// 4. �ش� ���ⵥ���� ����
		ldao.deleteLoan(loanCode);
	}
	
	/**
	 * �����û�ڰ� ��ȯ�ߴ��� �ź� Ȯ���Ͽ� ��ȯ�۾��� �����ϴ� �޼���
	 */
	@Override
	@Transactional
	@Scheduled(cron="0 0/1 * 1,5,10,15,20,25 * ?")
	//@Scheduled(cron="0/10 * * * * ?")
	public void checkRepay() {
		// 1. ���� ����Ʈ ��������
		List<ABCLoanDto> loanList = ldao.getLoanList();
		logger.info("���⸮��Ʈ:\n"+loanList.toString());
		
		// 2. ��ȯ�� Ȯ��
		for(ABCLoanDto loan : loanList){
			// ��ȯ���̰� ���� ��ȯ�Ϸᰡ ���� ���� ������ ��� ��ȯ�۾� ����
			if(ABCUtility.isSameDate(loan.getRequestDate()) &&
					!loan.getProgress().equals("��ȯ�Ϸ�")){
				logger.info("�������� ����: " + loan);
				
				// �ʿ��� ���� ���� �� ����
				Map<String, Object>map = new HashMap<String, Object>();
				
				// ��ȯ�� ���ϱ�
				int monthlyPay = ABCUtility.getRepayMoney(
					loan.getLoanMoney(), loan.getLoanDate(),loan.getInterestRate()/100.0);
				logger.info("��ȯ��: " + monthlyPay);
				
				// �����û���� �ܾ�Ȯ��
				int balanceOfLoan = accountDao.getBalance(loan.getEmail());
				logger.info("�����û���� �ܾ�: " + balanceOfLoan);
				
				// �ܾ��� ������ ���
				if(balanceOfLoan <= 0){
					logger.info("�ܾ׺������� ��ȯ ��ŵ");
					continue;
				}
				
				// �����û���� ��ȯ���� �����ǰ�� ���·� ��ü
				map.put("email", loan.getEmail());
				map.put("investMoney", monthlyPay);
				accountDao.withdraw(map);
				logger.info("�����û���� ��ȯ�� ��ݿϷ�");
				
				// ���� ��ǰ�� �Ա�
				map.put("loanCode", loan.getLoanCode());
				map.put("investMoney", monthlyPay);
				accountDao.deposit(map);
				logger.info("�����ǰ�� ���·� �Ա� �Ϸ�");
				
				// �����ǰ�� ���¿��� ������ �� �����ڿ��� ��ȯ
				// �ش� ������ ������ ����Ʈ ������
				List<ABCInvestDto> investList = 
						investDao.getInvestListByLoan(loan.getLoanCode());
				
				// �����ڿ��� ��ȯ
				int totalRepay = monthlyPay;
				for(ABCInvestDto invest : investList){
					// �ش� �����ڿ��� ��ȯ�ؾ� �� �ݾ�
					int repayMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(),0.06);
					logger.info("������ ��ȯ��: " + repayMoney);
					
					// ��������� ��ġ�� ����
					if(totalRepay <= 0) break;	// ��ȯ�ݾ��� ������ ��� 
					map.put("loanCode", loan.getLoanCode());
					map.put("money", repayMoney);
					accountDao.withdrawForInvest(map);
					totalRepay -= repayMoney;
					logger.info("���� ��ġ��: " + totalRepay);
					
					// �������� ���¿� �Ա�
					map.put("email", invest.getEmail());
					map.put("money", repayMoney);
					accountDao.depositForRepay(map);
				}
				logger.info("�������� �ԱݿϷ�");
				
				// �����ڿ��� ��ȯ
				accountDao.depositForAdmin(totalRepay);
				logger.info("������ �Ա� �Ϸ�:" + totalRepay);
				
				// �ش� ������ ȸ�� �� ����
				loan.setRound(loan.getRound()+1);
				ldao.updateRound(loan);
				logger.info("������ ȸ�� �� �����Ϸ�");
				
				
				/* �����û���� ���⳻�� �߰��ϱ� */
				// �ֱ� ���⳻�� ��������
				ABCLoanTransactionDto preLoanTran = 
					loanTranDao.getRecentTransaction(loan.getLoanCode());
				logger.info("�ֱ� ���⳻��: " + preLoanTran);
				
				int stackRepayRate = 0;
				int stackRepayOrigin = 0;
				int collectRate = 0;
				float interestRate = loan.getInterestRate() / 100F;
				int balance = 0;
				
				// ������ȯ���� �� ������ȯ���ڱ� ���ϱ�
				// ù ��ȯ������ ���(null�� ���)
				if(preLoanTran == null) {
					logger.info("ù ��ȯ�����Դϴ�.");
					
					// �����Ȳ�� ��ȯ������ ����
					loan.setProgress("��ȯ��");
					ldao.updateProgress(loan);
					logger.info("�����Ȳ�� ��ȯ������ ����Ϸ�");
					
					// ������ ���
					stackRepayRate = (int)(ABCUtility.getInterest(loan.getLoanMoney(), interestRate));
					stackRepayOrigin = monthlyPay - stackRepayRate;
					logger.info("��ȯ���ڱ�: " + stackRepayRate);
					logger.info("��ȯ����: " + stackRepayOrigin);
				} else{ // ���� ������ �����Ͽ� ����
					// �ܱݱ��ϱ�
					balance = loan.getLoanMoney() - preLoanTran.getStackRepayOrigin();
					stackRepayRate = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
					stackRepayOrigin = (int)Math.round(
							ABCUtility.getEqualPrincipalPayment(loan.getLoanMoney(), 
																loan.getLoanDate(), 
																interestRate)
									- ABCUtility.getInterest(balance, interestRate));
					logger.info("��ȯ���ڱ�: " + stackRepayRate);
					logger.info("��ȯ����: " + stackRepayOrigin);
					
					// ������ ���
					stackRepayRate += preLoanTran.getStackRepayRate();
					stackRepayOrigin += preLoanTran.getStackRepayOrigin();
					
					logger.info("������ȯ���ڱ�: " + stackRepayRate);
					logger.info("������ȯ����: " + stackRepayOrigin);
					logger.info("���⳻�� ���� �� �ܱ�: " + balance);
				}
				
				// ȸ���� ���ϱ�
				collectRate = (int)((double)stackRepayOrigin / loan.getLoanMoney() * 100);
				logger.info("ȸ����: " + collectRate);
				
				// ��ȯ�Ϸ����� Ȯ��
				// �Ϸ��̸� �����Ȳ�� ��ȯ�Ϸ�� ���� �� �ش� ��ǰ�� ���� ����
				if(stackRepayOrigin >= loan.getLoanMoney()){
					loan.setProgress("��ȯ�Ϸ�");
					ldao.updateProgress(loan);
					accountDao.deleteByLoan(loan.getLoanCode());
					logger.info("##��ȯ�Ϸ��۾� �Ϸ�##");
				} else{ // �Ϸᰡ �ƴϸ� ���� �� ��ȯ�� ���. ����Ⱓ�� �ʰ��� ��� ����
					String requestDate = ABCUtility.calcRepayDate(loan.getRepay());
					loan.setRequestDate(requestDate);
					if(loan.getRound() >= loan.getLoanDate()){ // ȸ�� ���� ����Ⱓ���� ū ��� 
						// ����Ⱓ ����
						loan.setLoanDate(loan.getLoanDate()+1);
						ldao.extendLoanDate(loan);
						logger.info("����Ⱓ ���� �Ϸ�");
					}
					ldao.nextRepayDate(loan);
					logger.info("���� �� ��ȯ�� ��� �Ϸ�:"+requestDate);
				}
								
				// ���⳻�� ��ü ����
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
				
				// ���⳻�� �߰�
				loanTranDao.addTransaction(loanTran);
				logger.info("���⳻�� �߰���:" + loanTranDao.getRecentTransaction(loan.getLoanCode()));
				
				
				/* ���������ڵ��� ���ڳ��� �߰�	 */
				for(ABCInvestDto invest : investList){
					// �ֱ� ���ڳ��� ��������
					ABCInvestTransactionDto preInvestTran = 
						investTranDao.getRecentTransaction(invest.getInvestSeq());
					logger.info("�ֱ� ���ڳ���: " + preInvestTran);
					
					// �ݾ� ����� ���� �ʱ�ȭ
					interestRate = 0.06F;
					int intendProfit = 0; // �������ͱ�=>����
					int stackCollect = 0; // ���� ȸ����
					int origMoney = ABCUtility.getRepayMoney(
							invest.getInvestMoney(), loan.getLoanDate(), interestRate);
					logger.info("���ڱݿ� ���� ��ȯ��: " + origMoney);
					
					// ù ��ȯ�� ���(null�� ���)
					if(preInvestTran == null){
						logger.info("ù ��ȯ�����Դϴ�.");
						
						intendProfit = (int)ABCUtility.getInterest(invest.getInvestMoney(), interestRate);
						stackCollect = origMoney - intendProfit;
						logger.info("�������ͱ�: " + intendProfit);
						logger.info("ȸ����: " + stackCollect);
					} else{
						balance = invest.getInvestMoney() - preInvestTran.getStackCollect();
						intendProfit = (int)Math.round(ABCUtility.getInterest(balance, interestRate));
						stackCollect = (int)Math.round(
								ABCUtility.getEqualPrincipalPayment(invest.getInvestMoney(), 
																	loan.getLoanDate(), 
																	interestRate)
								- ABCUtility.getInterest(balance, interestRate)
								);
						logger.info("�������ͱ�: " + intendProfit);
						logger.info("ȸ����: " + stackCollect);
						
						// ȸ���� ����
						stackCollect += preInvestTran.getStackCollect();
						logger.info("����ȸ����: " + stackCollect);
					}
					
					// ���ڳ��� ���� �� ���
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
					logger.info("���ڳ��� �߰���: " + investTranDao.getRecentTransaction(invest.getInvestSeq()));
				}
				logger.info("#####################################################################################################");
			} // if��
		} // for��
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
