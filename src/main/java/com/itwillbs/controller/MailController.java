package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.service.MailServiceImpl;

// @EnableAsync => 지정된 특정 메서드(@Async 붙어있는!!) 호출시 비동기 방식으로 처리 가능하게 설정 

@Controller
@EnableAsync
public class MailController {

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Inject
	private MailServiceImpl mailService;
	
	//http://localhost:8088/sendMail
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public void sendMailGET() throws Exception {
		logger.debug("sendMailGET() 호출 - 시작");
		
		// 메일 보내기 작업을 기존의 작업과 상관 없이 처리 할 수 있다. 
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// 메일 보내기 -> 서비스로 위임
				try {
//					mailService.sendMail("chan4145@naver.com","이메일 테스트 제목","이메일 테스트 내용");
					//메일 본문(내용) html 형태로 처리
					StringBuffer sb = new StringBuffer();
					sb.append("<html>");
					sb.append("<head></head>");
					sb.append("<body>");
					sb.append("<h1>안녕하세요 아이티윌 입니다.</h1>");
					sb.append("<h2>과정 수강안내 메일입니다.</h2>");
					sb.append("<a href = 'http://www.itwillbs.co.kr'>아이티윌 홈페이지</a>");
					sb.append("<img src='http://c2d2303t1.itwillbs.com/earlybirdProject/resources/upload/fcdb6e39-9e5a-45fa-93c0-320fd3fac2ce_%ED%81%B4%EB%9E%98%EC%8A%A49-2.png'/>");
					sb.append("</body>");
					sb.append("</html>");
					
					mailService.sendMail("chan4145@naver.com","이메일 테스트 2",sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		
		logger.debug("sendMailGET() 호출 - 끝");
		
	}
}
