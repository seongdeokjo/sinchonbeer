package com.bitcamp.sc.domain.buynow.repository.impl;

import com.bitcamp.sc.domain.buynow.domain.Buynow;
import com.bitcamp.sc.domain.buynow.repository.BuynowDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisBuynowDao implements BuynowDao {




    @Override
    public List<Buynow> getListByMidx(long midx) {
        return null;
    }
}
