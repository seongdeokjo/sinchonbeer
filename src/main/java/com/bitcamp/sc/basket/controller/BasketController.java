package com.bitcamp.sc.basket.controller;

import com.bitcamp.sc.basket.domain.BasketDto;
import com.bitcamp.sc.basket.domain.BasketVo;
import com.bitcamp.sc.basket.service.BasketService;
import com.bitcamp.sc.member.domain.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    // 장바구니 조회 및 총 금액 가져오기
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String getBasketPage(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        if (loginInfo != null) {
            List<BasketVo> list = basketService.getList(loginInfo.getIdx());
            model.addAttribute("list", list);
            int total = basketService.getTotalPayByMidx(loginInfo.getIdx());

            model.addAttribute("total", total);

            log.info(model.getAttribute("list").toString());
        }
        return "basket/basket";
    }

    // 장바구니 목록생성
    @RequestMapping(value = "/basket", method = RequestMethod.GET) // 원래는POST로 받을것
    public String getBasket(BasketDto bDto) {
        log.info(bDto.toString());
        // 장바구니 목록을 만들고
        if (bDto != null) {
            basketService.saveBasket(bDto);
        }
        return "redirect:/basket/cart";
    }
}