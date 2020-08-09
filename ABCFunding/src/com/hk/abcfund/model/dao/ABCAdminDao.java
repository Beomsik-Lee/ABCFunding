/**
 * 
 */
package com.hk.abcfund.model.dao;

import java.io.Serializable;
import java.util.List;

import com.hk.abcfund.model.dto.ABCAdminLoanManageDto;
import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;

/**
 * 관리자 화면의 DAO 인터페이스
 * @author 9age
 *
 */
public interface ABCAdminDao extends Serializable {
	List<ABCJudgeDto> getJudgeList();
	ABCJudgeDto getJudgeDetail(int loanCode);
	void addJudgeResult(ABCJudgeResultDto jrdto);
	ABCJudgeResultDto getJudge(int loanCode);
	void deleteJudgeByLoan(int loanCode);
	ABCAdminLoanManageDto getAdminLoanInfo1();
	ABCAdminLoanManageDto getAdminLoanInfo2();
	ABCAdminLoanManageDto getAdminLoanInfo3();
	ABCAdminLoanManageDto getAdminLoanInfo4();
}
