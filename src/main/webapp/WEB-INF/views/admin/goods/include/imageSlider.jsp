<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath }/resources/vendors/unslider/unslider.css" rel="stylesheet">
<ul id="goodsImg" class="rslides">
	<c:forEach var="i" items="${g.images }">
	<li>
		<img src="${pageContext.request.contextPath }/${i.save_path}/${i.save_name}" width="400px" height="auto"/>
	</li>
	</c:forEach>
</ul>
<script src="${pageContext.request.contextPath }/resources/vendors/unslider/unslider.js"></script>
<script>
$('#goodsImg').responsiveSlides({
	pager : true,
	pause : true
});
</script>