/**
 * 
 */
package com.hk.abcfund.model.service;

import java.io.Serializable;
import java.util.List;

import com.hk.abcfund.model.dto.ABCAdminLoanManageDto;
import com.hk.abcfund.model.dto.ABCInvestChartDto;
import com.hk.abcfund.model.dto.ABCInvestManageDto;
import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanChartDto;

/**
 * Service interface for administrator
 * @author 9age
 *
 */
public interface ABCAdminSerivce extends Serializable {
	List<ABCJudgeDto>getJudgeList();
	ABCJudgeDto getJudgeDetail(int loanCode);
	void addJudgeResult(ABCJudgeResultDto jrdto);
	void deleteJudgeByLoan(int loanCode);
	List<ABCInvestChartDto> getInvestChartData(String email);
	List<ABCLoanChartDto> getLoanChartData(String email);
	List<ABCInvestManageDto> getInvestManageData();
	ABCAdminLoanManageDto getLoanManage();
}