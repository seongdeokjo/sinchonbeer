package com.bitcamp.sc.domain.basket.service;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;

import java.util.List;

public interface BasketService {
    void saveBasket(BasketDto bDto);
    List<BasketDto> getList(long midx);
    int getTotalPayByMidx(long midx);
    int getDeleteRowByGidx(long gidx, long midx);
    int getDeleteRowByGidx(List<Integer> gidxList, long midx);
    void deleteAllByMidx(long midx);
    int changeBasketAmount(BasketDto bDto);
}
