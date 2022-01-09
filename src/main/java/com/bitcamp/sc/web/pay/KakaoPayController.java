package com.bitcamp.sc.web.pay;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.service.MemberService;
import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.order.service.OrderService;
import com.bitcamp.sc.domain.pay.service.impl.PayServiceImpl;
import com.bitcamp.sc.domain.pay.service.impl.type.KakaoPay;
import com.bitcamp.sc.web.shop.dto.ShopDto;
import com.bitcamp.sc.domain.tour.domain.TourDto;
import com.bitcamp.sc.domain.tour.service.MailService;
import com.bitcamp.sc.domain.tour.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.sc.domain.pay.domain.KakaoPayApproval;
import com.bitcamp.sc.domain.pay.domain.PayInfo;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@AllArgsConstructor
@Controller
public class KakaoPayController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private KakaoPay kakaoPay;
	private PayServiceImpl payService;
	private OrderService orderService;
	private TourService tourService;
	private MailService mailService;
	private MemberService memberService;
	
	@GetMapping("/kakaoPay")
	public String kakaoPayGet() {
		return "pay/kakaoPay";
	}
	
	@PostMapping("/kakaoPay/tour")
	public String kakaoPayTour(
			@ModelAttribute TourDto tour,
			@RequestParam("pType") String pway,
			Model model
			) {
		
		OrderInfo orderInfo = OrderInfo.builder()
									   .category("tour")
									   .price(tour.getPrice())
									   .tourIdx(tourService.getTidxByTdate(tour.getSelectDate()))
									   .tourPeople(tour.getTourPeople())
									   .memberIdx(tour.getMidx())
									   .build();
		
		orderService.createOrder("tour", orderInfo);
			
		return "redirect:" + kakaoPay.kakaoPayReady(orderInfo);
	}
	
	@PostMapping("/kakaoPay/shop")
	public String kakaoPayShop(
			@ModelAttribute ShopDto shop,
			Model model
			) {
		
		Address memberAddress = memberService.getMemberAdd(shop.getMidx());
		
		OrderInfo orderInfo = OrderInfo.builder()
									   .category("shop")
									   .price(shop.getPrice())
									   .goodsIdx(shop.getGidx())
									   .addressIdx(memberAddress.getIdx())
									   .memberIdx(shop.getMidx())
									   .amount(shop.getAmount())
									   .build();
		
		orderService.createOrder("shop", orderInfo);
			
		return "redirect:" + kakaoPay.kakaoPayReady(orderInfo);
	}
	
	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess(
			@RequestParam("pg_token") String pg_token,
			@RequestParam("orderIdx") long orderIdx,
			Model model) {
		
		OrderInfo orderInfo = orderService.getOrderInfo(orderIdx);
		
		KakaoPayApproval kakaoPayApproval = kakaoPay.kakaoPayInfo(pg_token, orderInfo);
		logger.info("getCid = "+kakaoPayApproval.getCid() +", getTid = "+kakaoPayApproval.getTid()+
					"getTax_free = "+kakaoPayApproval.getTax_free_amount()+", getAmount = "+kakaoPayApproval.getAmount()+", get_vat_amount =  "+kakaoPayApproval.getVat_amount()
					);
		
		PayInfo payInfo = payService.approvalToPayInfo(kakaoPayApproval);
		payService.savePayment(payInfo);
		
		log.info(payInfo.toString());
		model.addAttribute("payIdx", payInfo.getIdx());
		
		if (orderInfo.getCategory().equals("tour")) {
			tourService.addTourPeopleByDate(orderInfo.getTourPeople(), tourService.getTourDateByTidx(orderInfo.getTourIdx()));
			mailService.completeMail(payInfo, memberService.getMember(orderInfo.getMemberIdx()));
		}
		
		orderService.confirmOrder(orderInfo.getIdx());
		
		return "pay/kakaoPaySuccess";
	}
	
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel(@RequestParam("orderIdx") long orderIdx, Model model) {
		if (orderService.getOrderInfo(orderIdx) != null) {
			orderService.deleteOrder(orderIdx);
		}
		return "pay/kakaoPayCancel";
	}
	
	@GetMapping("/kakaoPayComplete")
	public String kakaoPayComplete(@RequestParam("payIdx") String payIdx, Model model) {
		model.addAttribute("payIdx", payIdx);
		return "pay/kakaoPayComplete";
	}
	
	@GetMapping("/paySuccess")
	public String paySuccess(@RequestParam(value = "payIdx", required = false) String payIdx, Model model) {
		PayInfo payInfo = payService.getPayInfo(Integer.parseInt(payIdx));
		OrderInfo orderInfo = orderService.getOrderInfo(payInfo.getOrderIdx());
		
		model.addAttribute("payInfo", payInfo);
		model.addAttribute("orderInfo", orderInfo);
		
		addAddressToModel(orderInfo, model);
		
		return selectPaySuccessPageByType(orderInfo.getCategory());
	}
	
	private void addAddressToModel(OrderInfo orderInfo, Model model) {
		if (orderInfo.getCategory().equals("shop")) {
			LoginInfo member = memberService.getMember(orderInfo.getMemberIdx());
			
			Address memberAddress = memberService.getMemberAdd(orderInfo.getMemberIdx());
			
			model.addAttribute("addressInfo", memberAddress);
			model.addAttribute("memberInfo", member);
		}
	}
	
	private String selectPaySuccessPageByType(String type) {
		if (type.equals("tour")) {
			return "pay/tourPaySuccess";
		}
		return "pay/shopPaySuccess";
	}
}
