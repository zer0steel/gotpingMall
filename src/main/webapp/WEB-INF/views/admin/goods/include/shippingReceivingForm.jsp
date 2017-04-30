<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<form id="stock-form" data-parsley-validate 
					class="form-horizontal" method="post">
					
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
					
					<div class="form-group">
						<c:forEach var="opt" items="${g.goodsOptions }">
						<table class="table table-striped table-bordered table-stock" data-o_no="${opt.o_no }">
						<thead>
							<tr>
								<td width="10%"></td>
			 					<c:forEach var="col" items="${opt.values }">
								<td width="*">${col }</td>
								</c:forEach>
							</tr>
						</thead>
						<tbody>					
							<tr>
								<td>${opt.o_name }</td>
								<c:forEach var="s" items="${opt.go_stocks }">
								<td><input type=number class="form-control input-stock" value="0"></td>
								</c:forEach>
							</tr>
						</tbody>
						</table>
						</c:forEach>
					</div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="button" class="btn btn-info" formaction="option/update.yo" id="btn-optionStockAdd">등록</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
(function() {
	var $stockTable = $('.table-stock');
	
	$("#btn-enroll").click(function() {
		addStock();
	});
	
	var addStock = function() {
		if( !checkEmptyForm() )
			return;
		if( $stockTable.data('o_no') ) {
			openModal();
			return;
		}
		$("#sr-form").submit();
	}
	
	var checkEmptyForm = function() {
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
	
	var openModal = function() {
		$(".input-stock").each(function() {
			$(this).val(0);
		});
		$("#totalInput").val(0);
		$("#totalExpect").val($("input[name=amount]").val());
		$("#stockModal").modal();
	}
	
	Array.prototype.allTheSame = function() {
		if(this.length === 1) 
			return true;
		
		for(var i = 1; i < this.length; i++) {
			if(this[0] !== this[i])
				return false;
		}
		
		return true;
	}
	
	$(".input-stock").keyup(function() {
		calculateTotalInput();
	});
	
	var calculateTotalInput = function() {
		var inputs = sumOfStocks();
		
		var $totalInput = $('#totalInput');
		if( !inputs.allTheSame() ) {
			$totalInput.val('재고값이 다릅니다.');
			return;
		}
		$totalInput.val(inputs[0]);
		var totalExpect = Number($("#totalExpect").val());
		if(totalExpect < inputs[0])
			$totalInput.css("color","red");
		else
			$totalInput.css("color","");
	}
	
	var sumOfStocks = function() {
		var inputs = [];
		$stockTable.each(function() {
			inputs.push( calculateInput( $(this) ) );
		});
		return inputs;
	}
	
	var calculateInput = function($table) {
		var inputTotal = 0;
		$table.find(".input-stock").each(function() {
			inputTotal += Number($(this).val());
		});
		return inputTotal;
	}
	
	$('#btn-optionStockAdd').click(function() {
		var options = getOptions();
		$(options).each(function() {
			$('<input />').attr({'type':'hidden','name':'goodsOptions'})
			.val(JSON.stringify(this)).appendTo( $('#sr-form') );
		});
		$("#sr-form").submit();
	});
	
	var getOptions = function() {
		var options = [];
		$stockTable.each(function() {
			options.push( createOption( $(this) ) );
		});
		return options;
	}
	
	var createOption = function($table) {
		var option = {
			g_no : $('input[name=g_no]').val(),
			o_no : $table.data('o_no'),
			go_stocks : []
		};
		$table.find('.input-stock').each(function() {
			option.go_stocks.push( $(this).val() );
		})
		return option;
	} 
	
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