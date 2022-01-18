package com.bitcamp.sc.web.basket.controller;

import com.bitcamp.sc.web.basket.dto.BasketDto;
import com.bitcamp.sc.domain.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BasketRestController {

    private final BasketService basketService;

    @GetMapping("/deleteRow")
    public boolean getDeleteRow(@RequestParam("gidx") long gidx,@RequestParam("midx") long midx) {
        boolean result = false;
        int cnt = basketService.deleteRowByGidx(gidx,midx);
        if(cnt > 0) {
            log.info("선택 상품이 삭제되었습니다.");
            result = true;
        }
        return  result;
    }

    @GetMapping("/deleteAllByMidx/{midx}")
    public void deleteAll(@PathVariable("midx") long midx) {
        basketService.deleteAllByMidx(midx);
    }

    @GetMapping("/basket/changeAmount")
    public boolean modifyAmount(BasketDto bDto) {
        boolean result = false;
        return result;
    }

    // 선택된 체크 박스 삭제
    @PostMapping("/deleteBasketByPicking")
    public int deleteBasketByPicking(
            @RequestParam(value="gidxList[]") List<Integer> gidxList,
            @RequestParam(value="midx") long midx) {
        int result = 0;
        log.info("gidx List : " + gidxList.toString());
        log.info("받아온 midx 값 : " + midx);
        if(!gidxList.isEmpty()) {
            result = basketService.deleteGoodsByGidxList(gidxList, midx);
        }
        return result;
    }
}