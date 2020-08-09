package com.hk.abcfund.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.abcfund.model.dto.ABCInvestDto;

/**
 * ���� DAO ���� Ŭ����
 * @author 9age
 *
 */
@Repository
public class ABCInvestDaoImpl implements ABCInvestDao {
	@Autowired
	private SqlSession sqlSession;
	
	/** ������ ���� ���ӽ����̽� */
	private String namespace = "ABCInvest.";
		
	@Override
	public void addInvest(ABCInvestDto invest) {
		sqlSession.insert(namespace + "addInvest", invest);
	}

	@Override
	public ABCInvestDto selectByEnL(Map<String, Object> map) {
		return (ABCInvestDto)sqlSession.selectOne(namespace + "selectByEnL", map);
	}

	@Override
	public List<ABCInvestDto> getInvestListByLoan(int loanCode) {
		return sqlSession.selectList(namespace + "getInvestListByLoan", loanCode);
	}

	@Override
	public void deleteByLoan(int loanCode) {
		sqlSession.delete(namespace + "deleteByLoan", loanCode);
	}

	@Override
	public List<ABCInvestDto> getInvestMoneyList(String email) {
		return sqlSession.selectList(namespace + "getInvestMoneyList", email);
	}

	@Override
	public List<ABCInvestDto> getInvestList(String email) {
		return sqlSession.selectList(namespace + "getInvestList", email);
	}

	@Override
	public List<ABCInvestDto> getInvestListAll() {
		return sqlSession.selectList(namespace + "getInvestListAll");
	}

}
