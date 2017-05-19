<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="review-payment">
	<h2>주문상품</h2>
</div>
<section id="cart_items">
	<div class="container">
		<div class="table-responsive cart_info" style="overflow: hidden;">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">상품</td>
						<td class="description"></td>
						<td class="price">가격</td>
						<td class="quantity">수량</td>
						<td class="total">합계</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="d" items="${o.details }">
					<tr>
						<td class="cart_product">
							<a href="">
								<img src="${pageContext.request.contextPath }/${d.goods.mainImg.save_path }/${d.goods.mainImg.save_name }" alt="" width="40%" height="auto">
							</a>
						</td>
						<td class="cart_description">
							<h4>
								<a href="">${d.goods.name }</a>
							</h4>
							<p>${d.optionStock.combination }</p>
						</td>
						<td class="cart_price">
							<p>${d.goods.realPrice + d.optionStock.os_extra_cost } 원</p>
						</td>
						<td class="cart_quantity">
							<input class="cart_quantity_input" type="text" name="quantity" value="${d.amount }" size="2" readonly>
						</td>
						<td class="cart_total">
							<p class="cart_total_price">${d.total_price }원</p>
						</td>
					</tr>
					</c:forEach>
					<%-- <tr>
						<td colspan="4">&nbsp;</td>
						<td>
							<table class="table table-condensed total-result">
								<tr>
									<td>상품구매금액</td>
									<td><span>${o.total_price } 원</span></td>
								</tr>
							</table>
						</td>
					</tr> --%>
				</tbody>
			</table>
		</div>
		<!-- 
		<div class="checkout-options">
			<h3>New User</h3>
			<p>Checkout options</p>
			<ul class="nav">
				<li>
					<label><input type="checkbox"> Register Account</label>
				</li>
				<li>
					<label><input type="checkbox"> Guest Checkout</label>
				</li>
				<li>
					<a href=""><i class="fa fa-times"></i>Cancel</a>
				</li>
			</ul>
		</div>/checkout-options
		
		<div class="register-req">
			<p>Please use Register And Checkout to easily get access to your order history, or use Checkout as Guest</p>
		</div>/register-req-->
		
		<div class="shopper-informations">
			<form class="form-horizontal">
			
					<div class="col-md-6">
						<h3>구매자 정보</h3>
						<br>
						
						<input type="text" class="form-control input" data-label="이름" value="${m.name }">
						<input type="text" class="form-control input" data-label="이메일" value="${m.email }">
						<c:if test="${m == null }">
							<input type="password" class="form-control input" data-label="구매비밀번호">
							<input type="password" class="form-control input" data-label="비밀번호확인">
						</c:if>
						
						<div class="clearfix"></div>
						
						<c:if test="${m != null }">
						<h3>할인</h3>
						<div class="form-group">
							<label class="control-label col-md-3">마일리지</label>
							<div class="col-md-3">
								<input type="number" class="form-control" max="${m.mileage }" min="0" id="mileage">
							</div>
							<div class="col-md-6">
								(사용가능 마일리지 : <span style="color: orange;">${m.mileage }</span> 원)
							</div>
						</div>
						</c:if>
						
						<a class="btn btn-primary" href="">Get Quotes</a>
						<a class="btn btn-primary" href="#" id="checkout">결제</a>
					</div>
					
					<div class="col-md-6">
						<h3>배송 정보</h3>
						<br>
						<input type="text" class="form-control input" data-label="이름" value="${m.name }">
						<input type="text" class="form-control input" data-label="이메일" value="${m.email }">
						<div class="form-group">
							<label class="control-label col-md-3">우편번호</label>
							<div class="col-md-4">
								<input type="text" id="input-postcode" class="form-control" value="${m.address.postCode }" readonly>
							</div>
							<div class="col-md-3">
								<button type="button" id="btn-addrFinder" class="btn btn-info" >찾기</button>
							</div>
						</div>
						<input type="text" class="form-control input" data-label="주소" id="input-addr" value="${m.address.base }" readonly>
						<input type="text" class="form-control input" data-label="상세주소" id="input-extraAddr" value="${m.address.extra }">
						<textarea class="form-control input" rows="4" cols="5" 
						data-label="주문 메시지" maxlength="200" placeholder="200자 내외로 적어주세요"></textarea>
					</div>
				
					<div class="col-md-6">
						
					</div>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/addrFinder.js?"></script>
<script>
(function() {
	IMP.init('imp92243006');
	$('#checkout').click(function() {
	})

	$('.form-control.input').each(function() {
		let label = $(this).data('label');
		let $div = createTag(label);
		$(this).replaceWith($div);
		$div.find('div.col-md-6').append($(this));
	});

	function openCheckoutModule() {
		IMP.request_pay({
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '결제테스트',
			amount : 14000,
			buyer_email : 'iamport@siot.do',
			buyer_name : '구매자',
			buyer_tel : '010-1234-5678',
			buyer_addr : '서울특별시 강남구 삼성동',
			buyer_postcode : '123-456'
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
		});
	}
	
	function createTag(label) {
		return $('<div />').addClass('form-group').append(
				$('<label />').addClass('control-label col-md-3').text(
						label), $('<div />').addClass('col-md-6').append())
	}
}())
</script>
