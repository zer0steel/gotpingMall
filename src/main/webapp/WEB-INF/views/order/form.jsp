<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="review-payment">
	<h2>주문상품</h2>
</div>
<section id="cart_items">
	<div class="container">
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">상품</td>
						<td class="description"></td>
						<td class="price">가격</td>
						<td class="quantity">수량</td>
						<td class="total">총금액</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="g" items="${goods }">
					<c:forEach var="os" items="${g.optionStocks }">
					<tr>
						<td class="cart_product">
							<a href="">
								<img src="${pageContext.request.contextPath }/${g.mainImg.save_path }/${g.mainImg.save_name }" alt="" width="40%" height="auto">
							</a>
						</td>
						<td class="cart_description">
							<h4>
								<a href="">${g.name }</a>
							</h4>
							<p>${os.combination }</p>
						</td>
						<td class="cart_price">
						<c:choose>
							<c:when test="${g.discount_rate == 0 }">
								<p>${os.extra_cost } 원</p>
							</c:when>
							<c:otherwise>
								<p>${g.discount_price + os.os_extra_cost } 원</p>
							</c:otherwise>
						</c:choose>
						</td>
						<td class="cart_quantity">
							<div class="cart_quantity_button">
								<input class="cart_quantity_input" type="text" name="quantity" value="${os.os_stock }" autocomplete="off" size="2">
							</div>
						</td>
						<td class="cart_total">
							<p class="cart_total_price">${(g.discount_price + os.os_extra_cost) * os.os_stock }원</p>
						</td>
						<td class="cart_delete">
							<a class="cart_quantity_delete" href="">
								<i class="fa fa-times"></i>
							</a>
						</td>
					</tr>
					</c:forEach>
				</c:forEach>
		
					<tr>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">
							<table class="table table-condensed total-result">
<!-- 								<tr>
									<td>상품 금액</td>
									<td>$59</td>
								</tr>
								<tr>
									<td>배송료</td>
									<td>$2</td>
								</tr> -->
								<tr>
									<td>총금액</td>
									<td><span>$61</span></td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</section>