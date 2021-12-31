package com.bitcamp.sc.tour.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.pay.domain.PayInfo;
import com.bitcamp.sc.tour.domain.ChangeTourDto;
import com.bitcamp.sc.tour.service.MailService;


@Slf4j
@Component
@AllArgsConstructor
public class MailServiceImpl implements MailService {
	
	private  JavaMailSender mailSender;
	
	private  SpringTemplateEngine templateEngine;
	
	// 보내는 사람 이메일
	private static final String FROM_EMAIL ="watersun326@gmail.com";
	
	// 예약 변경
	@Override
	public void changeMail(ChangeTourDto changeDto, LoginInfo loginInfo)  {
		
			// 메일 서비스로 보내기
	
			log.info("변경 메일 서비스 진입");
			MimeMessage message = mailSender.createMimeMessage();
			// 파일 첨부 가능 
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(message, true);
			 
			// 메일 제목 
			helper.setSubject("신촌맥주 양조장 투어 예약이 변경되었습니다.");
			// 받는 사람
			helper.setTo(loginInfo.getEmail());
			// 보내는 사람 
			helper.setFrom(FROM_EMAIL);
			// 내용 
			Context context = new Context();
			context.setVariable("name",loginInfo.getName());
			context.setVariable("phone",loginInfo.getPhone());
			context.setVariable("people", changeDto.getTourPeople());
			context.setVariable("newDate",changeDto.getNewDate());
			
			// html 경로 가져오기
			String html = templateEngine.process("mail/changeMail", context);
			
			// 가져온 메시지 내용저장
			helper.setText(html, true);
			}catch (MessagingException e) {
				
				e.printStackTrace();
			}
		
			// 메일 전송
	        mailSender.send(message);
	        log.info("변경 메일 전송 완료");
	}

	// 환불 
	@Override
	public void refundMail(PayInfo payInfo,LoginInfo loginInfo) {
		log.info("취소 메일 서비스 진입");
		
		MimeMessage message = mailSender.createMimeMessage();
		// 파일 첨부 가능 
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		 
		// 메일 제목 
		helper.setSubject("신촌맥주 양조장 투어 예약이 취소 되었습니다.");
		// 받는 사람
		helper.setTo(loginInfo.getEmail());
		// 보내는 사람 
		helper.setFrom(FROM_EMAIL);
		// 내용 
		Context context = new Context();
		context.setVariable("name",loginInfo.getName());
		context.setVariable("price",payInfo.getPrice());
		context.setVariable("pway", payInfo.getWay());
		
		// html 경로 가져오기
		String html = templateEngine.process("mail/refundMail", context);
		
		// 가져온 메시지 내용저장
		helper.setText(html, true);
		}catch (MessagingException e) {
			
			e.printStackTrace();
		}
	
		// 메일 전송
        mailSender.send(message);
        log.info("취소 메일 전송 완료");
	}


	// 예약 완료
	@Override
	@Async("mailExecutor")
	public void completeMail(PayInfo payInfo,LoginInfo member) {
		log.info("결제 완료 메일 서비스 진입");
		
		MimeMessage message = mailSender.createMimeMessage();
		// 파일 첨부 가능 
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		 
		// 메일 제목 
		helper.setSubject("신촌맥주 양조장 투어 예약이 완료되었습니다.");
		// 받는 사람
		helper.setTo(member.getEmail());
		// 보내는 사람 
		helper.setFrom(FROM_EMAIL);
		// 내용 
		Context context = new Context();
		context.setVariable("name",member.getName());
		context.setVariable("phone",member.getPhone());
		context.setVariable("pway", payInfo.getWay());
		context.setVariable("pprice", payInfo.getPrice());
		context.setVariable("pdate", payInfo.getDate());
		
		// html 경로 가져오기
		String html = templateEngine.process("mail/completeMail", context);
		
		// 가져온 메시지 내용저장
		helper.setText(html, true);
		}catch (MessagingException e) {
			
			e.printStackTrace();
		}
	
		// 메일 전송
        mailSender.send(message);
        log.info("결제  메일 전송 완료");
	}
}