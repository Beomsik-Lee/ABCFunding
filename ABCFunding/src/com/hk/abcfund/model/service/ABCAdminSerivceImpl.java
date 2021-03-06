package com.hk.abcfund.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.abcfund.model.dao.ABCAdminDao;
import com.hk.abcfund.model.dao.ABCInvestDao;
import com.hk.abcfund.model.dao.ABCInvestTransactionDao;
import com.hk.abcfund.model.dao.ABCLoanDao;
import com.hk.abcfund.model.dao.ABCLoanTransactionDao;
import com.hk.abcfund.model.dto.ABCAdminLoanManageDto;
import com.hk.abcfund.model.dto.ABCInvestChartDto;
import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestManageDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanChartDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;

/**
 * Service class for administrator
 * @author 9age
 */
@Service
public class ABCAdminSerivceImpl implements ABCAdminSerivce {
	/** Admin DAO */
	@Autowired
	private ABCAdminDao adao;
	
	/** Loan DAO */
	@Autowired
	private ABCLoanDao loanDao;
	
	/** Investment DAO */
	@Autowired
	private ABCInvestDao investDao;
	
	/** Investment Detail DAO */
	@Autowired
	private ABCInvestTransactionDao investTranDao;
	
	/** Loan Detail DAO */
	@Autowired
	private ABCLoanTransactionDao loanTranDao;
	
	/** slf4j Logger */
	private static final Logger logger = LoggerFactory
			.getLogger(ABCLoanServiceImpl.class);
	
	/**
	 * Get list of non-approval loans
	 */
	@Override
	public List<ABCJudgeDto> getJudgeList() {
		return adao.getJudgeList();
	}

	/**
	 * Get non-approval loan by loan code
	 * @param loanCode A loan code
	 */
	@Override
	public ABCJudgeDto getJudgeDetail(int loanCode) {
		return adao.getJudgeDetail(loanCode);
	}

	/**
	 * Process examine result
	 * @param ABCJudgeResultDto A DTO that has a examine result
	 */
	@Override
	@Transactional
	public void addJudgeResult(ABCJudgeResultDto jrdto) {
		// Add examine result
		adao.addJudgeResult(jrdto);
		
		// If approval, change progress to 'progressing'
		if(jrdto.getResult() == 1) {
			ABCLoanDto loan = new ABCLoanDto(jrdto.getLoanCode(), "펀딩진행중");
			loanDao.updateProgress(loan);
		}
	}
	
	/**
	 * Delete a examine record by loan code 
	 * @param loanCode A loan code
	 */	
	@Override
	public void deleteJudgeByLoan(int loanCode) {
		adao.deleteJudgeByLoan(loanCode);
	}
	
	/**
	 * Generate data for investment graph
	 * @param email A member's email
	 * @return Return data generated by JSON
	 */
	@Override
	public List<ABCInvestChartDto> getInvestChartData(String email) {
		// Get investment (detail) list by email
		List<ABCInvestDto> investList = investDao.getInvestList(email);
		List<ABCInvestTransactionDto> investTranList = investTranDao.getTransactionReserve(email);
		
		// Declare list of investment chart graph
		List<ABCInvestChartDto> chartList = new ArrayList<ABCInvestChartDto>();
		
		for(int i = 0; i < investList.size(); i++){
			ABCInvestDto invest = investList.get(i);
			int investSeq = invest.getInvestSeq();
			int investMoney = invest.getInvestMoney();
			int stackInterest = 0;
			int stackOrigin = 0;
			int max = -999;
			
			for(int j = 0; j < investTranList.size(); j++){
				ABCInvestTransactionDto investTran = investTranList.get(j);
				if(investSeq == investTran.getInvestSeq()){
					if(max == -999) max = 0;
					
					// Calculate stack interest
					stackInterest += investTran.getIntendProfit();
					
					// Find a history of max stack
					if(investTran.getRound() > investTranList.get(max).getRound())
						max = j;
				}
			}
			if(max != -999)
				stackOrigin = investTranList.get(max).getStackCollect();
			
			// Add to chart list
			chartList.add(new ABCInvestChartDto(investSeq, investMoney, stackOrigin, stackInterest));
		}
		
		return chartList;
	}
	
