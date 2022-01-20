package com.swempire.web.condition.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swempire.web.condition.VO.ConditionPaginationVO;
import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.service.ConditionService;

@Controller
public class ConditionBoardController {

	@Inject
	private ConditionService conditionservice;

	/* 메인화면 */
	@RequestMapping(value = "/condition", method = RequestMethod.GET)
	public String condition(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, ConditionPaginationVO pagination,Model model, @RequestParam(required = false, defaultValue = "x") String arr)
			throws Exception {
		//전체 게시글 개수
		int listCnt = conditionservice.conditionListCnt();
		
		pagination.pageInfo(page, range, listCnt);
		
		List<ConditionVO> conditionlist = conditionservice.conditionListLimitSelect(pagination);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("conditionlist", conditionlist);
		
		return "/condition/condition";
	}

	@RequestMapping(value = "/condition", method = RequestMethod.POST)
	public String conditionInsert(@RequestParam(required = false, defaultValue = "1") String orga_name,
			@RequestParam(required = false, defaultValue = "1") String orga_url, ConditionVO conditionvo)
			throws Exception {

		conditionvo.setOrga_name(orga_name);
		conditionvo.setOrga_url(orga_url);

		conditionservice.insertBoard(conditionvo);

		return "redirect:/condition";
	}
	/*
	 * @ResponseBody
	 * @RequestMapping(value = "/selectOption", method = RequestMethod.POST) public
	 * String listCntSelect(ConditionPaginationVO conpaging) throws Exception {
	 * 
	 * System.out.println(conpaging.getListSize());
	 * 
	 * return ""; }
	 */
}
