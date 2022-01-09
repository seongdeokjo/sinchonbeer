function frmSubmit() {
    if($('input[name=payType]').is(':checked') == false) {
        alert('결제 수단이 선택되지 않았습니다. \n 결제수단을 선택해주세요.');
        return false;
    } else if ($('input[id=payAgree]').is(':checked') == false) {
        alert('구매조건 확인 및 결제 진행에 동의에 동의 해주세요.');
        return false;
    }

    window.name = "opner_win";
    var myForm = document.payForm;
    var myWin = window.open("about:blank", "kakaoPayWin", "status=yes, scrollbars=yes, width=440, height=500, left=300, top=100");
    myForm.method = "post";
    myForm.target = "kakaoPayWin";
    myForm.action = "/kakaoPay/shop";
    myForm.submit();
}

$("#payAllCheck").click(function () {
    $(".check").prop('checked', $(this).prop('checked'));
});