<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="title_left">
	<h2>재고 관리</h2>
</div>
<form id="sr-form" data-parsley-validate 
	class="form-horizontal form-label-left" method="post" action="#">
	<input type="hidden" name="g_no" value=${g.g_no }>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			분류
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<select name="category" class="form-control" id="hc" required>
				<option value="0">눌러서 선택하세요</option>
				<c:forEach var="c" items="${hc }">
					<option value="${c.code }">${c.korName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			수량
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="number" name="amount" class="form-control" required>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			가격
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="number" name="price" class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			비고
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<textarea rows="" cols="" name="detail" class="form-control" id="detail"></textarea>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
			<button type="button" class="btn btn-success" id="btn-enroll">등록</button>
		</div>
	</div>
</form>


<script type="text/javascript">
$("#btn-enroll").click(function() {
	if( !checkEmptyForm() )
		return;
	var data = $("#sr-form").serializeArray();
	requestInsertSRHistory(data).done(function(goods) {
		setGoodsForm(goods);
	}).fail(function(err) {
		console.log(err);
		alert("재고값이 음수가 되므로 내역을 추가할 수 없습니다.");
	});
});

$("select[name=category]").change(function() {
	var code = $(this).val();
	setInputPriceBox(code);
});

function setInputPriceBox(statusCode) {
	const SHIPPING = 1, EXISTING_STOCK = 2, SELL = 3, LOSS_DISTORED = 4, FREE = 5;
	var priceBox = $("input[name=price]");
	
	if(statusCode == SHIPPING)
		priceBox.val($("input[name=purchase_price]").val());
	else if(statusCode == SELL)
		priceBox.val($("input[name=sell_price]").val());
	else
		priceBox.val(0);
}

function requestInsertSRHistory(data) {
	return $.ajax({
		url : "sr/insert.yo",
		data : data,
		type : "post",
		cache : false
	})
}

function checkEmptyForm() {
	if($("select[name=category]").val() == 0) {
		alert("분류는 필수 입력사항 입니다.")
		return;
	}
	if($("input[name=amount]").val().length == 0) {
		alert("수량은 필수 입력사항 입니다.")
		return;
	}
	
	const OTHER = 6;
	if( $("#hc").val() >= OTHER) {
		var textLen = $("#detail").val().length;
		if(textLen == 0) {
			alert("기타사유를 선택하셨으면 비고란을 작성하셔야 합니다.");
			return false;
		}
	}
	return true;
}

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
	$("#stock").html(g.stock);
	$("select[name=status]").val(g.status.korName)
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
</script>