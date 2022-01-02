package com.bitcamp.sc.domain.basket.service;

import com.bitcamp.sc.domain.basket.domain.BasketDto;
import com.bitcamp.sc.domain.basket.domain.BasketVo;

import java.util.List;

public interface BasketService {
    void saveBasket(BasketDto bDto);
    List<BasketVo> getList(int midx);
    int getTotalPayByMidx(int midx);
    int getDeleteRowByGidx(int gidx, int midx);
    int getDeleteRowByGidx(List<Integer> gidxList, int midx);
    void deleteAllByMidx(int midx);
    int changeBasketAmount(BasketDto bDto);
}
