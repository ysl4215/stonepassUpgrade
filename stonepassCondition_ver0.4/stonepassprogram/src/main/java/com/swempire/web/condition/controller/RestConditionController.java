package com.swempire.web.condition.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swempire.web.condition.Curl2;
import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.service.ConditionService;
import com.swempire.web.condition.service.CurlService;

@RestController
public class RestConditionController {

	@Inject
	private CurlService curlService;

	@Inject
	private ConditionService conditionservice;

	String condition;
	Curl2 curl = new Curl2();

	@RequestMapping(value = "/conditionCurl", method = RequestMethod.POST)
	public String curl(@RequestParam("values") String values, @RequestParam("bid") String bid, ConditionVO conditionvo,
			Model model) throws Exception {

		int intbid = Integer.parseInt(bid);

		int checked = Integer.parseInt(values);

		if (checked == 1) {
			conditionvo.setBid(intbid);

			curlService.orgaSelect(conditionvo);

			String strUrl = curlService.orgaSelect(conditionvo).getOrga_url();

			curl.get(strUrl, null);

			System.out.println(curl.getCurlCode());

			int curlCode = curl.getCurlCode();
			int errorNum = curl.getErrorNum();

			System.out.println(errorNum + "gggg");

			if (curlCode == 200 && errorNum != 0) {
				System.out.println("연결상태 양호");

				String condition = "O";

				model.addAttribute("condition", condition);

				return condition;

			} else if (curlCode != 200 || errorNum == 0) {
				System.out.println("연결상태 불량");

				String condition = "X";

				model.addAttribute("condition", condition);

				return condition;
			}

			// curlService.curlShoot();

		}
		return "";

	}

	@RequestMapping(value = "/conditionCurlArray", method = RequestMethod.POST)
	public String[] curlArray(@RequestParam(required = false, defaultValue = "null", value = "bidArray") int[] bidArray,
			@RequestParam(required = false, defaultValue = "null", value = "values") String values,
			ConditionVO conditionvo, Model model) throws Exception {

		int checked = Integer.parseInt(values);

		System.out.println(checked + "1111");
		List<ConditionVO> list = curlService.orgaListSelect(conditionvo);
		String[] arr = new String[list.size()];
		if (checked == 1) {

			conditionvo.setBidArray(bidArray);

			for (int i = 0; i < list.size(); i++) {
				list.get(i).getOrga_url();
				curl.get(list.get(i).getOrga_url(), null);
				System.out.println(curl.getCurlCode());

				int curlCode = curl.getCurlCode();
				int errorNum = curl.getErrorNum();

				if (curlCode == 200 && errorNum != 0) {

					this.condition = "O";

				} else if (curlCode != 200 || errorNum == 0) {

					this.condition = "X";

				}

				arr[i] = condition;

			}

			return arr;
		}

		return arr;

	}

	/*
	 * @RequestMapping(value = "/conditionCurlArrayOff", method =
	 * RequestMethod.POST) public String[] curlArray2(@RequestParam(required =
	 * false, defaultValue = "null", value="bidArray" ) int[]
	 * bidArray, @RequestParam(required = false,defaultValue = "null",
	 * value="values") String values, ConditionVO conditionvo,Model model) throws
	 * Exception { System.out.println(bidArray); System.out.println(111); int
	 * checked = Integer.parseInt(values);
	 * 
	 * System.out.println(checked+"1111"); List<ConditionVO> list =
	 * curlService.orgaListSelect(conditionvo); String[] arr = new
	 * String[list.size()];
	 * 
	 * //System.out.println(Arrays.toString(bidArray));
	 * 
	 * return arr;
	 * 
	 * }
	 */

	

}