package com.bitcamp.sc.domain.basket.repository.impl;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.basket.repository.BasketDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisBasketDao implements BasketDao {

    private static final String NAME_SPACE = "BasketMapper";
    private final SqlSessionTemplate template;

    // 장바구니 생성
    @Override
    public void save(Basket basket) {
       template.insert(NAME_SPACE+".save", basket);
    }
    
    // 회원의 장바구니 상품 모두 가져오기
    @Override
    public List<Basket> findAllByMidx(long midx) {
        return template.selectList(NAME_SPACE+".findAllByMidx",midx);
    }

    //
    @Override
    public int getTotalPay(long midx) {
        log.info("mybatis 진입");
        Integer test = template.selectOne(NAME_SPACE+".getTotal",midx) ;
        test  = test == null ? 0 : test;
        log.info(String.valueOf(test));
        return test;
    }

    @Override
    public int deleteRowByGidx(long gidx,long midx) {
        Map<String,Object> params = new HashMap<>();
        params.put("gidx", gidx);
        params.put("midx", midx);
        return template.delete(NAME_SPACE+".deleteRow",params);
    }

    @Override
    public int deleteGoods(List<Integer> list, long midx) {
        log.info("선택 상품 삭제 ----------------");
        log.info("gidx list 개수 = {}",list.size());
        Map<String,Object> params = new HashMap<>();
        params.put("gidxList", list);
        params.put("midx", midx);
        return template.delete(NAME_SPACE+".deleteGoods",params);
    }

    @Override
    public void deleteAll(long midx) {
        template.delete(NAME_SPACE+".deleteAll",midx);
    }

    @Override
    public Basket getBasket( long midx, long gidx) {
        Map<String,Object> params = new HashMap<>();
        params.put("gidx", gidx);
        params.put("midx", midx);
        return template.selectOne(NAME_SPACE+".findByGidxAndMidx",params);
    }

    @Override
    public void updateCount(Basket basket) {
        template.update(NAME_SPACE+".updateBasket", basket);
    }
}