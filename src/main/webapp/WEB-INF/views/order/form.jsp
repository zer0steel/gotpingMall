<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
ul li img{
	vertical-align:top; display:block; float:left;
}
.table tr th {
    width: 0;
}
</style>
<!--start-ckeckout-->
<div class="ckeckout">
	<div class="container">
		<div class="ckeck-top heading">
			<h2>CHECKOUT</h2>
		</div>
		<div class="ckeckout-top">
			<div class="cart-items">
				<h3>주문상품</h3>
				<table class="table">
					<thead>
						<tr>
							<th width="150 !important" style="max-width: 150px !important;">이미지</th>
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
								<img  alt="" width="150px" height="auto" 
								src="${pageContext.request.contextPath }/${d.stock.goods.mainImg.save_path }/${d.stock.goods.mainImg.save_name }">
							</td>
							<td class="name">
								<strong class="goodsName" style="font-size: 20px;">${d.stock.goods.name }</strong>
								<c:if test="${not empty d.stock.combination }">
								<p class="option">
									<img src="${pageContext.request.contextPath }/resources/images/small/basket_option.gif">
									&nbsp;${d.stock.combination }
								</p>
								</c:if>
							</td>
							<td>
								<p>${d.stock.realPrice } 원</p>
							</td>
							<td>
								${d.change_amount } 개
							</td>
							<td>
								${d.stock.realPrice * d.change_amount } 원
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
				
				<form class="form-horizontal" action="successCheckout.yo" method="post" id="form-checkout">
		 			<input type="hidden" name="d_no" value="${o.d_no }">
		 			<input type="hidden" name="m_no" value="${m.m_no }">
					<input type="hidden" name="total_price" value="${o.total_price }" data-total-price="${o.total_price }">
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-10 panel panel-default">
							<h3>구매자 정보</h3>
							<br>
							
							<input type="text" name="buyer" class="form-control input" data-label="이름" value="${m.name }">
							<input type="text" name="buyer_email" class="form-control input" data-label="이메일" value="${m.email }">
							<c:if test="${m == null }">
								<input type="password" class="form-control input" data-label="구매비밀번호">
								<input type="password" name="order_password" class="form-control input" data-label="비밀번호확인">
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
									<input type="text" id="input-postcode" class="form-control addr" name="address.postCode" value="${m.address.postCode }" readonly>
								</div>
								<div class="col-md-3">
									<button type="button" id="btn-addrFinder" class="btn btn-info" >찾기</button>
								</div>
							</div>
							<input type="text" class="form-control input addr" name="address.base" data-label="주소" id="input-addr" value="${m.address.base }" readonly>
							<input type="text" class="form-control input" name="address.extra" data-label="상세주소" id="input-extraAddr" value="${m.address.extra }">
							<textarea class="form-control input" rows="4" cols="5" 
							data-label="주문 메시지" maxlength="200" placeholder="200자 내외로 적어주세요"></textarea>
						</div>
						<div class="col-md-1"></div>
					</div>
					
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-md-10 panel panel-default">
							<div class="row">
								<div class="table-responsive">
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
											</tr>
										</tbody>
									</table>
								</div>
								<c:if test="${m != null }">
								<br />
								<div class="clearfix"></div>
								<div class="form-group">
									<label class="control-label col-md-2">마일리지</label>
									<div class="col-md-3">
										<input type="number" class="form-control" max="${m.mileage }" name="use_mileage">
									</div>
									<div class="col-md-6">
										(사용가능 마일리지 : <span style="color: orange;">${m.mileage }</span> 원)
									</div>
								</div>
								</c:if>
							</div>
							<hr />
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-8" style="text-align: center;">
									 <h3>결제 금액 : <span id="final-price" style="font-size: 30px; color: red;">${o.total_price }</span> 원</h3>
								</div>
								<div class="col-md-2"></div>
							</div>
						</div>
						<div class="col-md-1"></div>
					</div>
					<br /><br />
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button type="button" class="btn btn-success btn-block btn-lg" id="btn-checkout">결제하기</button>
						</div>
						<div class="col-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/addrFinder.js?"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/order/import.js?var=1"></script>
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
	
	//-------------------------------------------------------

	var $inputMileage = $('input[name=use_mileage]');
	var $checkoutPrice = $('input[name=total_price]');
	
	$inputMileage.keyup(function() {
		let inputMileage = Number($(this).val());
		let remainMileage = Number($(this).attr('max'));
		let totalPrice = Number($checkoutPrice.data('total-price'));
		
		modifyMileage(inputMileage, remainMileage, totalPrice);
	});
	
	function modifyMileage(useMileage, remainMileage, totalPrice) {
		if(mileageValueCheck(useMileage, remainMileage, totalPrice)) {
			let remainPrice = calculrateCheckoutPrice(useMileage, totalPrice);
			setPriceInTag(useMileage, remainPrice);
		}
		else {
			let minValue = remainMileage < totalPrice ? remainMileage : totalPrice;
			setPriceInTag(minValue, totalPrice - minValue);
		}
		
	}
	
	function mileageValueCheck(use, remain, totalPrice) {
		if(use < 0) {
			alert('마일리지 사용량이 0보다 적습니다.');
			return false;
		}
		else if(use > remain) {
			alert('보유하신 마일리지보다 더 많이 사용하실 수 없습니다.');
			return false;
		}
		else if(use > totalPrice) {
			alert('결제금액보다 더 많이 마일리지를 사용하실 수 없습니다.');
			return false;
		}
		return true;
	}
	
	function calculrateCheckoutPrice(useMileage, totalPrice) {
		return remainPrice = totalPrice - useMileage;
	}
	
	function setPriceInTag(useMileage, remainPrice) {
		$checkoutPrice.val(remainPrice);
		$('input[name=use_mileage]').val(useMileage);
		$('#final-price').text(remainPrice);
		$('#span-mileage').text(useMileage ? useMileage : 0);
	}
	
	//결제하기 버튼 클릭
	$('#btn-checkout').click(function() {
		let inputMileage = Number($inputMileage.val());
		let remainMileage = Number($inputMileage.attr('max'));
		let totalPrice = Number($checkoutPrice.data('total-price'));
		
		if(!mileageValueCheck(inputMileage, remainMileage, totalPrice))
			return;
		
		let checkoutData = getFormData( $('form') );
		checkoutData.name = getGoodsName();
		prepareCheckout(checkoutData);
	});
	
	function getFormData($form){
	    var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	}
	
	function prepareCheckout(checkoutData) {
		let mileageUseInfo = mileageInfo( $('form') );
		if(mileageUseInfo.change_amount) {
			requestMileageCheck(mileageUseInfo)
			.done(function(remainPrice) {
				checkout(remainPrice);
			}).fail(function(err) {
				alert('비정상적인 접근');
			});
		}
		else
			openCheckout(checkoutData);
		
		//------------------------------------//
		function checkout(remainPrice) {
			remainPrice = Number(remainPrice);
			if(remainPrice === 0) {
				$('#form-checkout').submit();
			}
			else if(remainPrice > 0)
				openCheckout(checkoutData);
			else
				alert('비정상적인 접근');
		}
	}
	
	function mileageInfo($form) {
		return {
			m_no 			: $form.find('input[name=m_no]').val(),
			change_amount 	: Number($form.find('input[name=use_mileage]').val()),
			total_price 	: Number($form.find('input[name=total_price]').data('total-price'))
		};
	}
	
	var getGoodsName = function() {
		let $strong = $('strong.goodsName');
		let goodsName = $strong[0].innerHTML;
		if($strong.length > 1) {
			goodsName += " 외 " + ($strong.length - 1) + "개"
		}
		return goodsName;
	}
	
	var requestMileageCheck = function(mileageInfo) {
		return $.ajax({
			url : 'mileageCheck.yo',
			type : 'post',
			data : mileageInfo
		});
	}
}())
</script>
