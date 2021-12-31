/**
 * 
 */
 
		// 현재 날짜 이전의 날짜는 선택 비활성화
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

			// 현재 날짜 보다 이전이거나 다음달 말일 이후인 경우에는 사용 불가
			if (cur >= date || date >= next) {
				return [ false ];
			}
			return [ true ];
		}   
        
	
     // 예약 변경 버튼 클릭시    
	     function button(idx){
	    	 console.log(idx);
	    	 $('#myModal'+idx).on('show.bs.modal',function(){   		 
	       			// datepicker 관리 js
	    			//input을 datepicker로 선언
	    			$('#datepicker'+idx).datepicker(
	    					{
	    						dateFormat : 'yy-mm-dd' //달력 날짜 형태
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
	    						,
	    						buttonText : "선택" //버튼 호버 텍스트              
	    						,
	    						yearSuffix : "년" //달력의 년도 부분 뒤 텍스트
	    						,
	    						monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
	    								'7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 텍스트
	    						,
	    						monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
	    								'7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip
	    						,
	    						dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 텍스트
	    						,
	    						dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
	    						dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일',
	    								'토요일' ] //달력의 요일 Tooltip
	    						,
	    						minDate : "-5y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	    						,
	    						maxDate : "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	    						,
	    						beforeShowDay : noBefore,
	    						
	    						onSelect : function() {
	    							var date = $('#datepicker'+idx).val();
	
	    							console.log(date);
	    							$('#check'+idx).val(date);
	
	    							$.ajax({
	    								url : '/tour/count',
	    								type : 'get',
	    								data : {
	    									mid : date
	    								},
	    								success : function(data) {
	    									var tpeople = $('#tourPeople'+idx).val();
	    									console.log(data);
	    									if (data == 0 || tpeople > data) {
	    										$('#count'+idx).text('예약이 완료되었습니다.');
	    										// 예약 가능인원이 0 이거나 현재 예약한 인원보다 적게 남았을 경우 선택창 비활성화
	    										$("#cancle"+idx).attr("disabled",true);
	    									} else {
	    										$('#count'+idx).text('가능인원: ' + data);
	    										$("#cancle"+idx).attr("disabled",false);
	    									}
	    								},
	    								complete : function() {
	
	    								}
	    							}); /* ajax 끝 */
	
	    						}
    					});
	       	 });	 
	     }
     // 모달 취소 버튼 클릭시
     function cancle(idx){  	
    		$('#count'+idx).text('');
    		$('#check'+idx).val('');
    	 $('#myModal'+idx).modal('hide');
     }
     
     // 모달 변경 버튼 클릭시 
     function change(idx){
    	let date =  $('#check'+idx).val();
    	 $('#newDate'+idx).val(date);
    	 $('#myModal'+idx).modal('hide');
     }
   
     
     // 예약 확정 버튼 클릭시
     function modify(idx){
    		 /* 예약 변경 확정 버튼 클릭시 js */
		if (confirm('변경된 예약일로 바꾸시겠습니까?') == true) {
			// 새로운 날짜 선택을 하지 않았을 경우
			if ($('#newDate'+idx).val() == $('#resDate'+idx).val()) {
				alert('변경된 내역이 없습니다.');
				return false;
			} else {
				
				let changeInfo = {
						// 에약된 인원
						 "tourPeople" : $('#tourPeople'+idx).val(),
						// 변경된 날짜	
						"newDate" : $('#newDate'+idx).val(),
						// 현재 날짜
						"resDate" : $('#resDate'+idx).val(),				
						// 주문 번호 
						"oidx" : idx	
				};
				
				// 
				$.ajax({
					url : '/tour/changeTour',
					type : 'post',
					contentType : "application/json",
					data :JSON.stringify(changeInfo),			
					success : function(data) {
						console.log(data);
						if(data == true){
							alert('예약이 변경되었습니다.');
							sendChangeMail(changeInfo);
							// 현재 페이지 다시로드	
							location.reload();
						}
					},
					error : function(request, status, error) {
						alert('서버 통신에 문제가 발생했습니다. 다시 실행해주세요.');
						console.log("code:" + request.status + "\n" + "error:" + error);
					}
				}); // ajax 끝 
			}

		} else {
			location.reload();
			return false;
		}		
     }
     
     // 예약 변경 안내 메일 
     function sendChangeMail(changeInfo){
    	 console.log(changeInfo);
    	 $.ajax({
    		 url : '/tour/sendMailchange',
    		 type : 'post',
    		 contentType : "application/json",
    		 data : JSON.stringify(changeInfo)
    	 });
     }
     
     
     
     // 예약 취소 버튼 클릭시
     function reservationCancle(idx){
    	let people = $('#tourPeople'+idx).val();
    	let tdate = $('#resDate'+idx).val();
    	let oidx = idx;
    	
    	console.log(idx);
    	console.log(people+' : '+tdate);
    	console.log(typeof idx +" : "+ typeof people +" : "+ typeof idx);
		if (confirm('예약을 취소하시겠습니까?') == true) {
		
			$.ajax({
				url : '/tour/cancleOrder/',
				type : 'get',
				data : {
					"people" : people,
					"tdate" : tdate,
					"idx" : idx
				}, 
				success : function(data){
					if(data == 1){
						alert('예약이 취소되었습니다.');
						cancleMail(idx);
						location.reload();
					}
				},
				error : function(request, status, error) {
					alert('서버 통신에 문제가 발생했습니다. 다시 실행해주세요.');
					console.log("code:" + request.status + "\n" + "error:" + error);
				}
			});
						
		} else {
			return false;
		}
		
     }
     
     function cancleMail(idx){
    	$.ajax({
    		url : 'sendCancleMail/'+idx,
    		type : 'get',
    		success : function(){
    		
    				console.log('취소 메일 전송 성공');
    			}
    		});
    	}   
     