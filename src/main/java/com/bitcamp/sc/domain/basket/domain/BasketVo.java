package com.bitcamp.sc.domain.basket.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BasketVo {
    private int bidx;
    private long midx;
    private long gidx;
    private String gname;
    private String gphoto;
    private int bcount;
    private int gprice;
}
