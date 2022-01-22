package com.bitcamp.sc.domain.shop.service.impl;

import com.bitcamp.sc.domain.goods.domain.Goods;
import com.bitcamp.sc.domain.goods.repository.GoodsDao;
import com.bitcamp.sc.web.shop.dto.GoodsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.shop.service.ShopService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
	private final GoodsDao goodsDao;

	// 바로 주문 했을시 상품정보를 가져와야함.
	@Override
	public GoodsResponse getGoodsVO(long goodsIdx) {
		Goods goodsVO = goodsDao.findByIdx(goodsIdx);
		return new GoodsResponse(goodsVO);
	}
}