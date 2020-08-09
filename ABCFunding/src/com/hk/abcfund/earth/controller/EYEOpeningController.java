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

	/** 기본 제목 */
	public static String MAIN_TITLE = "EYEOpening";

	/**
	 * 후원등록 요청받는 메서드
	 */
	@RequestMapping(value = "addEarth.do", method = RequestMethod.POST)
	@Transactional
	public String addEarth(HttpServletRequest request, EarthDto dto, Model model) throws Exception {
		// 기본제목에 해당 기능명을 붙인다.
		model.addAttribute("title", "회원가입 :: " + MAIN_TITLE);
		// 회원 등록
		String targetno = request.getParameter("targetno").trim().replace(",", "");
		String targetsum = request.getParameter("targetsum").trim().replace(",", "");
		System.out.println("add목표인원은" + targetno + "add목표금액은" + targetsum);
		dto.setTargetno(targetno);
		dto.setTargetsum(targetsum);
		service.addEarth(dto);
		
		
//		자 설명을 시작할게  MultiparthttpServletRequest라는 게있어 기존의 request와는 다르게 파일을 가져오기 위해서 형변환을 해준거야 
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request; // casting 캐스팅형변환
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); // request-> mHSR 변환 -> filename가져오기->iterator에 넣기 
	
		MultipartFile multipartFile = null; //multipartfile 이라는 객체 생성 iterartor 라고 이해하지말거  
		while (iterator.hasNext()) { //itertor 에 다음 list가 있을떄까지 
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());//리스트의 다음번이름을 getFile해 그걸 multipartFIle에넣어 
			//파일 오리지널이름 whitehouse.jpg
			String fname = multipartFile.getOriginalFilename();
			
			if (multipartFile.isEmpty() == false) { //multipartFile이 비어있다면 아래를 진행
				EarthFile Efile = new EarthFile(); // 새로운 EarthFIle 객체 생성 
				int totalRecordCount = service.getMaxEarth(); // 전체 레코드중에 위에서 add가 시행되잖아 그래서 add 시행됫는데 파일 저장에러뜨면
															// 디비상에서 add는 됫는데 파일은 저장안되서 레코드출력에러가나서 
															//add Earth 하면 1, 2, 3, 10 , 11, 12 이런식으로 는단말야 차례대로 안늘고 
															// seq가 차례대로 안늘때가잇어 그래서 add 하고나서 maxEarth하면 방금 만들어진거 seq갖고옴
					
				System.out.println(totalRecordCount); //이건 그냥 출력해볼려고만든거고
				Efile.setEarthno(totalRecordCount); //EarthFile이라는 놈은 earthno, applyname, applypath, applysavename, ---- 이런식으로 쭉잇는데 
													//여기선 4가지만 해도 생성되게 생성자를 해놈 그래서 녀석들의 정보를 setEarthno,setAPplyname 등으로 자바 객체에
													// 채어넣어줘 그다음에 

				// 신청 파일이름
				String fnameto = (totalRecordCount + fname);
				System.out.println("fnameto : " + fnameto);
				Efile.setApplyname(fnameto);

				System.out.println("Applyname: " + fname);
				// 신청 파일 저장 경로
				String SaveFilePath = ("./eyeuploadfile/" + totalRecordCount + fname);
				Efile.setApplypath(SaveFilePath);
				System.out.println("SaveFilePath: " + SaveFilePath);
				// 신청 파일 서버 저장 경로
				String ServerFilePath = ("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
						+ totalRecordCount + fname);
				Efile.setApplysavename(ServerFilePath);
				System.out.println("ServerFilePath: " + ServerFilePath);

				service.addEarthFile(Efile); //여기서 addEarthFile을 통해서 객체를가지고가서 디비에저장시켜 
				File file = new File("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
						+ totalRecordCount + fname); 
				multipartFile.transferTo(file); //transferTO(file) 하면 아마 파일이 올라감  
			}
		}
		return "contact.tiles";
	}

	/**
	 * 메인 페이지 이동 메서드 제목 설정만 하고 바로 메인페이지로 이동
	 * 
	 * @param model
	 * @return 메인 페이지의 타일즈명
	 * @throws Exception
	 */
	@RequestMapping(value = "sponsor.do", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", MAIN_TITLE);

		return "eyeopening.tiles";
	}

	@RequestMapping(value = "aboutearth.do", method = RequestMethod.GET)
	public String aboutearth(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", "ABOUTEARTH");

		return "aboutearth.tiles";
	}

	@RequestMapping(value = "startearth.do", method = RequestMethod.GET)
	public String startearth(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", "STARTEARTH");

		return "startearth.tiles";
	}

	@RequestMapping(value = "endearth.do", method = RequestMethod.GET)
	public String endearth(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", "ENDEARTH");

		return "endearth.tiles";
	}

	@RequestMapping(value = "contact.do", method = RequestMethod.GET)
	public String contact(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", "CONTACT");

		return "contact.tiles";
	}

	@RequestMapping(value = "contactdetail.do", method = RequestMethod.GET)
	public String contactdetail(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", "CONTACTDETAIL");

		return "contactdetail.tiles";
	}

	@RequestMapping(value = "earthingdetail.do", method = RequestMethod.GET)
	public String earthingdetail(Model model) throws Exception {
		// 제목 설정
		model.addAttribute("title", "EARTHINGDETAIL");

		return "earthingdetail.tiles";
	}

	/**
	 * 내가 쓴 글 찾기 메서드
	 */
	@RequestMapping(value = "earthajax.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody // JSON 사용
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
	 * 내가 쓴 글 비밀번호 입력
	 */
	@RequestMapping(value = "myearthpw.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myearthpw(HttpServletRequest request, int earthno, Model model) throws Exception {

		EarthPwDto edto = service.getEarthPw(earthno);
		// System.out.println("작은dto"+edto.getEarthno() + ", " + edto.getPw());
		// 제목 설정
		model.addAttribute("title", "MYEARTHPW");
		model.addAttribute("earth", edto);

		String message = request.getParameter("message");
		if (message != null) {
			model.addAttribute("message", message);
		}

		return "myearthpw.tiles";
	}

	/**
	 * 내가 쓴 글 비밀번호 확인
	 */
	@RequestMapping(value = "myearthpwconfirm.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myearthpwconfirm(HttpServletRequest request, int earthno, Model model) throws Exception {
		int pw = Integer.parseInt(request.getParameter("pw"));
		// System.out.println("pw"+pw+"earth"+earthno);
		String retView = "";
		int cnt = service.myearthpwconfirm(pw, earthno);
		if (cnt == 0) {
			// 패스워드 틀린경우
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("earthno", earthno);
			retView = "redirect:myearthpw.do";
		} else {
			model.addAttribute("earthnum", earthno);
			retView = "redirect:myearthdetail.do";
		}

		// System.out.println("earthnum"+earthno);
		// System.out.println("earthnum"+earthnum);
		// 제목 설정
		model.addAttribute("title", "MYEARTHPWCONFIRM");

		return retView;
	}

	/**
	 * 내가 쓴 글 디테일 메서드
	 */
	@RequestMapping(value = "myearthdetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String myearthdetail(HttpServletRequest request, Model model) {
		int earthno = Integer.parseInt(request.getParameter("earthnum"));
		// System.out.println("디테일로 넘어오나?"+earthno);
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
	 * 내가 쓴 글 수정 메서드
	 */
	@RequestMapping(value = "updateMyearth.do", method = RequestMethod.POST)
	@Transactional
	public String updateMyearth(HttpServletRequest request, EarthDto dto, Model model) throws Exception {
		String targetno = request.getParameter("targetno").trim().replace(",", "");
		String targetsum = request.getParameter("targetsum").trim().replace(",", "");
		// System.out.println("목표인원은" + targetno + "목표금액은" + targetsum);
		dto.setTargetno(targetno);
		dto.setTargetsum(targetsum);
		service.updateMyearth(dto);

		// 파일수정
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
					// 기존 파일이 있는경우

					service.myearthDeleteFile(Efile.getEarthno());
					// 신청 파일이름
					String fnameto = (Efile.getEarthno() + fname);
					Efile.setApplyname(fnameto);
					// 신청 파일 저장 경로
					String SaveFilePath = ("./eyeuploadfile/" + Efile.getEarthno() + fname);
					Efile.setApplypath(SaveFilePath);
					// 신청 파일 서버 저장 경로
					String ServerFilePath = ("C:/workspace/ABCFunding/WebContent/eyeuploadfile/"
							+ Efile.getEarthno() + fname);
					Efile.setApplysavename(ServerFilePath);

					service.addEarthFile(Efile);
					File file = new File(
							"C:/workspace/ABCFunding/WebContent/eyeuploadfile/" + Efile.getEarthno() + fname);
					multipartFile.transferTo(file);
				}

				// 기존 파일이 없는 경우
				// 신청 파일이름
				else {

					EarthFile sfile = new EarthFile();
					sfile.setEarthno(dto.getEarthno());
					String fnameto = (dto.getEarthno() + fname);

					sfile.setApplyname(fnameto);
					// 신청 파일 저장 경로
					String SaveFilePath = ("./eyeuploadfile/" + dto.getEarthno() + fname);
					sfile.setApplypath(SaveFilePath);
					// 신청 파일 서버 저장 경로
					
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
	 * 내가 쓴 글 이미지 삭제
	 */
	@RequestMapping(value = "myearthfiledelete.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String myearthfiledelete(int earthno, Model model) throws Exception {
			
			service.myearthDeleteFile(earthno);
			// 제목 설정
			model.addAttribute("title", "MYEARTHFILEDELETE");
			model.addAttribute("earthnum", earthno);
			
			return "redirect:myearthdetail.do";
		}
	
	/**
	 * 내가 쓴 글 삭제
	 */
	@RequestMapping(value = "myearthdelete.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String myearthdelete(int earthno, Model model) throws Exception {
			
			service.myearthDeleteFile(earthno);
			service.myearthDelete(earthno);
			// 제목 설정
			model.addAttribute("title", "MYEARTHDELETE");
			return "contact.tiles";
		}
///////----------------------------------------------------------------------------//

	
	
	
	
	
	
}