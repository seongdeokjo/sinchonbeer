package com.bitcamp.sc.web.tour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitcamp.sc.domain.order.domain.Order;
import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.order.service.OrderService;
import com.bitcamp.sc.domain.tour.service.TourChangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.sc.domain.pay.domain.PayInfo;
import com.bitcamp.sc.domain.pay.service.PayService;
import com.bitcamp.sc.web.tour.dto.EditTourDto;
import com.bitcamp.sc.domain.tour.service.TourMailService;
import com.bitcamp.sc.domain.tour.service.TourService;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TourChangeController {

	private final TourChangeService changeTourService;
	private final OrderService orderService;
	private final TourService service;	
	private final TourMailService mailService;
	private final PayService payService;

	// 투어 예약 변경/확인/취소 페이지 가져오기
	@RequestMapping(value = "/tour/change-info", method = RequestMethod.GET)
	public String getChangePage(Model model, HttpServletRequest req) {
		LoginInfo login = getLoginInfo(req);
		List<Order> list = orderService.getOrderTourByMidxAndType(login.getIdx(),"tour");
		model.addAttribute("tourOrderList", list);
		return "tour/reservation/change/change-info";
	}

	// 예약 변경 확정 버튼 클릭 처리 -> orders테이블 tidx 수정 -> tour 테이블 날짜 각각 인원 수정
	@PostMapping("/tour/changeTour/{idx}")
	@ResponseBody
	public boolean changeTour(@RequestBody EditTourDto changeDto,@PathVariable("idx") long oidx) {
		return changeTourService.changeTourOrder(oidx,changeDto);
	}
	
	// 예약 변경 메일 전송
	@PostMapping("/tour/sendMailchange/{idx}")
	@ResponseBody
	public void sendChangeMail(@RequestBody EditTourDto changeDto, HttpServletRequest req) {
		LoginInfo login = getLoginInfo(req);
		if(changeDto != null && login != null) {
			mailService.changeMail(changeDto, login);
		}
	}
	
	// 예약 취소
	@GetMapping("/tour/cancleOrder/{oidx}")
	@ResponseBody
	public int cancelTourOrder(@PathVariable("oidx") long oidx, @RequestParam("people") int people,
			@RequestParam("tdate") String tdate) {
		int result = orderService.changeOrderStatus(oidx, "cancled");
		return (result == 1) ? service.subTourPeopleByDate(people, tdate) : 0;
	}
	
	// 예약 취소 메일 
	@GetMapping("/tour/sendCancleMail/{oidx}")
	@ResponseBody
	public void sendCancleMail(@PathVariable("oidx") long idx,HttpServletRequest req) {
		LoginInfo login = getLoginInfo(req);
		PayInfo payInfo = payService.getPayInfoByOrderIdx(idx);
		if(payInfo !=null) {
			mailService.refundMail(payInfo,login);
		}
	}

	// 세션 정보 가져오기
	private LoginInfo getLoginInfo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		LoginInfo login = (LoginInfo) session.getAttribute("loginMember");
		return login;
	}
}