<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-title">
	<div class="title_left">
		<h3>재고 변동 내역</h3>
	</div>
</div>
<br />
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>검색</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li>
						<a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="x_panel">
		<div class="x_title">
			<h2>목록</h2>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<table id="dTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>분류</th>
						<th>상품</th>
						<th>변동량</th>
						<th>변동일</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="d" items="${list }">
					<tr>
						<td>${d.category.kor }</td>
						<td><a href="${pageContext.request.contextPath }/admin/deal/detail.yo?d_no=${d.d_no }">${d.d_name }</a></td>
						<td>${d.total_change_amount } 개</td>
						<td>${d.regdate }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>