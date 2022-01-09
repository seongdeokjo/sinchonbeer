package com.bitcamp.sc.domain.goods.repository;

import com.bitcamp.sc.domain.goods.domain.Goods;

public interface GoodsDao {

	// 01.상품추가
	 void save(Goods vo);
	
	// 02.상품삭제
	 void deleteGoods(long idx);
	
	// 03.상품정보
	 Goods findByIdx(long idx);

}