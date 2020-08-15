package com.hk.abcfund.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.service.ABCAccountService;
import com.hk.abcfund.model.service.ABCAdminSerivce;
import com.hk.abcfund.model.service.ABCInvestService;
import com.hk.abcfund.model.service.ABCLoanService;
import com.hk.abcfund.model.service.ABCMemberService;
import com.hk.abcfund.util.ABCUtility;

/**
 * Administrator page controller
 * @author 9age
 *
 */
@Controller
public class ABCAdminController {
	/** Administrator service */
	@Autowired
	private ABCAdminSerivce adminservice;
	
	/** Account service */
	@Autowired
	private ABCAccountService accountService;
	
	/** Member service */
	@Autowired
	private ABCMemberService memberService;
	
	/** Investment service */
	@Autowired
	private ABCInvestService investService;
	
	/** Loan service */
	@Autowired
	private ABCLoanService loanService;
	
	/** Default title */
	public static String MAIN_TITLE = "ABC Funding";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCAdminController.class);
	
	/**
	 * Go to examine list page
	 * @param model To set title
	 * @return tiles name of examine list
	 */
	@RequestMapping (value="judgeManage.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeManage(Model model) {
		// Set title with examine
		model.addAttribute("title", "Examine Management :: " + MAIN_TITLE);
		
		// Get waiting list of loan
		List<ABCJudgeDto> list = adminservice.getJudgeList();
		
		// Add to model
		model.addAttribute("judgeList", list);
		
		return "judgelist.tiles";
	}
	
	/**
	 * Go to examine detail page
	 * @param model To set title
	 * @return tiles name of examine detail page
	 */
	@RequestMapping (value="judgeDetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeDetail(HttpServletRequest request,Model model) {
		// Set title with examine detail
		model.addAttribute("title", "Examine Detail :: " + MAIN_TITLE);
		
		// Get loan code from request
		int loanCode = Integer.parseInt(request.getParameter("loanCode"));
		
		// Get examine detail from loan code and add it to model
		model.addAttribute("judgeDetail", adminservice.getJudgeDetail(loanCode));
		
		return "judgedetail.tiles";
	}
	
	/**
	 * Processing examine of loan
	 * @param model To set title
	 * @return Redirect to examine management
	 */
	@RequestMapping (value="judgeDetailAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeDetailAf(Model model,ABCJudgeResultDto jrdto) {
		// Set title with result
		model.addAttribute("title", "Examine Result :: " + MAIN_TITLE);
		
		// Store examine result
		adminservice.addJudgeResult(jrdto);
		
		// If approved, create a account for loan
		if(jrdto.getResult() == 1) {
			accountService.accountForLoan(jrdto.getLoanCode());
		}
		
		return "redirect:/judgeManage.do";
	}
	
	/**
	 * Go to member management page
	 * @param model To set title
	 * @return tiles name of member management page
	 */
	@RequestMapping(value="memberManage.do", method=RequestMethod.GET)
	public String memeberManage(Model model){
		// Set title
		model.addAttribute("title", "Membership Management :: " + MAIN_TITLE);
		
		// Add member list to model (exclusive administrator and dump)
		model.addAttribute("memberList", memberService.getMemberList());
		
		return "memberManage.tiles";
	}
	
	/**
	 * Go to member detail page
	 * @param model To set title
	 * @param email email
	 * @return tiles name of member detail page
	 */
	@RequestMapping(value="memberDetail.do", method=RequestMethod.GET)
	public String memeberDetail(Model model, String email){
		// Set title
		model.addAttribute("title", "Member Detail :: " + MAIN_TITLE);
		
		// Get the member DTO by email
		ABCMemberDto member = memberService.getMember(email);
		
		// Calculate member's birth date
		member.setYear(""+ABCUtility.getAge(member.getBirth()));
		
		// Add required data to model
		model.addAttribute("investList", investService.getInvestList(email));
		model.addAttribute("loanList", loanService.getLoanListAll(email));
		model.addAttribute("investTranList", investService.getTransactionReserve(email));
		model.addAttribute("loanTranList", loanService.getTransactionBySorted(email));
		model.addAttribute("payingCount", loanService.getPayingCount(email));
		model.addAttribute("member", member);
		model.addAttribute("investData", adminservice.getInvestChartData(email));
		model.addAttribute("loanData", adminservice.getLoanChartData(email));
		
		return "memberDetail.tiles";
	}
	
	/**
	 * Go to investment management page
	 * @param model To set title
	 * @return tiles name of investment management
	 */
	@RequestMapping(value="investManage.do", method=RequestMethod.GET)
	public String investManage(Model model){
		// Set title
		model.addAttribute("title", "Investment Management :: " + MAIN_TITLE);
		
		// Add list of investment management to model
		model.addAttribute("investManageList", adminservice.getInvestManageData());
		
		return "investManage.tiles";
	}
	
	/**
	 * Go to loan management page
	 * @param model To set title
	 * @return tiles name of loan management page
	 */
	@RequestMapping(value="loanManage.do", method=RequestMethod.GET)
	public String loanManage(Model model){
		// Set title
		model.addAttribute("title", "Loan Management:: " + MAIN_TITLE);
		
		logger.info("loanManage.do Success!" + new Date());
		
		// Add loan management data to model
		model.addAttribute("loanManage", adminservice.getLoanManage());
		
		return ("loanManage.tiles");
	}
}