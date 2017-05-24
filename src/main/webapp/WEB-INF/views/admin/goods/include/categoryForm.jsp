<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" name="c_no" value="0">
<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	분류 레벨
	</label>
	<div class="col-md-6 col-sm-6">
		<select name="menu_level" class="form-control category" required>
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
		<input type="text" name="super_no" readonly>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	분류명
	</label>
	<div class="col-md-6 col-sm-6">
		<input type="text" name="title" class="form-control" value="${g.title }" required>
	</div>
</div>