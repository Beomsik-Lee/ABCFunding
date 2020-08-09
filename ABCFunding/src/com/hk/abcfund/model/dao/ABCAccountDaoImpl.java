package com.hk.abcfund.model.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCInvestDto;

/**
 * 계좌 DAO 구현 클래스
 * @author 9age
 *
 */
@Repository
public class ABCAccountDaoImpl implements ABCAccountDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** MyBatis 계좌의 네임스페이스 */
	private String namespace = "ABCAccount.";

	@Override
	public void addAccount(ABCAccountDto dto) {
		sqlSession.insert(namespace + "addAccount", dto);
	}

	@Override
	public ABCAccountDto getAccount(String email) {
		return (ABCAccountDto)sqlSession.selectOne(
			namespace + "getAccount", email);
	}

	@Override
	public void withdraw(Map<String, Object> map) {
		sqlSession.update(namespace + "withdraw", map);
	}

	@Override
	public void deposit(Map<String, Object> map) {
		sqlSession.update(namespace + "deposit", map);
	}

	@Override
	public void addForLoan(Map<String, Object> map) {
		sqlSession.insert(namespace + "addForLoan", map);
	}

	@Override
	public void updateForPayback(List<ABCInvestDto> list) {
		for(ABCInvestDto dto : list){
			sqlSession.update(namespace + "updateForPayback", dto);
		}
	}

	@Override
	public void deleteByLoan(int loanCode) {
		sqlSession.delete(namespace + "deleteByLoan", loanCode);
	}

	@Override
	public void depositForRequest(int loanCode) {
		sqlSession.update(namespace + "depositForRequest", loanCode);
	}

	@Override
	public void withdrawByLoan(int loanCode) {
		sqlSession.update(namespace + "withdrawByLoan", loanCode);
	}

	@Override
	public void depositForRepay(Map<String, Object> map) {
		sqlSession.update(namespace + "depositForRepay", map);
	}

	@Override
	public void depositForAdmin(int money) {
		sqlSession.update(namespace + "depositForAdmin", money);
	}

	@Override
	public void withdrawForInvest(Map<String, Object> map) {
		sqlSession.update(namespace + "withdrawForInvest", map);
	}

	@Override
	public void deleteAccount(String email) {
		sqlSession.delete(namespace + "deleteAccount", email);
	}

	@Override
	public int getBalance(String email) {
		return (Integer)sqlSession.selectOne(namespace + "getBalance", email);				
	}
}
