<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="prdt"> 
	<div class="container">
		<div class="prdt-top">
			<div class="col-md-9 prdt-left" id="prdt-list">
			
				<c:forEach var="g" items="${goods }">
				<div class="col-md-4 product-left p-left product">
					<div class="product-main simpleCart_shelfItem">
						<a href="${pageContext.request.contextPath }/goods/detail.yo?g_no=${g.g_no}" class="mask">
							<img class="img-responsive zoom-img" src="${pageContext.request.contextPath }/${g.mainImg.save_path }/${g.mainImg.save_name }" alt="" />
						</a>
						<div class="product-bottom">
							<h3>${g.name }</h3>
							<p>Explore Now</p>
							<h4>
								<a class="item_add" href="#"><i></i></a>
								<span class=" item_price">${g.realPrice }</span>
							</h4>
						</div>
						<c:if test="${g.discount_rate > 0 }">
						<div class="srch srch1">
							<span>-${g.discount_rate }%</span>
						</div>
						</c:if>
					</div>
				</div>
				</c:forEach>
				
			</div>
			<div class="col-md-3 prdt-right">
				<jsp:include page="/WEB-INF/views/templateMenu/sideMenu.jsp"></jsp:include>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script>
(function() {
	const $listContainer = $('#prdt-list');
	const products = $('.product');
	let len = products.length;
	for(let i = 0; i < len; i += 3) {
		$('<div />').addClass('product-one')
		.append(
			addPadding(products[i]), 
			addPadding(products[i + 1]), 
			addPadding(products[i + 2]), 
			$('<div />').addClass('clearfix')
		).appendTo( $listContainer );
	}
	
	function addPadding(tag) {
		return $(tag).css('padding' , '15px');
	}
}())
</script>