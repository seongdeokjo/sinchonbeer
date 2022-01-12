// 전체 선택
function checkAll() {
    if ($("#th_checkAll").is(':checked')) {
        $("input[name=checkRow]").prop("checked", true);
    } else {
        $("input[name=checkRow]").prop("checked", false);
    }
}
/* 변경 버튼 클릭시 모달 값 초기화 */
function button(gidx) {
    console.log('modal start : ' + gidx);
    $('#myModal' + gidx).on('shown.bs.modal', function (e) {
        let count = $('#count' + gidx).val();
        if (count != $('#cnt' + gidx).val()) {
            $('#cnt' + gidx).val(count);
        }
    });
}
/* +,- 버튼 클릭시 값이 1씩 증가 또는 감소 */
function count(gidx, type) {
    let cnt = $('#cnt' + gidx).val();
    console.log('맨 처음 cnt  값 : ' + cnt);
    if (type === 'plus') {
        console.log('plus 실행');
        cnt++;
        console.log('plus 연산 완료  : ' + cnt)
        if (cnt > 1) {
            $('#minus' + gidx).removeAttr('disabled');
            console.log('cnt > 1 보다 큰 경우 실행');
        }
        console.log('plus 연산 종료');
    } else if (type === 'minus') {

        console.log('minus 실행' + cnt);
        --cnt;
        if (cnt < 1) {
            $('#minus' + gidx).attr('disabled', 'disabled');
            console.log('cnt <= 1 일 경우 블락');
            return false;
        }
        console.log('minus 연산 종료 : ' + cnt);
    }
    $('#cnt' + gidx).val(cnt);
}
/* 모달 안의 수량 수정 후 변경 버튼 클릭시  */
function modify(gidx, midx) {
    let count = $('#count' + gidx).val();
    let cnt = $('#cnt' + gidx).val();
    if (count == cnt) {
        console.log('변경된 값이 없습니다.');
        return false;
    }
    $.ajax({
        url: '/basket/changeAmount', type: 'get', data: {
            gidx: gidx, midx: midx, amount: cnt
        }, success: function (data) {
            if (data == true) {
                alert('변경 되었습니다.');
                location.reload();
            }
        }, error: function () {
        }
    });
}
// 상품 총가격
function getTotalPrice() {
    var gprice_list = $('._cartItemCheckbox');
    var totalPrice = 0;
    for (var i = 0; i < gprice_list.length; i++) {
        if (gprice_list[i].checked) {
            totalPrice += Number(gprice_list[i].value);
        }
    }
    $('#totalPrice').text(totalPrice);
}

// 결제 금액
function getPayPrice() {
    var gprice_list = $('._cartItemCheckbox');
    var payPrice = 0;
    for (var i = 0; i < gprice_list.length; i++) {
        if (gprice_list[i].checked) {
            payPrice += Number(gprice_list[i].value);
        }
    }
    $('#payPrice').text(payPrice);
}

$(document).ready(function () {
    getTotalPrice();
    getPayPrice();
    $('.check').change(function () {
        getTotalPrice();
    });
    $('.check').change(function () {
        getPayPrice();
    });
    let midx = $('#midx').val();
    let gidx = $('#gidx').val();
    /* 전체 장바구니 삭제 */
    $('#removeAll').on('click', function () {
        if (!$('#th_checkAll').is(':checked')) {
            alert('선택된 항목이 없습니다.');
            $('#th_checkAll').focus();
        } else {
            if (confirm('장바구니를 비우시겠습니까?')) {
                $.ajax({
                    url: '/deleteAllByMidx/' + midx, type: 'get'
                });
                alert('삭제 되었습니다.');
                location.reload();

            } else {
                return false;
            }
        }
    });
    /* 한 로우만 삭제  */
    $('.deleteRow').on('click', function () {
        console.log(midx + " : " + gidx);
        $.ajax({
            url: '/deleteRow', type: 'get', data: {
                midx: midx, gidx: gidx
            }, success: function (data) {
                if (data == true) {
                    alert('삭제되었습니다.');
                }
                location.reload();
            }
        });
    });
    /* 선택된 상품 삭제 */
    $('#deleteGoodsByPicking').on('click', function () {
        // 체크된 radio 박스를 담을 배열 선언
        let check_list = [];

        let check2 = $('input[name=checkRow]:checked');
        /* 선택된 값이 없을 경우 return */
        if (check2.length < 1) {
            alert('선택된 값이 없습니다.');
            return false;
        }
        $.each(check2, function (index, item) {
            // next() 선택된 raido 박스의  형제 선택자(gidx)
            check_list.push($(this).next().val());
        })
        console.log(check_list);
        // 멤버 번호와 , gidx 값 객체 저장
        let object = {
            "midx": midx, "gidxList": check_list
        }
        console.log(object);
        console.log(object.gidxList);
        $.ajax({
            url: '/deleteBasketByPicking', type: 'post', data: object, success: function (data) {
                if (data > 0) {
                    alert('삭제 성공');
                    location.reload();
                }
            }, error: function (request, status, error) {
                console.log("AJAX_ERROR");
            }
        });
    });
    // 주문하기(선택된 row 값 가져오기)
    $("#chooseOrder").on('click', function () {
        let list = [];
        let check = $('input[name=checkRow]:checked');
        if (check.length < 1) {
            alert('선택된 상품이 없습니다.');
            return false;
        }
        $.each(check, function (index, item) {
            // next() 선택된 radio 박스의 형제 선택자()
            list.push($(this).nextAll("#bidx").val());
            list.push($(this).nextAll("#gphoto").val());
            list.push($(this).nextAll('#gname').val());
            list.push($(this).nextAll("#count").val());
            list.push($(this).nextAll("#gprice").val());
            //bidx번호
            let num = $(this).nextAll('#bidx').val();
            console.log(num);
        });
        console.log(list);
        $("form").submit();
    });
});