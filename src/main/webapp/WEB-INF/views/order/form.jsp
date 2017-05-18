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
						<td></td>
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
							<div class="cart_quantity_button">
								<input class="cart_quantity_input" type="text" name="quantity" value="${d.amount }" autocomplete="off" size="2">
							</div>
						</td>
						<td class="cart_total">
							<p class="cart_total_price">${d.total_price }원</p>
						</td>
						<td class="cart_delete">
							<a class="cart_quantity_delete" href="">
								<i class="fa fa-times"></i>
							</a>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">
							<table class="table table-condensed total-result">
								<tr>
									<td>상품구매금액</td>
									<td><span>${o.total_price } 원</span></td>
								</tr>
							</table>
						</td>
					</tr>
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
			<div class="row">
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
						<a class="btn btn-primary" href="">Get Quotes</a>
						<a class="btn btn-primary" href="">Continue</a>
					</div>
					
					<div class="col-md-6">
						<h3>배송 정보</h3>
						<br>
						<input type="text" class="form-control input" data-label="이름" value="${m.name }">
						<input type="text" class="form-control input" data-label="이메일" value="${m.email }">
						<div class="form-group">
							<label class="control-label col-md-3">우편번호</label>
							<div class="col-md-4">
								<input type="text" id="input-postcode" class="form-control" value="${m. }" readonly>
							</div>
							<div class="col-md-3">
								<button type="button" id="btn-addrFinder" class="btn btn-info" >찾기</button>
							</div>
						</div>
						<input type="text" class="form-control input" data-label="주소" id="input-addr" readonly>
						<input type="text" class="form-control input" data-label="상세주소" id="input-extraAddr">
						<div class="order-message">
							<p>주문 메시지</p>
							<textarea name="message"  rows="16"></textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/addrFinder.js?"></script>
<script>
(function() {
	$('div.form-group.input').each(function() {
		let label = $(this).data('label');
		let name = $(this).data('name');
		let value = $(this).data('value');
		let id = $(this).data('id');
		let readonly = $(this).data('readonly') != undefined;
		
		$(this).append(
			$('<label />').addClass('control-label col-md-3').text(label),
			$('<div />').addClass('col-md-6').append(
				$('<input />').addClass('form-control').attr({'name':name, 'id':id, 'readonly': readonly}).val(value)
			)
		);
	});
	
	$('.form-control.input').each(function() {
		let label = $(this).data('label');
		let $div = createTag(label);
		$(this).replaceWith( $div );
		$div.find('div.col-md-6').append( $(this) );		
	});
	
	function createTag(label) {
		return $('<div />').addClass('form-group').append(
				$('<label />').addClass('control-label col-md-3').text(label),
				$('<div />').addClass('col-md-6').append()
			)
	}
	
	function getAttr(tag) {
		let attr = tag.attributes;
		let attributes = [];
		for(var a of attr) {
			let prop = "{}";
			attributes.push(prop.toString());
		}
		console.log(attributes);
		return attributes;
	}
}())
</script>
