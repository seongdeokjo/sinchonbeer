package com.bitcamp.sc.domain.shop.repository.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitcamp.sc.domain.shop.domain.GoodsVO;
import com.bitcamp.sc.domain.shop.repository.GoodsDao;

@Repository


public class MybatisGoodsDao implements GoodsDao{

	private static final String NAME_SPACE="GoodsMapper";
	
	private final SqlSessionTemplate template;
	
	@Autowired
	public MybatisGoodsDao(SqlSessionTemplate template) {
		this.template = template;
	}
	

	// 1.상품추가
	@Override
	public GoodsVO insertGoods(GoodsVO vo) {
		template.insert(NAME_SPACE + ".insertGoods", vo);
		return vo;
	}

	// 2.상품 삭제
	@Override
	public void deleteGoods(int idx) {
		template.delete(NAME_SPACE + ".deleteGoods", idx);
		
	}


	// 3.샹폼 챶기
	@Override
	public GoodsVO findIdx(int idx) {
		return template.selectOne(NAME_SPACE + ".findIdx", idx);
	}


	

	

	

}
