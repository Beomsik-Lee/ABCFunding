package com.hk.abcfund.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.service.ABCAccountService;
import com.hk.abcfund.model.service.ABCAdminSerivce;
import com.hk.abcfund.model.service.ABCInvestService;
import com.hk.abcfund.model.service.ABCLoanService;
import com.hk.abcfund.model.service.ABCMemberService;
import com.hk.abcfund.util.SMTPAuthenticator;

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
        
        // Send email of authentication
        emailAuthentication(dto, "authAf.do");
        
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
    public String login(Model model, String isFail) {
        // Set login title
        model.addAttribute("title", "로그인 :: " + MAIN_TITLE);
        
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
        // when request failed
        ABCMemberDto member = service.login(dto);
        if(member == null) {
            return "redirect:/login.do?isFail=true"; // Go to login page
        }
        
        // If login success, put the member object to session
        request.getSession().setAttribute("login", member);
        
        /* judge normal member or administrator */
        String tile = "redirect:/main.do";
        if (member.getGrade() == 2)
            tile = "redirect:/admin.do";
        
        return tile;
    }
    
    /**
     * Set next page after admin login
     * @param model To set the title
     * @return tiles name of login
     */
    @RequestMapping (value="admin.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String admin(Model model) {
        // Set title of administrator
        model.addAttribute("title", "Administrator :: " + MAIN_TITLE);
        
        return "admin.tiles";
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
     * Move to password find page
     * @param model
     * @return tiles name of password find page
     */
    @RequestMapping(value="searchpwd.do", method = RequestMethod.GET)
    public String searchpwd(Model model) {
        // Set title of find password
        model.addAttribute("title", "Find password" + MAIN_TITLE);
        
        return "searchpwd.tiles";
    }
    
    /**
     * Set next page after find password
     * @param dto Only have email data
     * @return Redirect to main page
     */
    @RequestMapping(value="searchpwdAf.do", method = RequestMethod.POST)
    public String searchpwdAf(ABCMemberDto dto) {
        // Send authentication email
        emailAuthentication(dto, "pwdAfAuth.do");
        
        // Change new authentication code
        service.changeAuthCode(dto);
        
        return "redirect:/main.do";
    }
    
    /**
     * Go to change password page
     * @param model To set title
     * @return tiles name of change password
     */
    @RequestMapping(value="pwdAfAuth.do", method = RequestMethod.GET)
    public String pwdAfAuth(Model model, HttpServletRequest request ,ABCMemberDto dto) {
        // set title of change password
        model.addAttribute("title", "Change Password :: " + MAIN_TITLE);
        
        // email decoding
        byte[] decoded = Base64.decodeBase64(dto.getEmail().getBytes());
        
        // set decoded email
        dto.setEmail(new String(decoded));
        
        // add to session
        request.getSession().setAttribute("authAf", dto);
        
        return "pwdAfAuth.tiles";
    }
    
    /**
     * Set next page after change password
     * @param request To get added session
     * @param pwd To change password
     * @return Redirect to login page
     */
    @RequestMapping(value="pwdAfAuthAf.do", method = RequestMethod.POST)
    public String pwdAfAuthAf(HttpServletRequest request , String pwd) {
        // Set password to session
        ABCMemberDto dto = (ABCMemberDto)(request.getSession().getAttribute("authAf"));
        dto.setPwd(pwd);
        
        // invalidate no use of session
        request.getSession().invalidate();
        
        // change password on request of authentication
        service.changePwdOnAuth(dto);
        
        return "redirect:/login.do";
    }
    
    /**
     * Authenticate the email
     * @param dto Member object for authentication
     * @param path path for authentication
     */
    public void emailAuthentication(ABCMemberDto dto, String path) {
        try {
            // Create UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");
            dto.setAuthCode(uuid);
            
            /* Set data to send the email */
            // set my email
            String from = "dlqjatlr990@gmail.com";
            
            // set title
            String subject = "[ABC Funding]Authentication on your request";
            
            // encoding email
            byte[] encodeEmail = Base64.encodeBase64(dto.getEmail().getBytes());
            String content =
                "http://localhost:8090/ABCFunding/"+path+"?email="+
                        new String(encodeEmail)+"&authCode="+uuid;

            /* Set data to connect to SMTP*/
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "465");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.debug", "true");
            p.put("mail.smtp.socketFactory.port", "465");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.socketFactory.fallback", "false");            
            
            // SMTP Authentication
            Authenticator auth = new SMTPAuthenticator();
            
            // Create session with SMTP connection
            Session session = Session.getInstance(p, auth);
            
            // set session debug
            session.setDebug(true);
            
            /* Create instance for email contents */
            MimeMessage msg = new MimeMessage(session);
            Address fromAddr = new InternetAddress(from);
            Address toAddr = new InternetAddress(dto.getEmail());
            String emailContent = makeEmail(content, dto.getEmail());
            
            // set subject
            msg.setSubject(subject);

            // set from-email
            msg.setFrom(fromAddr); 

            // set to-email
            msg.addRecipient(Message.RecipientType.TO, toAddr); 
            
            // set text
            msg.setText(emailContent, "UTF-8");
            
            // set MIME
            msg.setHeader("content-Type", "text/html;charset=utf-8");
            
            // send email
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write email body
     * @param content email message
     * @param email to-email
     * @return Created HTML String
     */
    public String makeEmail(String content, String email){
        StringBuffer sb = new StringBuffer(); 

        sb.append("<HTML>\n"); 
        sb.append("<HEAD>\n"); 
        sb.append("<TITLE>"+"Notification"+"</TITLE>\n"); 
        sb.append("<META content=text/html; charset=utf-8>\n"); 
        sb.append("</HEAD>\n"); 
        sb.append("<BODY text=black vLink=blue aLink=red link=blue bgColor=#ffffff>\n"); 
        
        sb.append("<P align=left><B><FONT size=8px color=black>Confirm Authentication</FONT></B></P>\n");
        sb.append("<P align=left><B><FONT size=6px color=black>Welcome "+email+"!!</FONT></B></P>\n");
        sb.append("<P align=left><B><FONT size=6px color=black>Click the button</FONT></B></P>\n");
        sb.append("<P align=left><a href='" + content +"'><FONT size=5px color=blue>Confirm</FONT></a></P>\n");

        sb.append("</BODY>\n"); 
        sb.append("</HTML>\n"); 
        
        return sb.toString();
    }
    
    /**
     * Confirm authentication from email
     * @param dto Membership dto
     * @param model
     * @return tiles name of login page
     */
    @RequestMapping(value = "authAf.do", method = RequestMethod.GET)
    public String authAf(ABCMemberDto dto, Model model) {
        // set title of email authentication
        model.addAttribute("title", "Authentication :: " + MAIN_TITLE);
        
        try {
            // Decode email
            byte[] decoded = Base64.decodeBase64(dto.getEmail().getBytes());
            
            // Set email decoded
            dto.setEmail(new String(decoded));
            
            // Grant permission of normal member
            System.out.println(dto);
            service.doAuthMember(dto);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login.do";
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
        model.addAttribute("title", "내정보" + MAIN_TITLE);
        
        // Get login data from session
        ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
        
        // Request to service
        model.addAttribute("myInfo", service.getMyInfo(login.getEmail()));
        
        logger.info("myInfo.do Success!" + new Date());
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
            if(!loan.getProgress().equals("상환완료")){ // Cancel loan of not-approval or funding
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