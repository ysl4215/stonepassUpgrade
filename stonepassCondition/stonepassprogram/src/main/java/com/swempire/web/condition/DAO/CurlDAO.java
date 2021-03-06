package com.swempire.web.condition.DAO;

import java.util.List;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.ServerCurlVO;

public interface CurlDAO {

	public void curlShoot() throws Exception;

	public ConditionVO orgaSelect(ConditionVO conditionvo) throws Exception;
	
	public List<ConditionVO> orgaListSelect(ConditionVO conditionvo) throws Exception;
	
	public int serverCurlConnection(ServerCurlVO servercurlvo) throws Exception;
	
	public ServerCurlVO serverCurlConnectionYN() throws Exception;

}
