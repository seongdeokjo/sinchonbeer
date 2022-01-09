package com.bitcamp.sc.domain.basket.repository.impl;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.basket.repository.BasketDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<BasketDto> findAllByMidx(long midx) {
        return template.selectList(NAME_SPACE+".findAllByMidx",midx);
    }

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
    public void deleteAll(long midx) {
        template.delete(NAME_SPACE+".deleteAll",midx);
    }

    @Override
    public int checkBasket(long gidx, long midx) {
        log.info("check mybatis 진입");
        log.info(gidx+":"+midx);
        Map<String,Object> params = new HashMap<>();
        params.put("gidx", gidx);
        params.put("midx", midx);

        Integer test=template.selectOne(NAME_SPACE+".checkBasket", params);
        log.info("test : " + test);
        int result = (test == null) ? 0 : test;
        log.info("result int 변환 : " + result);
        return result;
    }

    @Override
    public void modifyAmount(BasketDto bDto) {
        template.update(NAME_SPACE+".updateBasket", bDto);
    }

    @Override
    public int changeBasketAmount(BasketDto bDto) {
        return template.update(NAME_SPACE+".changeAmount", bDto);
    }
}