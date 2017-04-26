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
<!-- 상품 분류 -->
<div class="row">
	<div class="col-md-6">
		<div class="x_panel">
		
			<div class="x_title">
				<h2 id="">목록</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<jsp:include page="include/categorySelectBox.jsp"></jsp:include>
				<button class="btn btn-warning" id="btn-update">수정</button>
				<button class="btn btn-danger" id="btn-delete">삭제</button>
				<form action="category/delete.yo" id="category-deleteForm" method="post">
					<input type="hidden" name="c_no">
				</form>
			</div>
		</div>
	</div>
	<!-- 상품 분류 등록 -->
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>등록</h2>
				<!-- 툴박스 -->
				<ul class="nav navbar-right panel_toolbox">
					<li>${msg }</li>
					<li>
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			
			<!-- 박스 내용 -->
			<div class="x_content">
				<form id="category-insertForm" data-parsley-validate 
					class="form-horizontal form-label-left" method="post" action="category/insert.yo">
					
					<jsp:include page="include/categoryForm.jsp"></jsp:include>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="button" class="btn btn-success" id="btn-enroll">등록</button>
							<button type="reset" class="btn btn-primary">입력 초기화</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 박스 내용 끝 -->
		</div>
	</div>
	<!-- 상품 분류 등록 끝 -->
</div>
<!-- 상품 분류 끝 -->
	
<div class="row">
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2 id="">옵션 목록</h2>
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
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h2 id="h2-title"></h2>
					<select name="o_no" size="5" style="width: 100%;"></select>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2 id="">옵션 추가</h2>
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
</div>
<!-- 수정 모달 -->
<div class="modal fade" id="updateModal" >
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<!-- 닫기(x) 버튼 -->
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- header title -->
				<h4 class="modal-title">수정</h4>
			</div>
			<!-- body -->
			<div class="modal-body">
				<form id="category-updateForm" data-parsley-validate 
					class="form-horizontal form-label-left" method="post" action="category/update.yo">
					<jsp:include page="include/categoryForm.jsp"></jsp:include>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="submit" class="btn btn-warning">수정</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 수정 모달 끝 -->

<!-- 수정 모달 -->
<div class="modal fade" id="optionModal" >
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<!-- 닫기(x) 버튼 -->
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- header title -->
				<h4 class="modal-title">수정 / 삭제</h4>
			</div>
			<!-- body -->
			<div class="modal-body">
				<form id="option-form" data-parsley-validate 
					class="form-horizontal form-label-left" method="post">
					
					<input type="hidden" name="o_no">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						분류명
						</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" class="form-control" name="title" readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						옵션 이름
						</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" class="form-control" name="o_name" required>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="submit" class="btn btn-warning" formaction="option/update.yo">수정</button>
							<button type="button" class="btn btn-danger btn-delete" formaction="option/delete.yo">삭제</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 수정 모달 끝 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/goods/categoryForm.js"></script>
<script type="text/javascript">
$(".btn-delete").click(function() {
	if(confirm("정말로 삭제하시겠습니까?")) {
		var url = $(this).attr("formaction");
		$(this).parents("form").attr("action", url).submit();
	}
});

$("#btn-enroll").click(function() {
	if( validationCheck() ) {
		if( $("select[name=menu_level]").val() != Category.BIG )
			$("#category-insertForm").find("select[name=super_no]").removeAttr("disabled");
		$("#category-insertForm").submit();
	}
});

function validationCheck() {
	var menu_level = $("select[name=menu_level]").val();
	if(menu_level == CategoryOption.CHOICE.code) {
		alert("분류레벨이 선택되지 않았습니다.")
		return false;
	}
	
	var super_no = $("select[name=super_no]").val();
	if( menu_level >= Category.MIDDLE && super_no == CategoryOption.NOT_EXIST_SUPER.code) {
		alert(CategoryOption.NOT_EXIST_SUPER.msg);
		return false;
	}
	
	if( menu_level >= Category.MIDDLE && super_no == CategoryOption.CHOICE.code) {
		alert("상위분류가 선택되지 않았습니다.");
		return false;
	}
	
	if( $("input[name=title]").val().length == 0) {
		alert("분류명이 입력되지 않았습니다.");
		return false;
	}
	return true;
}

