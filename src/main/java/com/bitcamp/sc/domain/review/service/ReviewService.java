package com.bitcamp.sc.domain.review.service;

import java.util.List;
import com.bitcamp.sc.domain.review.domain.ReviewVO;
public interface ReviewService {

    List<ReviewVO> findAll();

}
