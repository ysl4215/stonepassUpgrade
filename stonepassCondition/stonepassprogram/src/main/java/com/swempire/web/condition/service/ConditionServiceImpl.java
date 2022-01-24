package com.swempire.web.condition.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.swempire.web.condition.DAO.ConditionDAO;
import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.Search;

@Service
public class ConditionServiceImpl implements ConditionService{
	@Inject
	private ConditionDAO conditionDAO;

	public List<ConditionVO> getBoardList() throws Exception {
		return conditionDAO.getBoardList();
	}
	
	
	@Override
	public List<ConditionVO> conditionListLimitSelect(Search search) throws Exception {
		return conditionDAO.conditionListLimitSelect(search);
	}

	@Override
	public int conditionListCnt(Search search) throws Exception {
		return conditionDAO.conditionListCnt(search);
	}

	@Override
	public int insertBoard(ConditionVO conditionvo) throws Exception {
		return conditionDAO.insertBoard(conditionvo);
	}
	
	@Override
	public ConditionVO contentBoard(ConditionVO conditionvo) throws Exception {
		return conditionDAO.contentBoard(conditionvo);
	}

	@Override
	public int modifyBoard(ConditionVO conditionvo) throws Exception {
		return conditionDAO.modifyBoard(conditionvo);
	}

	@Override
	public void deleteBoard(ConditionVO conditionvo) throws Exception {
		conditionDAO.deleteBoard(conditionvo);
	}



	

}
