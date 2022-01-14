package com.bitcamp.sc.domain.tour.service;


import com.bitcamp.sc.domain.tour.repository.TourDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AvailablePeopleService {
	private final TourDao dao;
	// 현재 예약 가능 인원 가져오기
	public Integer selectCount(String date) {
		return dao.selectCount(date);
		}
}
