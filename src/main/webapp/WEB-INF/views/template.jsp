<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title> ---- GOTPING MALL ---- </title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/prettyPhoto/3.1.6/css/prettyPhoto.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet">
    
    <link href="${pageContext.request.contextPath }/resources/css/template/price-range.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/resources/css/template/main.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/resources/css/template/responsive.css" rel="stylesheet">
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" 
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="templateMenu/topMenu.jsp"></jsp:include>
<c:if test="${viewPage.equals('front.jsp') }">
	<jsp:include page="templateMenu/slideAD.jsp"></jsp:include>
</c:if>
<section>
	<div class="container">
		<div class="row">
			<c:choose>
				<c:when test="${noSideFrame == true }">
					<div class="col-sm-9">
						<jsp:include page="${viewPage }"></jsp:include>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-sm-3">
						<jsp:include page="templateMenu/sideMenu.jsp"></jsp:include>
					</div>
					<div class="col-sm-9 padding-right">
						<jsp:include page="${viewPage }"></jsp:include>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</section>
<br><br><br>
<jsp:include page="templateMenu/footer.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath }/resources/js/template/price-range.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/template/jquery.scrollUp.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/js/template/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/template/jquery.prettyPhoto.js"></script>
    <script src="${pageContext.request.contextPath }/resources/js/template/main.js"></script>
</body>
</html>