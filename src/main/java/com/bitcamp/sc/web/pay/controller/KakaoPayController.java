package com.bitcamp.sc.web.pay.controller;

import com.bitcamp.sc.domain.address.service.AddressService;
import com.bitcamp.sc.domain.buynow.service.BuynowService;
import com.bitcamp.sc.domain.pay.domain.KakaoPayRefund;
import com.bitcamp.sc.web.address.dto.MemberAddress;
import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.member.service.MemberService;
import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.order.service.OrderService;
import com.bitcamp.sc.domain.pay.service.impl.PayServiceImpl;
import com.bitcamp.sc.domain.pay.service.impl.type.KakaoPay;
import com.bitcamp.sc.web.shop.dto.BuynowDto;
import com.bitcamp.sc.web.tour.dto.TourDto;
import com.bitcamp.sc.domain.tour.service.TourMailService;
import com.bitcamp.sc.domain.tour.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.domain.pay.domain.KakaoPayApproval;
import com.bitcamp.sc.domain.pay.domain.PayInfo;

import lombok.AllArgsConstructor;

@Slf4j
@AllArgsConstructor
@Controller
public class KakaoPayController {
	private KakaoPay kakaoPay;
	private PayServiceImpl payService;
	private OrderService orderService;
	private TourService tourService;
	private TourMailService mailService;
	private MemberService memberService;
	private BuynowService buynowService;
	private AddressService addressService;
	
	@GetMapping("/kakaoPay")
	public String kakaoPayGet() {
		return "pay/kakaoPay";
	}

	@GetMapping("/kakaoPay/tour/refund/{oidx}")
	@ResponseBody
	public String kakaoPayTourRefund(@PathVariable("oidx") long oidx,
									 @RequestParam("people") int people,
									 @RequestParam("tdate") String tdate) {
		String result = "N";
		PayInfo payInfo = payService.getPayInfoByOrderIdx(oidx);
		KakaoPayRefund kakaoPayRefund = kakaoPay.kakaoPayRefund(payInfo);

		if(kakaoPayRefund != null && kakaoPayRefund.getStatus().equals("CANCEL_PAYMENT")){
			log.info("결제 취소 완료");
			payService.refundPayByOidx(Long.parseLong(kakaoPayRefund.getPartner_order_id()));
			orderService.changeOrderStatus(Long.parseLong(kakaoPayRefund.getPartner_order_id()), "cancle");
			tourService.subTourPeopleByDate(people, tdate);
			result = "Y";
		}
		return result;
	}
	
	@PostMapping("/kakaoPay/tour")
	public String kakaoPayTour(
			@ModelAttribute TourDto tour,
			@RequestParam("pType") String pway) {
				OrderInfo orderInfo = OrderInfo.builder()
									   .category(tour.getCategory())
									   .price(tour.getPrice())
									   .tourIdx(tourService.getTidxByTdate(tour.getSelectDate()))
									   .tourPeople(tour.getTourPeople())
									   .memberIdx(tour.getMidx())
									   .build();
		log.info("tourDto = {}",tour);
		orderService.createOrder(orderInfo);
		return "redirect:" + kakaoPay.kakaoPayReady(orderInfo);
	}
	
	@PostMapping("/kakaoPay/shop")
	public String kakaoPayShop(
			@ModelAttribute BuynowDto shop) {
		log.info("shop  ={} ",shop);
		OrderInfo orderInfo = OrderInfo.builder()
									   .category("shop")
									   .price(shop.getPrice())
									   .goodsIdx(shop.getGidx())
									   .addressIdx(shop.getAidx())
									   .memberIdx(shop.getMidx())
									   .amount(shop.getAmount())
									   .build();
		log.info("orderInfo = {}",orderInfo);
		long orderIdx = orderService.createOrder(orderInfo);
		buynowService.saveBuynow(orderIdx,shop.getGidx(),shop.getAmount());
		log.info("바로구매한 상품 저장 성공");
		return "redirect:" + kakaoPay.kakaoPayReady(orderInfo);
	}
	
	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess(
			@RequestParam("pg_token") String pg_token,
			@RequestParam("orderIdx") long orderIdx,
			Model model) {
		
		OrderInfo orderInfo = orderService.getOrderInfo(orderIdx);

		KakaoPayApproval kakaoPayApproval = kakaoPay.kakaoPayInfo(pg_token, orderInfo);
		log.info("getCid = "+kakaoPayApproval.getCid() +", getTid = "+kakaoPayApproval.getTid()+
					"getTax_free = "+kakaoPayApproval.getTax_free_amount()+", getAmount = "+kakaoPayApproval.getAmount()+
						", get_vat_amount =  "+kakaoPayApproval.getVat_amount());
		
		PayInfo payInfo = payService.approvalToPayInfo(kakaoPayApproval);
		payService.savePayment(payInfo);
		log.info(payInfo.toString());
		model.addAttribute("payIdx", payInfo.getIdx());
		if (orderInfo.getCategory().equals("tour")) {
			tourService.addTourPeopleByDate(orderInfo.getTourPeople(), tourService.getTourDateByTidx(orderInfo.getTourIdx()));
//			mailService.completeMail(payInfo, memberService.getMember(orderInfo.getMemberIdx()));
		}
		orderService.confirmOrder(orderInfo.getIdx());
		return "pay/kakaoPaySuccess";
	}
	
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel(@RequestParam("orderIdx") long orderIdx) {
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
			MemberAddress address = addressService.getAddressByMidx(member.getIdx());
			model.addAttribute("memberInfo", member);
			model.addAttribute("address",address);
		}
	}
	
	private String selectPaySuccessPageByType(String type) {
		if (type.equals("tour")) {
			return "pay/tourPaySuccess";
		}
		return "pay/shopPaySuccess";
	}
}