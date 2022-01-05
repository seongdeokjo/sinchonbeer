package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.bitcamp.sc.member.config.WebSecurityConfig;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.domain.MemberAddress;
import com.bitcamp.sc.domain.member.domain.RegRequest;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRegService {
    private final SqlSessionTemplate template;
    private final PasswordEncoder passwordEncoder;

    private MemberDao memberDao;

    public int regMember(RegRequest regRequest) {

        // 주소를 제외한 회원가입 - toMember(): midx, email, pw, name, phone
        memberDao = template.getMapper(MemberDao.class);
        Member member = regRequest.getMemberRegRequest().toMember();
        //암호화
        encryptionPw(member);

        int resultCnt = memberDao.insertMember(member); // 1또는 0 반환.
        if (resultCnt == 1) {
            log.info("member = {}", member);
            int idx = member.getIdx();
            // 사용자가 입력한 주소를 주소테이블에 넣기
            int result = regAddress(regRequest, idx);
            return result;
        }
        return 0;
    }

    private int regAddress(RegRequest regRequest, int idx) {
        int result = 0;
        MemberAddress memberAddress = regRequest.getMemberAddressRequest().toMemberAddress();
        if (memberAddress.formValidate()) {
            memberAddress.setMidx(idx);
            result = memberDao.insertAddress(memberAddress);
        }
        return result;
    }

    private void encryptionPw(Member member) {
        String securityPw = passwordEncoder.encode(member.getPw());
        log.info("암호화 테스트 : " + securityPw);
        member.setPw(securityPw);
        log.info("암호화 결과 : " + passwordEncoder.matches(member.getPw(), securityPw));
    }
}
