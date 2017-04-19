<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" name="c_no" value="0">
<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	분류 레벨
	</label>
	<div class="col-md-6 col-sm-6">
		<select name="menu_level" class="form-control category" required>
			<option value="0">눌러서 선택하세요</option>
			<option value="${big.code }">${big.korName }</option>
			<option value="${middle.code }">${middle.korName }</option>
			<option value="${small.code }">${small.korName }</option>
		</select>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	상위 분류
	</label>
	<div class="col-md-6 col-sm-6">
		<select name="super_no" class="form-control category" disabled>
			<option value="">----------------</option>
			<c:forEach items="${big.categories }" var="c">
				<option value="${c.key }" data-menu_level="${c.value.menuLevel.code }">
					${c.value.title }
				</option>
			</c:forEach>
			<c:forEach items="${middle.categories }" var="c">
				<option value="${c.key }" data-menu_level="${c.value.menuLevel.code }">
					${c.value.title }
				</option>
			</c:forEach>
		</select>
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