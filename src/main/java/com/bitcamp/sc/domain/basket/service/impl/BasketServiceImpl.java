package com.bitcamp.sc.domain.basket.service.impl;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.domain.Basket;
import com.bitcamp.sc.domain.basket.repository.BasketDao;
import com.bitcamp.sc.domain.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            log.info("add count = {}",findBasket.getCount());
            bDao.updateCount(findBasket);
        } else {
            log.info("생성된 장바구니가 존재하지 않습니다.");
            Basket newBasket = bDto.toBasket();
            bDao.save(newBasket);
        }
    }

    // 장바구니 리스트 가져오기
    @Override
    public List<BasketDto> getList(long midx) {
        List<BasketDto> list = new ArrayList<>();
        if (midx != 0) {
            list = bDao.findAllByMidx(midx);
            if (list.isEmpty()) {
                log.info("장바구니 목록이 없습니다.");
            }
        }
        return list;
    }

    // 장바구니 총액 가져오기
    @Override
    public int getTotalPayByMidx(long midx) {
        return bDao.getTotalPay(midx);
    }

    // 장바구니 한 행만 삭제
    @Override
    public int getDeleteRowByGidx(long gidx, long midx) {
        return bDao.deleteRowByGidx(gidx, midx);
    }

    // 장바구니 모두 삭제
    @Override
    public void deleteAllByMidx(long midx) {
        bDao.deleteAll(midx);
    }

    // 장바구니 페이지에서 수량 변경
    @Override
    public int changeBasketAmount(BasketDto bDto) {
        return bDao.changeBasketAmount(bDto);
    }

    // 장바구니 선택한 품목 삭제
    @Override
    public int getDeleteRowByGidx(List<Integer> gidxList, long midx) {
        int result = 0;
        for (int i = 0; i < gidxList.size(); i++) {
            result += bDao.deleteRowByGidx((int) gidxList.get(i), midx);
            log.info("gidx반복 횟수" + 1);
        }
        log.info("result 값" + result);
        return result;
    }
}