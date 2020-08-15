package com.hk.abcfund.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.service.ABCLoanService;
import com.hk.abcfund.util.ABCUtility;

/**
 * Loan application controller
 * @author 9age
 *
 */
@Controller
public class ABCLoanController {
	/** loan service */
	@Autowired
	ABCLoanService loanService;
	
	/** default title */
	public static String MAIN_TITLE = "ABC Funding";
	
	/**
	 * Go to loan application page
	 * @param model To set title
	 * @return tiles name of loan application page
	 * @throws Exception
	 */
	@RequestMapping(value = "loan.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String loan(Model model,HttpServletRequest request ,ABCMemberDto dto) throws Exception {
		// Set title with loan application
		model.addAttribute("title", "Loan Application :: "+MAIN_TITLE);
		
		return "loan.tiles";
	}
	
	/**
	 * Go to loan success page after apply
	 * @param model To set title
	 * @return tiles name of loan success page
	 * @throws Exception
	 */
	@RequestMapping(value = "loanAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	@Transactional
	public String loanAf(Model model,HttpServletRequest request ,ABCLoanDto ldto) throws Exception {
		// Set title of loan application
		model.addAttribute("title", "Applying Loan :: "+MAIN_TITLE);
		
		// Upload the image file
		MultipartFile uploadfile = ldto.getUploadfile();
		if (uploadfile != null) {
			// file path
			String path = request.getServletContext().getRealPath("/uploadFile");
			
			/* Set name of image file */
			Random random = new Random(System.currentTimeMillis());
			String fname = 
					Long.toString(random.nextLong()) + uploadfile.getOriginalFilename();
			
			ldto.setFname(fname);
			try{
				File file = new File(path + "/" +fname);
				uploadfile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Apply loan and calculate the repay date
		ldto.setRequestDate(ABCUtility.calcRepayDate(ldto.getRepay()));
		loanService.addLoan(ldto);
		
		return "loanSuc.tiles";
	}
	
	/**
	 * Cancel the loan
	 * @param loanCode code of loan
	 * @return Redirect to my info page
	 */
	@RequestMapping(value="loanCancel.do", method=RequestMethod.GET)
	public String loanCancel(int loanCode) {
		// Cancel the loan
		loanService.loanCancel(loanCode);
		
		return "redirect:/myInfo.do";
	}
}