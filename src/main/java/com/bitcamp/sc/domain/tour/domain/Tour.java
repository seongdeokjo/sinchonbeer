package com.bitcamp.sc.domain.tour.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
@NoArgsConstructor
public class Tour {

    private long idx;
    private Date tourDate;
    private int current;
    private int total;
    private int price;
}
