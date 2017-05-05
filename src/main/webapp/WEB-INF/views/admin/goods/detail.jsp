<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-title">
	<div class="title_left">
		<h3>상품 수정</h3>
	</div>
</div>
<div class="clearfix"></div>
<br />

<div class="row">
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>기본 정보</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link" id="detail-collapse"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form id="goods-form" method="post" action="update.yo" 
					data-parsley-validate class="form-horizontal form-label-left">
					<jsp:include page="include/goodsForm.jsp"></jsp:include>
					
					<input type="hidden" name="g_no" value=${g.g_no }>
					<input type="hidden" id="hidden-status_code" value=${g.status_code }>
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
							<button type="submit" class="btn btn-danger" id="btn-delete" formaction="delete.yo">삭제</button>
							<a class="btn btn-info" href="list.yo">목록으로</a>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
	
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>상품 이미지</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link" id="detail-collapse"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<td>이미지</td>
							<td>파일명</td>
							<td>표시 위치</td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="img" items="${g.images }">
						<tr>
							<td width="20%">
								<img alt="" width="80%" height="auto"
								src="${pageContext.request.contextPath }/${img.save_path}/${img.save_name}">
							</td>
							<td>${img.real_name }</td>
							<td>${img.location }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-6">
		<jsp:include page="include/shippingReceivingForm.jsp"></jsp:include>
	</div>
	
	<div class="col-md-6">
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
	<c:if test="${!empty g.goodsOptions }">
	<div class="col-md-6">
		<div class="x_panel">
			<div class="x_title">
				<h2>재고 상세 정보</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link" id="detail-collapse"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<td>조합</td>
							<td>재고량</td>
						</tr>
					</thead>
					<tbody>					
					<c:forEach var="os" items="${g.optionStocks }">
						<tr>
							<td>${os.combination }</td>
							<td>${os.os_stock }</td>
						</tr>
					</c:forEach>
						<tr>
							<td>총합</td>
							<td>${g.stock }</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</c:if>
</div>
<script src="${pageContext.request.contextPath }/resources/js/goods/goodsOption.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/goods/categorySelectBox.js"></script>
<script type="text/javascript">
(function() {
	var option = goods.option();
	
	var setDiabled = (function() {
		$("#goods-form input").attr("readonly", true);
		$("#goods-form select").attr("disabled", true);
		$("#goods-form textarea").attr("disabled", true);
	}());
	var setEnable = function() {
		$("#goods-form input").removeAttr("readonly");
		$("#goods-form select").removeAttr("disabled");
		$("#goods-form textarea").removeAttr("disabled");
		$(this).html("수정 하기");
	}
	
	$("select[name=status_code]").val( $("#hidden-status_code").val() );
	
	var updateMode = false;
	$("#btn-update").click(function() {
		if( updateMode ) {
			console.log( $("#goods-form").serializeArray() );
			$("#goods-form").submit();
		}
		else {
			setEnable();
			updateMode = true;
		}
	});
})();
</script>