/**
 * 
 */
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
 * 대출신청 컨트롤러
 * @author 9age
 *
 */
@Controller
public class ABCLoanController {
	/** 대출 서비스 */
	@Autowired
	ABCLoanService loanService;
	
	/** 기본 제목 */
	public static String MAIN_TITLE = "ABC Funding";
	
	/**
	 * 대출신청 페이지 이동 메서드
	 * 제목 설정만 하고 바로 대출신청 페이지로 이동
	 * @param model
	 * @return 대출신청 페이지의 타일즈명
	 * @throws Exception
	 */
	@RequestMapping(value = "loan.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String loan(Model model,HttpServletRequest request ,ABCMemberDto dto) throws Exception {
		// 제목 설정
		model.addAttribute("title", "대출신청 :: "+MAIN_TITLE);
		
		return "loan.tiles";
	}
	
	/**
	 * 대출신청 서비스 호출 후 화면이동하는 메서드
	 * @param model
	 * @return 대출신청 페이지의 타일즈명
	 * @throws Exception
	 */
	@RequestMapping(value = "loanAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	@Transactional
	public String loanAf(Model model,HttpServletRequest request ,ABCLoanDto ldto) throws Exception {
		// 제목 설정
		model.addAttribute("title", "대출신청 처리중 :: "+MAIN_TITLE);
		
		// 대출 신청시 이미지 파일 업로드
		MultipartFile uploadfile = ldto.getUploadfile();
		if (uploadfile != null) {
			// 파일경로
			String path = request.getServletContext().getRealPath("/uploadFile");
			
			/* 저장할 이미지명 */
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
		
		// 대출 신청 등록
		// 다음 달 상환일 잡기
		ldto.setRequestDate(ABCUtility.calcRepayDate(ldto.getRepay()));
		loanService.addLoan(ldto);
		
		return "loanSuc.tiles"; // 대출 신청 성공 화면으로 이동
	}
	
	/**
	 * 대출취소하는 메서드
	 * @param loanCode 대출코드
	 * @return 내정보 페이지로 리다이렉트
	 */
	@RequestMapping(value="loanCancel.do", method=RequestMethod.GET)
	public String loanCancel(int loanCode) {
		// 대출취소 서비스
		loanService.loanCancel(loanCode);
		
		return "redirect:/myInfo.do";
	}
}
