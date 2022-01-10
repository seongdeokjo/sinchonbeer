package com.bitcamp.sc.domain.address.repository;


import com.bitcamp.sc.domain.address.domain.Address;
import org.apache.ibatis.annotations.Insert;

public interface AddressDao {
    //회원 주소 입력
    void save(Address memberAddress);
    // 주소 변경
    
    // 주소 삭제
    
    // 주소 조회
    Address findAddressByMidx(long idx);
}