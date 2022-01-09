package com.bitcamp.sc.domain.shop.service;

import com.bitcamp.sc.domain.goods.domain.Goods;

public interface ShopService {
	Goods getGoodsVO(int goodsIdx); // buy now 했을 때, 상품정보를 가져올것.

}