package com.swempire.web.condition.controller;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swempire.web.condition.Curl2;
import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.service.CurlService;

@RestController
public class RestConditionController_back {

	@Inject
	private CurlService curlService;
	
	
	Curl2 curl = new Curl2();

	@RequestMapping(value = "/conditionCurl11", method = RequestMethod.POST)
	public String curl(@RequestParam("arr") String arr, @RequestParam("bid") String bid, ConditionVO conditionvo,Model model)
			throws Exception {

		
		int intbid = Integer.parseInt(bid);

		int a = Integer.parseInt(arr);

		if (a == 1) {
			conditionvo.setBid(intbid);
			
			curlService.orgaSelect(conditionvo);
			  
			String strUrl = curlService.orgaSelect(conditionvo).getOrga_url();
			
			curl.get(strUrl, null);	
			
			
				System.out.println(curl.get2());
				
				int curlCode = curl.get2();
				int errorNum = curl.get3();
				
				System.out.println(errorNum+"gggg");
				
				if(curlCode==200 && errorNum !=0) {
					System.out.println("연결상태 양호");
					
					String condition = "O";
					
					return condition;
					
					
				}else if (curlCode != 200 || errorNum==0) {
					System.out.println("연결상태 불량");
					
					String condition = "X";
					
					return condition;
				}
			 
			//curlService.curlShoot();

		} 

		return "-";

	}

}
