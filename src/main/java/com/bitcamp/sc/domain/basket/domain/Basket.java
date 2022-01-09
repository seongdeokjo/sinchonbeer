package com.bitcamp.sc.domain.basket.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Basket {
    private long id;
    private long midx;
    private long gidx;
    private int count;

    public Basket(long midx, long gidx, int count) {
        this.midx = midx;
        this.gidx = gidx;
        this.count = count;
    }
}