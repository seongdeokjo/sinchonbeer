package com.bitcamp.sc.domain.basket.service;

import com.bitcamp.sc.domain.basket.domain.BasketDto;
import com.bitcamp.sc.domain.basket.domain.BasketVo;

import java.util.List;

public interface BasketService {
    void saveBasket(BasketDto bDto);
    List<BasketVo> getList(long midx);
    int getTotalPayByMidx(long midx);
    int getDeleteRowByGidx(long gidx, long midx);
    int getDeleteRowByGidx(List<Integer> gidxList, long midx);
    void deleteAllByMidx(long midx);
    int changeBasketAmount(BasketDto bDto);
}
