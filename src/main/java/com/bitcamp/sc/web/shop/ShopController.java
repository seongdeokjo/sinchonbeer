package com.bitcamp.sc.web.shop;

import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bitcamp.sc.web.shop.dto.GoodsToBuyNow;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {

    private final MemberService memberService;
	
	// shop메인 페이지
	@GetMapping
	public String getShop() {
		return "shop/shopMain";
	}
	
	// 상품 상세페이지
	@GetMapping("/{idx}")
	public String getShopDesc(@PathVariable("idx") long idx) {
		return "shop/product"+idx;

	}

	 // BuyNow 페이지로 가기
	 // @RequestParam("name 이름") 타입지정 변수명
	@GetMapping("/shop-payment")
	public String getBuyNow(HttpSession session, Model model,
			@ModelAttribute GoodsToBuyNow buynow,
			@RequestParam("gphotoname") String gphotoname,
			@RequestParam("gname") String gname,
			@RequestParam("gidx") long gidx,
			@RequestParam("gprice") int gprice,
			@RequestParam("amount") int amount) {
			
			model.addAttribute("buynow", buynow);
			
			model.addAttribute("gphotoname", gphotoname);
			model.addAttribute("gname", gname);
			model.addAttribute("gidx", gidx);
			model.addAttribute("gprice",gprice);
			model.addAttribute("amount", amount);
			
			LoginInfo loginInfo = (LoginInfo)session.getAttribute("loginInfo");
			
//			Address memberAddress = memberService.getMemberAdd(loginInfo.getIdx());
//
//			model.addAttribute("address1", memberAddress.getAddress1());
//			model.addAttribute("address2", memberAddress.getAddress2());
			
			return "shop/shop_payment";
	}
}