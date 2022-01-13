package com.bitcamp.sc.domain.buynow.repository;

import com.bitcamp.sc.domain.buynow.domain.Buynow;

import java.util.List;

public interface BuynowDao {
    List<Buynow> getListByMidx(long midx);
}
