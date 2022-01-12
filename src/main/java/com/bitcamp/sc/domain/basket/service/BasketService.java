package com.bitcamp.sc.domain.basket.service;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.web.basket.dto.BasketListResponse;

import java.util.List;

public interface BasketService {
    void saveBasket(BasketDto bDto);
    List<BasketListResponse> getList(long midx);
    int getTotalPayByMidx(long midx);
    int getDeleteRowByGidx(long gidx, long midx);
    int getDeleteGoodsByGidxList(List<Integer> gidxList, long midx);
    void deleteAllByMidx(long midx);
}
