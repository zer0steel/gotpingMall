<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="advertisement">
	<div class="container">
		<img src="${pageContext.request.contextPath }/resourcesimages/shop/advertisement.jpg" alt="" />
	</div>
</section>

<div class="left-sidebar">
	<h2>상품 분류</h2>
	<div class="panel-group category-products" id="accordian">
	
		<c:forEach var="c" items="${big.categories }">
			<c:if test="${!empty c.value.sub }">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordian" href="#${c.key }">
							<span class="badge pull-right"><i class="fa fa-plus"></i></span>
							${c.value.title }
						</a>
					</h4>
				</div>
				<div id="${c.key }" class="panel-collapse collapse">
					<div class="panel-body">
						<ul>
						<c:forEach var="sub" items="${c.value.sub }">
							<li><a href="${pageContext.request.contextPath }/goods/list.yo?c_no=${sub.c_no }">${sub.title } </a></li>
						</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			</c:if>
		</c:forEach>
		
		<c:forEach var="c" items="${big.categories }">
			<c:if test="${empty c.value.sub }">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a href="#">${c.value.title }</a>
					</h4>
				</div>
			</div>
			</c:if>
		</c:forEach>
		
	</div>

	<div class="brands_products"><!--brands_products-->
		<h2>Brands</h2>
		<div class="brands-name">
			<ul class="nav nav-pills nav-stacked">
				<li><a href=""> <span class="pull-right">(50)</span>Acne</a></li>
				<li><a href=""> <span class="pull-right">(56)</span>Grüne Erde</a></li>
				<li><a href=""> <span class="pull-right">(27)</span>Albiro</a></li>
				<li><a href=""> <span class="pull-right">(32)</span>Ronhill</a></li>
				<li><a href=""> <span class="pull-right">(5)</span>Oddmolly</a></li>
				<li><a href=""> <span class="pull-right">(9)</span>Boudestijn</a></li>
				<li><a href=""> <span class="pull-right">(4)</span>Rösch creative culture</a></li>
			</ul>
		</div>
	</div><!--/brands_products-->
	
	<div class="price-range"><!--price-range-->
		<h2>Price Range</h2>
		<div class="well">
			 <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
			 <b>$ 0</b> <b class="pull-right">$ 600</b>
		</div>
	</div><!--/price-range-->
	
	<div class="shipping text-center"><!--shipping-->
		<img src="${pageContext.request.contextPath }/resources/images/home/shipping.jpg" alt="" />
	</div><!--/shipping-->
	
</div>