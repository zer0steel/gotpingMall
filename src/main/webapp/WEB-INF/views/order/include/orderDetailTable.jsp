<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.orderInfo ul {
	list-style: none;
	text-align: center;
}

.orderInfo ul li {
	display: inline-block;
	margin-right: 20px;
	font-size: 18px;
}
</style>
<div class="row">
	<div class="col-md-offset-1 col-md-10">
		<div class="well orderInfo">
			<ul>
				<li>주문일자 : <fmt:formatDate value="${pay.pay_date }" pattern="yyyy.MM.dd"/></li>
				<li>주문번호 : ${pay.order_uid }</li>
			</ul>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-1 col-md-10">
		<table class="table">
			<thead>
				<tr>
					<td width="55%">상품 정보</td>
					<td width="15%">금액(수량)</td>
					<td width="15%">배송비</td>
					<td width="15%">진행 상황</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="d" items="${pay.order.details }">
				<tr>
					<td>
						<div>
							<div class="col-md-4">
								<img alt="로드 실패" width="100%" height="auto" class="goodsImg"
								src="${pageContext.request.contextPath }/${d.stock.goods.mainImg.save_path }/${d.stock.goods.mainImg.save_name }">
							</div>
							<div class="col-md-8">
								<strong style="font-size: 16px;">${d.stock.goods.name }</strong>
								<c:if test="${not empty d.stock.combination }">
								<p class="option">
									<img src="${pageContext.request.contextPath }/resources/images/small/basket_option.gif">
									&nbsp;${d.stock.combination }
								</p>
								</c:if>
							</div>					
						</div>
					</td>
					<td><p>${d.unit_price * -1 * d.change_amount } 원</p><p>( ${d.change_amount * -1 } 개)</p></td>
					<td>무료</td>
					<td></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-3 col-md-6">
		<table class="table">
			<tr>
				<td>결제수단</td>
				<td>${pay.p_way.kor } <c:if test="${not empty pay.p_way_detail }">(${pay.p_way_detail })</c:if></td>
			</tr>
			<tr>
				<td>총 상품 금액</td>
				<td>${pay.order.total_price }</td>
			</tr>
			<c:if test="${not empty lm }">
			<tr>
				<td>마일리지 사용량</td>
				<td>${pay.use_mileage }</td>
			</tr>
			</c:if>
			<tr>
				<td>결제하신금액</td>
				<td>${pay.pay_amount }</td>
			</tr>
		</table>
	</div>
</div>