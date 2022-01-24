package com.swempire.web.condition.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.Search;
import com.swempire.web.condition.service.ConditionService;

@Controller
public class ConditionBoardController {

	@Inject
	private ConditionService conditionservice;

	/* 메인화면 */
	@RequestMapping(value = "/condition", method = RequestMethod.GET)
	public String condition(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, Model model,
			@RequestParam(required = false, defaultValue = "testTitle") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) throws Exception {
		
		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);

		// 전체 게시글 개수를 얻어와 listCnt에 저장
		int listCnt = conditionservice.conditionListCnt(search);
System.out.println("keyword == "+keyword);
System.out.println("search == "+search.toString());
		// 검색
		search.pageInfo(page, range, listCnt);

		// 페이징
		model.addAttribute("pagination", search);
		// 게시글 화면 출력
		model.addAttribute("conditionlist", conditionservice.conditionListLimitSelect(search));
		System.out.println(listCnt);
		
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
}
