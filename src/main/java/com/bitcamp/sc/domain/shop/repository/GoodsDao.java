package com.bitcamp.sc.domain.shop.repository;

import com.bitcamp.sc.domain.shop.domain.GoodsVO;

public interface GoodsDao {

	// 01.상품추가
	 GoodsVO insertGoods(GoodsVO vo);
	
	// 02.상품삭제
	 void deleteGoods(long idx);
	
	// 03.상품정보
	 GoodsVO findIdx(long idx);
	



	
	
}
