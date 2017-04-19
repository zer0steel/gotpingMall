<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.subMenu_level option{	display: none;	}
</style>
<form class="form-horizontal form-label-left">

	<div class="col-md-4 form-group has-feedback">
		<h2>대분류</h2>
		<span>
			<select name="big" size="6" class="form-control" data-menu_level="${big.code }">
			<c:forEach items="${big.categories }" var="c">
				<option value="${c.key }">${c.value.title }</option>
			</c:forEach>
			</select>
		</span>
	</div>
	
	<div class="col-md-4 form-group has-feedback">
		<h2>중분류</h2>
		<span>
			<select name="middle" size="6" class="form-control subMenu_level" data-menu_level="${middle.code }" disabled>
			<c:forEach items="${middle.categories }" var="c">
				<option value="${c.key }" data-parent_no="${c.value.super_no }">${c.value.title }</option>
			</c:forEach>
			</select>
		</span>
	</div>
	
	<div class="col-md-4 form-group has-feedback">
		<h2>소분류</h2>
		<span>
			<select name="small" size="6" class="form-control subMenu_level" data-menu_level="${small.code }" disabled>
			<c:forEach items="${small.categories }" var="c">
				<option value="${c.key }" data-parent_no="${c.value.super_no }">${c.value.title }</option>
			</c:forEach>
			</select>
		</span>
	</div>
	
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/goods/categorySelectBox.js"></script>