package com.bitcamp.sc.domain.review.service;

import java.io.IOException;
import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.web.review.dto.ReviewSaveDto;

public interface ReviewService {

    List<ReviewVO> findAll();
    long save(ReviewSaveDto saveDto) throws IOException;

}
