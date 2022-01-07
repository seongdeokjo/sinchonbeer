 $(document).ready(function () {
        <!-- script(1). (html1)이메일/비밀번호 찾기 메인화면에서 탭으로 클릭하여 화면전환시키기 -->
        $('#profile-tab').on('click', function () {
            $('.id_tab').addClass('display_none');
            $('.pw_tab').removeClass('display_none');
        });

        $('#home-tab').on('click', function () {
            $('.id_tab').removeClass('display_none');
            $('.pw_tab').addClass('display_none');
        });

        <!-- script(2). (html1)이메일/비밀번호 찾기 메인화면에서 input의 label에 대한 효과.  -->
        //이름input박스를 누르면 label 이름이 fadein 되고
        //이름input박스 바깥을 누르면 label 이름이 사라짐-아이디 중복 확인과 비슷.
        //[이메일 찾기 - 이름]
        $('#username').focus(function () {
            $('#name_label').css('color', 'darkgray');
        });
        //[이메일 찾기 - 전화번호]
        $('#phone').focus(function () {
            $('#phone_label').css('color', 'darkgray');
        });
        //[비밀번호 찾기 - 이름]
        $('#name').focus(function () {
            $('#pwName_label').css('color', 'darkgray');
        });
        //[비밀번호 찾기 - 이메일]
        $('#email').focus(function () {
            $('#pwUserEmail_label').css('color', 'darkgray');
        });

        //공백이면 흰색 글씨로, 공백이 아니면 그레이 글씨색 유지.
        //[이메일 찾기 - 이름]
        $('#username').focusout(function () {
            if ($('#username').val() == '') {//공백일 때
                $('#name_label').css('color', 'white');
            } else {//공백이 아닐 때
                $('#name_label').css('color', 'darkgray');
            }
        });

        //[이메일 찾기 - 전화번호]
        $('#phone').focusout(function
            () {
            if ($('#phone').val() == '') {
                $('#phone_label').css('color', 'white');
            } else {
                $('#phone_label').css('color', 'darkgray');
            }
        });
        //[비밀번호 찾기 - 이름]
        $('#name').focusout(function () {
            if ($('#name').val() == '') {//공백일 때
                $('#pwName_label').css('color', 'white');
            } else {//공백이 아닐 때
                $('#pwName_label').css('color', 'darkgray');
            }
        });
        //[비밀번호 찾기 - 이메일]
        $('#email').focusout(function () {
            if ($('#email').val() == '') {//공백일 때
                $('#pwUserEmail_label').css('color', 'white');
            } else {//공백이 아닐 때
                $('#pwUserEmail_label').css('color', 'darkgray');
            }
        });

        <!-- script(3) (html1)이메일/비밀번호 찾기 메인화면에서 이메일찾기 form에 대한 ajax. 성공 시 html1과 html2를 이어줌. -->
        let epc = $('#emailPwFindFormContent');
        let ec = $('#emailSuccessContent');
        let pc = $('#pwContinueContent');

        /* $('#emailSearch').submit(function() {*///ec,pc 위에 이 스크립트가 있을 때에는 왜 작동이 안되었을까?
        $('#emailSearch').on('click', function () {
            let name = $('#username').val();
            let phone = $('#phone').val();
            console.log(name + ':' + phone);
            $.ajax({
                url: '/members/find/email',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "name": name,
                    "phone": phone
                }),
                success: function (data) {
                    if (data.trim() != '') {
                        console.log(data);
                        epc.addClass('display_none');
                        ec.removeClass('display_none');
                        $('#inquiryEmail').html(data);
                    } else {
                        alert('일치하는 이메일 정보가 없습니다. 다시 입력해 주세요.')
                    }
                }
            });
        });
        <!-- script(4) (html1)이메일/비밀번호 찾기 메인화면에서 비밀번호 찾기 form에 대한 ajax. 성공 시 html1에서 html3으로 이어줌. -->
        $('#pwSearch').on('click', function () {
            let name = $('#name').val();
            let email = $('#email').val();
            console.log(name + ':' + email);
            $.ajax({
                url: '/members/find/pw',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "name": name,
                    "email": email
                }),
                success: function (data) {
                    if (data.trim() != '') {
                        console.log(data);
                        epc.addClass('display_none');
                        pc.removeClass('display_none');
                        $('#inquiryPw').html(data);
                    } else {
                        alert('일치하는 이메일 정보가 없습니다. 다시 입력해 주세요.')
                    }
                }
            });
        });

        <!-- script(5~6) (html3)이메일로 인증보낼지 여부 묻는 비밀번호 찾기화면-->
        $('#pwContinueBtn').on('click', function () {<!-- script(5) 인증번호가 전송 중일 때 버튼의 달라지는 효과.  -->
            $('#pwContinueBtn').val('전송 중...');
            $('#pwContinueBtn').attr('disabled', true);
            //$('#pwContinueBtn').addClass('.sending'); --이거 왜 적용이 안되는거지?
            $('#pwContinueBtn').css({
                backgroundColor: 'white',
                border: '1px solid red',
                //cursor: 'progress',
                color: 'red',
                'font-weight': 'bold'
            });

            <!-- script(6) (html3)비밀번호 찾기화면에서 찾은 이메일주소로 인증번호 보내는 진행 계속할 것인지 묻는 것에 대한 ajax. 성공 시 htm3에서 html4으로 이어줌. -->
            //'계속'을 누르면 ajax로 인증메일을 보내고  메일 보냈는지 여부를 데이터로 받기
            let inquiryEmail = $('#inquiryPw').html();
            let pwInputCode = $('#pwInputCodeContent');
            console.log(inquiryEmail);
            $.ajax({
                url: '/inquiry/pw/auth',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({"userEmail": inquiryEmail,}),
                success: function () {//인증번호를 입력하는 다음창이 나오게 하기.
                    alert('인증번호가 전송되었습니다. 메일을 확인해 주세요.');
                    //현재 페이지가 들어가고
                    $('#pwContinueContent').addClass('display_none');
                    //pwInputCodeContent 영역이 나옴
                    $('#pwInputCodeContent').removeClass('display_none');
                }
            });
        });
        <!-- script(7) (html4)비밀번호 찾기화면에서 인증번호 입력하여 인증 성공하면 비밀번호 재설정하는 페이지로 보내주는 ajax. 성공 시 htm4에서 html5으로 이어줌. -->
        $('#codeBtn').on('click', function () {
            let inquiryEmail = $('#inquiryPw').html();
            let pcc = $('#pwContinueContent');
            let pic = $('#pwInputCodeContent');
            let inputNum = $('#authNum').val();
            console.log(inquiryEmail);
            $.ajax({
                url: '/members/check/code',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "userEmail": inquiryEmail,
                    "inputNum": inputNum
                }),
                success: function (data) {
                    console.log(data);
                    //인증번호를 입력하는 다음창이 나오게 하기.
                    if (data == 'Y') {//alert('성공');
                        alert('인증되었습니다.');
                        pic.addClass('display_none');
                        $('#pwResetContent').removeClass('display_none');
                    } else if (data == 'N') {
                        alert('잘못 입력하셨습니다. 다시 입력해 주세요.');
                        return false;
                    }
                }
            });
        });

        <!-- script(8~9) (html5)비밀번호 재설정form 화면 -->
        $('#pwResetBtn').on('click', function () {<!-- script(8) 비밀번호 재설정 form의 정규식과 제한 설정해 두기. -->
            //확인 버튼 눌렀을 때 비밀번호 재설정 form에 대한 반응
            //1. 비밀번호 input이 빈칸일 경우 메시지 띄우고 빨간색 border 주기//  '새로운 비밀번호를 입력해 주세요.'
            //2. 비밀번호 확인 input이 빈칸일 경우 ...//					'새로운 비밀번호를 다시 한 번 입력해 주세요.'
            //3. 두 input이 일치하지 않을 경우 ....//						'비밀번호가 일치하지 않습니다.'
            //4. 비밀번호 정규식에 맞추어서 입력하지 않을 경우 ...//				'~~하게 입력해 주세요.'
            //5. input을 눌렀을 경우 메시지와 border 빨간색이 없어짐.
            let pwExp = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,20}$/;
            //비밀번호 입력이 안되어 있을 경우
            if ($('#newPw').val() == '') {
                $('#newPwInput').css('border-color', 'red');
                $('.newPwMsg').html('새로운 비밀번호를 입력해 주세요.');
                return false;
                //비밀번호 입력 정규식
            } else if (!pwExp.test($('#newPw').val().trim())) {
                $('.newPwMsg').html('영어, 숫자, 특수기호 모두를 포함해서 8~30자리 입력해 주세요.');
                $('#newPwInput').css('border-color', 'red');
                return false;
            }
            //비밀번호 확인 입력이 안되어 있을 경우
            if ($('#reNewPw').val() == '') {
                $('.newPwMsg').html('새로운 비밀번호를 한 번 더 입력해 주세요.');
                $('#newPwInput').css('border-color', '#ddd');
                $('#newPwInput').css('border-bottom-color', 'red');
                $('#reNewPwInput').css('border-color', 'red');
                return false;
            }
            //비밀번호 두 개가 일치하지 않을 경우
            if ($('#newPw').val().trim() != $('#reNewPw').val().trim()) {
                $('.newPwMsg').html('비밀번호가 일치하지 않습니다.');
                $('#newPwInput').css('border-color', 'red');
                $('#reNewPwInput').css('border-color', 'red');
                return false;
            }

            <!-- script(9). 재설정 form에서 입력한 비밀번호가 db에 잘 저장되도록 하는 ajax. html5에서 html6으로 이어줌. -->
            //ajax 시작
            let inquiryEmail = $('#inquiryPw').html();
            console.log(inquiryEmail);
            $.ajax({
                url: '/members/find/resetPw',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "userEmail": inquiryEmail,
                    "userPw": $('#newPw').val()
                }),
                success: function (data) {
                    console.log(data);
                    //db에 새 비밀번호 저장이 완료되었다면 y 반환.
                    if (data == 'Y') {
                        $('#pwResetContent').addClass('display_none');
                        $('#pwSuccessContent').removeClass('display_none');
                    } else if (data == 'N') {
                        alert('실패! 다시 입력해 주세요.');
                        return false;
                    }
                }
            });
        });

        //input을 눌렀을 경우 메시지 초기화.
        $('#newPw').focus(function () {
            $('.newPwMsg').html('');
            $('#newPwInput').css('border-color', '#ddd');
            $('#reNewPwInput').css('border-color', '#ddd');
        });
        $('#reNewPw').focus(function () {
            $('.newPwMsg').html('');
            $('#newPwInput').css('border-color', '#ddd');
            $('#reNewPwInput').css('border-color', '#ddd');
        });
    });