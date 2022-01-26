package com.bitcamp.sc.domain.member.service;

import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.mypage.repository.MypageDao;
import com.bitcamp.sc.web.member.dto.RegRequest;
import com.bitcamp.sc.web.mypage.dto.EditMemberRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
   MemberEditService editService;
   @Autowired
   MemberRegService regService;
   @Autowired
   MypageDao mypageDao;
   @Autowired
   MemberDao memberDao;

    @Test
    void 회원_수정(){
       long idx = 57;
        Member byMidx = mypageDao.findMemberAddressByMidx(idx);
        log.info("member = {}",byMidx);

        String editPw = "1234567";
        String editPhone = "01012345699";
        String editPostCode = "02";
        String editAddress1 = "부산";
        String editAddress2 = "2";

        EditMemberRequest request = new EditMemberRequest();
        request.setIdx(idx);
        request.setNewPw(editPw);
        request.setPhone(editPhone);
        request.setPostcode(editPostCode);
        request.setAddress1(editAddress1);
        request.setAddress2(editAddress2);

        int result = editService.updateMember(idx,request);
        log.info("result = {}",result);

        assertThat(result).isEqualTo(2);

        Member completeMember = mypageDao.findMemberAddressByMidx(byMidx.getIdx());
        log.info("-------------------------- result member = {}",completeMember);

        assertThat(completeMember.getPhone()).isEqualTo(editPhone);

    }

}
