package com.swempire.web.comm.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.swempire.web.condition.VO.ConditionVO;
import com.swempire.web.condition.VO.EmailVO;
import com.swempire.web.condition.service.ConditionService;
import com.swempire.web.condition.service.CurlService;
import com.swempire.web.condition.service.EmailService;

@Component
public class Scheduler {

	@Inject
	ConditionService conditionservice;

	@Inject
	private EmailService emailservice;

	@Inject
	private CurlService curlService;
	
	private static ArrayList<String> errorOrga;
	
	public ArrayList<String> getErrorOrga() {
		return errorOrga;
	}

	int stop;

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	private ThreadPoolTaskScheduler scheduler;
	private String cron = "*/10 * * * * *";

	
	public void startScheduler() {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		// scheduler setting
		scheduler.schedule(getRunnable(), getTrigger());
	}

	public void changeCronSet(String cron) {
		this.cron = cron;
	}

	public void stopScheduler() {
		scheduler.shutdown();
	}

	private Runnable getRunnable() {
		Curl curl = new Curl();
		EmailVO emailvo = new EmailVO();
		
		// do something
		return () -> {

			try {	String connection = curlService.serverCurlConnectionYN().getConnection();
			if (connection.equals("n")) {
			} else {
				try {
					List<ConditionVO> orgaList = conditionservice.getBoardList();

					// System.out.println(conditionservice.getBoardList());
					List<EmailVO> emailList = emailservice.emailListSelect();
					
					ArrayList<String> array = new ArrayList<String>();
					for (int i = 0; i < orgaList.size(); i++) {

						String url = conditionservice.getBoardList().get(i).getOrga_url();
						curl.get(url, null);

						int curlCode = curl.getCurlCode();
						int errorNum = curl.getErrorNum();

						if (curlCode == 200 && errorNum != 0) {
							
							this.stop = 10;
						} else if (curlCode != 200 || errorNum == 0) {
							
							// 에러발생한 기관(bid) Select
							emailvo.setBid(orgaList.get(i).getBid());
							emailvo.setOrga_name(orgaList.get(i).getOrga_name());
							
							//연결상태불량 기관 타이틀명 저장
							String errorOrgaName = emailservice.emailErrorOrganameSelect(emailvo).getOrga_name();
								
							System.out.println(errorOrgaName);
							
							// 에러발생한 이메일주소 Recipient set
							for (int j = 0; j < emailList.size(); j++) {
								SendMailUtil.setRecipient(emailList.get(j).getEmail());

								// 연결상태 불량인 기관이름을 title에 추가(SendMailUtil클래스에 title값 저장)
									SendMailUtil.setTitle(errorOrgaName);
									
									// email보내기
									//SendMailUtil.send();
							}
							
							
								/*
								 * //연결상태불량 기관 타이틀명 저장 String[] errorOrgaName = {
								 * emailservice.emailErrorOrganameSelect(emailvo).getOrga_name() };
								 * 
								 * System.out.println(errorOrgaName.length);
								 * 
								 * // 에러발생한 이메일주소 Recipient set for (int j = 0; j < emailList.size(); j++) {
								 * SendMailUtil.setRecipient(emailList.get(j).getEmail());
								 * 
								 * // 연결상태 불량인 기관이름을 title에 추가(SendMailUtil클래스에 title값 저장) for (int k = 0; k <
								 * errorOrgaName.length; k++) {
								 * 
								 * SendMailUtil.setTitle(errorOrgaName[k]);
								 * 
								 * // email보내기 //SendMailUtil.send(); } }
								 */
							
							
							this.stop = 0;
							array.add(orgaList.get(i).getOrga_name());
						}
					}
					Scheduler.errorOrga = array;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		};
	}

	private Trigger getTrigger() {
		return new CronTrigger(cron);
	}

	@PostConstruct
	public void init() {
		startScheduler();
	}

	@PreDestroy
	public void destroy() {
		stopScheduler();
	}
}
