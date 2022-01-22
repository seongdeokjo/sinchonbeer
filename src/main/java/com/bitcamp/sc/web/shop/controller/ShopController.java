package com.bitcamp.sc.web.shop.controller;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.service.AddressService;
import com.bitcamp.sc.domain.shop.service.ShopService;
import com.bitcamp.sc.web.address.dto.MemberAddress;
import com.bitcamp.sc.web.login.dto.LoginInfo;
import com.bitcamp.sc.domain.member.service.MemberService;
import com.bitcamp.sc.web.shop.dto.GoodsResponse;
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

    private final ShopService shopService;
    private final AddressService addressService;

    // shop메인 페이지
    @GetMapping
    public String getShop() {
        return "shop/shopMain";
    }

    // 상품 상세페이지
    @GetMapping("/{idx}")
    public String getShopDesc(@PathVariable("idx") long idx, Model model) {
        model.addAttribute("gidx", idx);
        return "shop/product" + idx;
    }

    // BuyNow 페이지로 가기
    @GetMapping("/shop-payment/{idx}")
    public String getBuyNow(@PathVariable("idx") long gidx, Model model,HttpSession session,
                            @RequestParam("amount") int amount) {

        GoodsResponse goodsResponse = shopService.getGoodsVO(gidx);
        log.info("goods = {}",goodsResponse);

        model.addAttribute("amount", amount);
        model.addAttribute("goods",goodsResponse);
        LoginInfo loginInfo =(LoginInfo)session.getAttribute("loginMember");
        MemberAddress address = addressService.getAddressByMidx(loginInfo.getIdx());
        model.addAttribute("address",address);

        return "shop/shop_payment";
    }
}