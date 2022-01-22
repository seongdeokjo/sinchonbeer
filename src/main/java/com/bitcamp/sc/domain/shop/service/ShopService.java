package com.bitcamp.sc.domain.shop.service;

import com.bitcamp.sc.web.shop.dto.GoodsResponse;

public interface ShopService {
	GoodsResponse getGoodsVO(long goodsIdx); // buy now 했을 때, 상품정보를 가져올것.

}