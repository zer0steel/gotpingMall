<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			
<form class="form-horizontal form-label-left">

	<div class="col-md-3 form-group has-feedback">
		<h2>대분류</h2>
		<span>
			<select name="big" size="6" class="form-control" data-menu_level="0">
			<c:forEach items="${categories }" var="c">
			<c:if test="${c.menu_level == 0 }">
				<option value="${c.c_no }">${c.title }</option>
			</c:if>
			</c:forEach>
			</select>
		</span>
	</div>
	
	<div class="col-md-3 form-group has-feedback">
		<h2>중분류</h2>
		<span>
			<select name="middle" size="6" class="form-control subMenu_level" data-menu_level="1" disabled>
			<c:forEach items="${categories }" var="c">
			<c:if test="${c.menu_level == 1 }">
				<option value="${c.c_no }" data-parent_no="${c.parent_no }">${c.title }</option>
			</c:if>
			</c:forEach>
			</select>
		</span>
	</div>
	
	<div class="col-md-3 form-group has-feedback">
		<h2>소분류</h2>
		<span>
			<select name="small" size="6" class="form-control subMenu_level" data-menu_level="2" disabled>
			<c:forEach items="${categories }" var="c">
			<c:if test="${c.menu_level == 2 }">
				<option value="${c.c_no }" data-parent_no="${c.parent_no }">${c.title }</option>
			</c:if>
			</c:forEach>
			</select>
		</span>
	</div>
	
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/goods/category.js"></script>