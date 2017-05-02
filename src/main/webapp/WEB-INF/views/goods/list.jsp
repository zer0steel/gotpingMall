<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:forEach var="g" items="${goods }">
<div class="col-sm-4">
	<div class="product-image-wrapper">
	
		<div class="single-products">
			<div class="productinfo text-center">
				<a href="${pageContext.request.contextPath }/goods/detail.yo?g_no=${g.g_no}">
					<img src="${pageContext.request.contextPath }/${g.mainImg.save_path }/${g.mainImg.save_name }" alt="" />
				</a>
				<h2><a href="${pageContext.request.contextPath }/goods/detail.yo?g_no=${g.g_no}">${g.name }</a></h2>
				<c:choose>
					<c:when test="${g.discount_rate > 0}">
					<p style="text-decoration: line-through;">${g.sell_price } 원</p>
					<span>${g.discount_price } 원</span>
					</c:when>
					<c:otherwise>
					<p>${g.sell_price } 원</p>
					</c:otherwise>
				</c:choose>
				<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>장바구니에 담기</a>
			</div>
		</div>
		
	</div>
</div>
</c:forEach>