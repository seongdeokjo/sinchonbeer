package com.bitcamp.sc.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.bitcamp.sc.member.config.WebSecurityConfig;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.domain.Address;
import com.bitcamp.sc.web.member.dto.RegRequest;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class MemberRegService {
    private final SqlSessionTemplate template;
    private final PasswordEncoder passwordEncoder;

    private MemberDao memberDao;

    public boolean regMember(RegRequest regRequest) {
        boolean result = false;
        // 회원등록 - toMember(): midx, email, pw, name, phone
        memberDao = template.getMapper(MemberDao.class);
        Member member = regRequest.toMember();
        //암호화
        encryptionPw(member);

        int resultCnt = memberDao.saveMember(member); // 1또는 0 반환.
        if (resultCnt == 1) {
            log.info("member = {}", member);
            int idx = member.getIdx();
            // 사용자가 입력한 주소등록
            result = regAddress(regRequest, idx);

        }
        return result;
    }
    
    // 주소 등록
    private boolean regAddress(RegRequest regRequest, int idx) {
        boolean result = false;
        Address memberAddress = regRequest.toMemberAddress();
        if (memberAddress.formValidate()) {
            memberAddress.setMidx(idx);
           int resultCnt = memberDao.saveAddress(memberAddress);
           if(resultCnt == 1){
               result = true;
           }
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
