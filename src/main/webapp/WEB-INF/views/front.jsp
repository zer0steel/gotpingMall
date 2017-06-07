<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--product-starts-->
<h2 style="text-align: center;">WEEKLY BEST</h2>
<div class="product"> 
	<div class="container">
		<div class="product-top">
		<c:forEach var="g" items="${goods }">
			<div class="col-md-3 product-left">
				<div class="product-main simpleCart_shelfItem">
				
					<a href="${pageContext.request.contextPath }/goods/detail.yo?g_no=${g.g_no}" class="mask">
						<img class="img-responsive zoom-img" src="${pageContext.request.contextPath }/${g.mainImg.save_path }/${g.mainImg.save_name }" alt="" />
					</a>
					<div class="product-bottom">
						<h3>${g.name }</h3>
						<p>Explore Now</p>
						<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">${g.realPrice }</span></h4>
					</div>
					<c:if test="${g.discount_rate > 0 }">
					<div class="srch">
						<span>-${g.discount_rate }%</span>
					</div>
					</c:if>
				</div>
			</div>
		</c:forEach>
		</div>
		
	</div>
</div>
	<!--product-end-->
	<!-- <ul class="pagination">
		<li class="active"><a href="">1</a></li>
		<li><a href="">2</a></li>
		<li><a href="">3</a></li>
		<li><a href="">&raquo;</a></li>
	</ul> -->