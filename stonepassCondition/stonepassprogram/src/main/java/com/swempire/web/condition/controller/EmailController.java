package com.swempire.web.condition.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swempire.web.condition.VO.EmailPaginationVO;
import com.swempire.web.condition.VO.EmailVO;
import com.swempire.web.condition.service.EmailService;

@Controller
public class EmailController {

	@Inject
	EmailService emailservice;
	
	@RequestMapping(value= "/email", method = RequestMethod.GET)
	public String email(Model model, @RequestParam(required = false, defaultValue = "1") int page,
	@RequestParam(required = false, defaultValue = "1") int range, EmailPaginationVO pagination ) throws Exception {
		
		//전체 게시글 개수
		int listCnt = emailservice.emailListCnt();
		
		pagination.pageInfo(page, range, listCnt);
		
		List<EmailVO> emaillist = emailservice.emailListLimitSelect(pagination);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("emaillist", emaillist);
		
		return "/condition/email";
	}
	
	@ResponseBody
	@RequestMapping(value = "/emailInsert", method = RequestMethod.POST)
	public String emailInsert(@RequestParam(required = false, defaultValue = "null") String email,
			@RequestParam(required = false, defaultValue = "null")String name,EmailVO emailvo) throws Exception {
		System.out.println(name+ "   "+email);
		emailvo.setName(name);
		emailvo.setEmail(email);
				
		emailservice.emailInsert(emailvo);
				
		return "redirect:/condition";
	}
	
	@ResponseBody
	@RequestMapping(value = "/emailDelete", method = RequestMethod.POST)
	public String emailDelete(@RequestParam(required = false, defaultValue = "null") int bid,
			EmailVO emailvo) throws Exception {
		
		emailvo.setBid(bid);
		
		emailservice.emailDelete(emailvo);		
		
		return "redirect:/condition";
	}
	
	
}
