package com.bitcamp.sc.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.bitcamp.sc.shop.domain.GoodsVO;
import com.bitcamp.sc.shop.repository.GoodsDao;
import com.bitcamp.sc.shop.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{

	GoodsDao goodsDao;
	
	@Autowired
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
