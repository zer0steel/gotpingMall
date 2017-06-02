<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.table tr th {width: 0;}
.greeting{font-size: 30px; text-shadow: black;}
</style>
<div class="contact">
	<div class="container">
		<div class="contact-top heading">
			<h2>주문 완료</h2>
		</div>
		<br />
		<div class="contact-text">
			<div class="col-md-6 col-md-offset-3">
				<p align="center" class="greeting">G-MALL을 이용해 주셔서 감사합니다.</p>
				<c:if test="${not empty lm }">
					<p align="center">주문하신 내역은 주문조회에서 다시 확인이 가능합니다.</p>
				</c:if>
				<br />
				<table class="table">
					<thead>
						<tr>
							<th width="50%" colspan="2">결제 정보</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>주문번호</td>
							<td>${pay.order_uid }</td>
						</tr>
						<tr>
							<td>결제방법</td>
							<td>${pay.p_way.kor }</td>
						</tr>
						<tr>
							<td>상품금액</td>
							<td>${pay.order.total_price }</td>
						</tr>
						<tr>
							<td>배송비</td>
							<td>무료</td>
						</tr>
						<c:if test="${not empty lm }">
						<tr>
							<td>마일리지 할인</td>
							<td>${pay.use_mileage }</td>
						</tr>
						</c:if>
						<tr>
							<td>결제금액</td>
							<td>${pay.pay_amount }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>