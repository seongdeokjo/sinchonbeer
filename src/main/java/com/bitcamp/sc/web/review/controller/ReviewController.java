package com.bitcamp.sc.web.review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.service.ReviewService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	// 리뷰 메인 페이지
	@GetMapping
	public String getReview(Model model){
		List<ReviewVO> list = reviewService.findAll();
		log.info("list ={}",list);
		model.addAttribute("reviewInfo",list);
		return "review/reviewMain";
	}

	// 리뷰 등록 페이지
	@GetMapping("/save")
	public String writeForm(){
		return "review/writing2";
	}

	@PostMapping("/save")
	public String saveReview(){
		return "redirect:/reviews";
	}

	// 리뷰 수정 페이지

	// 리뷰 삭제 페이지

	// 리뷰 상세 페이지
	@GetMapping("/{idx}")
	public String getDetail(@PathVariable long idx){

		return "review/view";
	}
}