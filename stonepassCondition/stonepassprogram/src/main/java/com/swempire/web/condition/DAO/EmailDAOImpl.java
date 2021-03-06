package com.swempire.web.condition.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.swempire.web.condition.VO.EmailPaginationVO;
import com.swempire.web.condition.VO.EmailVO;

@Repository
public class EmailDAOImpl implements EmailDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<EmailVO> emailListSelect() throws Exception {	
		return sqlSession.selectList("com.swempire.web.condition.emailMapper.emailListSelect");
	}
	
	@Override
	public List<EmailVO> emailListLimitSelect(EmailPaginationVO pagination) throws Exception {
		return sqlSession.selectList("com.swempire.web.condition.emailMapper.emailListLimitSelect", pagination);
	}
	
	@Override
	public int emailListCnt() throws Exception {
		return sqlSession.selectOne("com.swempire.web.condition.emailMapper.emailListCnt");
	}

	@Override
	public int emailInsert(EmailVO emailvo) throws Exception {
		return sqlSession.insert("com.swempire.web.condition.emailMapper.emailInsert", emailvo);
	}

	@Override
	public void emailDelete(EmailVO emailvo) throws Exception {
		sqlSession.delete("com.swempire.web.condition.emailMapper.emailDelete", emailvo);
	}

	@Override
	public EmailVO emailErrorOrganameSelect(EmailVO emailvo) throws Exception {
		return sqlSession.selectOne("com.swempire.web.condition.emailMapper.emailErrorOrganameSelect", emailvo);
	}

	
}
