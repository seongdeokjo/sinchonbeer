package com.bitcamp.sc.domain.order.domain;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.goods.domain.Goods;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.tour.domain.Tour;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@ToString
public class Order {
    private long idx;
    private Timestamp date;
    private String category;
    private int price;
    private Tour tour;
    private int tourPeople;
    private Member member;
    private Address address;
    private Goods goods;
    private int amount;

    @Builder
    public Order(String category, int price, Tour tour, int tourPeople, Member member, Address address, Goods goods, int amount) {
        this.category = category;
        this.price = price;
        this.tour = tour;
        this.tourPeople = tourPeople;
        this.member = member;
        this.address = address;
        this.goods = goods;
        this.amount = amount;
    }
}