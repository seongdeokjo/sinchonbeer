package com.bitcamp.sc.domain.shop.service.impl;

import com.bitcamp.sc.domain.shop.domain.GoodsVO;
import com.bitcamp.sc.domain.shop.repository.GoodsDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bitcamp.sc.domain.shop.service.ShopService;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService{

	GoodsDao goodsDao;

	public ShopServiceImpl(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;

	}

	// 바로 주문 했을시 상품정보를 가져와야함.
	@Override
	public GoodsVO getGoodsVO(int goodsIdx) {
		GoodsVO goodsVO = goodsDao.findIdx(goodsIdx);
		return goodsVO;
	}

	
	

}