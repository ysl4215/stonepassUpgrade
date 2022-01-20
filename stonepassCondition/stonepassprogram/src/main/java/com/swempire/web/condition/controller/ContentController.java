package com.swempire.web.condition.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.service.ConditionService;

@Controller
public class ContentController {
	
	@Inject
	private ConditionService conditionservice;
	
	// 컨트롤러에서 반환형 void를 사용하면 Mapping값으로 JSP찾아감
	@GetMapping("/condition/content")
	public void contentBoard(@RequestParam(required = false, defaultValue = "1") int bid, ConditionVO conditionvo,
			Model model) throws Exception {
	
		conditionservice.contentBoard(conditionvo);

		model.addAttribute("contentboard", conditionservice.contentBoard(conditionvo));
	}
	
	@RequestMapping(value = "/condition/conditionModify", method = RequestMethod.POST)
	public String conditionModify(@RequestParam(required = false, defaultValue = "1") String orga_name,
			@RequestParam(required = false, defaultValue = "1") String orga_url,
			@RequestParam(required = false, defaultValue = "1") int bid, ConditionVO conditionvo) throws Exception {
		System.out.println(orga_url);
		System.out.println(orga_name);
		conditionvo.setBid(bid);
		conditionvo.setOrga_name(orga_name);
		conditionvo.setOrga_url(orga_url);

		conditionservice.modifyBoard(conditionvo);

		return "redirect:/condition";
	}
	
	@ResponseBody
	@RequestMapping(value = "/condition/conditionDelete", method = RequestMethod.POST)
	public String conditionDelete(@RequestParam(required = false, defaultValue = "1") int bid, ConditionVO conditionvo)
			throws Exception {

		conditionservice.deleteBoard(conditionvo);

		return "success";
	}
}
