package com.bitcamp.sc.domain.tour.domain;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Tour {

    private long idx;
    private Timestamp date;
    private int current;
    private int total;
    private int price;
}
