 $(function() {
		let midx = $('#midx').val();
		// 정규식 : 숫자만 가능 11자리		 
		//	let regexp = /^[0-9]{11}*$/	
		// 숫자만 입력가능한 input
		$("input:text[name=phoneNumber]").keyup(function(e) {
			reg = /[^0-9]/gi;
			v = $(this).val();
		    if (reg.test(v)) {
		    	$(this).val(v.replace(reg, ''));
		    	$(this).focus();
		    }
		});
		// 문자 인증 : 이름,이메일은 세션에 저장된 loginInfo를 통해 가져옴 ->
		// 휴대전화는 직접 입력 (db 멤버 휴대전화 번호와 비교할지?) ->
		// 인증번호 전송 버튼 : 입력된 휴대 전화 번호로 6자리 난수 전송 -> ajax url:'/sendMessage type:'get' ->
		// 전송한 인증번호와 사용자가 입력한 인증번호가 같은지 검증 -> 
		// 일치 : alert(인증 확인) 
		// 불일치 : alert('인증번호가 일치 하지 않습니다. 다시 입력해주세요.') location.reload()?  
		$('#submitMessage').on('click',function(){
			let phone = $('#userphone').val();
			console.log(phone);
			$.ajax({
				url : '/verifyMyPhone',
				type : 'get',
				data : {
					phone : phone					
				},
				success : function(result){
					if(result == 'N'){
						alert('등록된 번호가 아닙니다. \n 다시 시도해주세요.');
						return false;
					}
					$.ajax({
						url : '/sendMessage',
						type : 'get',
						data : {
							 phone : phone
							},
						success : function(res){
							console.log(res);
							$('#checkNum').on('click',function() {
								if (res == $('#verifyNum').val()) {
									alert('인증 성공! \n 휴대폰 인증이  정상적으로 완료되었습니다.');
									// 확인 박스 상태 변경
									$('#verifyNum').val('');
									$('#checkNum').attr('disabled','disabled');	
								} else {
									alert('인증오류 \n 인증번호가 올바르지 않습니다.\n 다시 인증해주세요.');
									location.reload();
								}
							});
						},
						error : function(res){
							// 에러 처리
						}
					});
				},
				error : function(){
					// 에러 처리
				}
			}); 
		});
		let payHow = $('#payHow').val();
		console.log(payHow);
		 
		let reDate = $('#appendDate').text();
		// 해당 날짜의 요일을 리턴하는 함수	
		function day(reDate) {
			let week=['월','화','수','목','금','토','일'];
			let dayOfWeek = week[new Date(reDate).getDay()];
			return dayOfWeek;
		}
		// 리턴된 요일을 날짜에 append로 추가 
		let result = day(reDate);
		$('#appendDate').append(' ('+result+')');
			// 동의 후 확인 클릭
			$('#modalButton').click(function() {
				$('#agreementTermsOfUse').val('Y');
				$('#agreementTermsOfUse').prop('checked', true);
		});
	    // 결제 수단 선택 시 약관 동의 버튼 체크 여부 확인
		$('input[name=paymentType]').on('click',function() {
			if (!$('input[name=agreementPrivacy]').is(':checked') || 
				!$('input[name=agreementTermsOfUse]').is(':checked') ||
				!$('input[name=agreementRefund]').is(':checked') ) {

				alert("동의 버튼을 클릭해주세요.");
				$(this).focus();
					return false;
			} else {
				console.log($('input[name=paymentType]:checked').val());
				$('#payHow').val($('input[name=paymentType]:checked').val());
				console.log($('#payHow').val());
			}
		});
	});

		// 카카오 페이 결제
		function frmSubmit() {
			if ($('#payHow').val() == '') {
				alert('결제 수단이 선택되지 않았습니다. \n 결제수단을 선택해주세요.');
				$('input[name=paymentType]').focus();
				return false;
			}
			window.name = "opner_win";
			let myForm = document.payForm;
			let myWin = window
					.open("about:blank", "kakaoPayWin",
							"status=yes, scrollbars=yes, width=440, height=500, left=300, top=100");
			myForm.method = "post";
			myForm.target = "kakaoPayWin";
			myForm.action = "/kakaoPay/tour";
			myForm.submit();
		}