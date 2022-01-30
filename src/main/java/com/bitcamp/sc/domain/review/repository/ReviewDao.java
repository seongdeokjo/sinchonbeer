package com.bitcamp.sc.domain.review.repository;

import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;

public interface ReviewDao {
    List<ReviewVO> findAll();
    void save(ReviewVO reviewVO);
}
