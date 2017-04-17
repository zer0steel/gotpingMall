<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.modal-dialog.modal-fullsize{
	width: 50%; height: 50%; 
}
</style>
</head>
<body>
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
				<jsp:include page="include/categorySelectBox.jsp"></jsp:include>
				<script type="text/javascript">
					$("select").attr("size",0).val("");
				</script>
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
<script type="text/javascript">


$(".goods").click(function() {
	var g_no = $(this).data("g_no");
	requesetDetailGoods(g_no).done(function(goods) {
		setGoodsForm(goods);
	});
});

function setGoodsForm(g) {
	$("input[name=g_no]").val(g.g_no);
	$("select[name=menu_level]").val(g.menuLevel.code);
	$("select[name=parent_no]").val(g.parent_no);
	$("input[name=title]").val(g.title);
	$("input[name=c_no]").val(g.c_no);
	$("input[name=name]").val(g.name);
	$("input[name=purchase_price]").val(g.purchase_price);
	$("input[name=sell_price]").val(g.sell_price);
	$("input[name=discount_rate]").val(g.discount_rate);
	$("input[name=saving_mileage]").val(g.saving_mileage);
	$("input[name=stock]").val(g.stock);
	$("select[name=status]").val(g.status.code)
	setRecentHistory(g.history);
}

function setRecentHistory(history) {
	$("#recentHistory").find("tbody").empty();
	$(history).each(function() {
		var data = $(this)[0];
		var category_td = $("<td />").html(data.category);
		var amount_td = $("<td />").html(data.amount);
		var regdate_td = $("<td />").html(data.regdate);
		var detail_td = $("<td />").html(data.detail);
		var tr = $("<tr></tr>").append($(category_td), $(amount_td), $(regdate_td), $(detail_td));
		$("#recentHistory").find("tbody").append($(tr));
	});
}

function requesetDetailGoods(g_no) {
	return $.ajax({
		url : "detail.yo",
		data : {g_no : g_no},
		dataType : "json"
	})
}
</script>
</body>
</html>