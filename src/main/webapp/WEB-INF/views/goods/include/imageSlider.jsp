<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/ideal-image-slider/1.5.1/ideal-image-slider.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/ideal-image-slider/1.5.1/themes/default/default.min.css" rel="stylesheet">

<div id="goodsImg">
	<c:forEach var="i" items="${g.images }" varStatus="s">
	<c:choose>
	<c:when test="${s.first }">
		<img src="${pageContext.request.contextPath }/${i.save_path}/${i.save_name}" width="100%" height="auto"/>
	</c:when>
	<c:otherwise>
		<img data-src="${pageContext.request.contextPath }/${i.save_path}/${i.save_name}" width="100%" height="auto"/>
	</c:otherwise>
	</c:choose>
	</c:forEach>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/ideal-image-slider/1.5.1/ideal-image-slider.min.js"></script>
<script>
new IdealImageSlider.Slider('#goodsImg');
</script>