package com.bitcamp.sc.domain.buynow.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class BuynowRepositoryTest {

    @Autowired
    private BuynowDao dao;

    @Test
    void 단일상품_주문_생성(){
        long oidx = 17;
        long gidx = 1;
        int count = 1;

        dao.save(oidx,gidx,count);

    }
}
