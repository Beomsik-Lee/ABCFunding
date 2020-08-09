package com.hk.abcfund.earth.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hk.abcfund.earth.model.dto.ECodes;
import com.hk.abcfund.earth.model.dto.EarthDto;
import com.hk.abcfund.earth.model.dto.EarthFile;
import com.hk.abcfund.earth.model.dto.EarthPwDto;
import com.hk.abcfund.earth.model.service.EarthService;

/**
 * @author
 *
 */
@Controller
public class EYEOpeningController {
	@Autowired
	EarthService service;

	/** �⺻ ���� */
	public static String MAIN_TITLE = "EYEOpening";

	/**
	 * �Ŀ���� ��û�޴� �޼���
	 */
	@RequestMapping(value = "addEarth.do", method = RequestMethod.POST)
	@Transactional
	public String addEarth(HttpServletRequest request, EarthDto dto, Model model) throws Exception {
		// �⺻���� �ش� ��ɸ��� ���δ�.
		model.addAttribute("title", "ȸ������ :: " + MAIN_TITLE);
		// ȸ�� ���
		String targetno = request.getParameter("targetno").trim().replace(",", "");
		String targetsum = request.getParameter("targetsum").trim().replace(",", "");
		System.out.println("add��ǥ�ο���" + targetno + "add��ǥ�ݾ���" + targetsum);
		dto.setTargetno(targetno);
		dto.setTargetsum(targetsum);
		service.addEarth(dto);
		
		
//		�� ������ �����Ұ�  MultiparthttpServletRequest��� ���־� ������ request�ʹ� �ٸ��� ������ �������� ���ؼ� ����ȯ�� ���ذž� 
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request; // casting ĳ��������ȯ
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); // request-> mHSR ��ȯ -> filename��������->iterator�� �ֱ� 
	
		MultipartFile multipartFile = null; //multipartfile �̶�� ��ü ���� iterartor ��� ������������  
		while (iterator.hasNext()) { //itertor �� ���� list�� ���������� 
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());//����Ʈ�� �������̸��� getFile�� �װ� multipartFIle���־� 
			//���� ���������̸� whitehouse.jpg
			String fname = multipartFile.getOriginalFilename();
			
