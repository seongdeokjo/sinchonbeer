package com.bitcamp.sc.web.tour;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitcamp.sc.domain.login.LoginInfo;
import com.bitcamp.sc.domain.tour.service.TourAuthPhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitcamp.sc.domain.tour.service.AvailablePeopleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TourRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final AvailablePeopleService availableService;	
	private final TourAuthPhoneNumberService authNumberService;

	@GetMapping("/tour/count")
	public int getCount(@RequestParam("mid") String date) {
		logger.info("받아온 날짜 : "+date);
		return availableService.selectCount(date);
	}

	
	// db 휴대전화번호와 일치여부 
	@GetMapping("/verifyMyPhone")
	public String verifyMyPhone(@RequestParam("phone") String ph,HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginInfo login = (LoginInfo)session.getAttribute("loginInfo");
		String result ="";
		if(login.getPhone().equals(ph)) {
			result = "Y";
			logger.info("휴대전화 번호 일치");
		}else {
			result = "N";
			logger.info("휴대전화 번호 불일치");
		}
			
		return result;
	}

	// 휴대전화 인증
	@GetMapping("/sendMessage")
	public String getPhoneNumber(@RequestParam("phone") String ph) {
		
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}

		logger.info("수신자 번호 : " + ph);
		logger.info("인증번호 : " + numStr);
//		authNumberService.authNumberByPhone(ph, numStr);

		return numStr;

	}

}
