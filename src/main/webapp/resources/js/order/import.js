/**
 * 
 */

var openCheckout = (function($form) {
	IMP.init('imp92243006');
	
	const MSG = '입력되지 않았습니다.';
	var validationCheck = function(checkoutInfo) {
		if(!checkoutInfo.name)
			alert('상품명을 가져올수 없습니다');
		else if(!checkoutInfo.total_price)
			alert('금액이 ' + MSG);
		else if(!checkoutInfo.pay_way)
			alert('결제수단이 선택되지 않았습니다.');
//		else if(!checkoutInfo.email)
//			alert('상품명을 가져올수 없습니다');
		else if(!checkoutInfo.buyer)
			alert('구매자 성함이 ' + MSG);
		else if(!checkoutInfo.buyer_email)
			alert('구매자 이메일주소가 ' + MSG);
		else if(!checkoutInfo['address.base'])
			alert('배송지 주소가 ' + MSG);
		else if(!checkoutInfo['address.postCode'])
			alert('배송지 우편번호가 ' + MSG);
		else
			return true;
		return false;
			
	}
	
	var requestSaveCheckoutInfo = function(info) {
		return $.ajax({
			url : 'successCheckout.yo',
			type : 'post',
			data : info
		});
	}
	
	var createHiddenTag = function(name, value) {
		return $('<input />').attr({'type':'hidden', 'name':name}).val(value);
	}
	
	return function(checkoutInfo) {
		if(!validationCheck(checkoutInfo))
			return;
		
		checkoutInfo.address = checkoutInfo['address.base'] + " " + checkoutInfo['address.extra'];
		IMP.request_pay({
			pay_method : checkoutInfo.pay_way,
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : checkoutInfo.name,
			amount : checkoutInfo.total_price,
			buyer_email : checkoutInfo.buyer_email,
			buyer_name : checkoutInfo.buyer,
			buyer_addr : checkoutInfo.address,
			buyer_postcode : checkoutInfo['address.postCode'],
			custom_data : checkoutInfo.d_no
		}, function(rsp) {
			if (rsp.success) {
				$form.append(
					createHiddenTag('order_uid', rsp.imp_uid),
					createHiddenTag('pay_amount', rsp.paid_amount)
				).submit();
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				console.log(msg);
			}
		});
	}
}($('form')))