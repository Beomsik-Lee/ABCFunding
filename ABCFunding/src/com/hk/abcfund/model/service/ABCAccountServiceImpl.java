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
 * Account service implements
 * @author 9age
 *
 */
@Service
public class ABCAccountServiceImpl implements ABCAccountService {
	/** Account DAO */
	@Autowired
	private ABCAccountDao accountDao;
	
	/** Loan DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/**
	 * Get account by email
	 * @param email A email to reference
	 * @return Return account DTO by DAO
	 */
	@Override
	public ABCAccountDto getAccount(String email) {
		return accountDao.getAccount(email);
	}
	
	/**
	 * Create account for approval loans
	 * @param loanCode loan code for reference
	 */
	@Override
	@Transactional
	public void accountForLoan(int loanCode) {
		// Create hash map for assign several variable
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 1. Get a loan by loan code
		ABCLoanDto loan = loanDao.getLoan(loanCode);
		
		// If pruduct's title is more than 12 bytes, then cut it down 
		String title = loan.getTitle();
		if(title.getBytes().length > 12) {
			title = title.substring(0, 6);
		}
		
		// 2. Create virtual account number
		String accountNo = ABCUtility.createVANumber();
		
		// 3. Add to map
		map.put("accountNo", accountNo);
		map.put("loanCode", loanCode);
		map.put("title", title);
		
		// 4. Add a loan by DAO
		accountDao.addForLoan(map);
	}
	
	/**
	 * Deposit for repayments
	 * @param map 
	 */
	@Override
	public void depositAtMyInfo(Map<String, Object> map) {
		accountDao.depositForRepay(map);
	}
}