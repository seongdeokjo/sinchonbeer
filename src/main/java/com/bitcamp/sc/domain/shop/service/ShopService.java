package com.bitcamp.sc.domain.shop.service;




import com.bitcamp.sc.domain.shop.domain.GoodsVO;

public interface ShopService {
	
	


	GoodsVO getGoodsVO(int goodsIdx); // buy now 했을 때, 상품정보를 가져올것.
	
	//CartInfo getGoodsVOFromCart(int goodsIdx); // 장바구니에서상품 정보 가져옴

	


	
}
