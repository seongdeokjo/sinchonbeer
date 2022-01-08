package com.bitcamp.sc.domain.goods.repository;

import com.bitcamp.sc.domain.goods.domain.GoodsVO;

public interface GoodsDao {

	// 01.상품추가
	 void save(GoodsVO vo);
	
	// 02.상품삭제
	 void deleteGoods(long idx);
	
	// 03.상품정보
	 GoodsVO findByIdx(long idx);

}