<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row">
	<div class="col-md-12">
	
	</div>
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>카테고리 등록</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="category-form" data-parsley-validate class="form-horizontal form-label-left" action="insertCategory.yo" method="post">
				
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						카테고리 레벨
						</label>
						<div class="col-md-6 col-sm-6">
							<select name="step" class="form-control" required>
								<option value="">눌러서 선택하세요</option>
								<option value="0">대분류</option>
								<option value="1">중분류</option>
								<option value="2">소분류</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						상위 카테고리
						</label>
						<div class="col-md-6 col-sm-6">
							<select name="parent_no" class="form-control" disabled="disabled">
							<option value="">눌러서 선택하세요</option>
							<c:forEach items="${categories }" var="c">
								<c:if test="${c.step != 2 }">
									<option value="${c.c_no }" data-step="${c.step }">
										${c.title }
									</option>
								</c:if>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">
						카테고리명
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
							사용 : <input type="radio" name="in_use" checked>
							미사용 : <input type="radio" name="in_use">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="reset" class="btn btn-primary">입력 초기화</button>
							<button type="submit" class="btn btn-success" id="btn-enroll">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$("select[name=step]").change(function() {
	var c = Category.setStep( $(this).val() );
	if( c.isSubStep() ) {
		$("select[name=parent_no]").attr("required","required").removeAttr("disabled");
		$("select[name=parent_no]").children("option").each(function() {
			var step = $(this).data("step");
			if( c.checkSuperStep( step ) ) 
				$(this).css("display","block");
			else
				$(this).css("display","none");
		});
	}
	else {
		$("select[name=parent_no]").attr("disabled", "disabled").val("");
	}
});

var Category = {
	BIG : 0, MIDDLE : 1, SMALL : 2,
	setStep : function(step) {
		this.step = step;
		return this;
	},
	isSubStep : function() {
		return this.step > this.BIG;
	},
	checkSuperStep : function(step) {
		return this.step - 1 == step;
	}
};
</script>
</body>
</html>
