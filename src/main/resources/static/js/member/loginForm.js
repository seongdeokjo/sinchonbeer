$(function () {
    $('#loginForm').submit(function(event) {
        if ($('#memail').val() == '') {
            $('#loginForm div.msg').removeClass('display_none');
            $('#loginForm div.msg').html('이메일을 입력해 주세요');
            $('.form_box').css('border-color', 'red');
        }else if ($('#mpw').val() == '') {
            $('#loginForm div.msg').removeClass('display_none');
            $('#loginForm div.msg').html('비밀번호를 입력해 주세요');
            $('.form_box').css('border-color', 'red');
        }
        event.preventDefault();
        ajax_login();
    });

    //이메일/pw의 input을 누르면 메시지 효과 사라지게 하기.(메시지, border 빨간색)
    $('#memail, #mpw').focus(function() {
        $('#loginForm div.msg').addClass('display_none');
        $('.form_box').css('border-color', '#ddd');
    });
});

function ajax_login() {
    let allData = {
        "memail": $('#memail').val(),
        "mpw": $('#mpw').val(),
        "redirectUri": $('#referer').val(),
        "reEmail": ($("input:checkbox[id='emailChk']").is(":checked")) ? 'on' : ''
    };

    $.ajax({
        url : '/login',
        type : 'post',
        contentType : "application/json",
        data : JSON.stringify(allData),
        success : function(data) {
            console.log(data);
            if (data.result == true) {
                //로그인 성공했지만 이전페이지가 없거나 회원가입 성공한 페이지가 이전 페이지라면  메인페이지로 이동하기.
                if (allData.redirectUri == ""
                    || allData.redirectUri == null
                    || allData.redirectUri == undefined) {
                    console.log('redirect uri가 없어');
                    location.href = '/';
                } else {
                    location.href = data.refererUri;
                }
            } else if($('#memail').val() != ''&& $('#mpw').val() != ''){
                $('#loginForm div.msg').removeClass('display_none');
                $('#loginForm div.msg').html( '아이디 또는 비밀번호가 잘못 입력 되었습니다.');
                $('.form_box').css('border-color', 'red');
                allData.redirectUri = data.refererUri;
                return false;
            }
        },
        error : function(request, status, error) {
            console.log("code:" + request.status + "\n" + "error:" + error);
        },
    });
}