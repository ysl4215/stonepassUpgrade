package com.swempire.web.condition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrgaInsertController {
	
	@GetMapping(value = "/orgainsert")
	public String organizationGET2() throws Exception {

		return "/condition/orgainsert";
	}

}
