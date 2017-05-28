/**
 * 
 */

(function() {
	IMP.init('imp92243006');
	const MSG = '입력되지 않았습니다.';
	function validationCheck(checkoutInfo) {
		if(!checkoutInfo.name)
			alert('상품명을 가져올수 없습니다');
		else if(!checkoutInfo.amount)
			alert('금액이 ' + MSG);
//		else if(!checkoutInfo.email)
//			alert('상품명을 가져올수 없습니다');
		else if(!checkoutInfo.buyerName)
			alert('구매자 성함이 ' + MSG);
		else if(!checkoutInfo.buyerTel)
			alert('구매자 전화번호가 ' + MSG);
		else if(!checkoutInfo.address)
			alert('배송지 주소가 ' + MSG);
		else if(!checkoutInfo.postCode)
			alert('배송지 우편번호가 ' + MSG);
		else
			return true;
		return false;
			
	}
	
	function openCheckoutModule(checkoutInfo) {
		if(!validationCheck(checkoutInfo))
			return;
		IMP.request_pay({
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : checkoutInfo.goodsName,
			amount : checkoutInfo.remainPrice,
			buyer_email : checkoutInfo.email,
			buyer_name : checkoutInfo.buyerName,
			buyer_tel : checkoutInfo.buyerTel,
			buyer_addr : checkoutInfo.address,
			buyer_postcode : checkoutInfo.postCode
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
				requestSaveCheckoutInfo({
					o_no : info.imp_uid,
					price : info.paid_amount,
					status : info.status
				});
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
		});
	}
}())