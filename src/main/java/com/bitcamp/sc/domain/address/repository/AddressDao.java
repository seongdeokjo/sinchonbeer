package com.bitcamp.sc.domain.address.repository;


import com.bitcamp.sc.domain.address.domain.Address;
import org.apache.ibatis.annotations.Insert;

public interface AddressDao {
    //회원 주소 입력

    void save(Address memberAddress);
}