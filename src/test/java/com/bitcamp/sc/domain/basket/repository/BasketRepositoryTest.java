package com.bitcamp.sc.domain.basket.repository;

import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.goods.domain.Goods;
import com.bitcamp.sc.domain.goods.repository.GoodsDao;
import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.web.basket.dto.BasketListResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    void 장바구니_단일상품_조회(){
        long midx = 57;
        long gidx = 1;
        Basket basket = basketDao.getBasket(midx,gidx);
        log.info("basket = {}",basket);
    }
    
    @Test
    @Transactional(readOnly = true)
    void 장바구니_전체상품_조회(){
        long midx = 57;
        List<Basket> allByMidx = basketDao.findAllByMidx(midx);
        log.info("find baskets = {}",allByMidx);
    }

    @Test
    @Transactional
    void 장바구니_단일상품_삭제(){
        long midx = 57;
        long gidx = 1;
        Basket basket = basketDao.getBasket(midx,gidx);

        int result = basketDao.deleteRowByGidx(basket.getGoods().getIdx(), basket.getMidx());
        assertThat(result).isEqualTo(1);
    }

    @Test
    @Transactional
    void 장바구니_선택상품_삭제(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        long midx = 57;

        int result = basketDao.deleteGoods(list, midx);
        log.info("result = {}",result );
        assertThat(result).isEqualTo(2);

    }
}
