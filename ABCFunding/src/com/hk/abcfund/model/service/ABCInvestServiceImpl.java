package com.hk.abcfund.model.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCInvestTransactionDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCMemberDao;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * ���� ���� ���� ���� Ŭ����
 * @author 9age
 *
 */
@Service
public class ABCInvestServiceImpl implements ABCInvestService {
	/** ȸ�� DAO */
	@Autowired
	private ABCMemberDao memberDao;
	
	/** ���� DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** �ɻ� DAO */
	@Autowired
	private ABCAdminDao adminDao;
	
	/** ���� DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** ���� DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** ���ڳ��� DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/**
	 * �Է� ���� ������ DTO�� DAO�� ����� �ֽ��ϴ�.
	 * @param member ȸ�� DTO
	 * @param loan ���� DTO
	 * @param judge �ɻ� DTO
	 */
	@Override
	@Transactional
	public void getInvestDetail(List<Object> list, int loanCode) {
		// 1. �����ڵ带 �̿��Ͽ� ���ⵥ���͸� �����´�.
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// 2. ������ ���ⵥ���Ϳ��� �ش� �̸����� ���� ȸ���� �����͸� �����´�.
		ABCMemberDto member = memberDao.getMember(loan.getEmail());
		
		// 3. �����ڵ带 �̿��Ͽ� �ɻ絥���͸� �����´�.
		ABCJudgeResultDto judge = adminDao.getJudge(loanCode);
		
		// ������ ��ü���� ����Ʈ�� ��Ƶд�.
		list.add(loan);
		list.add(member);
		list.add(judge);
	}
	
	/**
	 * ���ڰ� �̷������ �޼���
	 * @param email �����ڸ� �ĺ��� �̸���
	 * @param loanCode �����ǰ�� �ĺ��� �����ڵ�
	 * @param investMoney ���ڱ�
	 */
	@Override
	@Transactional
	public void investRequest(String email, int loanCode, int investMoney) {
		// �Ű������� �ؽ��ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("loanCode", loanCode);
		map.put("investMoney", investMoney);
		
		// 1. �� ���� ���¿��� ����
		try{
			accountDao.withdraw(map);
		} catch(Exception e){
			// ����Ʈ ���忡�� üũ�� �κ��̶� �ʿ����.
		}
		
		// 2. �����ǰ�� ���·� �Ա�
		accountDao.deposit(map);
		
		// 3. �������� ȸ�� �����Ϳ��� ���ڰǼ� ����
		memberDao.incInvest(email);
		
		// 4. �����ǰ ���� ����
		loanDao.investAfter(map);
		
		// 5. ���� ���ڵ� �߰�
		investDao.addInvest(new ABCInvestDto(email, loanCode, investMoney));
		
	}
	
	/**
	 * �ݵ��� �Ϸ�Ǿ����� Ȯ���ϴ� �޼���
	 * @param loanCode �ݵ��ϷḦ Ȯ���� ������ �����ڵ�
	 * @return �ϷῩ��
	 */
	@Override
	@Transactional
	public void checkComplete(int loanCode) {
		// �ش� �����ڵ��� ���� ������ ��������
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// ����ݾ��� ��ǥ�ݾװ� ��ġ�ϴ� ���
		if(loan.getCurrentMoney() == loan.getLoanMoney()) {
			// requestDate�� ù ��ȯ��¥�� �ٲ۴�.
			String repayDate = ABCUtility.calcRepayDate(loan.getRepay());
			loan.setProgress("�ݵ��Ϸ�");
			loan.setRequestDate(repayDate);
			loanDao.fundComplete(loan);
			
			// �����û�ڿ��� ��û�� ����
			accountDao.depositForRequest(loanCode);
			
			// �ش� ������ ������¿��� �ܾ� �ʱ�ȭ
			accountDao.withdrawByLoan(loanCode);
		}
	}
	
	/**
	 * �ش� ȸ���� �ش� ��ǰ�� �����ߴ��� ���θ� Ȯ���ϴ� �޼���
	 * @param email �ĺ��� ȸ���� �̸���
	 * @param loanCode �ĺ��� ��ǰ�� �����ڵ�
	 * @return ���������� true, �ƴϸ� false
	 */
	@Override
	public boolean isInvested(String email, int loanCode) {
		boolean isInvested = false;	// ��ǥ���θ� �����ϴ� ���� ����
		
		// �̸��ϰ� �����ڵ带 ������ �ؽ��� ����
		Map<String, Object> map = new HashMap<String, Object>();
		
		// �̸��ϰ� �����ڵ带 �ؽ��ʿ� ��´�.
		map.put("email", email);
		map.put("loanCode", loanCode);
		
		// �ؽ����� �̿��Ͽ� �ش� ���⿡ �ش� �����ڰ� �ִ��� Ȯ���ؼ� �����´�.
		ABCInvestDto invest = investDao.selectByEnL(map);
		
		// ���� ��������� �����Ͽ� ��ü�� ���� �ƴ� ����� �̹� �����ߴٴ� ���̴�.
		if(invest != null) isInvested = true;
		
		return isInvested;
	}
	
	/**
	 * ȸ���� ���ڳ��� ����Ʈ ��������
	 * @param email ȸ���� �̸���
	 */
	@Override
	public List<ABCInvestTransactionDto> getInvestTransaction(String email) {
		return investTranDao.getTransaction(email);
	}
	
	/**
	 * ȸ���� ���ڱ� ����Ʈ ��������
	 * @param email ȸ���� �̸���
	 * @return ������������ ���ĵ� ���� ����Ʈ
	 */
	@Override
	public List<ABCInvestDto> getInvestMoneyList(String email) {
		return investDao.getInvestMoneyList(email);
	}
	
	/**
	 * ȸ���� ���� ����Ʈ �������� 
	 * @param email ȸ���� �̸���
	 */
	@Override
	public List<ABCInvestDto> getInvestList(String email) {
		return investDao.getInvestList(email);
	}
	
	/** ȸ���� ���ڳ��� ����Ʈ ��������
	 *	@param email ȸ���� �̸��� 
	 */
	@Override
	public List<ABCInvestTransactionDto> getTransactionReserve(String email) {
		return investTranDao.getTransactionReserve(email);
	}

}
