<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title> ---- GOTPING MALL ---- </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/resources/template/mall/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/resources/template/mall/css/memenu.css" rel="stylesheet">
    
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" 
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<%-- <script type="application/javascript" src="${pageContext.request.contextPath }/resources/template/mall/js/jquery-1.11.0.min.js"></script> --%>
	
	<script type="application/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="application/javascript" src="https://cdn.jsdelivr.net/jquery.easydropdown/2.1.4/jquery.easydropdown.min.js"></script>
	<script type="application/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/simplecartjs/3.0.5/simplecart.min.js"></script>
	<script type="application/javascript" src="${pageContext.request.contextPath }/resources/template/mall/js/memenu.js"></script>
	
	<script type="application/x-javascript">
	const PATH = location.protocol + "//" + location.host + "/controller/";
	
	addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
		}, false); 
	function hideURLbar(){
		window.scrollTo(0,1);
	} 
	$(document).ready(function() {
		$(".memenu").memenu();
	});
	</script>
</head>
<body>
<jsp:include page="templateMenu/topMenu.jsp"></jsp:include>
<c:if test="${viewPage.equals('front.jsp') }">
	<jsp:include page="templateMenu/slideAD.jsp"></jsp:include>
</c:if>
<section>
	<jsp:include page="${viewPage }"></jsp:include>
	<div class="clearfix"></div>
</section>
<jsp:include page="templateMenu/footer.jsp"></jsp:include>
</body>
</html>