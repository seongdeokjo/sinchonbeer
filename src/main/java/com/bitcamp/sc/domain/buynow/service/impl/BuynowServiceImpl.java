package com.bitcamp.sc.domain.buynow.service.impl;

import com.bitcamp.sc.domain.buynow.repository.BuynowDao;
import com.bitcamp.sc.domain.buynow.service.BuynowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuynowServiceImpl implements BuynowService {

    private final BuynowDao buynowDao;

    @Override
    public void saveBuynow(long oidx, long gidx, int amount) {

    }
}
