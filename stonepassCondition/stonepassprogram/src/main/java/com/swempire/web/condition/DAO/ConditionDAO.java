package com.swempire.web.condition.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.swempire.web.condition.VO.ConditionPaginationVO;
import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.EmailPaginationVO;
import com.swempire.web.condition.VO.EmailVO;



public interface ConditionDAO {
	
	public List<ConditionVO> getBoardList() throws Exception;
	
	public List<ConditionVO> conditionListLimitSelect(ConditionPaginationVO pagination) throws Exception;
	
	public int conditionListCnt() throws Exception;
	
	public int insertBoard(ConditionVO conditionvo) throws Exception;
	
	public ConditionVO contentBoard(ConditionVO conditionvo) throws Exception;
	
	public int modifyBoard(ConditionVO conditionvo) throws Exception;
	
	public void deleteBoard(ConditionVO conditionvo) throws Exception;
	/*
	 * public ConditionVO getBoardContent(int bid) throws Exception;
	 * 
	 * 
	 * 
	 * public int insertBoard(ConditionVO boardVO) throws Exception;
	 * 
	 * 
	 * 
	 * public int updateBoard(ConditionVO boardVO) throws Exception;
	 * 
	 * 
	 * 
	 * public int deleteBoard(int bid) throws Exception;
	 * 
	 * 
	 * 
	 * public int updateViewCnt(int bid) throws Exception;
	 */



	
	
}
