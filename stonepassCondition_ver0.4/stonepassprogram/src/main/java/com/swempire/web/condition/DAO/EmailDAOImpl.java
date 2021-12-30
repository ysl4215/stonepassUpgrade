package com.swempire.web.condition.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.swempire.web.condition.VO.EmailVO;

@Repository
public class EmailDAOImpl implements EmailDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	
	@Override
	public int emailInsert(EmailVO emailvo) throws Exception {

		return sqlSession.insert("com.swempire.web.condition.emailMapper.emailInsert", emailvo);
	}


	@Override
	public List<EmailVO> listSelectEmail() throws Exception {
		
		return sqlSession.selectList("com.swempire.web.condition.emailMapper.listSelectEmail");
	}


	@Override
	public void emailDelete(EmailVO emailvo) throws Exception {
		sqlSession.delete("com.swempire.web.condition.emailMapper.emailDelete", emailvo);
		
	}

}