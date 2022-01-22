package com.bitcamp.sc.domain.buynow.repository.impl;

import com.bitcamp.sc.domain.buynow.domain.Buynow;
import com.bitcamp.sc.domain.buynow.repository.BuynowDao;
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
public class MybatisBuynowDao implements BuynowDao {

    private static final String NAME_SPACE="BuynowMapper";
    private final SqlSessionTemplate template;

    @Override
    public List<Buynow> getListByMidx(long midx) {
        return null;
    }

    @Override
    public void save(long oidx, long gidx, int amount) {
        Map<String, Object> params = new HashMap<>();
        params.put("oidx",oidx);
        params.put("gidx",gidx);
        params.put("amount",amount);
        template.insert(NAME_SPACE + ".save", params);
    }
}
