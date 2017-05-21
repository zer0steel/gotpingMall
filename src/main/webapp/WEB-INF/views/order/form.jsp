<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="cart_items">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 panel panel-default">
				<h2>주문상품</h2>
				<table class="table">
					<thead>
						<tr>
							<th width="20%"></th>
							<th width="40%">상품</th>
							<th width="15%">가격</th>
							<th width="10%">수량</th>
							<th width="*">합계</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${o.details }">
						<tr>
							<td>
								<img  alt="" width="50%" height="auto" 
								src="${pageContext.request.contextPath }/${d.goods.mainImg.save_path }/${d.goods.mainImg.save_name }">
							</td>
							<td class="name">
								<strong class="goods" style="font-size: 20px;">${d.goods.name }</strong>
								<p class="option">
									<img src="${pageContext.request.contextPath }/resources/images/small/basket_option.gif">
									&nbsp;${d.optionStock.combination }
								</p>
							</td>
							<td>
								<p>${d.goods.realPrice + d.optionStock.os_extra_cost } 원</p>
							</td>
							<td>
								<input class="cart_quantity_input" type="text" name="quantity" value="${d.amount }" size="2" readonly>
							</td>
							<td>
								<p class="cart_total_price">${d.total_price } 원</p>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3"></td>
							<td></td>
							<td>${o.total_price } 원</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="col-md-1"></div>
		</div>
		<form class="form-horizontal" action="successCheckout.yo" method="post">
			<input type="hidden" name="m_no" value="${m.m_no }">
			<input type="hidden" name="total_price" value="${o.total_price }" data-price="${o.total_price }">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10 panel panel-default">
					<h3>구매자 정보</h3>
					<br>
					
					<input type="text" name="buyer" class="form-control input" data-label="이름" value="${m.name }">
					<input type="text" name="email" class="form-control input" data-label="이메일" value="${m.email }">
					<c:if test="${m == null }">
						<input type="password" class="form-control input" data-label="구매비밀번호">
						<input type="password" class="form-control input" data-label="비밀번호확인">
					</c:if>
				</div>
				<div class="col-md-1"></div>
			</div>
			
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10 panel panel-default">
					<div style="display: inline;">
						<h3>배송 정보</h3>
					</div>
					<br>
					<input type="text" name="recipient" class="form-control input" data-label="수령인" value="${m.name }">
					<div class="form-group">
						<label class="control-label col-md-2">우편번호</label>
						<div class="col-md-4">
							<input type="text" id="input-postcode" class="form-control addr" value="${m.address.postCode }" readonly>
						</div>
						<div class="col-md-3">
							<button type="button" id="btn-addrFinder" class="btn btn-info" >찾기</button>
						</div>
					</div>
					<input type="text" class="form-control input addr" data-label="주소" id="input-addr" value="${m.address.base }" readonly>
					<input type="text" class="form-control input" data-label="상세주소" id="input-extraAddr" value="${m.address.extra }">
					<textarea class="form-control input" rows="4" cols="5" 
					data-label="주문 메시지" maxlength="200" placeholder="200자 내외로 적어주세요"></textarea>
				</div>
				<div class="col-md-1"></div>
			</div>
			
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10 panel panel-default">
					<h3>할인</h3>
					<table class="table table-checkout">
						<thead>
							<tr>
								<th>상품금액</th>
								
								<c:if test="${m != null }">
								<th></th>
								<th>마일리지</th>
								</c:if>
								
								<th></th>
								<th>배송비</th>
								<th></th>
								
								<th>결제 예상 금액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${o.total_price } 원</td>
								
								<c:if test="${m != null }">
								<td><img src="${pageContext.request.contextPath }/resources/images/small/bul_h23_minus.png"></td>
								<td><span id="span-mileage">0</span> 원</td>
								</c:if>
								
								<td><img src="${pageContext.request.contextPath}/resources/images/small/bul_h23_plus.png "></td>
								<td>무료</td>
								
								<td><img src="${pageContext.request.contextPath }/resources/images/small/bul_h23_equal.png"></td>
								<td><span id="span-checkout-price" data-price="${o.total_price }" style="color: red;">${o.total_price }</span> 원</td>
							</tr>
						</tbody>
					</table>
					<c:if test="${m != null }">
					<br />
					<div class="clearfix"></div>
					<div class="form-group">
						<label class="control-label col-md-2">마일리지</label>
						<div class="col-md-3">
							<input type="number" class="form-control" max="${m.mileage }" name="mileage">
						</div>
						<div class="col-md-6">
							(사용가능 마일리지 : <span style="color: orange;">${m.mileage }</span> 원)
						</div>
					</div>
					</c:if>
				</div>
				<div class="col-md-1"></div>
			</div>
					
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8" style="text-align: center;">
					 <h3>최종 결제 금액 : <span id="final-price" style="font-size: 30px; color: red;">${o.total_price }</span> 원</h3>
				</div>
				<div class="col-md-2"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button type="button" class="btn btn-success btn-block btn-lg" id="btn-checkout">결제하기</button>
				</div>
				<div class="col-md-4"></div>
			</div>
		</form>
	</div>
