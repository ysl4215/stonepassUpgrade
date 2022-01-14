package com.swempire.web.condition.service;

import java.util.List;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.EmailPaginationVO;
import com.swempire.web.condition.VO.EmailVO;
import com.swempire.web.condition.VO.ServerCurlVO;
import com.swempire.web.condition.VO.ServiceTestVO;

public interface CurlService {
	
	
	
	public ConditionVO orgaSelect(ConditionVO conditionvo) throws Exception;
	
	public List<ConditionVO> orgaListSelect(ConditionVO conditionvo, ServiceTestVO servicetestvo,
			EmailVO emailvo,EmailPaginationVO pagination) throws Exception;

	public String[] getArr();
	
	public int serverCurlConnection(ServerCurlVO servercurlvo)throws Exception;
	public ServerCurlVO serverCurlConnectionYN()throws Exception;
	
}
