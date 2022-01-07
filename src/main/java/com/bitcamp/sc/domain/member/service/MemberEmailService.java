package com.bitcamp.sc.domain.member.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.member.repository.MemberDao;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class MemberEmailService {

	private  JavaMailSender sender;
	private MemberDao memberDao;

	// 메일 전송
	@Async
	@Transactional
	public void sendMail(String userEmail) {
		String authNum = randomNum();
		log.info("번호 = {}",authNum);

		// 인증번호를 db에 저장하기.
		memberDao.updateCode(authNum, userEmail);

		MimeMessage message = sender.createMimeMessage();
		// MimeMessage에는 메일 내용이 들어가게 됨. 제목, 내용, 발신, 수신, 첨부
		try {

			// 메일 제목
			message.setSubject("신촌맥주에서 인증번호를 보내드립니다.", "UTF-8");

			// 메일 내용 컨텐츠 html
			String html = "<h1>인증번호 : " + authNum + "</h1>";

			// message에 내용 적용
			message.setText(html, "utf-8", "html");

			// from 설정 (보내는 사람 설정. 구글에서는 이 설정을 막아 놓았음)
			message.setFrom(new InternetAddress("seongdeok217@gmail.com"));

			// to 설정 (받는 사람 설정.)
			message.addRecipient(RecipientType.TO, new InternetAddress(userEmail, userEmail + " 님", "UTF-8"));
			// 괄호 내용 (보낼 이메일 주소 String address, String personal, String charset)
			// 메일 발송
			sender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	//랜덤 숫자로 된 인증번호 만들기
	private String randomNum() {
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		return numStr;
	}
}