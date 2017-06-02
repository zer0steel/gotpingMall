<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<thead>
		<tr>
			<td>상품 정보</td>
			<td>금액(수량)</td>
			<td>배송비</td>
			<td>진행 상황</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="d" items="${pay.order.details }">
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</c:forEach>
	</tbody>
</table>
