package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.mypage.domain.UpdateMember;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import com.bitcamp.sc.web.member.dto.RegRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
public class MemberEditServiceTest {

    @Autowired
    SqlSessionTemplate template;

    @Autowired
    MemberDao memberDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    MypageDao mypageDao;

    @Test
    void 회원_수정(){
        //given
        // 회원과 주소 생성
        RegRequest regRequest = new RegRequest();
        regRequest.setEmail("test1@naver.com");
        regRequest.setPw("1234");
        regRequest.setName("테스트");
        regRequest.setPhone("01012345678");
        regRequest.setPostcode("1111");
        regRequest.setAddress1("서울");
        regRequest.setAddress2("1");
        // 회원, 주소 저장
        Member member = regRequest.toMember();
        Address address = regRequest.toAddress();
        memberDao.save(member);
        log.info("saveMember ={}",member);
        long idx = member.getIdx();
        address.setMidx(idx);
        addressDao.save(address);
        log.info("saveAddress ={}",address);

        //then
        // 회원정보 가져오기
        UpdateMember memberAddress = mypageDao.getMemberInfo(idx);
        log.info("memberAddress = {}",memberAddress);

        memberAddress.setNewPw("2345");
        memberAddress.setPostcode("2222");
        memberAddress.setAddress1("부산");
        memberAddress.setAddress2("2");

        //when
        // 회원 정보 수정
        int result = mypageDao.updateMember(memberAddress);
        assertThat(result).isEqualTo(2);    // member, address

        memberAddress = mypageDao.getMemberInfo(idx);
        log.info("변경된 회원주소 = {}",memberAddress);
        assertThat(memberAddress.getAddress1()).isEqualTo("부산");
    }

}
