package com.hk.abcfund.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.model.dao.ABCAccountDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.util.ABCUtility;

/**
 * ���°��� ���� Ŭ����
 * @author 9age
 *
 */
@Service
public class ABCAccountServiceImpl implements ABCAccountService {
	/** ����DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** ���� DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/**
	 * �����ڵ�� ���������� ��������
	 * @param loanCode ������ �����ڵ�
	 * @return ���� �����Ͱ� ��� DTO
	 */
	@Override
	public ABCAccountDto getAccount(String email) {
		return accountDao.getAccount(email);
	}
	
	/**
	 * ���ι��� �����ǰ�� ���� ������¸� ����
	 * @param loanCode �����ڵ�
	 */
	@Override
	@Transactional
	public void accountForLoan(int loanCode) {
		// ���� �����͸� ���� �ؽ��� ����
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 1. �����ڵ�� ���ⵥ���� �������� �� ���� ����
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// ���� �����ǰ(����)�� 12����Ʈ�� �Ѵ� ��� ����
		String title = loan.getTitle();
		if(title.getBytes().length > 12) {
			title = title.substring(0, 6);
		}
		
		// 2. ������¹�ȣ ����
		String accountNo = ABCUtility.createVANumber();
		
		// 3. �ʿ��� ���
		map.put("accountNo", accountNo);
		map.put("loanCode", loanCode);
		map.put("title", title);
		
		// 4. ���»��� ��û
		accountDao.addForLoan(map);
	}

	@Override
	public void depositAtMyInfo(Map<String, Object> map) {
		accountDao.depositForRepay(map);
	}
}
