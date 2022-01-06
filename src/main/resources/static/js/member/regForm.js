let element_wrap;
$(document).ready(function () {
    //script(1) submit : 주소를 제외하고 빈칸 없이 입력해야만 회원가입이 넘어감. 
    let email = $('#email');
    let pw = $('#pw');
    let repw = $('#repw');
    let name = $('#username');
    let phone = $('#phone');
    let address1 = $('#address');
    let address2 = $('#detailAddress');
    $('#submitBtn').on('click',function () {
        //이메일 공백일 경우 메시지 출력
         if (email.val().trim().length < 1) {
            $('#email + div.msg').html('<p>필수항목입니다.</p>');
            $('#email + div.msg').css('display', 'block');
            email.addClass('red_outline');
            return false;
        }
        else {
             let emailEx = /\w+@\w+\.\w+/;
            //이메일 입력양식 제한
            if (!emailEx.test($(email).val().trim())) {
                $('#email + div.msg').html('<p>이메일 형식에 맞게 입력해 주세요</p>');
                $('#email + div.msg').css('display', 'block');
                email.addClass('red_outline');
                return false;
            }
        }

        //비번 정규식: 대/소문자 포함하여 8~16자 , 숫자나 특수기호 포함.
        //비밀번호의 공백문자 확인
        if (pw.val().trim().length < 1) {
            $('#repw + div.msg').html('<p>필수항목입니다.</p>');
            $('#repw + div.msg').css('display', 'block');
            pw.addClass('red_outline');
            repw.addClass('red_under_outline');

            return false;
        }
        else {  //비밀번호의 입력양식 제한
            let pwExp = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}$/;
            if (!pwExp.test($(pw).val().trim())) {
                $('#repw+div.msg').html('<p>영어, 숫자, 특수기호 모두를 포함해서 8~30자리 입력해 주세요.</p>')
                $('#repw+div.msg').css('display', 'block');
                pw.addClass('red_outline');
                repw.addClass('red_under_outline');
                console.log(pw.val());
                return false;
            }
        }

        // 비밀번호 일치 여부 확인하기
        if (pw.val().trim() != repw.val().trim()) {
            $('#repw+div.msg').html('<p>비밀번호가 일치하지 않습니다.</p>');
            $('#repw+div.msg').css('display', 'block');
            $('#pw_box').addClass('red_outline');
            pw.addClass('red_outline');
            repw.addClass('red_under_outline');
            return false;
        }
        //이름 : 한글 또는 영문
        //이름의 공백문자 확인.
        if (name.val().trim().length < 1) {
            $('#username+div.msg').html('<p>필수항목입니다.</p>');
            $('#username+div.msg').css('display', 'block');
            name.addClass('red_outline');
            return false;
        } else { //이름의 한글입력 제한
            let nameExp = /^[가-힣a-zA-Z]+$/;
            if (!nameExp.test($(name).val().trim())) {
                $('#username+div.msg').html('<p>이름을 올바르게 입력해주세요.</p>');
                $('#username+div.msg').css('display', 'block');
                name.addClass('red_outline');
                return false;
                
            }
        }
        
        if (phone.val().trim().length < 1) {
            $('#phone+div.msg').html('<p>필수항목입니다.</p>');
            $('#phone+div.msg').css('display', 'block');
            phone.addClass('red_outline');
            return false;
        } else {
            //전화번호 : - 없이 숫자만 6자 이상
            let phoneExp = /^[0-9]{5,12}$/;
            if (!phoneExp.test($(phone).val().trim())) {
                $('#phone+div.msg').html('<p>연락처를 올바르게 입력해 주세요.</p>');
                $('#phone+div.msg').css('display', 'block');
                phone.addClass('red_outline');
                return false;
            }
        }
         
        if(address1.val().trim().length < 1){
        	 $('#extraAddress+div.msg').html('<p>필수항목입니다.</p>');
             $('#extraAddress+div.msg').css('display', 'block');
             address1.addClass('red_outline');
             address2.addClass('red_under_outline');
             return false;
        }
    });

    //클릭하면 메시지 안보이게 동작하기
    $(email).focus(function () {
        $('#email+div.msg').css('display', 'none');
        $('#email+div.msg').html('');
        email.removeClass('red_outline')
    });
    $(pw).focus(function () {
        $('#repw+div.msg').css('display', 'none');
        $('#repw+div.msg').html('');
        pw.removeClass('red_outline');
        repw.removeClass('red_under_outline');
    });
    $(repw).focus(function () {
        $('#repw+div.msg').css('display', 'none');
        $('#repw+div.msg').html('');
        pw.removeClass('red_outline');
        repw.removeClass('red_under_outline');
    });
    $(username).focus(function () {
        $('#username+div.msg').css('display', 'none');
        $('#username+div.msg').html('');
        name.removeClass('red_outline')
    });
    $(phone).focus(function () {
        $('#phone+div.msg').css('display', 'none');
        $('#phone+div.msg').html('');
        phone.removeClass('red_outline')
    });
    $(address1||address2).focus(function () {
        $('#extraAddress+div.msg').css('display', 'none');
        $('#extraAddress+div.msg').html('');
        address1.removeClass('red_outline')
        address2.removeClass('red_under_outline')
    });
	
    // script (2) 아이디 중복 체크
	
    // 이메일이 빈칸일 때는 ajax로 넘어가기 전에 빈칸인지 체크하고 아무런 효과 주지 않기
	$('#email').focusout(function() {
		console.log($(this).val());
		if($(this).val() == null || $(this).val() == ''||$(this).val() == undefined){
			return false;
		}
		
		$.ajax({ 
			url : '/members/check/email',
			type : 'post',
			contentType: 'application/json; charset=UTF-8;',
			data : $(this).val(),
			success : function(data) {
				console.log(data);
				// data : Y / N
				console.log('data success 접근 성공');
				if (data == 'N') {
					console.log('중복되었음');
					$('#email + div.msg').html('<p>중복된 이메일입니다.</p>');
		            $('#email + div.msg').css('display', 'block');
		            $('#email').addClass('red_outline');
		            return false;
				}else{
					console.log('중복되지 않았음');
				}
			},
			error : function(request, status, error) {
				alert('서버 통신에 문제가 발생했습니다. 다시 실행해주세요.');
			}
		});
	});

    // script (3) 카카오 주소 api --

    // 우편번호 찾기 찾기 화면을 넣을 element
    element_wrap = document.getElementById('wrap');
    //(수정) iframe을 넣은 element 영역 외 클릭 시 element를 안보이게 한다.
    $('body').click(function (e) {
        if (!$(e.target).hasClass('foldTarget')) {
            element_wrap.style.display = 'none';

            // (수정) ifream을 넣은 element가 사라지면 주소 필드를 보이게 한다.
            document.getElementById('address').style.display = 'block';
        }
    });
    //주소 api 끝
});

function execDaumPostcode() {
    // (수정) 주소 필드를 안보이게 한다.
    document.getElementById('address').style.display = 'none';

    // 현재 scroll 위치를 저장해놓는다.
    let currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function (data) {
            // 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분.
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let addr = ''; // 주소 변수
            let extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // (수정) 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' || data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;

            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // (수정) 참고항목을 주소 필드에 넣는다.
            document.getElementById("address").value += extraAddr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_wrap.style.display = 'none';

            // (수정) ifream을 넣은 element가 사라지면 주소 필드를 보이게 한다.
            document.getElementById('address').style.display = 'block';

            // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
            document.body.scrollTop = currentScroll;
        },
        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
        onresize: function (size) {
            element_wrap.style.height = 300 + 'px';
        },
        width: '100%',
        height: '100%'
    }).embed(element_wrap);
    // iframe을 넣은 element를 보이게 한다.
    element_wrap.style.display = 'block';
}