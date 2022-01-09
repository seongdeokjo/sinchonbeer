package com.bitcamp.sc.web.basket;

import com.bitcamp.sc.web.SessionConst;
import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.basket.service.BasketService;
import com.bitcamp.sc.web.login.dto.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/cart")
    public String getBasketPage(HttpSession session, Model model) {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<BasketDto> list = basketService.getList(loginInfo.getIdx());
        int total = basketService.getTotalPayByMidx(loginInfo.getIdx());

        model.addAttribute("list", list);
        model.addAttribute("total", total);

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