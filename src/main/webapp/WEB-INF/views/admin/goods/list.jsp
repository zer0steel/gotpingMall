<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.modal-dialog.modal-fullsize{
	width: 50%; height: 50%; }
</style>
<div class="page-title">
	<div class="title_left">
		<h3>상품 목록</h3>
	</div>
</div>
<br />
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>검색</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div id="select"></div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="x_panel">
		<div class="x_title">
			<h2>목록</h2>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<table id="goodsTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>상품분류</th>
						<th>상품명</th>
						<th>수량</th>
						<th>판매 가격</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="g" items="${goods }">
					<tr class="goods" data-g_no="${g.g_no }">
						<td>${g.title }</td>
						<td><a href="detail.yo?g_no=${g.g_no }">${g.name }</a></td>
						<td>${g.stock } 개</td>
						<td>${g.sell_price } 원</td>
						<td>${g.status.kor }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="application/javascript" src="${pageContext.request.contextPath }/resources/js/goods/category.js?ver=2"></script>
<script>
(function() {
	goods.category({
		$root : $('#select')
	})
}())
</script>