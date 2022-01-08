package com.bitcamp.sc.domain.goods.repository.impl;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.goods.domain.GoodsVO;
import com.bitcamp.sc.domain.goods.repository.GoodsDao;

@Repository
@RequiredArgsConstructor
public class MybatisGoodsDao implements GoodsDao{

	private static final String NAME_SPACE="GoodsMapper";
	private final SqlSessionTemplate template;

	// 1.상품추가
	@Override
	public void save(GoodsVO vo) {
		template.insert(NAME_SPACE + ".save", vo);
	}

	// 2.상품 삭제
	@Override
	public void deleteGoods(long idx) {
		template.delete(NAME_SPACE + ".deleteGoods", idx);
		
	}

	// 3.샹폼 챶기
	@Override
	public GoodsVO findByIdx(long idx) {
		return template.selectOne(NAME_SPACE + ".findByIdx", idx);
	}
}