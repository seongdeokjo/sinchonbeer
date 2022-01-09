package com.bitcamp.sc.domain.basket.repository;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;

import java.util.List;

public interface BasketDao {
    // 장바구니 생성
    void save(Basket basket);
    // 장바구니 확인
    int checkBasket(long gidx, long midx);
    //장바구니 목록
    List<BasketDto> findAllByMidx(long midx);
    // 장바구니 총 가격
    int getTotalPay(long midx);
    // 장바구니 상품 1개 삭제
    int deleteRowByGidx(long gidx, long midx);
    // 장바구니 전체 삭제
    void deleteAll(long midx);
    // 장바구니 수정
    void modifyAmount(BasketDto bDto);

    int changeBasketAmount(BasketDto bDto);
}
