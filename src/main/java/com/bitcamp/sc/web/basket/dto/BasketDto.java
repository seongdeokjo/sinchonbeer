package com.bitcamp.sc.web.basket.dto;

import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.goods.domain.Goods;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasketDto {
    private long id;
    private long gidx;
    private long midx;
    private int count;


    public Basket toBasket(){
        return new Basket(this.midx,new Goods(gidx),this.count);
    }
}