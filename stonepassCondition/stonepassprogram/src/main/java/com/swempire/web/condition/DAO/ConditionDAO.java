package com.swempire.web.condition.DAO;

import java.util.List;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.Search;



public interface ConditionDAO {
	
	public List<ConditionVO> getBoardList() throws Exception;
	
	public List<ConditionVO> conditionListLimitSelect(Search search) throws Exception;
	
	public int conditionListCnt(Search search) throws Exception;
	
	public int insertBoard(ConditionVO conditionvo) throws Exception;
	
	public ConditionVO contentBoard(ConditionVO conditionvo) throws Exception;
	
	public int modifyBoard(ConditionVO conditionvo) throws Exception;
	
	public void deleteBoard(ConditionVO conditionvo) throws Exception;
}
