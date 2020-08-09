package com.hk.abcfund.model.dao;

import java.util.List;

import com.hk.abcfund.model.dto.ABCInvestTransactionDto;

/**
 * 투자내역의 DAO 인터페이스
 * @author 9age
 *
 */
public interface ABCInvestTransactionDao {
	ABCInvestTransactionDto getRecentTransaction(int investSeq);
	void addTransaction(ABCInvestTransactionDto investTran);
	List<ABCInvestTransactionDto> getTransaction(String email);
	List<ABCInvestTransactionDto> getTransactionReserve(String email);
	List<ABCInvestTransactionDto> getTransactionAll();
}
