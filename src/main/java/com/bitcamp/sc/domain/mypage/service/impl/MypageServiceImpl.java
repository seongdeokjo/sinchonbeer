package com.bitcamp.sc.domain.mypage.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.mypage.repository.impl.MybatisMypageDao;
import com.bitcamp.sc.domain.mypage.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.mypage.domain.OrderList;
import com.bitcamp.sc.domain.mypage.domain.RezList;

@Slf4j
@Service
public class MypageServiceImpl implements MypageService {
    private MybatisMypageDao dao;

    public MypageServiceImpl(MybatisMypageDao dao) {
        this.dao = dao;
    }

    // 주문 내역 조회
    @Override
    public List<OrderList> getOrderList(long idx) {
        return dao.getOrderList(idx);
    }

    // 예약 내역 조회
    @Override
    public List<RezList> getRezList(long idx) {
        return dao.getRezList(idx);
    }
}