	/**
	 * Extract chart data of loan
	 * @param email A member's email
	 */
	@Override
	public List<ABCLoanChartDto> getLoanChartData(String email) {
		// Declare chart list
		List<ABCLoanChartDto> chartList = new ArrayList<ABCLoanChartDto>();
		
		// Get detail list of loan by email
		List<ABCLoanTransactionDto> loanTranList = loanTranDao.getTransactionBySorted(email);
		
		// If history is empty, then return empty list 
		if(loanTranList.isEmpty()) return chartList;
		
		// Calculate max stack repayments rate and stack interest
		int stackRepayRate = 0;	// stack repayments rate
		int stackRepayOrigin = 0; // stack repayments
		int loanCode = loanTranList.get(0).getLoanCode();	// loan code
		
		for(int idx = 0; idx < loanTranList.size(); idx++){
			// Get history
			ABCLoanTransactionDto loanTran = loanTranList.get(idx);
			
			// If another loan, add data to chart list
			if(loanCode != loanTran.getLoanCode()){
				chartList.add(new ABCLoanChartDto(loanCode, stackRepayOrigin, stackRepayRate));
				loanCode = loanTran.getLoanCode();
				stackRepayRate = 0;
				stackRepayOrigin = 0;
			} else if(idx + 1 == loanTranList.size()){ // If last history
				// Get stack repayments rate
				stackRepayRate += loanTran.getStackRepayRate();
				
				// add stack repayments to chart list
				stackRepayOrigin = loanTran.getStackRepayOrigin();
				chartList.add(new ABCLoanChartDto(loanCode, stackRepayOrigin, stackRepayRate));
				
				break;
			}
			
			// Calculate stack repayments rate
			stackRepayRate += loanTran.getStackRepayRate();
			
			// Calculate stack repayments
			stackRepayOrigin = loanTran.getStackRepayOrigin();
		}
		logger.info("Chart data of loan: " + chartList);
		
		return chartList;
	}
	
	/** 
	 * Extract data of investments management
	 * @return The list of investment management
	  */
	@Override
	public List<ABCInvestManageDto> getInvestManageData() {
		// Declare chart list of investment management
		List<ABCInvestManageDto> chartList = new ArrayList<ABCInvestManageDto>();
		
		// Get all list of investments
		List<ABCInvestDto> investList = investDao.getInvestListAll();
		List<ABCInvestTransactionDto> investTranList = investTranDao.getTransactionAll();
				
		// Extract data from list of investment
		for(int i = 0; i < investList.size(); i++){
			ABCInvestDto invest = investList.get(i);
			int investMoney = invest.getInvestMoney(); // get investment
			int stackProfit = 0;  // stack profit
			String progress = "";
			
			for(int j = 0; j < investTranList.size(); j++){
				ABCInvestTransactionDto investTran = investTranList.get(j);
				
				if(invest.getInvestSeq() == investTran.getInvestSeq()){
					// Get stack profit
					stackProfit += investTran.getIntendProfit();
					
					// Get progress of investment
					progress = investTran.getProgress();
				}
			}
			chartList.add(new ABCInvestManageDto(
				invest.getInvestSeq(), investMoney, stackProfit, progress));
		}
		logger.info("Chart data of investment: "+chartList);
		
		return chartList;
	}
	
	/**
	 * Get data of loan management
	 * @return A DTO of loan management for administrator
	 */	
	@Override
	@Transactional
	public ABCAdminLoanManageDto getLoanManage() {
		logger.info("getLoanManage() Call Success!" + new Date());
		ABCAdminLoanManageDto dto =  new ABCAdminLoanManageDto();
		
		dto.setStackLoanNum((adao.getAdminLoanInfo1()).getStackLoanNum());
		dto.setStackLoanMoney((adao.getAdminLoanInfo1()).getStackLoanMoney()); // Set stack loans
		dto.setStackLoanRepayMoney((adao.getAdminLoanInfo2()).getStackLoanRepayMoney()); // Set repayments of stack loans
		dto.setLoanEndNum((adao.getAdminLoanInfo3()).getLoanEndNum()); // Set number of loan ends
		dto.setLoanMiddleNum((adao.getAdminLoanInfo4()).getLoanMiddleNum()); // Set number of loans progressing
		
		return dto;
	}

}