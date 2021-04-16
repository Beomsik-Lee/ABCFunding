package com.hk.abcfund.model.dao;

import java.util.List;
import java.util.Map;

import com.hk.abcfund.model.dto.ABCInvestDto;

/**
 * DAO interface for investment
 * @author 9age
 *
 */
public interface ABCInvestDao {
	void addInvest(ABCInvestDto invest);
	ABCInvestDto selectByEnL(Map<String, Object> map);
	List<ABCInvestDto> getInvestListByLoan(int loanCode);
	void deleteByLoan(int loanCode);
	List<ABCInvestDto> getInvestMoneyList(String email);
	List<ABCInvestDto> getInvestList(String email);
	List<ABCInvestDto> getInvestListAll();
}
