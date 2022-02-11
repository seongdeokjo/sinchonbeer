package com.bitcamp.sc.domain.review.repository;

import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.web.paging.Criteria;

public interface ReviewDao {
    List<ReviewVO> findAll(Criteria cri);
    ReviewVO findByIdx(long idx);
    int updateCountView(long idx);
    int getCountReview(Criteria cri);
    void save(ReviewVO reviewVO);
    void edit(ReviewVO reviewVO);
}
