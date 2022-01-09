package com.bitcamp.sc.web.basket.dto;

import com.bitcamp.sc.domain.basket.domain.Basket;
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
    private String name;
    private String photo;
    private int price;

    public Basket toBasket(){
        return new Basket(this.midx,this.gidx,this.count);
    }
}