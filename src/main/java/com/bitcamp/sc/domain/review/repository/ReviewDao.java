package com.bitcamp.sc.domain.review.repository;

import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;

public interface ReviewDao {


    List<ReviewVO> findAll();
    long save(ReviewVO reviewVO);
}
