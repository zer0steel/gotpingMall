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
					
					<input type="hidden" name="g_no" value="${g.g_no }">
					<input type="hidden" name="c_no" value="${g.c_no }">
					<input type="hidden" id="hidden-status_code" value=${g.status_code }>
					
					<input type="text" name="name" class="form-control input" data-label="상품명" value="${g.name }" required>
					<input type="number" name="purchase_price" class="form-control input" data-label="매입가격" value="${g.purchase_price }" required>
					<input type="number" name="sell_price"  class="form-control input" data-label="판매가격"value="${g.sell_price }" required>
					<input type="number" name="discount_rate"  class="form-control input" data-label="할인율 (%)" value="${g.discount_rate }" min="0" max="100" required>
					<input type="number" name="saving_mileage"  class="form-control input" data-label="마일리지 적립율 (%)" value="${g.saving_mileage }" min="0" max="100" required>
					<p class="form-control input" data-label="총 재고량">${g.stock }</p>
					
					<select name="status_code" class="form-control input" data-label="상품 상태" required>
					<c:forEach var="s" items="${status }">
						<option value="${s.code }">${s.kor }</option>
					</c:forEach>
					</select>
					
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
							<td>${sr.change_amount }</td>
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
					<c:forEach var="s" items="${g.stocks }">
						<tr>
							<td>${s.combination }</td>
							<td>${s.amount }</td>
						</tr>
					</c:forEach>
						<tr>
							<td>총합</td>
							<%-- <td>${g.stock }</td> --%>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</c:if>
</div>
<script type="text/javascript">
(function() {
	var setDiabled = (function() {
		$("#goods-form input").attr("readonly", true);
		$("#goods-form select").attr("disabled", true);
		$("#goods-form textarea").attr("disabled", true);
	}());
	var setEnable = function() {
		$("#goods-form input").removeAttr("readonly");
		$("#goods-form select").removeAttr("disabled");
		$("#goods-form textarea").removeAttr("disabled");
	}
	
	$("select[name=status_code]").val( $("#hidden-status_code").val() );
	
	var updateMode = false;
	$("#btn-update").click(function() {
		if( updateMode ) {
			$("#goods-form").submit();
		}
		else {
			setEnable();
			$(this).html("수정");
			updateMode = true;
		}
	});
})();
const CSS_CLASS = {
		label : "col-md-3 col-xs-12",
		div : "col-md-5 col-xs-12"
	}
</script>