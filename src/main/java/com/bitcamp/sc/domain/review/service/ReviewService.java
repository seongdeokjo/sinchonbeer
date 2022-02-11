package com.bitcamp.sc.domain.review.service;

import java.io.IOException;
import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.web.paging.Criteria;
import com.bitcamp.sc.web.review.dto.ReviewEditDto;
import com.bitcamp.sc.web.review.dto.ReviewSaveDto;

public interface ReviewService {

    List<ReviewVO> findAll(Criteria cri);
    ReviewVO findByIdx(long idx);
    int viewCountUp(long idx);
    int countReview(Criteria cri);
    void edit(Long idx, ReviewEditDto editDto) throws IOException;
    void save(ReviewSaveDto saveDto) throws IOException;

}
