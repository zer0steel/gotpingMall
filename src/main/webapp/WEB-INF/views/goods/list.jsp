<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="g" items="${goods }">
<div class="col-sm-4">
	<div class="product-image-wrapper">
	
		<div class="single-products">
			<div class="productinfo text-center">
				<a href="${pageContext.request.contextPath }/goods/detail.yo?g_no=${g.g_no}">
					<img src="${pageContext.request.contextPath }/${g.mainImg.save_path }/${g.mainImg.save_name }" alt="" />
				</a>
				<h2><a href="${pageContext.request.contextPath }/goods/detail.yo?g_no=${g.g_no}">${g.name }</a></h2>
				<p>${g.sell_price } 원</p>
				<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>장바구니에 담기</a>
			</div>
		</div>
		
	</div>
</div>
</c:forEach>

</body>
</html>