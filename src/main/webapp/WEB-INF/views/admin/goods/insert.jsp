<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.3.0/min/dropzone.min.css" rel="stylesheet">
<div class="page-title">
	<div class="title_left">
		<h3>상품 등록</h3>
	</div>
</div>
<br />
<div class="clearfix"></div>
<form id="goods-form" class="form-horizontal form-label-left" action="insert.yo" method="post" data-parsley-validate>
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>상품 정보</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div id="select" class="form-group"></div><br>
				<input type="hidden" name="c_no">
				<input type="text" name="name" class="form-control input" data-label="상품명" required>
				<input type="number" name="purchase_price" class="form-control input" data-label="매입가격" required>
				<input type="number" name="sell_price"  class="form-control input" data-label="판매가격" required>
				<input type="number" name="discount_rate"  class="form-control input" data-label="할인율 (%)" min="0" max="100" required>
				<input type="number" name="saving_mileage"  class="form-control input" data-label="마일리지 적립율 (%)" min="0" max="100" required>
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						<button type="reset" class="btn btn-primary">입력 초기화</button>
						<button type="button" class="btn btn-success" id="btn-enroll">등록</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>옵션 목록</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table class="table table-striped table-bordered" id="table-option"></table>
			</div>
		</div>
	</div>
</form>
<div class="col-md-6">
	<div class="x_panel">
		<div class="x_title">
			<h2>상품 이미지</h2>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<form action="${pageContext.request.contextPath}/file/upload.yo"
			class="dropzone" id="uploadFile" enctype="multipart/form-data" method="post" data-paramName="file">
			</form>
		</div>
	</div>
</div>

	
<script src="${pageContext.request.contextPath }/resources/js/goods/category.js?ver=2"></script>
<script src="${pageContext.request.contextPath }/resources/js/goods/goodsOption.js?ver=1"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.3.0/min/dropzone.min.js"></script>
<script type="text/javascript">
(function() {
	goods.category({
		$root : $('#select'),
		selectSize : 4,
		onChange : function(category) {
			if(category.levels.code == 1)
				setupOptions(category.c_no);
			$("input[name=c_no]").val(category.c_no);
		}
	});
	
	var option = goods.option($("#table-option"));
	
	var setupOptions = function(c_no) {
		requestGetOption(c_no).done(function(options) {
			option.setOptionData( options );
		});
		
		function requestGetOption(c_no) {
			return $.ajax({
				url : "option/get.yo?c_no=" + c_no,
				dataType : "json"
			});
		}
	}

	/*
	 * 상품을 등록한다.
	 */
	$("#btn-enroll").click(function() {
		var c_no = $("input[name=c_no]").val();
		if(!c_no) {
			alert("분류를 지정하셔야 합니다.");
			return;
		}
		if( !checkEmptyField() )
			return;
		$("#goods-form").submit();
	});

	/*
	 * 상품 등록 폼 안에 있는 필수입력항목중 입력 안된 항목이 있나 검사
	 */
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
	
	/*
	 * 숫자 입력 필드에서 포커스아웃될때 강제적으로 0을 부여
	 */
	$('#goods-form').find("input[type=number]").focusout(function() {
		alert('gg')
		if($(this).val().length == 0)
			$(this).val(0);
	});

	Dropzone.autoDiscover = false;
	var	isFirst = true;
	var fileCount = 0;
	$("#uploadFile").dropzone({
		init : function() {
			this.on("success", function(file, fileInfo) {
				delete fileInfo.save_path;
				delete fileInfo.regdate;
				if( isFirst ) {
					fileInfo.location = "main";
					isFirst = false;
				}
				else
					fileInfo.location = "main_sub";
				console.log(fileInfo);
				$("#goods-form").append(
					$("<input />").attr({'type':'text', 'name':'images[' + fileCount + '].f_no'}).val(fileInfo.f_no),
					$("<input />").attr({'type':'text', 'name':'images[' + fileCount + '].location'}).val(fileInfo.location),
					$("<input />").attr({'type':'text', 'name':'images[' + fileCount + '].save_name'}).val(fileInfo.save_name)
				);
				fileCount++;
			});
		}
	});
})();

const CSS_CLASS = {
		label : "col-md-3 col-xs-12",
		div : "col-md-5 col-xs-12"
	}
</script>