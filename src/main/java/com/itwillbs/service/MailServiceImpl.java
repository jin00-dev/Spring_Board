package com.itwillbs.service;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service(value = "mailService")
public class MailServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Inject
	private JavaMailSender mailSender;
	
	@Async // 해당 메서드를 비동기 방식으로 처리할 것!! 
	public void sendMail(String to, String subject, String content) throws Exception {
		logger.debug("메일보내기 시작 ");
		
		MimeMessage msg = mailSender.createMimeMessage();
		// 보낼 데이터에 마임 타입을 설정 할 수 있게 
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true,"UTF-8");
		//msgHelper.setCc(cc); 참조 
//		msgHelper.setFrom(from); 보내는 사람 - 설정 불가능... 악용소지 높아서..
		msgHelper.setSubject(subject); //제목
		msgHelper.setTo(to); // 받는사람
		msgHelper.setText(content,true); // 내용 
		
		//메일 전송 
		mailSender.send(msg);
		
		logger.debug("메일보내기 끝 ");
	}
	
	
}//MailServiceImpl
