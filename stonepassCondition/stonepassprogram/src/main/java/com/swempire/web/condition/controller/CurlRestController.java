package com.swempire.web.condition.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swempire.web.comm.util.Curl;
import com.swempire.web.comm.util.Scheduler;
import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.EmailPaginationVO;
import com.swempire.web.condition.VO.EmailVO;
import com.swempire.web.condition.VO.ServerCurlVO;
import com.swempire.web.condition.VO.ServiceTestVO;
import com.swempire.web.condition.service.CurlService;
import com.swempire.web.condition.service.EmailService;

@RestController
public class CurlRestController {

	@Inject
	private CurlService curlService;

	@Inject
	private EmailService emailservice;

	String condition;
	
	

	@RequestMapping(value = "/conditionCurlArray", method = RequestMethod.POST)
	public String[] curlArray(@RequestParam(required = false, defaultValue = "null", value = "bidArray") int[] bidArray,
			@RequestParam(required = false, defaultValue = "null", value = "values") String values,
			ConditionVO conditionvo, Model model, EmailVO emailvo, ServiceTestVO servicetestvo,
			EmailPaginationVO pagination
			) throws Exception {

		int checked = Integer.parseInt(values);
		servicetestvo.setChecked(checked);
		servicetestvo.setBidArray(bidArray);

		List<ConditionVO> list = curlService.orgaListSelect(conditionvo, servicetestvo, emailvo,pagination);

		String[] arr = new String[list.size()];

		List<EmailVO> emailList = emailservice.emailListLimitSelect(pagination);

		return curlService.getArr();
	}

	@RequestMapping(value = "/conditionCurl", method = RequestMethod.POST)
	public String curl(@RequestParam("values") String values, @RequestParam("bid") String bid, ConditionVO conditionvo,
			Model model) throws Exception {
		Curl curl = new Curl();
		int intbid = Integer.parseInt(bid);

		int checked = Integer.parseInt(values);

		if (checked == 1) {
			conditionvo.setBid(intbid);

			curlService.orgaSelect(conditionvo);

			String strUrl = curlService.orgaSelect(conditionvo).getOrga_url();

			curl.get(strUrl, null);

			int curlCode = curl.getCurlCode();
			int errorNum = curl.getErrorNum();

			if (curlCode == 200 && errorNum != 0) {
				String condition = "O";
				model.addAttribute("condition", condition);

				return condition;
			} else if (curlCode != 200 || errorNum == 0) {
				String condition = "X";
				model.addAttribute("condition", condition);

				return condition;
			}
		} else if (checked == 0) {
			return "";
		}
		return "";
	}
	
	@RequestMapping(value = "/serverCurl", method = RequestMethod.POST)
	public ArrayList<String> serverCurlArray(ServerCurlVO servercurlvo,Scheduler scheduler) throws Exception {
		
		
		curlService.serverCurlConnection(servercurlvo);
		
		//연결상태불량 기관이름 return
		return scheduler.getErrorOrga();
	}

}
