package com.bitcamp.sc.domain.basket.service.impl;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.basket.repository.BasketDao;
import com.bitcamp.sc.domain.basket.service.BasketService;
import com.bitcamp.sc.web.basket.dto.BasketListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketDao bDao;

    // 장바구니 생성
    @Override
    public void saveBasket(BasketDto bDto) {
        Basket findBasket = bDao.getBasket(bDto.getGidx(), bDto.getMidx());
        if (findBasket != null) {
            log.info("이미 장바구니에 값이 존재합니다.");
            findBasket.addCount(bDto.getCount());
            log.info("add count = {}", findBasket.getCount());
            bDao.updateCount(findBasket);
        } else {
            log.info("생성된 장바구니가 존재하지 않습니다.");
            Basket newBasket = bDto.toBasket();
            bDao.save(newBasket);
            log.info("생성된 장바구니 basket = {}", newBasket);
        }
    }

    // 장바구니 리스트 가져오기
    @Override
    public List<BasketListResponse> getList(long midx) {
          return bDao.findAllByMidx(midx).stream().map(BasketListResponse::new) // Basket -> BasketListResponse(Basket)
                  .collect(Collectors.toList());
    }

    // 장바구니 총액 가져오기
    @Override
    public int getTotalPayByMidx(long midx) {
        return bDao.getTotalPay(midx);
    }

    // 장바구니 한 상품만 삭제
    @Override
    public int getDeleteRowByGidx(long gidx, long midx) {
        return bDao.deleteRowByGidx(gidx, midx);
    }

    //회원의 장바구니 모두 삭제
    @Override
    public void deleteAllByMidx(long midx) {
        bDao.deleteAll(midx);
    }

    // 장바구니 선택한 품목 삭제
    @Override
    public int getDeleteGoodsByGidxList(List<Integer> gidxList, long midx) {
        return bDao.deleteGoods(gidxList, midx);
    }
}