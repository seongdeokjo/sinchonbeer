package com.bitcamp.sc.web.basket;

import com.bitcamp.sc.domain.basket.domain.BasketDto;
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
        int cnt = basketService.getDeleteRowByGidx(gidx,midx);
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
        int check = basketService.changeBasketAmount(bDto);
        if(check == 1) {
            result = true;
        }
        return result;
    }

    // 선택된 체크 박스 삭제
    @PostMapping("/deleteBasketByPicking")
    public int deleteBasketByPicking(
            @RequestParam(value="gidxList[]") List<Integer> gidxList,
            @RequestParam(value="midx") long midx
    ) {
        int result = 0;
        log.info("gidx List : " + gidxList.toString());
        log.info("받아온 midx 값 : " + midx);
        // service에 반복문?
        if(!gidxList.isEmpty()) {
            result = basketService.getDeleteRowByGidx(gidxList, midx);
        }
        if(result > 0) {
            log.info("삭제 성공");
        }
        return result;
    }
}
