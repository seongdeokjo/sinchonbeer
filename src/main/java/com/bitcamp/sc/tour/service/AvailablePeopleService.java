package com.bitcamp.sc.tour.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.tour.repository.TourDao;

@Service
public class AvailablePeopleService {
	private TourDao dao;
	
	@Autowired
	public AvailablePeopleService(TourDao dao) {
		this.dao = dao;
	}
	
	// 현재 예약 가능 인원 가져오기
	public int selectCount(String date) {
		return dao.selectCount(date);
		}
}
