/**
 * 
 */
 	$(function() {
			$('#count').children('option:not(:first)').hide();
			//input을 datepicker로 선언
			$("#datepicker")
					.datepicker(
							{
								dateFormat : 'yy-mm-dd (D) ' //달력 날짜 형태
								,
								closeText : '닫기',
								showOtherMonths : true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
								,
								showMonthAfterYear : true // 월- 년 순서가아닌 년도 - 월 순서
								,
								changeYear : true //option값 년 선택 가능
								,
								changeMonth : true //option값  월 선택 가능                
								,
								showOn : 'focus' //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
								// , buttonImage: "https://brewery.jejubeer.co.kr/static/images/reservationRenew/img_icon_calendar.png" //버튼 이미지 경로
								// , buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함

								,
								buttonText : "선택" //버튼 호버 텍스트              
								,
								yearSuffix : "년" //달력의 년도 부분 뒤 텍스트
								,
								monthNamesShort : [ '1월', '2월', '3월', '4월',
										'5월', '6월', '7월', '8월', '9월', '10월',
										'11월', '12월' ] //달력의 월 부분 텍스트
								,
								monthNames : [ '1월', '2월', '3월', '4월', '5월',
										'6월', '7월', '8월', '9월', '10월', '11월',
										'12월' ] //달력의 월 부분 Tooltip
								,
								dayNamesMin : [ '일', '월', '화', '수', '목', '금',
										'토' ] //달력의 요일 텍스트
								,
								dayNamesShort : [ '일', '월', '화', '수', '목', '금',
										'토' ],
								dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',
										'금요일', '토요일' ] //달력의 요일 Tooltip
								,
								minDate : "-5y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
								,
								maxDate : "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
								,
								beforeShowDay : noBefore,
								beforeShow : function() {

								},
								onSelect : function() {
									$('#msg').val('잔여석:');
									var date = $('#datepicker').val();
									// 서버로 전달할 데이터
									var sub = date.substring(0, 10);
									$('#select').val(sub);
									console.log(sub);

									var test = $('#select').val();
									console.log(test);

									$.ajax({
											url : '/tour/count',
											type : 'get',
											data : {
												mid : sub
												},
											success : function(data) {
												selectReset();
												console.log(data);
												var num = $('#count option').length;
												$('#msg').val(
													$('#msg').val()+ data+ '명');
													// 가능인원이 0인 경우
													if(data == 0) {
														$('#count').prop('disabled','disabled');
														// 가능인원이  최대 선택인원4명 보다 작은경우 남은인원까지만 선택가능	
													} 
													if(data < num){
														for (i = num; i > data; i--) {
															$('#count option:eq('+ i+ ')').hide();
																}
													}
												},
												error : function() {
													// 에러 처리
												}
											});

								}
							});

			//초기값을 오늘 날짜로 설정해줘야 합니다.
			$('#datepicker').datepicker('setDate', '+1D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)

			$('#btn').on('click', function(e) {
				if ($('#select').val() == 0) {
					alert('날짜를 선택해주세요');
					return false;
				}
				if ($('#count').val() == 0) {
					alert('인원을 선택해주세요');
					return false;
				}
				let idx = $('#login').val();
				console.log(idx);
				if(idx == 'Y'){
					alert('로그인 후 이용해 주새요.');
				}
				
			});
			
			$("select").each(function () {
			    $(this).val($(this).find('option[selected]').val());
			});

		});
		function selectReset() {
			$('#count option').show();
			//$('#count').val('0').prop("selected", true);
			$('#count').attr('disabled', false);
		}

		// [다음날 ~ 그 다음달 말일]까지만 선택 가능한 함수
		function noBefore(date) {
			// 현재 날짜 가져오기
			var cur = new Date();

			// 가져온 날짜의 연,월,일 저장
			var curYear = cur.getFullYear();
			var curMonth = cur.getMonth();
			var curDay = cur.getDate();

			//   그 달의 말일을 구하는 함수 : js 는 0 부터 11 까지로 표현 (0 -> 1월 / 0> 1일)  -> 각각 자리는 year,month,day 
			// datepicker 에서 보여지는 날짜가 다음날 부터가 기본 값이므로 day 자리에 0이 아닌 1부터 시작 ->해당 월의 마지막 일 정상 출력                      
			var next = new Date(curYear, curMonth + 2, 1);
			/*  if ( date < cur ) {
			        return [false];
			    }
			    if(date >= next){
			        return [false];
			    } */
			// 현재 날짜 보다 이전이거나 다음달 말일 이후인 경우에는 사용 불가
			if (cur >= date || date >= next) {
				return [ false ];
			}
			return [ true ];
		}
		