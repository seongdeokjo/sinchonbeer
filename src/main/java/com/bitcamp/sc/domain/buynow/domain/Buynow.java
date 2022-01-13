package com.bitcamp.sc.domain.buynow.domain;

import com.bitcamp.sc.domain.goods.domain.Goods;
import com.bitcamp.sc.domain.order.domain.Order;
import com.bitcamp.sc.domain.order.domain.OrderInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Buynow {
    private Goods goods;
    private Order order;
    private int count;

    @Builder
    public Buynow(Goods goods, Order order, int count) {
        this.goods = goods;
        this.order = order;
        this.count = count;
    }
}
