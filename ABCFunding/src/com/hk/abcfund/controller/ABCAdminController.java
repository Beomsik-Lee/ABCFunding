package com.hk.abcfund.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.abcfund.model.dto.ABCInvestDto;
import com.hk.abcfund.model.dto.ABCInvestTransactionDto;
import com.hk.abcfund.model.dto.ABCJudgeDto;
import com.hk.abcfund.model.dto.ABCJudgeResultDto;
import com.hk.abcfund.model.dto.ABCLoanDto;
import com.hk.abcfund.model.dto.ABCLoanTransactionDto;
import com.hk.abcfund.model.dto.ABCMemberDto;
import com.hk.abcfund.model.service.ABCAccountService;
import com.hk.abcfund.model.service.ABCAdminSerivce;
import com.hk.abcfund.model.service.ABCInvestService;
import com.hk.abcfund.model.service.ABCLoanService;
import com.hk.abcfund.model.service.ABCMemberService;
import com.hk.abcfund.util.ABCUtility;

/**
 * 관리자 화면을 제어하는 컨트롤러 클래스
 * @author 9age
 *
 */
@Controller
public class ABCAdminController {
	/** 관리자 서비스 */
	@Autowired
	private ABCAdminSerivce adminservice;
	
	/** 계좌 서비스 */
	@Autowired
	private ABCAccountService accountService;
	
	/** 회원 서비스 */
	@Autowired
	private ABCMemberService memberService;
	
	/** 투자 서비스 */
	@Autowired
	private ABCInvestService investService;
	
	/** 대출 서비스 */
	@Autowired
	private ABCLoanService loanService;
	
	/** 기본 제목 */
	public static String MAIN_TITLE = "ABC Funding";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCAdminController.class);
	
	/**
	 * 심사관리 화면으로 이동하는 메서드
	 * @param model
	 * @return 심사관리 타일즈명
	 */
	@RequestMapping (value="judgeManage.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeManage(Model model) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "심사관리 :: " + MAIN_TITLE);
		
		// 심사대기 상태의 리스트 가져오기
		List<ABCJudgeDto> list = adminservice.getJudgeList();
		
		// 심사대기 상태인 리스트 호출 후 담기
		model.addAttribute("judgeList", list);
		
		return "judgelist.tiles";
	}
	
	/**
	 * 심사대기 상세화면으로 이동하는 메서드
	 * @param model
	 * @return 심사관리 타일즈명
	 */
	@RequestMapping (value="judgeDetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeDetail(HttpServletRequest request,Model model) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "심사상세내용 :: " + MAIN_TITLE);
		
		// 해당 심사건의 loancode를 웹에서부터 받아와 조회
		int loanCode = Integer.parseInt(request.getParameter("loanCode"));
		model.addAttribute("judgeDetail", adminservice.getJudgeDetail(loanCode));
		
		return "judgedetail.tiles";
	}
	
	/**
	 * 심사대기 상세화면에서 심사결과 처리하는 메서드
	 * @param model
	 * @return 심사리스트 타일즈명
	 */
	@RequestMapping (value="judgeDetailAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeDetailAf(Model model,ABCJudgeResultDto jrdto) {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "심사결과 :: " + MAIN_TITLE);
		
		// 심사결과 정보를 db에 입력한다.
		adminservice.addJudgeResult(jrdto);
		
		// 승인됐다면 해당 가상계좌를 생성
		if(jrdto.getResult() == 1) {
			accountService.accountForLoan(jrdto.getLoanCode());
		}
		
		return "redirect:/judgeManage.do";
	}
	
	/**
	 * 회원관리 페이지로 이동하는 메서드
	 * @param model 타이틀을 변경할 객체
	 * @return 회원관리 페이지의 타일즈명
	 */
	@RequestMapping(value="memberManage.do", method=RequestMethod.GET)
	public String memeberManage(Model model){
		// 타이틀 설정
		model.addAttribute("title", "회원관리 :: " + MAIN_TITLE);
		
		// 회원 전체 리스트 등록(관리자 및 덤프는 제외)
		model.addAttribute("memberList", memberService.getMemberList());
		
		return "memberManage.tiles";
	}
	
	/**
	 * 회원 상세 페이지로 이동하는 메서드
	 * @param model 타이틀을 변경할 객체
	 * @param email 회원의 이메일
	 * @return 회원 상세 페이지의 타일즈명
	 */
	@RequestMapping(value="memberDetail.do", method=RequestMethod.GET)
	public String memeberDetail(Model model, String email){
		// 타이틀 설정
		model.addAttribute("title", "회원 상세정보 :: " + MAIN_TITLE);
		
		// 회원 데이터 가져오기
		ABCMemberDto member = memberService.getMember(email);
		
		// 나이 구하기
		member.setYear(""+ABCUtility.getAge(member.getBirth()));
		
		// 리스트 및 각종 데이터를 모델에 등록
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
	 * 투자관리 페이지로 이동하는 메서드
	 * @param model 타이틀을 변경할 객체
	 * @return 투자관리 페이지의 타일즈명
	 */
	@RequestMapping(value="investManage.do", method=RequestMethod.GET)
	public String investManage(Model model){
		// 타이틀 설정
		model.addAttribute("title", "투자관리 :: " + MAIN_TITLE);
		
		// 투자관리 리스트를 모델에 등록
		model.addAttribute("investManageList", adminservice.getInvestManageData());
		
		return "investManage.tiles";
	}
	
	/**
	 * 대출 관리 페이지로 이동하는 메서드
	 * @param model 타이틀을 변경할 객체
	 * @return 대출 관리 페이지의 타일즈명
	 */
	@RequestMapping(value="loanManage.do", method=RequestMethod.GET)
	public String loanManage(Model model){
		// 타이틀 설정
		model.addAttribute("title", "대출 관리 페이지:: " + MAIN_TITLE);
		
		logger.info("loanManage.do 호출 성공!" + new Date());
		// 대출관리 정보 호출해서 담기
		model.addAttribute("loanManage", adminservice.getLoanManage());
		
		return ("loanManage.tiles");
	}
}
