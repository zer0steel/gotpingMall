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
			<option value="-1" style="display: none;">하위분류가 존재하지 않습니다.</option>
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
		<select name="parent_no" class="form-control category" disabled>
			<option value="">----------------</option>
			<option value="-2">상위 분류가 존재하지 않습니다.</option>
			<option value="-1">선택하실수 없습니다.</option>
			<option value="0">눌러서 선택하세요</option>
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

<script type="text/javascript">
$("select[name=menu_level]").change(function() {
	var menu_level = $(this).val();
	setSuperCategorySelectBox(menu_level);
});

function setSuperCategorySelectBox(menu_level) {
	var c = Category.setMenu_level(menu_level);
	var superCategoryBox = $("select[name=parent_no]");
	
	if( c.isSubMenu_level() ) 
		showSuperCategory(c);
	else if( c.isBigMenu_level() )
		superCategoryBox.val("-1").attr("selected", true).attr("disabled",true);
	else
		superCategoryBox.val("").attr("selected", true).attr("disabled",true);
}

function showSuperCategory(subCategory) {
	var superCategoryBox = $("select[name=parent_no]");
	var categoryCount = 0;
	superCategoryBox.children("option").each(function() {
		var menu_level = $(this).data("menu_level");
		if( subCategory.checkSuperMenu_level( menu_level ) ) {
			$(this).css("display","block");
			categoryCount++;
		}
		else
			$(this).css("display","none");
	});
 	if( categoryCount > 0)
 		superCategoryBox.attr("required","required").removeAttr("disabled").val("0");
	else
		superCategoryBox.attr("disabled",true).val("-2");
}
</script>