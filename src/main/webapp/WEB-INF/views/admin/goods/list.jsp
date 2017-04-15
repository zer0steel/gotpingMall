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
						<td>${g.name }</td>
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
<div class="modal fade" id="detailModal" >
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<!-- 닫기(x) 버튼 -->
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- header title -->
				<h4 class="modal-title">상품 상세 정보</h4>
			</div>
			<!-- body -->
			<div class="modal-body">
				<form id="goods-form" data-parsley-validate 
					class="form-horizontal form-label-left" method="post" action="#">
					<input type="hidden" name="g_no">
					<img alt="상품 사진 없음">
					<jsp:include page="include/categoryForm.jsp"></jsp:include>
					<jsp:include page="include/goodsForm.jsp"></jsp:include>	
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="submit" class="btn btn-warning">수정 하기</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$("input").attr("readonly", true);
$(".category").attr("disabled", true);

$(".goods").click(function() {
	var g_no = $(this).data("g_no");
	requesetDetailGoods(g_no).done(function(goods) {
		setGoodsForm(goods);
	});
	$("#detailModal").modal();
});

function setGoodsForm(g) {
	$("select[name=menu_level]").val(g.menu_level.code);
	$("select[name=parent_no]").val(g.parent_no);
	$("input[name=title]").val(g.title);
	$("input[name=c_no]").val(g.c_no);
	$("input[name=name]").val(g.name);
	$("input[name=purchase_price]").val(g.purchase_price);
	$("input[name=sell_price]").val(g.sell_price);
	$("input[name=discount_rate]").val(g.discount_rate);
	$("input[name=saving_mileage]").val(g.saving_mileage);
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