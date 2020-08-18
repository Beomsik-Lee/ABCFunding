package com.hk.abcfund.model.service;

import java.util.List;

import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;

/**
 * Service interface for investment
 * @author 9age
 *
 */
public interface ABCInvestService {
	void getInvestDetail(List<Object> list, int loanCode);
	void investRequest(String email, int loanCode, int investMoney);
	boolean isInvested(String email, int loanCode);
	void checkComplete(int loanCode);
	List<ABCInvestTransactionDto> getInvestTransaction(String email);
	List<ABCInvestDto> getInvestMoneyList(String email);
	List<ABCInvestDto> getInvestList(String email);
	List<ABCInvestTransactionDto> getTransactionReserve(String email);
}