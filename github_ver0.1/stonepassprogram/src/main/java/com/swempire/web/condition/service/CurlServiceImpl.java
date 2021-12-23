package com.swempire.web.condition.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.swempire.web.condition.Curl;
import com.swempire.web.condition.Curl2;
import com.swempire.web.condition.DAO.CurlDAO;
import com.swempire.web.condition.VO.ConditionVO;


@Service
public class CurlServiceImpl implements CurlService{

	@Inject
	CurlDAO curldao;
	
	Curl2 curl = new Curl2();

	
	@Override
	public void curlShoot() throws Exception {
		
		
		System.out.println("shoot");
		Curl curl = new Curl();
		
		String[] headers = null;
		
		curl.get("https://login.daegu.ac.kr/stonepass/sp/v1/fido/facets", headers);
		
		
	}

	@Override
	public ConditionVO orgaSelect(ConditionVO conditionvo) throws Exception {
		
		String strUrl = curldao.orgaSelect(conditionvo).getOrga_url();
		
		
		
		curl.get(strUrl, null);	
		
		
		
			System.out.println(curl.get2());
			
			int curlCode = curl.get2();
			int errorNum = curl.get3();
			
			System.out.println(errorNum+"gggg");
			
			if(curlCode==200 && errorNum !=0) {
				System.out.println("연결상태 양호");
				
				
			}else if (curlCode != 200 || errorNum==0) {
				System.out.println("연결상태 불량");
			}
		
		
		return curldao.orgaSelect(conditionvo);
	}

}