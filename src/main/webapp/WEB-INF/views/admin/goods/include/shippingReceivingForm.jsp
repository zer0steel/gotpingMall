<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="x_panel">
		<div class="x_title">
			<h2>입출고내역 등록</h2>
			<ul class="nav navbar-right panel_toolbox">
				<li>
					<a class="collapse-link" id="detail-collapse"><i class="fa fa-chevron-up"></i></a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<input type="hidden" value="${msg }" id="msg">
			<form id="sr-form" data-parsley-validate class="form-horizontal form-label-left" method="post" action="sr/insert.yo">
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
			
		</div>
	</div>
</div>
<script type="text/javascript">
(function() {
	if($("#msg").val().length != 0)
		alert($("#msg").val());
})();

$("#btn-enroll").click(function() {
	if( !checkEmptyForm() )
		return;
	
	$("#sr-form").submit();
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
</script>