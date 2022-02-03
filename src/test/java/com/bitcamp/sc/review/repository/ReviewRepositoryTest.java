package com.bitcamp.sc.review.repository;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.repository.ReviewDao;
import com.bitcamp.sc.web.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    ReviewDao reviewDao;

    @Test
    @Transactional
    void 리뷰_조회(){

        Criteria cri = new Criteria();
        List<ReviewVO> all = reviewDao.findAll(cri);
        for (ReviewVO vo : all) {
            log.info("list = {}",vo.toString());
        }
    }


}
