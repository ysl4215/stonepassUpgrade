package com.swempire.web.condition.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.swempire.web.condition.DAO.EmailDAO;
import com.swempire.web.condition.VO.EmailVO;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Inject
	EmailDAO emaildao;

	@Override
	public int emailInsert(EmailVO emailvo) throws Exception {
		
		return emaildao.emailInsert(emailvo);
	}

	@Override
	public List<EmailVO> emailListSelect() throws Exception {
		
		return emaildao.emailListSelect();
	}

	@Override
	public void emailDelete(EmailVO emailvo) throws Exception {
		emaildao.emailDelete(emailvo);
		
	}

	@Override
	public EmailVO emailErrorOrganameSelect(EmailVO emailvo) throws Exception {
		
		return emaildao.emailErrorOrganameSelect(emailvo);
	}

}