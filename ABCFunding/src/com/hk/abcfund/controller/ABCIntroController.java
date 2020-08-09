/**
 * 
 */
package com.hk.abcfund.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 9age
 *
 */
@Controller
public class ABCIntroController {
	
	/* default title */
	public static String MAIN_TITLE = "ABC Funding";
	
	private static final Logger logger = LoggerFactory
			.getLogger(ABCIntroController.class);

	@RequestMapping(value = "intro.do", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		// set title
		model.addAttribute("title", MAIN_TITLE);
		
		logger.info("intro.do Success!" + new Date());
		return "intro.tiles";
	}

}