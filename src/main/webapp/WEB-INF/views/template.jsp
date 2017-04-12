<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/template.css">
<script
  src="https://code.jquery.com/jquery-3.2.1.min.js"
  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
  crossorigin="anonymous"></script>
</head>
<body>
<div class="mainArea">
	<nav>
		<a href="front.yo">메인</a>
		<c:choose>
			<c:when test="${lm == null }">
				<a href="login.yo">로그인</a>
				<a href="agreement.yo">회원가입</a>
			</c:when>
			<c:otherwise>
				<a href="logout.yo">로그아웃</a>
				<a href="#">나의 정보</a>
			</c:otherwise>
		</c:choose>
		<a href="admin.yo">관리자 페이지</a>
	</nav>
	<jsp:include page="${viewPage }"></jsp:include>
</div>
</body>
</html>