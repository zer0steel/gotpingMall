<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-title">
	<div class="title_left">
		<h3>회원 관리</h3>
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
			<table id="memberTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>아이디</th>
						<th>이메일</th>
						<th>등급</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="m" items="${list }">
					<tr>
						<td>${m.id }</td>
						<td>${m.email }</td>
						<td>${m.enumGrade }</td>
						<td>${m.join_date }</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>