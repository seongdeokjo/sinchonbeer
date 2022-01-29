package com.bitcamp.sc.web.review.controller;

import com.bitcamp.sc.web.review.dto.ReviewSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.service.ReviewService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
		return "review/saveReviewForm";
	}

	@PostMapping("/save")
	public String saveReview(ReviewSaveDto saveDto) throws IOException {
		log.info("dto = {}",saveDto);
		MultipartFile file = saveDto.getFile();
		String originalFilename = file.getOriginalFilename();
		log.info("originalName = {}",originalFilename);
		String name = file.getName();
		log.info("name = {}",name);
		long size = file.getSize();
		log.info("size = {}",size);
		reviewService.save(saveDto);
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