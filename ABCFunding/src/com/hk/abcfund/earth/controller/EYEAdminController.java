package com.hk.abcfund.earth.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.apache.catalina.util.StringParser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.abcfund.earth.model.dto.ApplyBackUp;
import com.hk.abcfund.earth.model.dto.ECodes;
import com.hk.abcfund.earth.model.dto.EarthDto;
import com.hk.abcfund.earth.model.dto.EarthFile;
import com.hk.abcfund.earth.model.dto.SponsorDto;
import com.hk.abcfund.earth.model.service.EarthService;
import com.hk.abcfund.earth.util.EyeEarthParam;
@Controller
public class EYEAdminController {
	@Autowired
	EarthService service;
	
	/** �⺻ ���� */
	public static String MAIN_TITLE = "EYEOpening";
	
	@RequestMapping(value = "sponsorManage.do", method = RequestMethod.GET)
	public String contactdetail(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "sponsorManage");
		
		return "sponsorManage.tiles";
	}
	@RequestMapping(value = "sponsorlist.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String sponsorlist(EyeEarthParam param,Model model) throws Exception {
		
		int sn=param.getPageNumber();
		int start=(sn)*param.getRecordCountPerPage()+1;
		int end=(sn+1)*param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		
		int totalRecordCount=
				service.getSponsorTotalCount();
		List<EarthDto> slist=
				service.getSponsorLists(param);
		model.addAttribute("title", "sponsorlist");
		model.addAttribute("sponsorlist", slist);//�ٲ�
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		return "sponsorlist.tiles";//�ٲ�
	}//
	
	@RequestMapping(value = "sponsorapply.do", method = RequestMethod.GET)
	public String sponsorapply(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "sponsorapply");
		
		return "sponsorapply.tiles";
	}
	@RequestMapping(value = "sponsorenddate.do", method = RequestMethod.GET)
	public String sponsorenddate(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "sponsorenddate");
		
		return "sponsorenddate.tiles";
	}
	
	@RequestMapping(value = "sponsoring.do", method = RequestMethod.GET)
	public String sponsoring(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "sponsoring");
		
		return "sponsoring.tiles";
	}
	@RequestMapping(value = "sponsorend.do", method = RequestMethod.GET)
	public String sponsorend(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "sponsorend");
		
		return "sponsorend.tiles";
	}

	@RequestMapping(value = "sponsordetail.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String sponsordetail(
			int earthno, Model model) {
		EarthDto edto = service.getEarth(earthno);
		ECodes code = service.getEcode(earthno);
		String name = code.getName();
		int codeno = code.getCode();
		EarthFile efile = service.getEarthFile(earthno);
		
		model.addAttribute("title", "sponsordetail");
		model.addAttribute("efile", efile);
		model.addAttribute("earth", edto);
		model.addAttribute("code",code);
		model.addAttribute("codeno",codeno);
		model.addAttribute("name",name);
		
		return "sponsordetail.tiles";
	}//
		
	
	@RequestMapping (value="updateCode.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateCode(HttpServletRequest request,Model model) {
		
		int code = Integer.parseInt(request.getParameter("code"));
		int earthno = Integer.parseInt(request.getParameter("earthno"));
		EarthDto edto = service.getEarth(earthno);
		int vcode = edto.getCode();
		edto.setCode(code);
		service.updateEcode(edto);
		ApplyBackUp a = service.getApplyBackup(earthno);
		
		if(code == 2 &&  a == null ) {
			edto.setTargetno(edto.getTargetno().trim().replace(",","").replace(" ",""));
			edto.setTargetsum(edto.getTargetsum().trim().replace(",","").replace(" ",""));
			service.applybackup(edto);
			 System.out.println("����Ϸ�");
		}
		else if (code == 2  && a != null) {
			service.deletebackup(earthno);
			edto.setTargetno(edto.getTargetno().trim().replace(",",""));
			edto.setTargetsum(edto.getTargetsum().trim().replace(",",""));
			service.applybackup(edto);
		}
		System.out.println(code);
		System.out.println(earthno);
		return "redirect:/sponsorlist.do";
	}


///////----------------------------------------------------------------------------//
///////----------------------------------------------------------------------------//
///////----------------------------------------------------------------------------//
///////----------------------------------------------------------------------------//
///////----------------------------------------------------------------------------//


	/**
	 * ���� �� �� ã�� �޼���
	 */
	@RequestMapping(value = "sponsorajax.do", 
	         method = {RequestMethod.POST,RequestMethod.GET})
	   @ResponseBody //JSON ��� 
	   public Map<String, List<SponsorDto>> sponsorajax(HttpServletRequest request,
			   Model model) {
			String name = request.getParameter("sponsorSearch");
			int check = Integer.parseInt(request.getParameter("sponsorCheck"));
			
			ECodes code = new ECodes();
			code.setCode(check);
			code.setName("%"+name+"%");
			System.out.println("string check : " + check);
			System.out.println("string name : " + name);
			
			List<SponsorDto> mylist = service.findSponsor(code);
			Map<String, List<SponsorDto>> map = new 
						HashMap<String, List<SponsorDto>>();
			for(int idx=0; idx<mylist.size(); idx++){
			System.out.println(mylist.toString());
			}
			map.put("mylist", mylist);
	      return map;
	   }//
	
	@RequestMapping(value = "earthapply.do",  method = {RequestMethod.POST,RequestMethod.GET})
	public String earthapply(Model model,EyeEarthParam param,SponsorDto dto,HttpServletRequest request) throws Exception{
		// ���� ����
		model.addAttribute("title", "earthapply");
		System.out.println("getSponsorid : " + dto.getSponsorid());
		System.out.println("getSponsorno : " + dto.getSponsorno());
		int sn=param.getPageNumber();
		int start=(sn)*param.getRecordCountPerPage()+1;
		int end=(sn+1)*param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		
		int totalRecordCount=
				service.getSponsorApplyTotalCount();
		List<EarthDto> slist = null;
		if(totalRecordCount >= 1 ){
			slist = service.getSponsorApplyLists(param);
			model.addAttribute("sponsorlist", slist);//�ٲ�
		}
	
		System.out.println(slist.get(0).getName());
		model.addAttribute("title", "sponsorlist");
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		model.addAttribute("sponname", dto.getSponsorid());
		model.addAttribute("sponno", dto.getSponsorno());
		return "earthapply.tiles";
		
	}
	
	@RequestMapping(value = "earthapply2.do",  method = {RequestMethod.POST,RequestMethod.GET})
	public String earthapply2(Model model,SponsorDto dto,HttpServletRequest request) throws Exception {
		// ���� ����
		model.addAttribute("title", "earthapply");
		int earthno = Integer.parseInt(request.getParameter("earthno"));
		System.out.println("earthno :" + earthno);
		System.out.println("getSponsorid : " + dto.getSponsorid());
		System.out.println("getSponsorno : " + dto.getSponsorno());
		EarthDto edto = service.getEarth(earthno);
		model.addAttribute("sponname", dto.getSponsorid());
		model.addAttribute("sponno", dto.getSponsorno());
		
		return "earthapply2.tiles";
		
	}
	
}