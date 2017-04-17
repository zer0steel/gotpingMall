<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-6">
	<div class="row">
		<div class="x_panel">
			<div class="x_title">
				<h2>상품 상세 정보</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link" id="detail-collapse"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="goods-form" method="post" action="update.yo" data-parsley-validate class="form-horizontal form-label-left">
					<input type="hidden" name="g_no" value=${g.g_no }>
					
					<div class="form-group">
						<input type="hidden" value="${g.menuLevel.code }" id="hidden-menulevel_code">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
						분류 레벨
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<select name="menu_level" class="form-control" required>
								<option value="${big.code }">${big.korName }</option>
								<option value="${middle.code }">${middle.korName }</option>
								<option value="${small.code }">${small.korName }</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<input type="hidden" value="${g.c_no }" id="hidden-c_no">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
						분류명
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<select id="c_no" class="form-control" required>
							<option value="0">---------------</option>
							<c:forEach var="c" items="${big.categories }">
								<option value="${c.key }" data-menuLevel="${big.code }">${c.value.title }</option>
							</c:forEach>
							<c:forEach var="c" items="${middle.categories }">
								<option value="${c.key }" data-menuLevel="${middle.code }">${c.value.title }</option>
							</c:forEach>
							<c:forEach var="c" items="${small.categories }">
								<option value="${c.key }" data-menuLevel="${small.code }">${c.value.title }</option>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<jsp:include page="include/goodsForm.jsp"></jsp:include>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
						총 재고량
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<p class="form-control col-md-7 col-xs-12" id="stock">${g.stock }</p>
						</div>
					</div>
					
					<div class="form-group">
						<input type="hidden" value="${g.status_code }" id="hidden-status_code">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
						상품 상태
						</label>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<select name="status_code" class="form-control" required>
							<c:forEach var="s" items="${status }">
								<option value="${s.code }">${s.kor }</option>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
							<button type="button" class="btn btn-warning" id="btn-update">수정 활성화</button>
							<button type="submit" class="btn btn-danger" id="btn-delete" formaction="delete.yo">상품 삭제</button>
							<a class="btn btn-info" href="list.yo">상품 목록으로</a>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>
<div class="col-md-6">
	<div class="row">
		<div class="x_panel">
			<div class="x_title">
				<h2>최근 변동 상황</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link" id="detail-collapse"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table id="recentHistory" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>분류</th>
							<th>수량</th>
							<th>변동일</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="sr" items="${g.history }">
						<tr>
							<td>${sr.enumCategory.korName }</td>
							<td>${sr.amount }</td>
							<td>${sr.regdate }</td>
							<td>${sr.detail }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="col-md-6">
	<jsp:include page="include/shippingReceivingForm.jsp"></jsp:include>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/goods/category.js"></script>
<script type="text/javascript">
(function() {
	$("#goods-form input").attr("readonly", true);
	$("#goods-form select").attr("disabled", true);
	$("#goods-form textarea").attr("disabled", true);
	var statusCode = $("#hidden-status_code").val();
	var menulevel = $("#hidden-menulevel_code").val();
	var c_no = $("#hidden-c_no").val();
	$("select[name=menu_level]").val(menulevel);
	$("select[name=status_code]").val(statusCode);
	$("#c_no").val(c_no);
})();

$("select[name=menu_level]").change(function() {
/* 	var menuLevel = $(this).val();
	var count = 0;
	$("select[name=title]").children("option").each(function() {
		if($(this).data("menuLevel") == menuLevel) {
			$(this).css("display","block");
			count++;
		}
		else
			$(this).css("display","none");
	});
	if(count == 0)
		$("select[name=title]").val(0); */
});

var updateMode = false;
$("#btn-update").click(function() {
	if( updateMode ) {
		$("input[name=c_no]").val($("#c_no").val());
		$("#goods-form").submit();
	}
	else {
		$("#goods-form input[name=stock]").attr("readonly", "readonly");
		$("#goods-form input").removeAttr("readonly");
		$("#goods-form select").removeAttr("disabled");
		$("#goods-form textarea").removeAttr("disabled");
		$(this).html("수정 하기");
		updateMode = true;
	}
});

$("#btn-delete").submit(function() {
	if( !confirm("정말로 해당 상품을 삭제하시겠습니까?") )
		return false;
});
</script>