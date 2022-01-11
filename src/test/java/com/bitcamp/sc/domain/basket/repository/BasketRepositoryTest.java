package com.bitcamp.sc.domain.basket.repository;

import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.goods.domain.Goods;
import com.bitcamp.sc.domain.goods.repository.GoodsDao;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class BasketRepositoryTest {
    @Autowired
    SqlSessionTemplate template;
    @Autowired BasketDao basketDao;
    @Autowired
    GoodsDao goodsDao;

    @Test
    @Transactional
    void 장바구니_생성(){
        long midx = 57;
        long gidx = 1;
        Goods goods = goodsDao.findByIdx(gidx);
        log.info("find goods = {}",goods);
        int count = 3;
        Basket saveBasket = new Basket(midx,goods,count);
        basketDao.save(saveBasket);
        log.info("new basket = {}",saveBasket);

    }

    @Test
    @Transactional(readOnly = true)
    void 장바구니_조회(){
        long midx = 57;
        long gidx = 1;
        Basket basket = basketDao.getBasket(midx,gidx);
        log.info("basket = {}",basket);
    }

    @Test
    @Transactional
    void 장바구니_삭제(){
        long midx = 57;
        long gidx = 1;
        Basket basket = basketDao.getBasket(midx,gidx);

        int result = basketDao.deleteRowByGidx(basket.getGoods().getIdx(), basket.getMidx());
        assertThat(result).isEqualTo(1);
    }
}
