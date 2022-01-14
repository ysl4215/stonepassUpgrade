package com.swempire.web.condition.service;

import java.util.List;

import com.swempire.web.condition.VO.ConditionPaginationVO;
import com.swempire.web.condition.VO.ConditionVO;


public interface ConditionService {

	public List<ConditionVO> getBoardList() throws Exception;
	
	public List<ConditionVO> conditionListLimitSelect(ConditionPaginationVO pagination) throws Exception;
	public int conditionListCnt() throws Exception; 
	
	public int insertBoard(ConditionVO conditionvo) throws Exception;
	
	public ConditionVO contentBoard(ConditionVO conditionvo) throws Exception; 
	
	public int modifyBoard(ConditionVO conditionvo) throws Exception;
	
	public void deleteBoard(ConditionVO conditionvo) throws Exception;

}
