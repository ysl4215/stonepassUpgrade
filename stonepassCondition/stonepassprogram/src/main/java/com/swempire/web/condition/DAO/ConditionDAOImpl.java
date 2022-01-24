package com.swempire.web.condition.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.Search;


@Repository
public class ConditionDAOImpl implements ConditionDAO {
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<ConditionVO> getBoardList() throws Exception {
		return sqlSession.selectList("com.swempire.web.condition.conditionMapper.getBoardList");
	}
	
	@Override
	public List<ConditionVO> conditionListLimitSelect(Search search) throws Exception {
		return sqlSession.selectList("com.swempire.web.condition.conditionMapper.conditionListLimitSelect", search);
	}

	@Override
	public int conditionListCnt(Search search) throws Exception {
		return sqlSession.selectOne("com.swempire.web.condition.conditionMapper.conditionListCnt",search);
	}

	@Override
	public int insertBoard(ConditionVO conditionvo) throws Exception {
		return sqlSession.insert("com.swempire.web.condition.conditionMapper.insertBoard", conditionvo);
	}
	
	@Override
	public ConditionVO contentBoard(ConditionVO conditionvo) throws Exception {
		return sqlSession.selectOne("com.swempire.web.condition.conditionMapper.contentBoard", conditionvo);
	}

	@Override
	public int modifyBoard(ConditionVO conditionvo) throws Exception {
		return sqlSession.update("com.swempire.web.condition.conditionMapper.modifyBoard", conditionvo);
	}

	@Override
	public void deleteBoard(ConditionVO conditionvo) throws Exception {
		sqlSession.delete("com.swempire.web.condition.conditionMapper.deleteBoard", conditionvo);
	}
}
