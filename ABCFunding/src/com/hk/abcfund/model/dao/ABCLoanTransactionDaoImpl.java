package com.hk.abcfund.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCLoanTransactionDto;

/**
 * DAO implement for transaction of loan
 * @author 9age
 *
 */
@Repository
public class ABCLoanTransactionDaoImpl implements ABCLoanTransactionDao {
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "ABCLoanTransaction.";	
	
	@Override
	public ABCLoanTransactionDto getRecentTransaction(int loanCode) {
		return (ABCLoanTransactionDto)sqlSession
				.selectOne(namespace + "getRecentTransaction", loanCode);
	}

	@Override
	public void addTransaction(ABCLoanTransactionDto loanTran) {
		sqlSession.insert(namespace + "addTransaction", loanTran);			
	}

	@Override
	public List<ABCLoanTransactionDto> getTransactionBySorted(String email) {
		return sqlSession.selectList(namespace + "getTransactionBySorted", email);
	}

}
