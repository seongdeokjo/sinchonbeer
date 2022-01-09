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

    // 회원의 주소 저장
    @Override
    public void save(Address memberAddress) {
        template.insert(NAME_SPACE+".save",memberAddress);
    }
    
    // 회원 번호로 회원+주소 가져오기
    @Override
    public Address findAddressByMidx(long idx) {
        return template.selectOne(NAME_SPACE+".findAddressByMidx",idx);
    }

}