package com.bitcamp.sc.web.tour;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bitcamp.sc.domain.tour.domain.TourDto;

@Controller

@RequestMapping("/tour")
public class TourController {
	
	// 투어 메인 페이지
	@GetMapping
	public String getTour() {
		return "tour/views/tour";
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
	@RequestMapping(value = "/notice/{idx}", method = RequestMethod.GET)
	public String getTourNoticeDesc(@PathVariable("idx") long idx) {
		return "tour/views/notice" + idx;
	}

	// 투어 예약 페이지 이동
	@RequestMapping(value = "/pick-date", method = RequestMethod.GET)
	public String getPickDate() {		
		return "tour/makeReservation/pick-date";
	}

	// 날짜 , 인원 선택 후 예약 폼으로 이동 --> 로그인 여부 체크 / 날짜,인원,카테고리(투어), 회원 정보가 잘 들어오는지 ?
	@RequestMapping(value = "/reserve/form", method = RequestMethod.GET)
	public String getForm(@ModelAttribute TourDto tour, Model model) {
		model.addAttribute("tour", tour);
		return "tour/makeReservation/reservationForm";
	}
}