package com.swempire.web.condition.DAO;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.swempire.web.condition.VO.ConditionVO;

@Repository
public class CurlDAOImpl implements CurlDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void curlShoot() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConditionVO orgaSelect(ConditionVO conditionvo) throws Exception {
			
		return sqlSession.selectOne("com.swempire.web.condition.conditionMapper.orgaSelect", conditionvo);
	}

}