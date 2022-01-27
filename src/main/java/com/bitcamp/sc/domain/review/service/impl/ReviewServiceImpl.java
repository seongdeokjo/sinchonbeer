package com.bitcamp.sc.domain.review.service.impl;

import java.util.List;

import com.bitcamp.sc.domain.review.domain.ReviewVO;
import com.bitcamp.sc.domain.review.repository.ReviewDao;
import com.bitcamp.sc.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao reviewDao;

    @Override
    public List<ReviewVO> findAll() {
        return reviewDao.findAll();
    }
}
