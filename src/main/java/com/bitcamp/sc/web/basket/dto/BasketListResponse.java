package com.bitcamp.sc.web.basket.dto;

import com.bitcamp.sc.domain.basket.domain.Basket;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BasketListResponse {

    private long idx;
    private long midx;
    private int count;
    private long gidx;
    private int price;
    private String name;
    private String photo;

    @Builder
    public BasketListResponse(Basket fromBasket){
        this.idx = fromBasket.getIdx();
        this.midx = fromBasket.getMidx();
        this.gidx = fromBasket.getGoods().getIdx();
        this.count = fromBasket.getCount();
        this.name = fromBasket.getGoods().getName();
        this.price = fromBasket.getGoods().getPrice();
        this.photo = fromBasket.getGoods().getPhoto();
    }



}
