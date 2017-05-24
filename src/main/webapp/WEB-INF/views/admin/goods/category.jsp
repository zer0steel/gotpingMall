<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-title">
	<div class="title_left">
		<h3>상품 분류</h3>
	</div>
</div>
<br />
<div class="clearfix"></div>
<div class="col-md-6">
	<div class="x_panel">
		<div class="x_title">
			<h2>등록</h2>
			<ul class="nav navbar-right panel_toolbox">
				<li>
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		
		<div class="x_content">
			<form id="category-insertForm" data-parsley-validate 
				class="form-horizontal form-label-left" method="post" action="category/insert.yo">
				
				<div id="select" class="form-group"></div>
				<input type="hidden" name="c_no">
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">
					분류 레벨
					</label>
					<div class="col-md-6 col-sm-6">
						<select name="levels" class="form-control category">
							<option value="1">대분류</option>
							<option value="2">중분류</option>
							<option value="3">소분류</option>
							<option value="4">하위분류가 존재하지 않습니다.</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">
					상위 분류
					</label>
					<div class="col-md-6 col-sm-6">
						<input type="text" name="super_name" class="form-control" value="분류 목록에서 선택하세요." readonly>
						<input type="hidden" name="super_no" class="form-control" value="" readonly>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">
					분류명
					</label>
					<div class="col-md-6 col-sm-6">
						<input type="text" name="title" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						<button type="submit" class="btn btn-success">등록</button>
						<button type="button" class="btn btn-warning" id="btn-update">수정</button>
						<button type="button" class="btn btn-danger" id="btn-delete" formaction="category/delete.yo">삭제</button>
						<button type="reset" class="btn btn-primary">입력 초기화</button>
					</div>
				</div>
				
			</form>
		</div>
	</div>
</div>
	
<div class="col-md-6">
	<div class="x_panel">
		<div class="x_title">
			<h2 id="">옵션</h2>
			<ul class="nav navbar-right panel_toolbox">
				<li>
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<table class="table table-striped table-bordered" id="table-option">
				<thead>
					<tr>
						<th width="10%">분류명</th>
						<th width="*">옵션 이름</th>
						<th width="15%">기능</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<form method="post" id="option-form">
				<input type="hidden" name="o_no">
				<input type="hidden" name="o_name">
				<input type="hidden" name="c_no">
			</form>
			<form action="option/insert.yo" method="post" id="option-insertForm" 
				data-parsley-validate class="form-horizontal form-label-left">
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">
					분류명
					</label>
					<div class="col-md-6 col-sm-6">
						<input type="text" class="form-control" required readonly>
						<input type="hidden" class="form-control" name="c_no">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-md-3 col-sm-3">
					옵션 이름
					</label>
					<div class="col-md-6 col-sm-6">
						<input type="text" name="o_name" class="form-control" required>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
						<button type="submit" class="btn btn-success" id="btn-enroll">등록</button>
						<button type="reset" class="btn btn-primary">입력 초기화</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/goods/category.js?ver=2"></script>
<script type="text/javascript">
(function() {
	goods.category({ 
		$root : $('#select'),
		selectSize : 4,
		onChange : function(category) {
 			setInsertForm(category);
 			setGoodsOptionForm(category);
 			setOptionTable(category);
		}
	});
	
	function setInsertForm(category) {
		let $form = $('#category-insertForm');
		$form.find('select[name=levels]').val(category.subLevel());
		$form.find('input[name=super_name]').val(category.title);
		$form.find('input[name=super_no]').val(category.c_no);
		$form.find('input[name=c_no]').val(category.c_no);
		
		$form.find('button[type=submit]').attr('disabled', category.subLevel() > 3);
	}
	
	$('#btn-delete').click(function() {
		let $form = $('#category-insertForm');
		deleteCategory($(this).attr('formaction'), $form);
	});
	
	var deleteCategory = function(url, $form) {
		if(!$form.find('input[name=c_no]').val()) {
			alert('선택된 분류가 없습니다.');
			return;
		}
		let c_no = $form.find('input[name=c_no]').val();
		requestSubCategory(c_no).done(function(subList) {
			if(subList.length > 0) {
				alert('하위 분류가 존재하므로 삭제하실 수 없습니다.')
				return;
			} 
			if(confirm('선택하신 분류를 삭제하시겠습니까?'))
				$form.attr('action', url).submit();
		});
		
	}
	
	function requestSubCategory(c_no) {
		return $.ajax({
			url : location.protocol + '//' + location.host + '/controller/goods/category/subList.yo',
			data : {c_no : c_no}
		})
	}
	
	function setGoodsOptionForm(category) {
		var $form = $("#option-insertForm");
		if(Number(category.levels.code) === 1) {
			$form.find("button[type=submit]").removeAttr("disabled");
			
			$form.find("input[name=c_no]").val(category.c_no)
			.prev().val(category.title);
		}
		else {
			$form.find("button[type=submit]").attr("disabled", true);
			$form.find("input[name=c_no]").prev().val("대분류에서만 추가할 수 있습니다.");
		}
	}

	function setOptionTable(category) {
		var $table = $("#table-option").find("tbody");
		$table.empty();
		
		var options = category.options;
		var len = $(options).each(function(idx) {
			var $tr = $("<tr />");
			if(idx == 0) {
				$("<td />").appendTo( $tr );
			}
			var $input = $("<input />")
				.addClass("form-control")
				.attr({'type':'text', 'readonly': true})
				.val( this.o_name );
			$("<td />").append( $input ).appendTo( $tr );
			$("<td />").append(
				$("<button />").addClass("btn btn-danger btn-delete")
					.attr({'formaction':'option/delete.yo'}).val(this.o_no).text('삭제')
			).appendTo( $tr );
			$tr.appendTo( $table );
		}).length;
		$table.find('td:first').attr('rowspan', len).text(category.title);
	}
	
	$('body').on('click','.btn-delete', function() {
		if(confirm("정말로 삭제하시겠습니까?")) {
			var url = $(this).attr("formaction");
			var o_no = $(this).val();
			
			var $form = $("#option-form");
			$form.find('input[name=o_no]').val(o_no);
			$form.attr('action', url).submit();
		}
	});
}());
</script>