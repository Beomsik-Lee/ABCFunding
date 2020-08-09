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
 * 투자와 관련된 컨트롤러
 * @author 9age
 *
 */
@Controller
public class ABCInvestController {
	/** 기본 제목 */
	public static String MAIN_TITLE = "ABC Funding";
	
	/** 대출관련 서비스 */
	@Autowired
	private ABCLoanService loanService;
	
	/** 투자관련 서비스 */
	@Autowired
	private ABCInvestService investService;
	
	/** 계좌관련 서비스 */
	@Autowired
	private ABCAccountService accountService;
	
	/**
	 * 투자하기 페이지로 이동하는 메서드
	 * @param model 제목을 설정할 객체
	 * @return 투자하기 페이지의 타일즈명
	 */
	@RequestMapping(value="invest.do", method=RequestMethod.GET)
	public String invest(Model model) {
		model.addAttribute("title", "투자하기 :: "+MAIN_TITLE);
		
		// 심사에서 승인된 대출리스트 가져오기
		List<ABCLoanDto> list = loanService.getLoanList();
		
		// 펀딩기간이 지난 대출상품 삭제
		int[] delArray = new int[list.size()];
		Arrays.fill(delArray, -1);	// 모든 배열요소를 -1로 초기화
		for(int idx = 0; idx < list.size(); idx++){
			ABCLoanDto dto = list.get(idx);
			if(ABCUtility.isExpired(dto.getRequestDate(), dto.getExpiryDate())) {
				loanService.loanCancel(dto.getLoanCode());
				delArray[idx] = idx;
			}
		}
		
		// 리스트 삭제
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
	 * 대출 상품을 눌렀을 때 해당 상세페이지로 이동하는 메서드
	 * @param model 제목 설정
	 * @param dto 대출코드 데이터가 있는 대출 DTO
	 * @param request 세션정보를 가져올 객체
	 * @return 투자 상세페이지의 타일즈명 
	 */
	@RequestMapping(value="investDetail.do", method=RequestMethod.GET)
	public String investDetail(Model model, HttpServletRequest request ,ABCLoanDto dto) {
		model.addAttribute("title", "투자 상세정보 :: "+MAIN_TITLE);
		
		/* 각각의 데이터를 담을 리스트 선언 */
		List<Object> list = new ArrayList<Object>();
		
		// 상세 투자 데이터 요청
		investService.getInvestDetail(list, dto.getLoanCode());
		
		// 담아둔 객체 꺼내기
		ABCLoanDto loan = (ABCLoanDto)list.get(0);
		ABCMemberDto personal = (ABCMemberDto)list.get(1);
		ABCJudgeResultDto judge = (ABCJudgeResultDto)list.get(2);
				
		// 나이 구하기
		int age = ABCUtility.getAge(personal.getBirth());
		
		// 투자 여부를 확인
		ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
		boolean isInvested = investService.isInvested(login.getEmail(), loan.getLoanCode());
		
		// 모델에 등록
		model.addAttribute("personal", personal);
		model.addAttribute("loan", loan);
		model.addAttribute("judge", judge);
		model.addAttribute("age", age);
		model.addAttribute("isInvested", isInvested);
		
		return "investDetail.tiles";
	}
	
	/**
	 * 투자신청 화면으로 이동하는 메서드
	 * @param model 제목 설정
	 * @param request 세션을 불러올 객체
	 * @param loanCode 대출코드
	 * @return 투자신청 페이지의 타일즈명
	 */
	@RequestMapping(value="doInvest.do", method=RequestMethod.GET)
	public String doInvest(Model model, HttpServletRequest request, int loanCode) {
		model.addAttribute("title", "투자신청 :: "+MAIN_TITLE);
		
		// 이메일로 계좌데이터 가져오기
		ABCMemberDto member = (ABCMemberDto)request.getSession().getAttribute("login");
		ABCAccountDto account = accountService.getAccount(member.getEmail());
		
		/* 투자가능금액 계산 */
		ABCLoanDto loan = loanService.getLoan(loanCode);
		int balance = account.getBalance();
		int investable = 0;
		
		// 예치금이 있을 경우
		if(balance > 0) {
			// 최대 투자금액
			int max = (int)(loan.getLoanMoney() * 0.2);
			
			// 남은 투자가능 금액
			int diff = loan.getLoanMoney() - loan.getCurrentMoney();
			
			// 남은 투자가능 금액 확인
			investable = (diff < max) ? diff : max;
			
			// 최대 투자금액 확인
			if(balance < investable)
				investable = balance;
		}
		
		// 모델에 담기
		model.addAttribute("account", account);
		model.addAttribute("loan", loan);
		model.addAttribute("investable", investable);
		
		return "doInvest.tiles";
	}
	
	/**
	 * 투자신청완료 화면으로 이동하는 메서드
	 * @param model 제목 설정
	 * @param request 세션을 불러올 객체
	 * @param loanCode 대출코드
	 * @param investMoney 대출신청금
	 * @return 투자완료 페이지의 타일즈명
	 */
	@Transactional
	@RequestMapping(value="investSuc.do", method=RequestMethod.POST)
	public String investSuc(Model model, HttpServletRequest request, 
			int loanCode, String title, int investMoney) {
		model.addAttribute("title", "투자완료 :: "+MAIN_TITLE);
		
		// 세션에서 회원 데이터 가져오기
		ABCMemberDto member = (ABCMemberDto)request.getSession().getAttribute("login");
		
		// 투자금은 만원 단위로 입력받았으므로 1만을 곱해준다.
		investMoney *= 10000;
		
		// 투자 요청
		investService.investRequest(member.getEmail(), loanCode, investMoney);
		
		// 펀딩완료 확인
		investService.checkComplete(loanCode);
		
		// 모델에 담기
		model.addAttribute("title", title);
		model.addAttribute("investMoney", investMoney);
		
		return "investSuc.tiles";
	}
	
}
