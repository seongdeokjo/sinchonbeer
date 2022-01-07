package com.bitcamp.sc.domain.tour.service;

import java.util.List;

import com.bitcamp.sc.domain.order.domain.OrderInfo;

public interface TourService {
	// 투어날짜로 tidx 값 가져오기
	int getTidxByTdate(String tdate);
	// 결제 : 투어 idx 로 투어 날짜 가져오기
	String getTourDateByTidx(long idx);
	// 투어예약 : 주문+결제 완료
	int addTourPeopleByDate(int tourPeople,String tdate);
	// 투어예약 취소
	int subTourPeopleByDate(int tourPeople,String tdate);
	// 주문 정보로 투어 날짜 리스트로 받아오기 
	List<String> getDateToList(List<OrderInfo> list);
}