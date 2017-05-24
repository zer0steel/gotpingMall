<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header">
	<div class="top-header">
		<div class="container">
			<div class="top-header-main">
				<div class="col-md-6 top-header-left">
					<div class="drop">
						<div class="box">
						</div>
						<div class="box1">
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-6 top-header-left">
					<div class="cart box_1">
						<c:choose>
							<c:when test="${lm == null }">
								<a href="${pageContext.request.contextPath }/agreement.yo" class="label">회원가입</a>
								<a href="${pageContext.request.contextPath }/login.yo" class="label">로그인</a>
								<a href="${pageContext.request.contextPath }/loginTest.yo?" class="label" id="test">테스트 로그인</a>
							</c:when>
							<c:otherwise>
								<input type="hidden" id="id" value="${lm.id }">
								<a href="#" class="label">나의 정보</a>
								<a href="${pageContext.request.contextPath }/logout.yo" class="label">로그아웃</a>
							</c:otherwise>
						</c:choose>
						<a href="${pageContext.request.contextPath }/admin.yo" class="label">관리자 페이지</a>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--start-logo-->
	<div class="logo">
		<a href="${pageContext.request.contextPath }/front.yo"><h1>GOT-PING MALL</h1></a>
	</div>
	<!--start-logo-->
	<!--bottom-header-->
	<div class="header-bottom">
		<div class="container">
			<div class="header">
				<div class="col-md-9 header-left">
				<div class="top-nav">
					<ul class="memenu skyblue">
						<li class="active">
							<a href="${pageContext.request.contextPath }/front.yo">Home</a>
						</li>
						<c:forEach var="big" items="${categories }">
						<li class="grid">
							<a href="${pageContext.request.contextPath }/goods/list.yo?c_no=${big.key }">${big.value.title }</a>
							<c:if test="${not empty big.value.sub }">
							<div class="mepanel">
								<div class="row">
									<div class="col1 me-one">
										<!-- <h4>Shop</h4> -->
										<ul>
										<c:forEach var="middle" items="${big.value.sub }">
											<li><a href="${pageContext.request.contextPath }/goods/list.yo?c_no=${middle.c_no }">${middle.title }</a></li>
										</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							</c:if>
						</li>
						</c:forEach>
						<li class="grid"><a href="typo.html">Blog</a></li>
						<li class="grid"><a href="contact.html">Contact</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="col-md-3 header-right"> 
				<div class="search-bar">
					<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
					<input type="submit" value="">
				</div>
			</div>
			<div class="clearfix"></div>
			</div>
		</div>
	</div>
</header>
<script type="application/javascript">
var isLogin = function() {
	return $('#id').val() ? true : false;
}

var getContextPath = function() {
	return $('#pageContextPath').val();
}
</script>