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
	<div class="col-md-12">
		<div class="x_panel">
		
			<div class="x_title">
				<h2 id="">목록</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<jsp:include page="include/categorySelectBox.jsp"></jsp:include>
				<button type="submit" class="btn btn-warning" id="btn-update">수정</button>
				<button type="submit" class="btn btn-danger" id="btn-delete">삭제</button>
			</div>
		</div>
	</div>
</div>
<!-- 상품 분류 끝 -->
	
<div class="row">
	<!-- 상품 분류 등록 -->
	<div class="col-md-6">
		<div class="x_panel">
			<!-- 박스 상단 타이틀 -->
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
				<!-- 툴박스 끝 -->
				<div class="clearfix"></div>
			</div>
			<!-- 박스 상단 타이틀 끝 -->
			
			<!-- 박스 내용 -->
			<div class="x_content">
				<form id="category-insertForm" data-parsley-validate 
					class="form-horizontal form-label-left" method="post" action="category/insert.yo">
					<jsp:include page="include/categoryForm.jsp"></jsp:include>
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="submit" class="btn btn-success" id="btn-enroll">등록</button>
							<button type="reset" class="btn btn-primary">입력 초기화</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 박스 내용 끝 -->
		</div>
	</div>
	<!-- 상품 분류 등록 끝 -->
	<!-- 미사용 태그 목록 -->
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>미사용중인 분류</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table class="table">
					<thead>
						<tr>
							<th>분류명</th>
							<th>분류레벨</th>
							<th>분류번호</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="c" items="${categories }">
					<c:if test="${c.in_use == false }">
						<tr>
							<td>${c.title }</td>
							<td>${c.menu_levelName }</td>
							<td>${c.c_no }</td>
						</tr>
					</c:if>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

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
						<label class="control-label col-md-3 col-sm-3">
						사용 여부
						</label>
						<div class="col-md-6 col-sm-6">
							사용 : <input type="radio" name="in_use" value="true" checked>
							미사용 : <input type="radio" name="in_use" value="false">
						</div>
					</div>
					
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


<script type="text/javascript">
$("#btn-update").click(function() {
	var big = $("select[name=big]").val();
	var middle = $("select[name=middle]").val();
	var small = $("select[name=small]").val();
	if(big == null && middle == null && small == null) {
		alert("선택된 분류가 없습니다");
		return;
	}
	$("div.modal").modal();
});

$("select[data-menu_level]").click(function() {
	var c_no = $(this).val();
	if( c_no == null )
		return;
	$("input[name=c_no]").val(c_no);
	requestDetailCategory(c_no).done(function(category) {
		setSuperCategorySelectBox();
		setInsertForm(category);
		setUpdateForm(category);
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
	var subMenu_level = category.menu_level + 1;
	subMenu_level = subMenu_level > category.SMALL ? 0 : subMenu_level;
	$("#category-insertForm select[name=menu_level]").val(subMenu_level);
	$("#category-insertForm select[name=parent_no]").val(category.c_no);
	var c = Category.setMenu_level(subMenu_level);
	if( c.isSubMenu_level() )
		$("#category-insertForm select[name=parent_no]").removeAttr("disabled");
	else
		$("#category-updateForm select[name=parent_no]").attr("disabled", true);
}

function setUpdateForm(category) {
	var c = Category.setCategory(category);
	c.isSubMenu_level() ? 
		$("#category-updateForm select[name=parent_no]").removeAttr("disabled") :
		$("#category-updateForm select[name=parent_no]").attr("disabled", true);
	$("#category-updateForm select[name=menu_level]").val(category.menu_level);
	$("#category-updateForm select[name=parent_no]").val(category.parent_no);
	$("#category-updateForm input[name=title]").val(category.title);
	$("#category-updateForm input[name=in_use][value='"+ category.in_use +"']").attr("checked", "checked");
}
</script>