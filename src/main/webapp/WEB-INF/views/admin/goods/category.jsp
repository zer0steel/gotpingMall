<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-title">
	<div class="page-left">
		<h3>상품 분류</h3>
	</div>
</div>
<br />
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
				<jsp:include page="categorySelectNode.jsp"></jsp:include>
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
				<h2>등록 / 수정 / 삭제</h2>
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
				<form id="category-form" data-parsley-validate class="form-horizontal form-label-left" method="post">
					<input type="hidden" name="c_no">
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						분류 레벨
						</label>
						<div class="col-md-6 col-sm-6">
							<select name="menu_level" class="form-control" required>
								<option value="">눌러서 선택하세요</option>
								<option value="0">대분류</option>
								<option value="1">중분류</option>
								<option value="2">소분류</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						상위 분류
						</label>
						<div class="col-md-6 col-sm-6">
							<select name="parent_no" class="form-control" disabled="disabled">
							<option value="">눌러서 선택하세요</option>
							<c:forEach items="${categories }" var="c">
								<c:if test="${c.menu_level != Category.SMALL }">
									<option value="${c.c_no }" data-menu_level="${c.menu_level }">
										${c.title }
									</option>
								</c:if>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						분류명
						</label>
						<div class="col-md-6 col-sm-6">
							<input type="text" name="title" class="form-control" value="" required>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						사용 여부
						</label>
						<div class="col-md-6 col-sm-6">
							사용 : <input type="radio" name="in_use" value="true">
							미사용 : <input type="radio" name="in_use" value="false">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="submit" class="btn btn-success" id="btn-enroll" formaction="category/insert.yo">등록</button>
							<button type="submit" class="btn btn-warning" id="btn-update" formaction="category/update.yo">수정</button>
							<button type="submit" class="btn btn-danger" id="btn-delete" formaction="category/delete.yo">삭제</button>
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

<script type="text/javascript">
$("select[data-menu_level]").change(function() {
	var c_no = $(this).val();
	$("input[name=c_no]").val(c_no);
	$.ajax({
		url : "category/detail.yo",
		data : {c_no : c_no },
		type : "post",
		dataType : "json"
	}).done(function(category) {
		if(category.menu_level != Category.BIG)
			$("select[name=parent_no]").removeAttr("disabled");
		else
			$("select[name=parent_no]").attr("disabled","disabled");
			
		$("select[name=menu_level]").val(category.menu_level);
		if(category.parent_no == 0)
			category.parent_no = "";
		$("select[name=parent_no]").val(category.parent_no);
		$("input[name=title]").val(category.title);
		$("input[name=in_use][value='"+ category.in_use +"']").attr("checked", "checked");
		setParentCategoryInputBox();
	});
});

$("select[name=menu_level]").change(function() {
	setParentCategoryInputBox();
});

function setParentCategoryInputBox() {
	var menu_level = $("select[name=menu_level]").val();
	var c = Category.setMenu_level(menu_level);
	if( c.isSubMenu_level() ) {
		$("select[name=parent_no]").attr("required","required").removeAttr("disabled");
		$("select[name=parent_no]").children("option").each(function() {
			var menu_level = $(this).data("menu_level");
			if( c.checkSuperMenu_level( menu_level ) ) 
				$(this).css("display","block");
			else
				$(this).css("display","none");
		});
	}
}
</script>