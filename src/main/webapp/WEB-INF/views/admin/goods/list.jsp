<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="page-title">
	<div class="page-left">
		<h3>상품 목록</h3>
	</div>
</div>
<br />
<div class="clearfix"></div>
<table id="datatable" class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>상품번호</th>
			<th>상품분류</th>
			<th>상품명</th>
			<th>수량</th>
			<th>판매 가격</th>
			<th>공개 여부</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="g" items="${goods }">
		<tr>
			<td>${g.g_no }</td>
			<td>${g.title }</td>
			<td>${g.name }</td>
			<td>${g.stock } 개</td>
			<td>${g.sell_price } 원</td>
			<td>
			<c:choose>
				<c:when test="${g.show }">공개</c:when>
				<c:otherwise>비공개</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>