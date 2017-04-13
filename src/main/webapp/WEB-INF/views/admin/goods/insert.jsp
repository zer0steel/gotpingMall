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
		<h3>상품 등록</h3>
	</div>
</div>
<div class="clearfix"></div>
<form id="goods-form" data-parsley-validate class="form-horizontal form-label-left">
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
		상품명
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="name" class="form-control col-md-7 col-xs-12" required>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
		상품 분류
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<select name="c_no" class="form-control" required>
				<option value="">눌러서 선택하세요</option>
				<c:forEach items="${categorys }" var="c">
					<option value="${c.c_no }">${c.title }</option>
				</c:forEach>
			</select>
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
		재고량
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="number" name="stock" class="form-control col-md-7 col-xs-12" required>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
		할인율
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input name="discount_rate" type="number" class="form-control col-md-7 col-xs-12" value="0">
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
		마일리지 적립률
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input name="saving_mileage" type="text" class="form-control col-md-7 col-xs-12" value="0">
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
	
	<div class="ln_solid"></div>
	<div class="form-group">
		<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
			<button type="reset" class="btn btn-primary">입력 초기화</button>
			<button type="button" class="btn btn-success" id="btn-enroll">등록</button>
		</div>
	</div>

</form>
<script type="text/javascript">
$("input[type=number]").focusout(function() {
	if($(this).val().length == 0)
		$(this).val(0);
});

$("#btn-enroll").click(function() {
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
</body>
</html>