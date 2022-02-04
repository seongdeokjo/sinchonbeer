package com.bitcamp.sc.domain.review.repository;

import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.web.paging.Criteria;

public interface ReviewDao {
    List<ReviewVO> findAll(Criteria cri);
    int getCountReview(Criteria cri);
    void save(ReviewVO reviewVO);
}
