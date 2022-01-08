package com.bitcamp.sc.domain.goods.repository;

import com.bitcamp.sc.domain.goods.domain.GoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class GoodsRepositoryTest {

    @Autowired
    GoodsDao goodsDao;

    @Test
    void 상품_저장_조회() {
        //given
        GoodsVO goods = GoodsVO.builder()
                .name("테스트상품1")
                .photo("이미지1")
                .price(10000)
                .title("상품1입니다.")
                .build();
        //when
        goodsDao.save(goods);
        long idx = goods.getIdx();
        GoodsVO findGoods = goodsDao.findByIdx(idx);
        //then
        assertThat(findGoods.getIdx()).isEqualTo(idx);
    }

    @Test
    void 상품_삭제(){
        //given
        GoodsVO goods = GoodsVO.builder()
                .name("테스트상품1")
                .photo("이미지1")
                .price(10000)
                .title("상품1입니다.")
                .build();

        //when
        goodsDao.save(goods);
        log.info("goods = {}",goods);
        long idx = goods.getIdx();
        goodsDao.deleteGoods(idx);

        //then
        assertThrows(NullPointerException.class, () -> goodsDao.findByIdx(idx).getIdx());
    }
}