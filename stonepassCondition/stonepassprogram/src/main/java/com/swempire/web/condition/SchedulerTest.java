//package com.swempire.web.condition;
//
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.stereotype.Component;
//
//import com.swempire.web.comm.util.SendMailUtil;
//import com.swempire.web.condition.VO.ConditionVO;
//import com.swempire.web.condition.VO.EmailVO;
//import com.swempire.web.condition.service.ConditionService;
//import com.swempire.web.condition.service.EmailService;
//
//@Component
//public class SchedulerTest {
//
//	@Inject
//	ConditionService conditionservice;
//
//	@Inject
//	private EmailService emailservice;
//
//	private ThreadPoolTaskScheduler scheduler;
//
//	
//	EmailVO emailvo = new EmailVO();
//	SendMailUtil smu = new SendMailUtil();
//
//	@Scheduled(fixedDelay = 5000)
//	public void autoUpdate() throws Exception {
//
//		List<ConditionVO> list = conditionservice.getBoardList();
//
//		System.out.println(conditionservice.getBoardList());
//		List<EmailVO> emailList = emailservice.emailListSelect();
//		Curl curl = new Curl();
//		for (int i = 0; i < list.size(); i++) {
//
//			String url = conditionservice.getBoardList().get(i).getOrga_url();
//			curl.get(url, null);
//
//			int curlCode = curl.getCurlCode();
//			int errorNum = curl.getErrorNum();
//
//			if (curlCode == 200 && errorNum != 0) {
//				System.out.println("연결상태 정상");
//			} else if (curlCode != 200 || errorNum == 0) {
//
//				emailvo.setBid(list.get(i).getBid());
//				emailservice.emailErrorOrganameSelect(emailvo);
//
//				String[] errorOrgaName = { emailservice.emailErrorOrganameSelect(emailvo).getOrga_name() };
//
//				// 에러발생한 이메일주소 Recipient set
//				for (int j = 0; j < emailList.size(); j++) {
//					smu.setRecipient(emailList.get(j).getEmail());
//
//					// 연결상태 불량인 기관이름을 title에 추가(SendMailUtil클래스에 title값 저장)
//					for (int k = 0; k < errorOrgaName.length; k++) {
//						smu.setTitle(errorOrgaName[k]);
//						// email보내기
//						smu.send();
//						System.out.println("연결상태 불량");
//
//					}
//				}
//
//			}
//		}
//	}
//
//}
