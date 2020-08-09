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
 * ������ ȭ���� �����ϴ� ��Ʈ�ѷ� Ŭ����
 * @author 9age
 *
 */
@Controller
public class ABCAdminController {
	/** ������ ���� */
	@Autowired
	private ABCAdminSerivce adminservice;
	
	/** ���� ���� */
	@Autowired
	private ABCAccountService accountService;
	
	/** ȸ�� ���� */
	@Autowired
	private ABCMemberService memberService;
	
	/** ���� ���� */
	@Autowired
	private ABCInvestService investService;
	
	/** ���� ���� */
	@Autowired
	private ABCLoanService loanService;
	
	/** �⺻ ���� */
	public static String MAIN_TITLE = "ABC Funding";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCAdminController.class);
	
	/**
	 * �ɻ���� ȭ������ �̵��ϴ� �޼���
	 * @param model
	 * @return �ɻ���� Ÿ�����
	 */
	@RequestMapping (value="judgeManage.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeManage(Model model) {
		// �⺻���� �ش� ��ɸ��� ���δ�.
		model.addAttribute("title", "�ɻ���� :: " + MAIN_TITLE);
		
		// �ɻ��� ������ ����Ʈ ��������
		List<ABCJudgeDto> list = adminservice.getJudgeList();
		
		// �ɻ��� ������ ����Ʈ ȣ�� �� ���
		model.addAttribute("judgeList", list);
		
		return "judgelist.tiles";
	}
	
	/**
	 * �ɻ��� ��ȭ������ �̵��ϴ� �޼���
	 * @param model
	 * @return �ɻ���� Ÿ�����
	 */
	@RequestMapping (value="judgeDetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeDetail(HttpServletRequest request,Model model) {
		// �⺻���� �ش� ��ɸ��� ���δ�.
		model.addAttribute("title", "�ɻ�󼼳��� :: " + MAIN_TITLE);
		
		// �ش� �ɻ���� loancode�� ���������� �޾ƿ� ��ȸ
		int loanCode = Integer.parseInt(request.getParameter("loanCode"));
		model.addAttribute("judgeDetail", adminservice.getJudgeDetail(loanCode));
		
		return "judgedetail.tiles";
	}
	
	/**
	 * �ɻ��� ��ȭ�鿡�� �ɻ��� ó���ϴ� �޼���
	 * @param model
	 * @return �ɻ縮��Ʈ Ÿ�����
	 */
	@RequestMapping (value="judgeDetailAf.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String judgeDetailAf(Model model,ABCJudgeResultDto jrdto) {
		// �⺻���� �ش� ��ɸ��� ���δ�.
		model.addAttribute("title", "�ɻ��� :: " + MAIN_TITLE);
		
		// �ɻ��� ������ db�� �Է��Ѵ�.
		adminservice.addJudgeResult(jrdto);
		
		// ���εƴٸ� �ش� ������¸� ����
		if(jrdto.getResult() == 1) {
			accountService.accountForLoan(jrdto.getLoanCode());
		}
		
		return "redirect:/judgeManage.do";
	}
	
	/**
	 * ȸ������ �������� �̵��ϴ� �޼���
	 * @param model Ÿ��Ʋ�� ������ ��ü
	 * @return ȸ������ �������� Ÿ�����
	 */
	@RequestMapping(value="memberManage.do", method=RequestMethod.GET)
	public String memeberManage(Model model){
		// Ÿ��Ʋ ����
		model.addAttribute("title", "ȸ������ :: " + MAIN_TITLE);
		
		// ȸ�� ��ü ����Ʈ ���(������ �� ������ ����)
		model.addAttribute("memberList", memberService.getMemberList());
		
		return "memberManage.tiles";
	}
	
	/**
	 * ȸ�� �� �������� �̵��ϴ� �޼���
	 * @param model Ÿ��Ʋ�� ������ ��ü
	 * @param email ȸ���� �̸���
	 * @return ȸ�� �� �������� Ÿ�����
	 */
	@RequestMapping(value="memberDetail.do", method=RequestMethod.GET)
	public String memeberDetail(Model model, String email){
		// Ÿ��Ʋ ����
		model.addAttribute("title", "ȸ�� ������ :: " + MAIN_TITLE);
		
		// ȸ�� ������ ��������
		ABCMemberDto member = memberService.getMember(email);
		
		// ���� ���ϱ�
		member.setYear(""+ABCUtility.getAge(member.getBirth()));
		
		// ����Ʈ �� ���� �����͸� �𵨿� ���
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
	 * ���ڰ��� �������� �̵��ϴ� �޼���
	 * @param model Ÿ��Ʋ�� ������ ��ü
	 * @return ���ڰ��� �������� Ÿ�����
	 */
	@RequestMapping(value="investManage.do", method=RequestMethod.GET)
	public String investManage(Model model){
		// Ÿ��Ʋ ����
		model.addAttribute("title", "���ڰ��� :: " + MAIN_TITLE);
		
		// ���ڰ��� ����Ʈ�� �𵨿� ���
		model.addAttribute("investManageList", adminservice.getInvestManageData());
		
		return "investManage.tiles";
	}
	
	/**
	 * ���� ���� �������� �̵��ϴ� �޼���
	 * @param model Ÿ��Ʋ�� ������ ��ü
	 * @return ���� ���� �������� Ÿ�����
	 */
	@RequestMapping(value="loanManage.do", method=RequestMethod.GET)
	public String loanManage(Model model){
		// Ÿ��Ʋ ����
		model.addAttribute("title", "���� ���� ������:: " + MAIN_TITLE);
		
		logger.info("loanManage.do ȣ�� ����!" + new Date());
		// ������� ���� ȣ���ؼ� ���
		model.addAttribute("loanManage", adminservice.getLoanManage());
		
		return ("loanManage.tiles");
	}
}