</section>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/addrFinder.js?"></script>
<script>
(function() {
	(function applyDesign(){
		$('.table-checkout').find('th, td').css({'text-align':'center','font-size':'17px'});
		
		$('.form-control.input').each(function() {
			let label = $(this).data('label');
			let $div = createTag(label);
			$(this).replaceWith($div);
			$div.find('div.col-md-6').append($(this));
		});
		
		function createTag(label) {
			return $('<div />').addClass('form-group').append(
					$('<label />').addClass('control-label col-md-2').text(
							label), $('<div />').addClass('col-md-6').append())
		}
	}());

	
	var $inputMileage = $('input[name=mileage]');
	
	$inputMileage.keyup(function() {
		if(!mileageValidationCheck($(this)))
			return;
		calculrateCheckoutPrice($(this).val(), $('#span-checkout-price'));
	});

	function mileageValidationCheck($input) {
		let max = $input.attr('max');
		let inputMileage = Number($input.val());
		if(0 > inputMileage) {
			alert('마일리지 사용량이 0보다 적습니다.');
			return false;
		}
		else if(max < inputMileage) {
			alert('보유하신 마일리지보다 더 많이 사용하실 수 없습니다.');
			return false;
		}
		else if(Number($('input[name=total_price]').data('price')) < inputMileage) {
			alert('결제금액보다 더 많이 마일리지를 사용하실 수 없습니다.');
			return false;
		}
		return true;
	}
	
	function calculrateCheckoutPrice(mileage, $checkoutSpan) {
		let price = Number($checkoutSpan.data('price'));
		let checkoutPrice = price - mileage;
		$checkoutSpan.text(checkoutPrice);
		$('#final-price').text(checkoutPrice);
		$('input[name=total_price]').val(checkoutPrice);
		$('#span-mileage').text( mileage ? mileage : 0);
	}
	
	//결제하기 버튼 클릭
	$('#btn-checkout').click(function() {
		if(!mileageValidationCheck($inputMileage))
			return;
		if(!validationCheck())
			return;
		prepareCheckout();
	});
	
	function validationCheck() {
		for(var tag of $('.addr')) {
			if(!$(tag).val()) {
				alert('주소가 입력되지 않았습니다.');
				return false;
			}
		}
		return true;
	}
	
	function prepareCheckout() {
		let checkoutInfo = formToJSON( $('form') );
		if(checkoutInfo.change_amount) {
			requestMileageCheck(checkoutInfo)
			.done(function(remainPrice) {
				remainPrice = Number(remainPrice);
				if(remainPrice === 0) {
					requestSaveCheckoutInfo({
						use_mileage : checkoutInfo.mileage,
						p_way : '전액 마일리지'
					}).done(function() {
						alert('확인')
					});
				}
				else if(remainPrice > 0)
					openCheckoutModule(remainPrice);
				else
					alert('비정상적인 접근');
			});
		}
		else
			openCheckoutModule();
	}
	
	var formToJSON = function($form) {
		return {
			m_no : $form.find('input[name=m_no]').val(),
			change_amount : $form.find('input[name=mileage]').val(),
			total_price : $form.find('input[name=total_price]').val()
		};
	}
	
	var getGoodsName = function() {
		let goodsNames = [];
		$('.name').each(function() {
			let goodsName = $(this).find('.goods').text().trim();
			let optName =$(this).find('.option').text().trim();
			goodsNames.push(goodsName + ' (' + optName + ')');
		})
		return goodsNames;
	}
	
	var requestMileageCheck = function(checkoutInfo) {
		return $.ajax({
			url : 'mileageCheck.yo',
			type : 'post',
			data : checkoutInfo
		});
	}
	
	IMP.init('imp92243006');
	function openCheckoutModule(remainPrice) {
		IMP.request_pay({
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : getGoodsName().join(','),
			amount : remainPrice ? remainPrice : $('input[name=total_price]').val(),
			buyer_email : $('input[name=email]').val(),
			buyer_name : $('input[name=buyer]').val(),
			buyer_tel : '010-1234-5678',
			buyer_addr : $('#input-addr').val() + " " + $('#input-extraAddr').val(),
			buyer_postcode : $('#input-postcode').val()
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
	var requestSaveCheckoutInfo = function(info) {
		return $.ajax({
			url : 'successCheckout.yo',
			type : 'post',
			data : info
		});
	}
}())
</script>
