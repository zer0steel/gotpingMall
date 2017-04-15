<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" name="c_no" value="0">
<div class="form-group">
	<label class="control-label col-md-3 col-sm-3">
	분류 레벨
	</label>
	<div class="col-md-6 col-sm-6">
		<select name="menu_level" class="form-control category" required>
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
		<select name="parent_no" class="form-control category" disabled="disabled">
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

<script type="text/javascript">
$("select[name=menu_level]").change(function() {
	setSuperCategorySelectBox();
});

function setSuperCategorySelectBox() {
	var menu_level = $("select[name=menu_level]").val();
	var c = Category.setMenu_level(menu_level);
	
	if( c.isSubMenu_level() ) 
		showSuperCategory($("select[name=parent_no]"), c);
	
	else 
		$("select[name=parent_no]").val("-1")
		.attr("selected", true).attr("disabled",true);
}

function showSuperCategory(selectNode, selectedCategory) {
	var categoryCount = 0;
	selectNode.children("option").each(function() {
		var superMenu_level = $(this).data("menu_level");
		if( selectedCategory.checkSuperMenu_level( superMenu_level ) ) {
			$(this).css("display","block");
			categoryCount++;
		}
		else
			$(this).css("display","none");
	});
	if( categoryCount > 0)
		$("select[name=parent_no]").attr("required","required").removeAttr("disabled").val("0");
	else
		$("select[name=parent_no]").attr("disabled",true).val("-2");
}
</script>