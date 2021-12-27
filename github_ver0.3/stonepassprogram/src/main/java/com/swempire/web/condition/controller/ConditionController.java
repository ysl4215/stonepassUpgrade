package com.swempire.web.condition.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.service.ConditionService;
import com.swempire.web.condition.service.CurlService;

@Controller
public class ConditionController {

	@Inject
	private ConditionService conditionservice;

	/* 메인화면 */
	@RequestMapping(value = "/condition", method = RequestMethod.GET)
	public String condition(Model model, @RequestParam(required = false, defaultValue = "x") String arr)
			throws Exception {

		model.addAttribute("boardList", conditionservice.getBoardList());

		return "/condition/condition";
	}

	/* 메인화면 */
	@RequestMapping(value = "/condition", method = RequestMethod.POST)
	public String condition2(@RequestParam(required = false, defaultValue = "1") String orga_name,
			@RequestParam(required = false, defaultValue = "1") String orga_url, ConditionVO conditionvo)
			throws Exception {

		conditionvo.setOrga_name(orga_name);
		conditionvo.setOrga_url(orga_url);

		conditionservice.insertBoard(conditionvo);

		return "redirect:/condition";
	}

	/* 팝업창 */
	@GetMapping("/condition/organization")
	public void organizationGET() throws Exception {

		/* logger.info("authorPopGET......."); */

	}
	
	@GetMapping("/condition/content")
	public void orgaModify(@RequestParam(required = false, defaultValue = "1") int bid,ConditionVO conditionvo) throws Exception {
System.out.println(bid);
		
	conditionservice.contentBoard(conditionvo);
	
	

	}



}