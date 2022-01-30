package com.bitcamp.sc.review.repository;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.repository.ReviewDao;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
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
    void 리뷰_생성(){
        ReviewVO reviewVO = new ReviewVO("테스트","사용자","hihihihihhi",4,"tour");

        reviewDao.save(reviewVO);

        List<ReviewVO> all = reviewDao.findAll();
        for (ReviewVO vo : all) {
            log.info("list = {}",vo.toString());
        }
    }


}
