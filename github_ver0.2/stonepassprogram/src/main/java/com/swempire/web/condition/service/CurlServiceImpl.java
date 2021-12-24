package com.swempire.web.condition.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.swempire.web.condition.Curl;
import com.swempire.web.condition.Curl2;
import com.swempire.web.condition.DAO.CurlDAO;
import com.swempire.web.condition.VO.ConditionVO;

@Service
public class CurlServiceImpl implements CurlService {

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

		return curldao.orgaSelect(conditionvo);
	}

	@Override
	public List<ConditionVO> oragListSelect(ConditionVO conditionvo) throws Exception {
		
		return curldao.orgaListSelect(conditionvo);
	}

}