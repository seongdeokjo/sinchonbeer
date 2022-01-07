package com.bitcamp.sc.domain.address.repository.impl;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisAddressDao implements AddressDao {
    private static final String NAME_SPACE = "AddressMapper";

    private final SqlSessionTemplate template;


    @Override
    public void save(Address memberAddress) {
        template.insert(NAME_SPACE+".save",memberAddress);
    }

}
