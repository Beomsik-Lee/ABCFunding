package com.hk.abcfund.controller;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.abcfund.enums.ABCGenderType;
import com.hk.abcfund.enums.ABCProgressType;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.dto.RSA;
import com.hk.abcfund.model.service.ABCAccountService;
import com.hk.abcfund.model.service.ABCAdminSerivce;
import com.hk.abcfund.model.service.ABCInvestService;
import com.hk.abcfund.model.service.ABCLoanService;
import com.hk.abcfund.model.service.ABCMemberService;
import com.hk.abcfund.util.ABCUtility;
import com.hk.abcfund.util.RSAUtility;

/**
 * @author 9age
 *
 */
@Controller
public class ABCMemberController {
    /** Member service */
    @Autowired
    private ABCMemberService service;
    
    /** Loan service */
    @Autowired
    private ABCLoanService loanService;
    
    /** Investment service */
    @Autowired
    private ABCInvestService investService;
    
    /** Account service */
    @Autowired
    private ABCAccountService accountService;
    
    /** Administrator service */
    @Autowired
    private ABCAdminSerivce adminService;
    
    /** Default title */
    public static String MAIN_TITLE = "ABC Funding";
    
    private static final Logger logger = LoggerFactory
            .getLogger(ABCMemberController.class);
    
    /**
     * Set the title and go to main page
     * @param model
     * @return tiles name of main page
     * @throws Exception
     */
    @RequestMapping(value = "main.do", method = RequestMethod.GET)
    public String main(Model model) throws Exception {
        // Set default title
        model.addAttribute("title", MAIN_TITLE);
        
        return "main.tiles";
    }
    
    /**
     * Set the title and go to registration page
     * @param model
     * @return tiles name of registration page
     * @throws Exception
     */
    @RequestMapping(value = "regi.do", method = RequestMethod.GET)
    public String regi(Model model) throws Exception {
        // Set registration title
        model.addAttribute("title", "Registration :: " + MAIN_TITLE);
        
        return "regi.tiles";
    }
    
    /**
     * Get request of Registration
     * @param model To set the title
     * @param dto Membership object
     * @return Success tiles name of registration
     * @throws Exception
     */
    @RequestMapping(value = "addMember.do", method = RequestMethod.POST)
    public String addMember(Model model, ABCMemberDto dto) throws Exception {
        // Set title with function name
        model.addAttribute("title", "Registration :: " + MAIN_TITLE);
        
        // Delete hyphen of birth day
        dto.setBirth(ABCUtility.getDateNoHyphen(dto.getBirth()));
        
        // Regist membership
        service.addMemeber(dto);
        
        return "regiSuc.tiles";
    }
    
    /**
     * Go to login page
     * @param model
     * @return tiles name of login
     */
    @RequestMapping (value="login.do", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request, String isFail) {
        // Set login title
        model.addAttribute("title", "Sign in :: " + MAIN_TITLE);
        
        // Create RSA key pair
        RSA rsa = null;
        try {
			rsa = new RSA(RSAUtility.genRSAKeyPair());
		} catch (Exception e) {
			logger.error(e.getMessage());
			isFail = "true";
		}
        
        // Set public key
        model.addAttribute("RSAModulus", rsa.getModulus());
        model.addAttribute("RSAExponent", rsa.getExponent());
        
        // Set private key
        request.getSession().setAttribute("RSAPrivateKey", rsa.getPrivateKey());
        
        // set variable which is failed
        model.addAttribute("isFail", isFail);
        
        return "login.tiles";
    }
    
    /**
     * Set next page after login
     * @param model To set the title
     * @param request To set the session
     * @return tiles name of login
     */
    @RequestMapping (value="loginAf.do", method = RequestMethod.POST)
    public String loginAf(Model model, HttpServletRequest request ,ABCMemberDto dto) {
    	// Get private key from session
    	PrivateKey privateKey = (PrivateKey)request.getSession().getAttribute("RSAPrivateKey");
    	
    	// Get email and password from DTO
    	String email = dto.getEmail();
    	String pwd = dto.getPwd();
    	
    	// Decrypt email and password with RSA and set those to DTO
    	dto.setEmail(RSAUtility.decryptRSAbyBase64(email, privateKey));
    	dto.setPwd(RSAUtility.decryptRSAbyBase64(pwd, privateKey));
    	
        // When request failed
        ABCMemberDto member = service.login(dto);
        if(member == null) {
            return "redirect:/login.do?isFail=true"; // Go to login page
        }
        
        // Set birth day with hyphen
        String joint = String.join("-", ABCUtility.parseBirth(member.getBirth()));
        member.setBirth(joint);
        
        // Set gender
        member.setGender(ABCGenderType.findName(member.getGender()));
        
        // If login success, put the member object to session
        request.getSession().setAttribute("login", member);
        
        return "redirect:/main.do";
    }

