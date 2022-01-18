package com.bitcamp.sc.domain.pay.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.pay.domain.PayInfo;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PayDao {
	private static final String NAME_SPACE = "PayMapper";
	private final SqlSessionTemplate template;

	public PayInfo save(PayInfo payInfo) {
		template.insert(NAME_SPACE + ".save", payInfo);
		return payInfo;
	}

	public PayInfo findByIdx(long idx) {
		return template.selectOne(NAME_SPACE + ".findByIdx", idx);
	}

	public PayInfo findByOrderIdx(long orderIdx) {
		return template.selectOne(NAME_SPACE + ".findByOrderIdx", orderIdx);
	}
	
	public List<PayInfo> findByMemberId(long memberIdx) {
		return template.selectOne(NAME_SPACE + ".findByMemberIdx", memberIdx);
	}

	public List<PayInfo> findByCategoryAndMemberIdx(String category, long memberIdx) {
		Map<String, Object> param = new HashMap<>();
		param.put("category", category);
		param.put("memberIdx", memberIdx);
		return template.selectList(NAME_SPACE + ".findByCategoryAndMemberIdx", param);
	}
}