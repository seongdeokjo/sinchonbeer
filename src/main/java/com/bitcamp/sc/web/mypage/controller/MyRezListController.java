package com.bitcamp.sc.web.mypage.controller;

import java.util.List;

import com.bitcamp.sc.domain.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bitcamp.sc.domain.mypage.domain.RezList;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyRezListController {
    @Autowired
    MypageService service;

    // 예약 내역 페이지 이동
    @GetMapping("/mypage/rezList/{id}")
    public String mypageTour(@PathVariable("id") long idx, Model model) {
        List<RezList> list = service.getRezList(idx);
        model.addAttribute("list", list);

        return "mypage/rezList";
    }
}

