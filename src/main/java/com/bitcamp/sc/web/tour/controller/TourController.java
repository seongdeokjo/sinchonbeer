package com.bitcamp.sc.web.tour.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bitcamp.sc.web.tour.dto.TourDto;

@Slf4j
@Controller
@RequestMapping("/tour")
public class TourController {
	
	// 투어 메인 페이지
	@GetMapping
	public String getTour() {
		return "tour/views/main";
	}

	// 투어 안내 페이지
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getTourInfo() {
		return "tour/views/info";
	}

	// 투어 공지 페이지
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String getTourNotice() {
		return "tour/views/notice";
	}

	// 투어 공지 페이지에서 /번호로 식별
	@RequestMapping(value = "/notice/{num}", method = RequestMethod.GET)
	public String getTourNoticeDesc(@PathVariable("num") long pageNum) {
		return "tour/views/notice" + pageNum;
	}

	// 투어 예약 페이지 이동
	@RequestMapping(value = "/pick-date", method = RequestMethod.GET)
	public String getPickDate() {
		return "tour/reservation/pick-date";
	}
	
	// 투어 결제 폼으로 이동
	@RequestMapping(value = "/reserve/form", method = RequestMethod.GET)
	public String getForm(TourDto tourDto, Model model) {
		log.info("tourDto = {}",tourDto);
		model.addAttribute("tour", tourDto);
		return "tour/reservation/reservationForm";
	}
}