package com.bitcamp.sc.tour.service;

import com.bitcamp.sc.member.domain.LoginInfo;
import com.bitcamp.sc.pay.domain.PayInfo;
import com.bitcamp.sc.tour.domain.ChangeTourDto;

public interface MailService {
	
	// 환불  알림 메일
	void refundMail(PayInfo payInfo,LoginInfo loginInfo);
	// 예약 변경 안내 메일 
	void changeMail(ChangeTourDto changeDto, LoginInfo loginInfo);
	// 예약 완료 메일 
	void completeMail(PayInfo payInfo,LoginInfo member);
}