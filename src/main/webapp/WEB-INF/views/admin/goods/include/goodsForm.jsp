<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<input type="hidden" name="c_no" required>
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
	상세 설명
	</label>
	<div class="col-md-6 col-sm-6 col-xs-12">
		<textarea rows="" cols="" name="detail"></textarea>
	</div>
</div>