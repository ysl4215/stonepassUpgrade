package com.swempire.web.condition.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.service.ConditionService;
import com.swempire.web.condition.service.CurlService;

@Controller
public class ConditionBoardController {

	@Inject
	private ConditionService conditionservice;

	/* 메인화면 */
	@RequestMapping(value = "/condition", method = RequestMethod.GET)
	public String condition(Model model, @RequestParam(required = false, defaultValue = "x") String arr)
			throws Exception {

		model.addAttribute("boardList", conditionservice.getBoardList());

		return "/condition/condition";
	}

	@RequestMapping(value = "/condition", method = RequestMethod.POST)
	public String conditionInsert(@RequestParam(required = false, defaultValue = "1") String orga_name,
			@RequestParam(required = false, defaultValue = "1") String orga_url, ConditionVO conditionvo)
			throws Exception {

		System.out.println(orga_name);

		conditionvo.setOrga_name(orga_name);
		conditionvo.setOrga_url(orga_url);

		conditionservice.insertBoard(conditionvo);

		return "redirect:/condition";
	}

	/* 메인화면 */
	/*
	 * @RequestMapping(value = "/condition", method = RequestMethod.POST) public
	 * String condition2(@RequestParam(required = false, defaultValue = "1") String
	 * orga_name,
	 * 
	 * @RequestParam(required = false, defaultValue = "1") String orga_url,
	 * ConditionVO conditionvo) throws Exception {
	 * 
	 * conditionvo.setOrga_name(orga_name); conditionvo.setOrga_url(orga_url);
	 * 
	 * conditionservice.insertBoard(conditionvo);
	 * 
	 * return "redirect:/condition"; }
	 */

	/* 팝업창 */

	// 컨트롤러에서 반환형 void를 사용하면 Mapping값으로 JSP찾아감
	@GetMapping("/orgainsert")
	public void organizationGET() throws Exception {

		/* logger.info("authorPopGET......."); */

	}
	// 컨트롤러에서 반환형 void를 사용하면 Mapping값으로 JSP찾아감
	/*
	 * @PostMapping("/condition/organization") public void organizationPOST() throws
	 * Exception {
	 * 
	 * logger.info("authorPopGET.......");
	 * 
	 * }
	 */

	// 컨트롤러에서 반환형 void를 사용하면 Mapping값으로 JSP찾아감
	@GetMapping("/condition/content")
	public void contentBoard(@RequestParam(required = false, defaultValue = "1") int bid, ConditionVO conditionvo,
			Model model) throws Exception {

		conditionservice.contentBoard(conditionvo);

		System.out.println(conditionservice.contentBoard(conditionvo));

		model.addAttribute("contentboard", conditionservice.contentBoard(conditionvo));

	}

	@RequestMapping(value = "/conditionModify", method = RequestMethod.POST)
	public String conditionModify(@RequestParam(required = false, defaultValue = "1") String orga_name,
			@RequestParam(required = false, defaultValue = "1") String orga_url,
			@RequestParam(required = false, defaultValue = "1") int bid, ConditionVO conditionvo) throws Exception {

		System.out.println(orga_name + "bbb" + orga_url);

		conditionvo.setBid(bid);
		conditionvo.setOrga_name(orga_name);
		conditionvo.setOrga_url(orga_url);

		conditionservice.modifyBoard(conditionvo);

		return "redirect:/condition";
	}

	@RequestMapping(value = "/conditionDelete", method = RequestMethod.GET)
	public String conditionDelete(@RequestParam(required = false, defaultValue = "1") int bid, ConditionVO conditionvo)
			throws Exception {

		System.out.println(bid);

		conditionservice.deleteBoard(conditionvo);

		return "/condition/condition";
	}
	
	@RequestMapping(value= "/emailInsert", method = RequestMethod.GET)
	public String emailInsert() {
		return "/condition/emailinsert";
	}
	
	@RequestMapping(value= "/emailInsert", method = RequestMethod.POST)
	public String emailInsert2(@RequestParam(required = false, defaultValue = "1") String email,
			@RequestParam(required = false, defaultValue = "1") String name) {
		System.out.println(email + "  "+ name);
		
		return "redirect:/condition";
	}

}