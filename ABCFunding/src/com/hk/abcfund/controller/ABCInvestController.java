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
 * ���ڿ� ���õ� ��Ʈ�ѷ�
 * @author 9age
 *
 */
@Controller
public class ABCInvestController {
	/** �⺻ ���� */
	public static String MAIN_TITLE = "ABC Funding";
	
	/** ������� ���� */
	@Autowired
	private ABCLoanService loanService;
	
	/** ���ڰ��� ���� */
	@Autowired
	private ABCInvestService investService;
	
	/** ���°��� ���� */
	@Autowired
	private ABCAccountService accountService;
	
	/**
	 * �����ϱ� �������� �̵��ϴ� �޼���
	 * @param model ������ ������ ��ü
	 * @return �����ϱ� �������� Ÿ�����
	 */
	@RequestMapping(value="invest.do", method=RequestMethod.GET)
	public String invest(Model model) {
		model.addAttribute("title", "�����ϱ� :: "+MAIN_TITLE);
		
		// �ɻ翡�� ���ε� ���⸮��Ʈ ��������
		List<ABCLoanDto> list = loanService.getLoanList();
		
		// �ݵ��Ⱓ�� ���� �����ǰ ����
		int[] delArray = new int[list.size()];
		Arrays.fill(delArray, -1);	// ��� �迭��Ҹ� -1�� �ʱ�ȭ
		for(int idx = 0; idx < list.size(); idx++){
			ABCLoanDto dto = list.get(idx);
			if(ABCUtility.isExpired(dto.getRequestDate(), dto.getExpiryDate())) {
				loanService.loanCancel(dto.getLoanCode());
				delArray[idx] = idx;
			}
		}
		
		// ����Ʈ ����
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
	 * ���� ��ǰ�� ������ �� �ش� ���������� �̵��ϴ� �޼���
	 * @param model ���� ����
	 * @param dto �����ڵ� �����Ͱ� �ִ� ���� DTO
	 * @param request ���������� ������ ��ü
	 * @return ���� ���������� Ÿ����� 
	 */
	@RequestMapping(value="investDetail.do", method=RequestMethod.GET)
	public String investDetail(Model model, HttpServletRequest request ,ABCLoanDto dto) {
		model.addAttribute("title", "���� ������ :: "+MAIN_TITLE);
		
		/* ������ �����͸� ���� ����Ʈ ���� */
		List<Object> list = new ArrayList<Object>();
		
		// �� ���� ������ ��û
		investService.getInvestDetail(list, dto.getLoanCode());
		
		// ��Ƶ� ��ü ������
		ABCLoanDto loan = (ABCLoanDto)list.get(0);
		ABCMemberDto personal = (ABCMemberDto)list.get(1);
		ABCJudgeResultDto judge = (ABCJudgeResultDto)list.get(2);
				
		// ���� ���ϱ�
		int age = ABCUtility.getAge(personal.getBirth());
		
		// ���� ���θ� Ȯ��
		ABCMemberDto login = (ABCMemberDto)request.getSession().getAttribute("login");
		boolean isInvested = investService.isInvested(login.getEmail(), loan.getLoanCode());
		
		// �𵨿� ���
		model.addAttribute("personal", personal);
		model.addAttribute("loan", loan);
		model.addAttribute("judge", judge);
		model.addAttribute("age", age);
		model.addAttribute("isInvested", isInvested);
		
		return "investDetail.tiles";
	}
	
	/**
	 * ���ڽ�û ȭ������ �̵��ϴ� �޼���
	 * @param model ���� ����
	 * @param request ������ �ҷ��� ��ü
	 * @param loanCode �����ڵ�
	 * @return ���ڽ�û �������� Ÿ�����
	 */
	@RequestMapping(value="doInvest.do", method=RequestMethod.GET)
	public String doInvest(Model model, HttpServletRequest request, int loanCode) {
		model.addAttribute("title", "���ڽ�û :: "+MAIN_TITLE);
		
		// �̸��Ϸ� ���µ����� ��������
		ABCMemberDto member = (ABCMemberDto)request.getSession().getAttribute("login");
		ABCAccountDto account = accountService.getAccount(member.getEmail());
		
		/* ���ڰ��ɱݾ� ��� */
		ABCLoanDto loan = loanService.getLoan(loanCode);
		int balance = account.getBalance();
		int investable = 0;
		
		// ��ġ���� ���� ���
		if(balance > 0) {
			// �ִ� ���ڱݾ�
			int max = (int)(loan.getLoanMoney() * 0.2);
			
			// ���� ���ڰ��� �ݾ�
			int diff = loan.getLoanMoney() - loan.getCurrentMoney();
			
			// ���� ���ڰ��� �ݾ� Ȯ��
			investable = (diff < max) ? diff : max;
			
			// �ִ� ���ڱݾ� Ȯ��
			if(balance < investable)
				investable = balance;
		}
		
		// �𵨿� ���
		model.addAttribute("account", account);
		model.addAttribute("loan", loan);
		model.addAttribute("investable", investable);
		
		return "doInvest.tiles";
	}
	
	/**
	 * ���ڽ�û�Ϸ� ȭ������ �̵��ϴ� �޼���
	 * @param model ���� ����
	 * @param request ������ �ҷ��� ��ü
	 * @param loanCode �����ڵ�
	 * @param investMoney �����û��
	 * @return ���ڿϷ� �������� Ÿ�����
	 */
	@Transactional
	@RequestMapping(value="investSuc.do", method=RequestMethod.POST)
	public String investSuc(Model model, HttpServletRequest request, 
			int loanCode, String title, int investMoney) {
		model.addAttribute("title", "���ڿϷ� :: "+MAIN_TITLE);
		
		// ���ǿ��� ȸ�� ������ ��������
		ABCMemberDto member = (ABCMemberDto)request.getSession().getAttribute("login");
		
		// ���ڱ��� ���� ������ �Է¹޾����Ƿ� 1���� �����ش�.
		investMoney *= 10000;
		
		// ���� ��û
		investService.investRequest(member.getEmail(), loanCode, investMoney);
		
		// �ݵ��Ϸ� Ȯ��
		investService.checkComplete(loanCode);
		
		// �𵨿� ���
		model.addAttribute("title", title);
		model.addAttribute("investMoney", investMoney);
		
		return "investSuc.tiles";
	}
	
}
