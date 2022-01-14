package com.bitcamp.sc.domain.tour.domain;

import lombok.Getter;

import java.sql.Date;

@Getter
public class Tour {

    private long idx;
    private Date date;
    private int current;
    private int total;
    private int price;
}
