<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" name="c_no" value="0">
<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	분류 레벨
	</label>
	<div class="col-md-6 col-sm-6">
		<select name="menu_level" class="form-control" required>
			<option value="0">눌러서 선택하세요</option>
			<option value="1">대분류</option>
			<option value="2">중분류</option>
			<option value="3">소분류</option>
		</select>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	상위 분류
	</label>
	<div class="col-md-6 col-sm-6">
		<select name="parent_no" class="form-control" disabled="disabled">
		<option value=""></option>
		<option value="-2" style="display: none">상위 분류가 존재하지 않습니다.</option>
		<option value="-1" style="display: none">선택하실수 없습니다.</option>
		<option value="0" style="display: none">눌러서 선택하세요</option>
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
		사용 : <input type="radio" name="in_use" value="true" checked>
		미사용 : <input type="radio" name="in_use" value="false">
	</div>
</div>