$("#btn-delete").click(function() {
	var c_no = $("#category-updateForm input[name=c_no]").val();
	if(c_no > 0) {
		if(confirm("정말로 해당 분류를 삭제하시겠습니까?")) {
			$("input[name=c_no]").val(c_no)
			$("#category-deleteForm").submit();
		}
	}
	else {
		alert("선택된 분류가 없습니다.")
	}
});

$("#btn-update").click(function() {
	if( checkCategorySelected() )
		$("div.modal").modal();
});

function checkCategorySelected() {
	var big = $("select[name=big]").val();
	var middle = $("select[name=middle]").val();
	var small = $("select[name=small]").val();
	if(big == null && middle == null && small == null) {
		alert("선택된 분류가 없습니다");
		return false;
	}
	return true;
}
/* 상단의 상품 분류 select box 클릭시 동작 */
$("select[data-menu_level]").click(function() {
	var c_no = $(this).val();
	if( c_no == null )
		return;
	
	requestDetailCategory(c_no).done(function(category) {
		setInsertForm(category);
		setUpdateForm(category);
		setGoodsOptionForm(category);
		setOptionList(category);
	});
});

function requestDetailCategory(c_no) {
	return $.ajax({
		url : "category/detail.yo",
		data : {c_no : c_no },
		type : "post",
		dataType : "json"
	});
}

function setInsertForm(category) {
	var insertForm = $("#category-insertForm");
	var subCategory = Category.setSubMenu_level( category.menu_level );
	if( subCategory.isExisting() ) {
		insertForm.find("select[name=super_no]").val( category.c_no );
		insertForm.find("select[name=menu_level]").val( subCategory.menu_level ).removeAttr("disabled");
		insertForm.find("input[name=title]").removeAttr("disabled");
		insertForm.find("#btn-enroll").removeAttr("disabled");
	}
	else {
		var code = CategoryOption.CANNOT_CHOICE.code;
		insertForm.find("select[name=super_no]").val(code).attr("disabled", true);
		insertForm.find("select[name=menu_level]").val(code).attr("disabled", true);
		insertForm.find("input[name=title]").attr("disabled", true);
		insertForm.find("#btn-enroll").attr("disabled", true);
	}
}

function setUpdateForm(category) {
	var c = Category.setCategory(category);
	var updateForm = $("#category-updateForm");
	c.isSubMenu_level() ? 
		updateForm.find("select[name=super_no]").removeAttr("disabled") :
		updateForm.find("select[name=super_no]").attr("disabled", true);
	updateForm.find("select[name=menu_level]").val(category.menu_level);
	updateForm.find("select[name=super_no]").val(category.super_no);
	updateForm.find("input[name=title]").val(category.title);
	updateForm.find("input[name=c_no]").val(category.c_no);
}

function setGoodsOptionForm(category) {
	$("#option-insertForm").find("input[name=c_no]").val(category.c_no)
	.prev().val(category.title);
}

function setOptionList(category) {
	$("#h2-title").html(category.title);
	$("select[name=o_no]").empty();
	var options = category.options;
	$(options).each(function(idx) {
		var option = $("<option />").html(options[idx].o_name).val(options[idx].o_no);
		$("select[name=o_no]").append(option);
	});
}

$("select[name=o_no]").on("click",function() {
	var o_no = $(this).val();
	var o_name = $(this).find("option:selected").text();
	var c_titlte = $("#h2-title").text();
	$("#option-form").find("input[name=title]").val(c_titlte);
	$("#option-form").find("input[name=o_no]").val(o_no);
	$("#option-form").find("input[name=o_name]").val(o_name);
	$("#optionModal").modal();
});
</script>