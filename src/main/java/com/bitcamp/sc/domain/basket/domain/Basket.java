package com.bitcamp.sc.domain.basket.domain;

import com.bitcamp.sc.domain.goods.domain.Goods;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Basket {
    private long idx;
    private long midx;
    private Goods goods;
    private int count;

    @Builder
    public Basket(long midx, Goods goods, int count) {
        log.info("basket 생성자 빌더 호출");
        this.midx = midx;
        this.goods = goods;
        this.count = count;
    }

    public int addCount(int count){
        return this.count += count;
    }
}