package com.bitcamp.sc.web.review.controller;

import com.bitcamp.sc.web.paging.Criteria;
import com.bitcamp.sc.web.paging.PageMaker;
import com.bitcamp.sc.web.review.dto.ReviewEditDto;
import com.bitcamp.sc.web.review.dto.ReviewSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.service.ReviewService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewService reviewService;
	@Value("${file.dir}")
	private String path;

	// 리뷰 메인 페이지
	@GetMapping
	public String getReview(Criteria cri, Model model){
		log.info("cri = {}",cri);
		List<ReviewVO> list = reviewService.findAll(cri);
		log.info("list ={}",list);
		model.addAttribute("reviewInfo",list);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(reviewService.countReview(cri));
		log.info("pageMaker = {}",pageMaker);
		model.addAttribute("pageMaker",pageMaker);
		return "review/reviewMain";
	}
	// 저장된 이미지 불러오기
	@ResponseBody
	@GetMapping("/image/{filename}")
	public Resource getImage(@PathVariable String filename) throws MalformedURLException {
		log.info("filename = {}",filename);
		return new UrlResource("file:"+path+filename);
	}

	// 리뷰 등록 페이지
	@GetMapping("/save")
	public String saveForm(Model model){
		model.addAttribute("saveDto",new ReviewSaveDto());
		return "review/saveReviewForm";
	}
	
	// 리뷰 등록
	@PostMapping("/save")
	public String saveReview(ReviewSaveDto saveDto, RedirectAttributes redirectAttributes) throws IOException {
		reviewService.save(saveDto);
		String message = "등록되었습니다.";
		redirectAttributes.addFlashAttribute("message",message);
		return "redirect:/reviews";
	}

	// 리뷰 수정 페이지
	@GetMapping("/edit/{idx}")
	public String getEditForm(@PathVariable Long idx,Model model){
		ReviewVO review = reviewService.findByIdx(idx);
		model.addAttribute("review",review);
		return "review/editReviewForm";
	}
	@PostMapping("/edit/{idx}")
	public String editReview(@PathVariable Long idx, ReviewEditDto editDto) throws IOException {
		reviewService.edit(idx,editDto);
		return "redirect:/reviews/read/"+idx;
	}

	// 리뷰 삭제 페이지
	@DeleteMapping("/delete/{idx}")
	public String deleteReview(@PathVariable Long idx,RedirectAttributes redirectAttributes){
		reviewService.delete(idx);
		String message = "삭제되었습니다.";
		redirectAttributes.addFlashAttribute("message",message);
		return "redirect:/reviews";
	}

	// 리뷰 상세 페이지
	@GetMapping("/read/{idx}")
	public String getDetail(@PathVariable("idx") Long idx, Model model, HttpServletRequest request, HttpServletResponse response) {
		findViewCookie(idx, request, response);
		ReviewVO review = reviewService.findByIdx(idx);
		model.addAttribute("view", review);
		return "review/view";
	}

	private void findViewCookie(Long idx, HttpServletRequest request, HttpServletResponse response) {
		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("reviewView")){
					oldCookie = cookie;
				}
			}
		}
		if(oldCookie != null){
			if(!oldCookie.getValue().contains("["+ idx.toString()+"]")){
				reviewService.viewCountUp(idx);
				oldCookie.setValue(oldCookie.getValue()+"_["+ idx +"]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(oldCookie);
			}
		}else{
			reviewService.viewCountUp(idx);
			Cookie newCookie = new Cookie("reviewView","["+ idx +"]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(newCookie);
		}
	}
}