<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form id="" data-parsley-validate class="form-horizontal form-label-left">

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12" for="g_name">
		상품명
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" id="g_name" required="required" class="form-control col-md-7 col-xs-12">
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12" for="g_category">
		상품 분류
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<select id="g_category" class="form-control" required>
				<option value="">눌러서 선택하세요</option>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12" for="last-name">
		매입가격
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" id="last-name" name="last-name" required="required" class="form-control col-md-7 col-xs-12">
		</div>
	</div>
	
	<div class="form-group">
		<label for="" class="control-label col-md-3 col-sm-3 col-xs-12">
		판매가격
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input id="" name="" type="text" class="form-control col-md-7 col-xs-12" >
		</div>
	</div>
	
	<div class="form-group">
		<label for="" class="control-label col-md-3 col-sm-3 col-xs-12">
		할인율
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input id="" name="" type="text" class="form-control col-md-7 col-xs-12" >
		</div>
	</div>
	
	<div class="form-group">
		<label for="" class="control-label col-md-3 col-sm-3 col-xs-12">
		마일리지 적립률
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input id="" name="" type="text" class="form-control col-md-7 col-xs-12" >
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
		상품 공개 여부
		</label>
		<p>
		</p>
	</div>
	
	<div class="ln_solid"></div>
	<div class="form-group">
		<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
			<button class="btn btn-primary" type="reset">입력 초기화</button>
			<button type="submit" class="btn btn-success">등록</button>
		</div>
	</div>

</form>

</body>
</html>