    /**
     * Set next page after logout
     * @param request To invalidate the session
     * @return tiles name of main page
     */
    @RequestMapping(value="logout.do", method=RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // session invalidate
        request.getSession().invalidate();
        
        return "redirect:/main.do";
    }
    
    /**
     * Go to my info page
     * @param request To get email of the member
     * @param model To set retrieved data
     * @return tiles name of my info page
     */
    @RequestMapping(value = "myInfo.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String myInfo(HttpServletRequest request, Model model) {
        // Set title of my info
        model.addAttribute("title", "My Info" + MAIN_TITLE);
        
        // Get login data from session
        ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
        
        // Request to service
        model.addAttribute("myInfo", service.getMyInfo(login.getEmail()));
        
        return "myInfo.tiles";
    }
    
    /**
     * Get list of loan data
     */
    @RequestMapping(value = "myLoanInfoList.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String myLoanInfoList(HttpServletRequest request, Model model) {
        // Set title of Loan detail
        model.addAttribute("title", "My Loan Detail :: " + MAIN_TITLE);
        
        // Get email from request
        String email = request.getParameter("email");
        
        // call service for loan list
        model.addAttribute("loanLists",service.getMyLoanInfoList(email));
        model.addAttribute("loanTranList", loanService.getTransactionBySorted(email));
        
        return "myInfoloan.tiles";
    }
    
    /** Go to change password page */
    @RequestMapping(value="changePwd.do", method=RequestMethod.GET)
    public String changePwd(Model model){
        // Set title with change password
        model.addAttribute("title", "Change Password :: " + MAIN_TITLE);
        
        return "changePwd.tiles";
    }
    
    /**
     * Change password
     * @param mdto It has authenticated password and email
     * @return After change password, go to my info page
     */
    @RequestMapping(value="pwdAf.do", method=RequestMethod.POST)
    public String pwdAf(ABCMemberDto mdto){
        // call change password method
        service.changePwd(mdto);
        
        return "redirect:/myInfo.do";
    }
    
    /**
     * Deposit to account from my info page
     * @param email member email
     * @param money To deposit
     * @return tiles name of my info page
     */
    @RequestMapping(value="doDeposit.do", method=RequestMethod.POST)
    public String doDeposit(String email, int money){
        logger.info("Email: " + email);
        logger.info("Deposit: " + money);
        
        // create map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("money", money);
        
        // call deposit method
        accountService.depositAtMyInfo(map);
        
        return "redirect:/myInfo.do";
    }
    
    /**
     * Go to Membership withdrawal page
     * @param request To get session
     * @param model To set title
     * @return tiles name of withdrawal page
     */
    @RequestMapping(value="dropMember.do", method=RequestMethod.GET)
    public String dropMember(Model model, HttpServletRequest request){
        // Set title with withdrawal
        model.addAttribute("title", "Membership Withdrawal :: " + MAIN_TITLE);
        
        // Get member DTO from session
        ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
        
        // Check remain payment
        List<ABCLoanDto> list = loanService.getRemainPayment(login.getEmail());
        
        // add model list
        model.addAttribute("remainList", list);
        
        return "dropMember.tiles";
    }
    
    /**
     * Process of membership withdrawal
     * @param request To get session
     * @return Redirect to logout page
     */
    @RequestMapping(value="dropMemberAf.do", method=RequestMethod.POST)
    public String dropMemberAf(HttpServletRequest request){
        // Get member DTO from session
        ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
        
        // Get email
        String email = login.getEmail();
        
        // Get all loan list
        List<ABCLoanDto> loanList = loanService.getLoanListAll(email);
        
        // Check progression of the loan
        for(ABCLoanDto loan : loanList){
            int loanCode = loan.getLoanCode();    // Get loan code
            if(!loan.getProgress().equals(ABCProgressType.REPAYED.getCode())){ // Cancel a loan which is not repayed
                loanService.loanCancel(loanCode);
            }
            
            // Delete examination loan
            adminService.deleteJudgeByLoan(loanCode);
        }
        
        // call withdrawal method
        service.dropMember(email);
        
        return "redirect:/logout.do";
    }
    
    /**
     * Go to investment detail page
     * @param model To set title
     * @param request To get session
     * @return tiles name of investment detail page
     */
    @Transactional
    @RequestMapping(value="myInfoInvest.do", method=RequestMethod.GET)
    public String myInfoInvest(Model model, HttpServletRequest request){
        // Set title of investment detail
        model.addAttribute("title", "Investment Detail :: " + MAIN_TITLE);
        
        // Get member DTO from session
        ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
        
        // Get email
        String email = login.getEmail();
        
        // Get list of loan invested by the member
        model.addAttribute("loanList",loanService.getLoanListByInvest(email));
        
        // Add list of investment to model
        model.addAttribute("investList", investService.getInvestMoneyList(email));
                
        // Add list of investment detail to model
        model.addAttribute("investTranList", investService.getInvestTransaction(email));
        
        return "myInfoInvest.tiles";
    }
}