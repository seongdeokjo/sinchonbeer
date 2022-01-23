package com.bitcamp.sc.web.mypage.controller;

import com.bitcamp.sc.domain.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MypageRestController {
    private final MypageService mypageService;

    @GetMapping("/find/order/goods/{idx}")
    public int getOrderGoodsCount(@PathVariable("idx") long idx){
        return mypageService.getOrderGoods(idx);
    }

    @GetMapping("/find/order/tour/{idx}")
    public int getOrderTourCount(@PathVariable("idx") long idx){
        return mypageService.getOrderTour(idx);
    }
}
