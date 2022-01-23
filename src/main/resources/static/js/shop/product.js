let gname;
let price;
let amount;
let totalPrice;
$(document).ready(function () {
    price = $('#price').val();
    amount = $('#amount').val();

    // 버튼 이벤트
    $('#plus').click(function () {
        amount++;
        totalPrice = price * amount;

        $('#price').val(totalPrice);
        $('#amount').val(amount);
    });

    $('#minus').click(function () {
        if(amount <= 1){
            totalPrice = price;
        }else {
            amount--;
            totalPrice = price * amount;
        }
        $('#price').val(totalPrice);
        $('#amount').val(amount);
    });

    $('#basket').on('click', function () {
        let ck = $('#check').val();
        console.log(ck);
        if ($('#check').val() === 'Y') {
            alert('로그인이 필요합니다.');
        }
    });
});