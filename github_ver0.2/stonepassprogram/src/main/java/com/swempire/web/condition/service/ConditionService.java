package com.swempire.web.condition.service;

import java.util.List;

import com.swempire.web.condition.VO.ConditionVO;


public interface ConditionService {

	public List<ConditionVO> getBoardList() throws Exception;
	
	public int insertBoard(ConditionVO conditionvo) throws Exception;

}