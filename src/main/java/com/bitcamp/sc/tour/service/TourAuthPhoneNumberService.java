package com.bitcamp.sc.tour.service;

import java.util.HashMap;

//import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import net.nurigo.java_sdk.api.Message;
//import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class TourAuthPhoneNumberService {

//	public void authNumberByPhone(String ph,String certifyCode) {
//		final String  api_key = "NCSHXS0WSAB8HQY2";
//		final String api_secret = "UOWIGLBEVMQGZSJL2Y4DPFOC6E6V4INO";
//
//		Message coolsms = new Message(api_key, api_secret);
//
//		// 4 params(to, from, type, text) are mandatory. must be filled
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("to", ph);
//		params.put("from", "01055401949");
//		params.put("type", "SMS");
//		params.put("text", "[#신촌맥주 본인인증] 인증번호 ["+certifyCode+"]를 입력해주세요. ");
//		params.put("app_version", "test app 1.2"); // application name and version
//
//		try {
//			JSONObject obj = (JSONObject) coolsms.send(params);
//
//			logger.info("json obj : "+obj);
//		} catch (CoolsmsException e) {
//			logger.info("message : "+e.getMessage());
//			logger.info("e.getCode : "+e.getCode());
//		}
//
//
//	}
//
//
}
