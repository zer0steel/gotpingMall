<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>GOTPINGMALL!</title>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<!-- Bootstrap -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/resources/template/admin/css/custom.min.css" rel="stylesheet">

</head>
<body class="nav-md">

<div class="container body">
	<div class="main_container">
		<!-- 사이드 메뉴 -->
		<jsp:include page="templateMenu/sideMenu.jsp"></jsp:include>
		<!-- 상단 메뉴 -->
		<jsp:include page="templateMenu/topMenu.jsp"></jsp:include>
		<!-- page content -->
		<div class="right_col" role="main">
			<div class="">
				<jsp:include page="${viewPage }"></jsp:include>
			</div>
		</div>
		<!-- /page content -->
		
		<footer>
			<div class="pull-right">
				모든지 판매하는 만능 온라인 쇼핑몰 <a href="${pageContext.request.contextPath}/front.yo">G-ping Mall</a>
			</div>
			<div class="clearfix"></div>
		</footer>
	</div>
</div>
<!-- Bootstrap -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- NProgress -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.min.js"></script>
<!-- Custom Theme Scripts -->
<script src="${pageContext.request.contextPath}/resources/template/admin/js/custom.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/commonUtil.js?ver=2"></script>
</body>
</html>
