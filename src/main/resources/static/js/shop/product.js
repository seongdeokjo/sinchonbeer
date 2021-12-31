 	var gphotoname; // 사진이름
    var gname;
    var price;
    var amount;
    var gidx;
    var totalPrice;

        $(document).ready(function(){
            price = $('#price').val();
            amount = $('#amount').val();
            gidx = $('#price').val();

            // 버튼 이벤트
            $('#plus').click(function(){
                amount++;
                totalPrice = price * amount;
                
                $('#sum_p_num').text(totalPrice);
                $('#amount').val(amount);
            });

            $('#minus').click(function(){
                amount--;
                totalPrice = price * amount;

                $('#sum_p_num').text(totalPrice);
                $('#amount').val(amount);
            });
            
            $('#basket').on('click',function(){
      			var ck = $('#check').val();
      			console.log(ck);
      			if($('#check').val() === 'Y'){
      				alert('로그인이 필요합니다.');	
      			}
            });
        }); 