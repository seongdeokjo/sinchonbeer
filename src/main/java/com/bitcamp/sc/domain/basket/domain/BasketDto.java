package com.bitcamp.sc.domain.basket.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BasketDto {
    private long gidx;
    private long midx;
    private int amount;
}