			if (multipartFile.isEmpty() == false) { //multipartFile�� ����ִٸ� �Ʒ��� ����
				EarthFile Efile = new EarthFile(); // ���ο� EarthFIle ��ü ���� 
				int totalRecordCount = service.getMaxEarth(); // ��ü ���ڵ��߿� ������ add�� ������ݾ� �׷��� add ����̴µ� ���� ���忡���߸�
															// ���󿡼� add�� �̴µ� ������ ����ȵǼ� ���ڵ���¿��������� 
															//add Earth �ϸ� 1, 2, 3, 10 , 11, 12 �̷������� �´ܸ��� ���ʴ�� �ȴð� 
															// seq�� ���ʴ�� �ȴö����վ� �׷��� add �ϰ��� maxEarth�ϸ� ��� ��������� seq�����
					
				System.out.println(totalRecordCount); //�̰� �׳� ����غ�������Ű�
				Efile.setEarthno(totalRecordCount); //EarthFile�̶�� ���� earthno, applyname, applypath, applysavename, ---- �̷������� ���մµ� 
													//���⼱ 4������ �ص� �����ǰ� �����ڸ� �س� �׷��� �༮���� ������ setEarthno,setAPplyname ������ �ڹ� ��ü��
													// ä��־��� �״����� 

				// ��û �����̸�
				String fnameto = (totalRecordCount + fname);
				System.out.println("fnameto : " + fnameto);
				Efile.setApplyname(fnameto);

				System.out.println("Applyname: " + fname);
				// ��û ���� ���� ���
				String SaveFilePath = ("./eyeuploadfile/" + totalRecordCount + fname);
				Efile.setApplypath(SaveFilePath);
				System.out.println("SaveFilePath: " + SaveFilePath);
				// ��û ���� ���� ���� ���
				String ServerFilePath = ("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
						+ totalRecordCount + fname);
				Efile.setApplysavename(ServerFilePath);
				System.out.println("ServerFilePath: " + ServerFilePath);

				service.addEarthFile(Efile); //���⼭ addEarthFile�� ���ؼ� ��ü���������� ���������� 
				File file = new File("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
						+ totalRecordCount + fname); 
				multipartFile.transferTo(file); //transferTO(file) �ϸ� �Ƹ� ������ �ö�  
			}
		}
		return "contact.tiles";
	}

	/**
	 * ���� ������ �̵� �޼��� ���� ������ �ϰ� �ٷ� ������������ �̵�
	 * 
	 * @param model
	 * @return ���� �������� Ÿ�����
	 * @throws Exception
	 */
	@RequestMapping(value = "sponsor.do", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", MAIN_TITLE);

		return "eyeopening.tiles";
	}

	@RequestMapping(value = "aboutearth.do", method = RequestMethod.GET)
	public String aboutearth(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "ABOUTEARTH");

		return "aboutearth.tiles";
	}

	@RequestMapping(value = "startearth.do", method = RequestMethod.GET)
	public String startearth(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "STARTEARTH");

		return "startearth.tiles";
	}

	@RequestMapping(value = "endearth.do", method = RequestMethod.GET)
	public String endearth(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "ENDEARTH");

		return "endearth.tiles";
	}

	@RequestMapping(value = "contact.do", method = RequestMethod.GET)
	public String contact(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "CONTACT");

		return "contact.tiles";
	}

	@RequestMapping(value = "contactdetail.do", method = RequestMethod.GET)
	public String contactdetail(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "CONTACTDETAIL");

		return "contactdetail.tiles";
	}

	@RequestMapping(value = "earthingdetail.do", method = RequestMethod.GET)
	public String earthingdetail(Model model) throws Exception {
		// ���� ����
		model.addAttribute("title", "EARTHINGDETAIL");

		return "earthingdetail.tiles";
	}

	/**
	 * ���� �� �� ã�� �޼���
	 */
	@RequestMapping(value = "earthajax.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody // JSON ���
	public Map<String, List<HashMap>> earthajax(HttpServletRequest request, Model model) {
		String name = request.getParameter("searchname");
		// System.out.println(name);
		// List<EarthDto> mylist=service.findEarth(name);
		List<HashMap> mylist = service.findEarthMap("%" + name + "%");
		Map<String, List<HashMap>> map = new HashMap<String, List<HashMap>>();
		map.put("mylist", mylist);
		return map;
	}//

	/**
	 * ���� �� �� ��й�ȣ �Է�
	 */
	@RequestMapping(value = "myearthpw.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myearthpw(HttpServletRequest request, int earthno, Model model) throws Exception {

		EarthPwDto edto = service.getEarthPw(earthno);
		// System.out.println("����dto"+edto.getEarthno() + ", " + edto.getPw());
		// ���� ����
		model.addAttribute("title", "MYEARTHPW");
		model.addAttribute("earth", edto);

		String message = request.getParameter("message");
		if (message != null) {
			model.addAttribute("message", message);
		}

		return "myearthpw.tiles";
	}

	/**
	 * ���� �� �� ��й�ȣ Ȯ��
	 */
	@RequestMapping(value = "myearthpwconfirm.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myearthpwconfirm(HttpServletRequest request, int earthno, Model model) throws Exception {
		int pw = Integer.parseInt(request.getParameter("pw"));
		// System.out.println("pw"+pw+"earth"+earthno);
		String retView = "";
		int cnt = service.myearthpwconfirm(pw, earthno);
		if (cnt == 0) {
			// �н����� Ʋ�����
			model.addAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			model.addAttribute("earthno", earthno);
			retView = "redirect:myearthpw.do";
		} else {
			model.addAttribute("earthnum", earthno);
			retView = "redirect:myearthdetail.do";
		}

		// System.out.println("earthnum"+earthno);
		// System.out.println("earthnum"+earthnum);
		// ���� ����
		model.addAttribute("title", "MYEARTHPWCONFIRM");

		return retView;
	}

	/**
	 * ���� �� �� ������ �޼���
	 */
	@RequestMapping(value = "myearthdetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myearthdetail(HttpServletRequest request, Model model) {
		int earthno = Integer.parseInt(request.getParameter("earthnum"));
		// System.out.println("�����Ϸ� �Ѿ����?"+earthno);
		EarthDto edto = service.getEarth(earthno);
		ECodes code = service.getEcode(earthno);
		String name = code.getName();
		int codeno = code.getCode();
		EarthFile efile = service.getEarthFile(earthno);

		model.addAttribute("title", "myearthdetail");
		model.addAttribute("efile", efile);
		model.addAttribute("earth", edto);
		model.addAttribute("code", code);
		model.addAttribute("codeno", codeno);
		model.addAttribute("name", name);

		return "myearthdetail.tiles";
	}//

	/**
	 * ���� �� �� ���� �޼���
	 */
	@RequestMapping(value = "updateMyearth.do", method = RequestMethod.POST)
	@Transactional
	public String updateMyearth(HttpServletRequest request, EarthDto dto, Model model) throws Exception {
		String targetno = request.getParameter("targetno").trim().replace(",", "");
		String targetsum = request.getParameter("targetsum").trim().replace(",", "");
		// System.out.println("��ǥ�ο���" + targetno + "��ǥ�ݾ���" + targetsum);
		dto.setTargetno(targetno);
		dto.setTargetsum(targetsum);
		service.updateMyearth(dto);

		// ���ϼ���
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		EarthFile Efile = new EarthFile();

		MultipartFile multipartFile = null;
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());

			String fname = multipartFile.getOriginalFilename();

			if (multipartFile.isEmpty() == false) {

				Efile.setEarthno(dto.getEarthno());

				Efile = service.getEarthFile(Efile.getEarthno());

				if (Efile != null) {
					// ���� ������ �ִ°��

					service.myearthDeleteFile(Efile.getEarthno());
					// ��û �����̸�
					String fnameto = (Efile.getEarthno() + fname);
					Efile.setApplyname(fnameto);
					// ��û ���� ���� ���
					String SaveFilePath = ("./eyeuploadfile/" + Efile.getEarthno() + fname);
					Efile.setApplypath(SaveFilePath);
					// ��û ���� ���� ���� ���
					String ServerFilePath = ("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
							+ Efile.getEarthno() + fname);
					Efile.setApplysavename(ServerFilePath);

					service.addEarthFile(Efile);
					File file = new File(
							"C:/workspace/ABCFunding/WebContent/eyeuploadfile/" + Efile.getEarthno() + fname);
					multipartFile.transferTo(file);
				}

				// ���� ������ ���� ���
				// ��û �����̸�
				else {

					EarthFile sfile = new EarthFile();
					sfile.setEarthno(dto.getEarthno());
					String fnameto = (dto.getEarthno() + fname);

					sfile.setApplyname(fnameto);
					// ��û ���� ���� ���
					String SaveFilePath = ("./eyeuploadfile/" + dto.getEarthno() + fname);
					sfile.setApplypath(SaveFilePath);
					// ��û ���� ���� ���� ���
					
					String ServerFilePath = ("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
							+ dto.getEarthno() + fname);
					sfile.setApplysavename(ServerFilePath);

					service.addEarthFile(sfile);
					File file = new File(
							"C:/workspace/ABCFunding/WebContent/eyeuploadfile/" + dto.getEarthno() + fname);
					multipartFile.transferTo(file);
				}
			}
		}

		model.addAttribute("title", "UPDATEMYEARTH");

		return "contact.tiles";
	}
	/**
	 * ���� �� �� �̹��� ����
	 */
	@RequestMapping(value = "myearthfiledelete.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String myearthfiledelete(int earthno, Model model) throws Exception {
			
			service.myearthDeleteFile(earthno);
			// ���� ����
			model.addAttribute("title", "MYEARTHFILEDELETE");
			model.addAttribute("earthnum", earthno);
			
			return "redirect:myearthdetail.do";
		}
	
	/**
	 * ���� �� �� ����
	 */
	@RequestMapping(value = "myearthdelete.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String myearthdelete(int earthno, Model model) throws Exception {
			
			service.myearthDeleteFile(earthno);
			service.myearthDelete(earthno);
			// ���� ����
			model.addAttribute("title", "MYEARTHDELETE");
			return "contact.tiles";
		}
///////----------------------------------------------------------------------------//

	
	
	
	
	
	
}