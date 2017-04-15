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
				<jsp:include page="include/categorySelectBox.jsp"></jsp:include>
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
				<jsp:include page="include/goodsForm.jsp"></jsp:include>				
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
$("select").change(function() {
	var c_no = $(this).val();
	$("input[name=c_no]").val( c_no );
});

$("input[type=number]").focusout(function() {
	if($(this).val().length == 0)
		$(this).val(0);
});

$("#btn-enroll").click(function() {
	if( !checkEmptyField() ) {
		return;
	}
	var goodsData = $("#goods-form").serializeArray();
	
	requestInsertGoods(goodsData).done(function(insertedCount) {
		showMessage(insertedCount);
	}).fail(function(err) {
		alert("잠시후 다시 시도해 주세요.")
		console.log(err);
	});
});

function checkEmptyField() {
	var emptyFields = $("input[required]").filter(function() {
		return $(this).val() === "";
	}).length;
	if(emptyFields > 0) {
		alert("입력 되지 않은 항목이 있습니다.");
		return false;
	}
	return true;
}

function showMessage(insertedCount) {
	if(insertedCount == 1)
		alert("등록 성공");
	else if(insertedCount == 0)
		alert("등록 실패");
	else
		alert("여러번 등록되었습니다")
}

function requestInsertGoods(goodsData) {
	return $.ajax({
		url : "insert.yo",
		type : "post",
		data : goodsData
	});
}
</script>
