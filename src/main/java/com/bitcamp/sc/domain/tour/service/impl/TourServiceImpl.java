package com.bitcamp.sc.domain.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.sc.domain.order.domain.OrderInfo;
import com.bitcamp.sc.domain.tour.repository.TourDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.bitcamp.sc.domain.tour.service.TourService;

@Slf4j
@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService {
	
	private final TourDao dao;

	// 투어 날짜로 투어 번호 가져오기
	@Override
	public int getTidxByTdate(String tdate) {
		return dao.getTidxbyTdate(tdate);
	}
	
	// 투어 : 결제 성공시 예약 날짜 인원 증가  / 
	@Override
	public int addTourPeopleByDate(int tourPeople,String tdate) {
		return dao.addTourPeople(tourPeople, tdate);
	}
	
	// 투어 : 결제 취소시 예약 날짜 인원 감소 
	@Override
	public int subTourPeopleByDate(int tourPeople, String tdate) {
		return dao.subTourPeople(tourPeople, tdate);
	}
	
	// 결제 : 투어 날짜로 투어 번호 가져오기
	@Override
	public String getTourDateByTidx(long tidx) {
		return dao.getTourDateByTidx(tidx);
	}

	// 날짜만 담긴 리스트 생성
	@Override
	public List<String> getDateToList(List<OrderInfo> list) {
		List<String> dateList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			String  date =	dao.getTourDateByTidx(list.get(i).getTourIdx());
			dateList.add(date);
		}
		return dateList;
	}
}