<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="sr-form" action="insertStock.yo" class="form-horizontal form-label-left" method="post" data-parsley-validate>
<input type="hidden" name="g_no" value=${g.g_no }>
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
						<input type="number" name="change_amount" class="form-control" required>
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
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="stockModal" >
		<div class="modal-dialog" style="display: table;">
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header">
					<!-- 닫기(x) 버튼 -->
					<button type="button" class="close" data-dismiss="modal">×</button>
					<!-- header title -->
					<h4 class="modal-title">재고 조정</h4>
				</div>
				<!-- body -->
				<div class="modal-body">
					<div class="form-group">
						<label class="control-label col-md-3">
						등록예정 재고량
						</label>
						<div class="col-md-3">
							<input class="form-control" type="number" readonly id="totalExpect">
						</div>
						<label class="control-label col-md-3">
						입력된 재고량
						</label>
						<div class="col-md-3">
							<input class="form-control" type="text" readonly value="0" id="totalInput">
						</div>
					</div>
					
					<div id="selectOption"></div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="button" class="btn btn-info" formaction="option/update.yo" id="btn-optionStockAdd">등록</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script src="${pageContext.request.contextPath }/resources/js/goods/stock.js"></script>
<script type="text/javascript">
(function() {
	var $stockTable = $('.table-stock');
	
	var option = goods.selectOption({
		g_no : $('input[name=g_no]').val(),
		$root : $('#selectOption'),
		updateMode : true,
		onStockChange : function(count) {
			let expectCount = Number($('#totalExpect').val())
			if(expectCount !== count)
				$('#totalInput').css('color', 'red');
			else
				$('#totalInput').css('color', '');
			$('#totalInput').val(count);
		}
	});
	
	$("#btn-enroll").click(function() {
		addStock();
	});
	
	var addStock = function() {
		if( !checkEmptyForm() )
			return;
		if(option.getOptionCount() > 0) {
			openModal();
			return;
		}
 		$("#sr-form").submit();
	}
	
	var checkEmptyForm = function() {
		if($("select[name=category]").val() == 0) {
			alert("분류는 필수 입력사항 입니다.")
			return false;
		}
		if($("input[name=change_amount]").val().length == 0) {
			alert("수량은 필수 입력사항 입니다.")
			return false;
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
	
	var openModal = function() {
		option.setGoodsPrice($('input[name=price]').val());
		$("#totalInput").val(0);
		$("#totalExpect").val($("input[name=change_amount]").val());
		$("#stockModal").modal();
	}
	
	$('#btn-optionStockAdd').click(function() {
		var totalInput = Number($("#totalInput").val());
		var totalExpect = Number($("#totalExpect").val());
		if(totalInput !== totalExpect) {
			alert('입력된 재고량이 등록하기로 한 예상 재고량과 다릅니다.');
			return;
		}
		$("#sr-form").submit();
	});
	
	
	$("select[name=category]").change(function() {
		var code = $(this).val();
		setInputPriceBox(code);
	});
	
	function setInputPriceBox(statusCode) {
		const SHIPPING = 1, EXISTING_STOCK = 2, SELL = 3, LOSS_DISTORED = 4, FREE = 5;
		var $priceBox = $("input[name=price]");
		
		if(statusCode == SHIPPING)
			$priceBox.val($("input[name=purchase_price]").val());
		else if(statusCode == SELL)
			$priceBox.val($("input[name=sell_price]").val());
		else
			$priceBox.val(0);
	}
})();
</script>