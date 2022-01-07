//package com.bitcamp.sc.tour.repository.impl;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.bitcamp.sc.tour.domain.TourOrderInfo;
//import com.bitcamp.sc.tour.repository.TourDao;
//
//@SpringBootTest
//public class MybatisTourDaoTest {
//	
//	@Autowired
//	TourDao dao;
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 가능인원조회() {
//		
//		// given
//		String date = "2021-09-21";
//		
//		
//		// when
//		int result = dao.selectCount(date);
//		
//		//then
////		assertNotNull(result, "정상적으로 가져옴");
//		assertEquals(12, result);
//		
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 투어예약완료() {
//	
//		long midx = 3;
//		String date = "2021-09-11";
//		
//		dao.addTourPeople(midx, date);		
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 투어취소() {
//		long midx=3;
//		String date ="2021-09-11";
//		
//		dao.subTourPeople(midx, date);
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 투어예약변경() {
//		// 멤버 번호
//		long midx=3;
//		// 기존 예약 날짜
//		String currentDate ="2021-09-11";
//		// 바뀌는 예약 날짜
//		String newDate = "2021-09-12";
//		String category = "tour";
//		
//		//select: 주문 테이블에서  회원의 투어예약 내역을 조회 화면에 뿌려줌 -> 
//		// 회원이 날짜를 선택 해당 날짜의 가능인원 여부를 비동기 통신으로 가져옴 -> 
//		// 바뀐 날짜가 선택되고 예약 변경 확정 버튼을 누르게 되면 회원번호와 새로운 날짜가 파라미터로 전송 -> 
//		// 주문 테이블에 있는 투어번호키가 새로운 날짜로 update 바뀜 - > 
//		// 동시에 투어에 기존예약 날짜의 인원은 (-) -> 바뀐예약 날짜의 인원은 (+)  
//		List<TourOrderInfo> selectTourOrders = new ArrayList<>();
//		selectTourOrders = dao.getTourOrderByMidx(midx, category);
//		assertThat(selectTourOrders.size()).isEqualTo(1);
//		
//		
//		//update: 주문 테이블 tidx 변경
//		dao.changeDateByMidx(midx,newDate );
//		//update : 기존 날짜 변경
//		dao.subTourPeople(midx, currentDate);
//		//update : 바뀐 날짜 변경 
//		dao.addTourPeople(midx, newDate);
//		
//		
//	}
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 투어날짜확인하기ByTidx() {
//
//		long idx = 23;
//	String expectedDate = "2021-09-25";	
//	String tdate =	dao.getTourDateByTidx(idx);
//		
//	assertEquals(expectedDate, tdate, "투어 번호로 투어 날짜 가져오기");	
//		
//			
//				
//	}
//	
//	
//	
//	// memberdao 테스트 ---> null....
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 로그인테스트() {
//
//		String email = "cool@naver.com";
//		String pw= "1111";
////		Member member = mdao.selectByEmailPw(email, pw);
//		
//			
////		System.out.println(member.getIdx());
//				
//	}
//	
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	void 투어예약변경하기() {
//
//	String newDate ="2021-09-05";
//	String resDate ="2021-09-06";
//	int tourPeople = 3;
//	
//	dao.modifyTour(tourPeople, newDate, resDate);
//	
//
//		
//		
//			
//				
//	}
//	
//	
//	
//	
//	
//	
//}
