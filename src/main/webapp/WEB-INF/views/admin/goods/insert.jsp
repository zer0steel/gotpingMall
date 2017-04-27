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
<!-- 상품 분류 -->
<div class="row">
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>상품 정보</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="goods-form" data-parsley-validate class="form-horizontal form-label-left" action="insert.yo" method="post">
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
				<h2 id="">분류 목록</h2>
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

<div class="row">
	<!-- <div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>옵션 편집</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="option-form" data-parsley-validate class="form-horizontal form-label-left" method="post">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						옵션명
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<select name="o_no" class="form-control">
								<option value="-1">-------------</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						옵션값
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" class="form-control" id="optionValue">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						옵션값
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<input type="text" class="form-control" id="optionValue">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="reset" class="btn btn-primary">입력 초기화</button>
							<button type="button" class="btn btn-success" id="btn-addOption">추가</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div> -->
	
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>옵션 목록</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<button class="btn btn-info" id="btn-addOptionTest">옵션 추가</button>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table class="table table-striped table-bordered" id="table-option">
					<thead>
						<tr>
							<td width="15%">옵션 이름</td>
							<td width="35%">항목</td>
							<td width="*">추가가격</td>
							<td width="10%">필수</td>
							<td width="10%">기능</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
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
				<form action="${pageContext.request.contextPath}/file/upload.yo"
				class="dropzone" id="uploadFile" enctype="multipart/form-data" method="post" data-paramName="file">
				</form>
			</div>
		</div>
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/4.3.0/min/dropzone.min.js"></script>
<script type="text/javascript">
var goods = goods || {};
goods.option = function() {
	'use strict';
	var $tbody, goodsOptions;
	
	/*
	 * 생성자함수 
	 * @param $inputTtbody : 옵션 tr들이 입력될 부모 tbody
	 */
	function option( $inputTbody ) {
		$tbody = $inputTbody;
		$tbody.on('click', 'a', function() {
			createOptionValueTr( $(this) );
		});
		$tbody.on('click', '.btn-delete', function() {
			deleteOptionValueTr( $(this) );
		});
	};
	
	/*
	 * 입력되어 있는 옵션들을 json객체리스트로 반환한다.
	 */
	option.getOptionList = function() {
		var option;
		var options = [];
		
		$tbody.find('tr').each(function(idx) {
			var $tr = $(this);
			
			if($tr.attr('class') == 'option') 
				option = createOption( $tr );
			else {
				option.value += $tr.find('input[name=value]').val() + '/';
				option.extra_cost += $tr.find('input[name=extra_cost]').val() + '/';
				
				var nextTr = $tr.next()[0];
				if($(nextTr).attr('class') != 'value')
					options.push( option );
			}
		});
		
		return options;
	};
	
	function createOption( $tr ) {
		return {
			o_no : $tr.find('select').val(),
			required : $tr.find('input[type=checkbox]').is(':checked'),
			extra_cost : '',
			value : ''
		}
	};
	
	option.setOptionData = function( options ) {
		$tbody.empty();
		goodsOptions = options
	};
	
	option.createOptionTr = function() {
		if( !optionCheck() )
			return;
		
		var $tr = $('<tr />').attr('class', 'option').appendTo( $tbody );
		
		var $select = $('<select />').addClass('form-control');
		$(goodsOptions).each(function() {
			$('<option />').html(this.o_name).val(this.o_no).appendTo( $select );
		});
		$('<td />').append( $select ).appendTo( $tr );
		$('<td />').append( $('<a />').addClass('btn btn-info').text('항목 추가') ).appendTo( $tr );
		$('<td />').appendTo( $tr );
		$('<td />').append( $('<input />').attr({'type':'checkbox'})).appendTo( $tr );
		$('<td />').append( $('<button />').addClass('btn btn-danger btn-delete').html('옵션 삭제') ).appendTo( $tr );
	};
	
	function optionCheck() {
		var optCount = $tbody.find('tr.option').length;
		if(goodsOptions == null) {
			alert('분류를 지정하셔야 합니다.');
			return false;
		}
		if(goodsOptions.length == 0) {
			alert('옵션이 존재하지 않습니다.')
			return false;
		}
		if(goodsOptions.length <= optCount) {
			// 최대 옵션 종류의 개수보다 더 많은 옵션을 추가하려고 할때 실행된다.
			alert('더이상 추가할수 없습니다.')
			return false;
		}
		return true;
	};
	
	function createOptionValueTr( $a ) {
		var $tr = $('<tr />').attr('class', 'value');
		$('<td />').appendTo( $tr );
		$('<td />').append($('<input />').attr({'type':'text', 'name':'value'})).appendTo( $tr );
		$('<td />').append($('<input />').attr({'type':'number', 'name':'extra_cost'})).appendTo( $tr );
		$('<td />').appendTo( $tr );
		$('<td />').append( $('<button />').addClass('btn btn-danger btn-warning btn-delete').html('삭제') ).appendTo( $tr );
		$a.parents('tr').after( $tr );
	};
	
	function deleteOptionValueTr( $btn ) {
		var $tr = $btn.parents('tr');
		if( $tr.attr('class') == 'option' && $tr.next().attr('class') == 'value' ) {
			alert('하위 옵션이 존재하면 삭제할수 없습니다.');
			return;
		}
		$tr.remove();
	};
	
	return option;
};

// 뷰페이지 부분
(function() {
	var option = goods.option();
	option( $("#table-option").find('tbody') );
	
	$("#btn-addOptionTest").click(function() {
		option.createOptionTr();
	});
	
	//대분류 항목이 클릭될때 실행
	$(".topMenu_level").change(function() {
		var c_no = $(this).val();
		requestGetOption(c_no).done(function(options) {
			option.setOptionData( options );
		});
	});
	
	/*
	 * 해당 분류번호에 등록된 옵션들을 가져온다.
	 * @param c_no : 분류번호
	 */
	var requestGetOption = function(c_no) {
		return $.ajax({
			url : "option/get.yo?c_no=" + c_no,
			dataType : "json"
		});
	}

	/*
	 * 상품을 등록한다.
	 */
	$("#btn-enroll").click(function() {
		var c_no = $("input[name=c_no]").val();
		if( c_no.length < 1 ) {
			alert("분류를 지정하셔야 합니다.");
			return;
		}
		else {
			if( !checkEmptyField() )
				return;
			addOptionsToForm( option.getOptionList() );
			
			$("#goods-form").submit();
		}
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
	 * 리스트에 있는 데이터를 form안에 json string으로 변환하여 넣는다.
	 */
	var addOptionsToForm = function(options) {
		$(options).each(function() {
			$("<input />")
			.attr({"type":"hidden", "name":"goodsOptionJSON"})
			.val( JSON.stringify( this ) )
			.appendTo( $("#goods-form") );
		});
	}
	
	/*
	 * 숫자 입력 필드에서 포커스아웃될때 강제적으로 0을 부여
	 */
	$("input[type=number]").focusout(function() {
		if($(this).val().length == 0)
			$(this).val(0);
	});

	/*
	 * 상단의 분류 클릭했을때 해당 분류번호를 상품 입력 폼 안에 입력
	 */
	$("select.menu_level").change(function() {
		var c_no = $(this).val();
		$("input[name=c_no]").val( c_no );
	});
	
	Dropzone.autoDiscover = false;
	var	isFirst = true;
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
				var data = JSON.stringify(fileInfo);
				var hiddenFileData = $("<input />").attr({"type":"text", "name":"fileInfoJSON"}).val(data);
				$("#goods-form").append( hiddenFileData );
			});
		}
	});
	
})();
</script>