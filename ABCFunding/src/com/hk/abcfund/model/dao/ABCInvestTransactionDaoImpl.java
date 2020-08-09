package com.hk.abcfund.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCInvestTransactionDto;

/**
 * 투자내역 구현 클래스
 * @author 9age
 *
 */
@Repository
public class ABCInvestTransactionDaoImpl implements ABCInvestTransactionDao {
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "ABCInvestTransaction.";

	@Override
	public ABCInvestTransactionDto getRecentTransaction(int investSeq) {
		return (ABCInvestTransactionDto)sqlSession
				.selectOne(namespace + "getRecentTransaction", investSeq);
	}

	@Override
	public void addTransaction(ABCInvestTransactionDto investTran) {
		sqlSession.insert(namespace + "addTransaction", investTran);
	}

	@Override
	public List<ABCInvestTransactionDto> getTransaction(String email) {
		return sqlSession.selectList(namespace + "getTransaction", email);
	}

	@Override
	public List<ABCInvestTransactionDto> getTransactionReserve(String email) {
		return sqlSession.selectList(namespace + "getTransactionReserve", email);
	}

	@Override
	public List<ABCInvestTransactionDto> getTransactionAll() {
		return sqlSession.selectList(namespace + "getTransactionAll");
	}

}
