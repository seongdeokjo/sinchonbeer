package com.bitcamp.sc.domain.member.repository.impl;

import com.bitcamp.sc.domain.member.domain.Member;
import com.bitcamp.sc.domain.member.repository.MemberDao;
import com.bitcamp.sc.web.login.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisMemberDao implements MemberDao {
    private static final String NAME_SPACE ="MemberMapper";
    private final SqlSessionTemplate template;

    @Override
    public void save(Member member) {
        template.insert(NAME_SPACE + ".save", member);
    }

    @Override
    public Member findByEmailAndPw(LoginForm form) {
        log.info("form = {}",form);
        return template.selectOne(NAME_SPACE+".findByEmailAndPw",form);
    }

    @Override
    public Member findByEmail(String email) {
        return template.selectOne(NAME_SPACE+".findByEmail",email);
    }

    @Override
    public Member findByNameAndPhone(@Param("name") String name, @Param("phone") String phone) {
        return template.selectOne(NAME_SPACE+".findByNameAndPhone");
    }

    @Override
    public Member findByNameAndEmail(@Param("name") String name, @Param("email") String email) {
        return template.selectOne(NAME_SPACE+".findByNameAndEmail");
    }

    @Override
    public Member findByMidx(long idx) {
        return template.selectOne(NAME_SPACE+".findByMidx",idx);
    }

    @Override
    public int updateCode(@Param("code") String code, @Param("email") String email) {
        return template.update(NAME_SPACE+".updateCode");
    }

    @Override
    public int updatePw(@Param("email")String email, @Param("pw")String pw) {
        return template.update(NAME_SPACE+".updatePw");
    }

    @Override
    public void deleteMember(long idx) {
        template.delete(NAME_SPACE+".deleteMember",idx);
    }
}