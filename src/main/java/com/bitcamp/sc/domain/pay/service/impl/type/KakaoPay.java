package com.bitcamp.sc.domain.pay.service.impl.type;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import com.bitcamp.sc.domain.order.domain.OrderInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bitcamp.sc.domain.pay.domain.KakaoPayApproval;
import com.bitcamp.sc.domain.pay.domain.KakaoPayReady;

@Service
public class KakaoPay {

	private static final String HOST = "http://localhost:8084/";
//	private static final String HOST = "http://3.35.0.242:8081/";
	
	private KakaoPayReady kakaoPayReady;
	private KakaoPayApproval kakaoPayApproval;
	
	public String kakaoPayReady(OrderInfo orderInfo) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK ef2f8bf297a5c48bad11089fb2ba33f0");
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", Long.toString(orderInfo.getIdx()));
        params.add("partner_user_id", Long.toString(orderInfo.getMemberIdx()));
        params.add("item_name", orderInfo.getCategory());
        params.add("quantity", "1");
        params.add("total_amount", Integer.toString(orderInfo.getPrice()));
        params.add("tax_free_amount", "100");
        params.add("approval_url", HOST + "kakaoPaySuccess?orderIdx=" + orderInfo.getIdx());
        params.add("cancel_url", HOST + "kakaoPayCancel?orderIdx=" + orderInfo.getIdx());
        params.add("fail_url", HOST + "kakaoPaySuccessFail");
		
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
			kakaoPayReady = restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/ready"), body, KakaoPayReady.class);
			
			return kakaoPayReady.getNext_redirect_pc_url();
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "/pay";
	}
	
	public KakaoPayApproval kakaoPayInfo(String pg_token, OrderInfo orderInfo) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK ef2f8bf297a5c48bad11089fb2ba33f0");
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", kakaoPayReady.getTid());
        params.add("partner_order_id", Long.toString(orderInfo.getIdx()));
        params.add("partner_user_id", Long.toString(orderInfo.getMemberIdx()));
        params.add("pg_token", pg_token);
        params.add("total_amount", Integer.toString(orderInfo.getPrice()));
		
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
			kakaoPayApproval = restTemplate.postForObject(new URI("https://kapi.kakao.com/v1/payment/approve"), body, KakaoPayApproval.class);
			
			return kakaoPayApproval;			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        
		return null;
	}
}
