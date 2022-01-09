package com.bitcamp.sc.domain.member.service.impl;

import com.bitcamp.sc.domain.address.domain.Address;
import com.bitcamp.sc.domain.address.repository.AddressDao;
import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.domain.member.service.MemberRegService;
import com.bitcamp.sc.web.member.dto.RegRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j

@Service
@RequiredArgsConstructor
public class MemberRegServiceImpl implements MemberRegService {
    private final MemberDao memberDao;
    private final AddressDao addressDao;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean saveMember(RegRequest regRequest) {
        boolean result = false;
        // 회원등록 - toMember(): email, pw, name, phone

        Member newMember = regRequest.toMember();
        //암호화
        encryptionPw(newMember);
        memberDao.save(newMember);
        if (newMember != null) {
            log.info("member = {}", newMember);
            // 사용자가 입력한 주소등록
            result = saveAddress(regRequest.toAddress(), newMember);
        }
        return result;
    }

    // 주소 등록
    private boolean saveAddress(Address newAddress, Member newMember) {
        boolean result = false;
        if (newAddress.formValidate()) {
            newAddress.setMember(newMember);
            addressDao.save(newAddress);
            if (newAddress != null) {
                log.info("newAddress ={}",newAddress);
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