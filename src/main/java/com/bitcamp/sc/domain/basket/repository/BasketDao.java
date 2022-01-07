package com.bitcamp.sc.domain.basket.repository;

import com.bitcamp.sc.domain.basket.domain.BasketDto;
import com.bitcamp.sc.domain.basket.domain.BasketVo;

import java.util.List;

public interface BasketDao {
    // 장바구니 생성
    int createBasket(BasketDto bDto);

    // 장바구니 확인
    int checkBasket(long gidx, long midx);
    List<BasketVo> getBasketList(long midx);
    int getTotalPay(long midx);
    int deleteRowByGidx(long gidx, long midx);
    void deleteAll(long midx);
    void modifyAmount(BasketDto bDto);
    int changeBasketAmount(BasketDto bDto);
}
