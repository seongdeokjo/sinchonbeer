package com.bitcamp.sc.domain.basket.repository.impl;

import com.bitcamp.sc.domain.basket.domain.BasketDto;
import com.bitcamp.sc.domain.basket.domain.BasketVo;
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

    @Override
    public int createBasket(BasketDto bDto) {
        log.info("장바구니 mybatis 진입"+bDto.toString());
        return template.insert(NAME_SPACE+".insertBasket", bDto);
    }

    @Override
    public List<BasketVo> getBasketList(int midx) {
        return template.selectList(NAME_SPACE+".getBasketList",midx);
    }

    @Override
    public int getTotalPay(int midx) {
        log.info("mybatis 진입");
        Integer test = template.selectOne(NAME_SPACE+".getTotal",midx) ;
        test  = test == null ? 0 : test;
        log.info(String.valueOf(test));
        return test;
    }

    @Override
    public int deleteRowByGidx(int gidx,int midx) {
        Map<String,Object> params = new HashMap<>();
        params.put("gidx", gidx);
        params.put("midx", midx);
        return template.delete(NAME_SPACE+".deleteRow",params);
    }

    @Override
    public void deleteAll(int midx) {
        template.delete(NAME_SPACE+".deleteAll",midx);
    }

    @Override
    public int checkBasket(int gidx, int midx) {
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
