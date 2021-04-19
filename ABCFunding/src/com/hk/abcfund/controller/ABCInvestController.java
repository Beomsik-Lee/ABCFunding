package com.hk.abcfund.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.abcfund.model.dto.ABCAccountDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.service.ABCAccountService;
import com.hk.abcfund.model.service.ABCInvestService;
import com.hk.abcfund.model.service.ABCLoanService;
import com.hk.abcfund.util.ABCUtility;

/**
 * Investment Controller
 * @author 9age
 *
 */
@Controller
public class ABCInvestController {
	/** Default title */
	public static String MAIN_TITLE = "ABC Funding";
	
	/** Loan service */
	@Autowired
	private ABCLoanService loanService;
	
	/** Investment service */
	@Autowired
	private ABCInvestService investService;
	
	/** Account service */
	@Autowired
	private ABCAccountService accountService;
	
	/**
	 * Go to list of investment page
	 * @param model To set title
	 * @return tiles name of investment list
	 */
	@RequestMapping(value="invest.do", method=RequestMethod.GET)
	public String invest(Model model) {
		model.addAttribute("title", "Investment :: "+MAIN_TITLE);
		
		// Get list of approval loan
		List<ABCLoanDto> list = loanService.getLoanList();
		
		// Cancel expired loans
		int[] delArray = new int[list.size()];
		Arrays.fill(delArray, -1);	// Initiate all to -1
		for(int idx = 0; idx < list.size(); idx++){
			ABCLoanDto dto = list.get(idx);
			if(ABCUtility.isExpired(dto.getRequestDate(), dto.getExpiryDate())) {
				loanService.loanCancel(dto.getLoanCode());
				delArray[idx] = idx;
			}
		}
		
		// Delete list of loan
		Iterator<ABCLoanDto> it = list.iterator();
		for(int idx = 0; it.hasNext(); idx++) {
			it.next();
			if(delArray[idx] != -1) {
				it.remove();
			}
		}
		
		model.addAttribute("loanList", list);
		
		return "invest.tiles";
	}
	
	/**
	 * Go to investment detail page
	 * @param model To set title
	 * @param dto A DTO having loan code
	 * @param request To get session
	 * @return tiles name of investment detail page 
	 */
	@RequestMapping(value="investDetail.do", method=RequestMethod.GET)
	public String investDetail(Model model, HttpServletRequest request ,ABCLoanDto dto) {
		model.addAttribute("title", "Investment Detail :: "+MAIN_TITLE);
		
		List<Object> list = new ArrayList<Object>();
		
		// Get data of detail investment
		investService.getInvestDetail(list, dto.getLoanCode());
		
		// Get objects from list
		ABCLoanDto loan = (ABCLoanDto)list.get(0);
		ABCMemberDto personal = (ABCMemberDto)list.get(1);
		ABCJudgeResultDto judge = (ABCJudgeResultDto)list.get(2);
				
		// Calculate age
		int age = ABCUtility.getAge(personal.getBirth());
		
		// Check if invested
		ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
		boolean isInvested = investService.isInvested(login.getEmail(), loan.getLoanCode());
		
		// Add to model
		model.addAttribute("personal", personal);
		model.addAttribute("loan", loan);
		model.addAttribute("judge", judge);
		model.addAttribute("age", age);
		model.addAttribute("isInvested", isInvested);
		
		return "investDetail.tiles";
	}
	
	/**
	 * Go to investing page
	 * @param model To set title
	 * @param request To get session
	 * @param loanCode A loan code
	 * @return tiles name of doing invest page
	 */
	@RequestMapping(value="doInvest.do", method=RequestMethod.GET)
	public String doInvest(Model model, HttpServletRequest request, int loanCode) {
		model.addAttribute("title", "Investment Application :: "+MAIN_TITLE);
		
		// Get account data by email
		ABCMemberDto member = (ABCMemberDto)request.getSession().getAttribute("login");
		ABCAccountDto account = accountService.getAccount(member.getEmail());
		
		/* Calculate if invest available */
		ABCLoanDto loan = loanService.getLoan(loanCode);
		int balance = account.getBalance();
		int investable = 0;
		
		// If account has balance
		if(balance > 0) {
			// Get max investments
			int max = (int)(loan.getLoanMoney() * 0.2);
			
			// Get difference between loan and current balance
			int diff = loan.getLoanMoney() - loan.getCurrentMoney();
			
			// Check investable
			investable = (diff < max) ? diff : max;
			
			// Check max investments
			if(balance < investable)
				investable = balance;
		}
		
		// Add to model
		model.addAttribute("account", account);
		model.addAttribute("loan", loan);
		model.addAttribute("investable", investable);
		
		return "doInvest.tiles";
	}
	
	/**
	 * Go to investment page
	 * @param model To set title
	 * @param request To get session
	 * @param loanCode loan code
	 * @param investMoney amount of investment
	 * @return tiles name of investment success page
	 */
	@Transactional
	@RequestMapping(value="investSuc.do", method=RequestMethod.POST)
	public String investSuc(Model model, HttpServletRequest request, 
			int loanCode, String title, int investMoney) {
		model.addAttribute("title", "Investment Success :: "+MAIN_TITLE);
		
		// Get member DTO from session
		ABCMemberDto member = (ABCMemberDto)request.getSession().getAttribute("login");
		
		// Amount of investment was in units of 10K WON so multiply 10000
		investMoney *= 10000;
		
		// Request investment
		investService.investRequest(member.getEmail(), loanCode, investMoney);
		
		// Check funding complete
		investService.checkComplete(loanCode);
		
		// Add to model
		model.addAttribute("title", title);
		model.addAttribute("investMoney", investMoney);
		
		return "investSuc.tiles";
	}
	
}