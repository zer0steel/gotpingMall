<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/resources/vendors/dropzone/dist/min/dropzone.min.css" rel="stylesheet">
<div class="page-title">
	<div class="title_left">
		<h3>상품 등록</h3>
	</div>
</div>
<br />
<div class="clearfix"></div>
<!-- 상품 분류 -->
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
		
			<div class="x_title">
				<h2 id="">목록</h2>
				<div class="clearfix"></div>
			</div>
			
			<div class="x_content">
				<jsp:include page="include/categorySelectNode.jsp"></jsp:include>
				<br>
				<a href="${pageContext.request.contextPath}/admin/goods/category.yo" class="btn btn-success btn-sm">분류 편집 하기</a>
			</div>
		</div>
	</div>
</div>
<!-- 상품 분류 끝 -->
<div class="row">
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>상품 정보</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="goods-form" data-parsley-validate class="form-horizontal form-label-left">
				<input type="hidden" name="c_no" id="c_no" required>
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">
					상품명
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<input type="text" name="name" class="form-control col-md-7 col-xs-12" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">
					매입가격
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<input type="number" name="purchase_price" class="form-control col-md-7 col-xs-12" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">
					판매가격
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<input name="sell_price" type="number" class="form-control col-md-7 col-xs-12" required>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">
					할인율 (%)
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<input name="discount_rate" type="number" class="form-control col-md-7 col-xs-12" value="0" min="0" max="100">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">
					마일리지 적립률 (%)
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<input name="saving_mileage" type="number" class="form-control col-md-7 col-xs-12" value="0" min="0" max="100">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">
					상품 공개 여부
					</label>
					<p>
						공개 <input type="radio" name="show" value="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						비공개 <input type="radio" name="show" value="false" checked>
					</p>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3 col-xs-12">
					상세 설명
					</label>
					<div class="col-md-6 col-sm-6 col-xs-12">
						<textarea rows="" cols="" name="detail"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						<button type="reset" class="btn btn-primary">입력 초기화</button>
						<button type="button" class="btn btn-success" id="btn-enroll">등록</button>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>상품 이미지</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form action="form_upload.html" class="dropzone"></form>
			</div>
		</div>
	</div>
	
	<div class="ln_solid"></div>
</div>
<script src="${pageContext.request.contextPath}/resources/vendors/dropzone/dist/min/dropzone.min.js"></script>
<script type="text/javascript">
$("#category-title").html("상품 분류");
$("select").change(function() {
	var c_no = $(this).val();
	$("#c_no").val( c_no );
});

$("input[type=number]").focusout(function() {
	if($(this).val().length == 0)
		$(this).val(0);
});

$("#btn-enroll").click(function() {
	var emptyFields = $("input[required]").filter(function() {
		return $(this).val() === "";
	}).length;
	if(emptyFields > 0) {
		alert("입력 되지 않은 항목이 있습니다.");
		return;
	}
	var goodsData = $("#goods-form").serializeArray();
	insertGoods(goodsData);
});

function insertGoods(goodsData) {
	$.ajax({
		url : "insert.yo",
		type : "post",
		data : goodsData
	}).done(function(insertedCount) {
		if(insertedCount == 1)
			alert("등록 성공");
		else if(insertedCount == 0)
			alert("등록 실패");
		else
			alert("여러번 등록되었습니다")
	}).fail(function(err) {
		alert("잠시후 다시 시도해 주세요.")
		console.log(err);
	});
}
</script>
