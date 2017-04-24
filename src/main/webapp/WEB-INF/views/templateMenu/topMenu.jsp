<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header">
	<div class="header_top">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 ">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li><a href="#"><i class="fa fa-phone"></i>010-8859-8235</a></li>
							<li><a href="#"><i class="fa fa-envelope"></i>jyc228@naver.com</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="header-middle"><!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="logo pull-left">
						<a href="${pageContext.request.contextPath }/front.yo">
							<font size="16" color="green">
							GOTPING MALL
							</font>
						</a>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
						<c:choose>
							<c:when test="${lm == null }">
								<li><a href="${pageContext.request.contextPath }/agreement.yo"><i class="fa fa-user fa-lg"></i>회원가입</a></li>
								<li><a href="${pageContext.request.contextPath }/login.yo"><i class="fa fa-lock fa-lg"></i>로그인</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#"><i class="fa fa-address-card-o fa-lg"></i>나의 정보</a></li>
								<li><a href="${pageContext.request.contextPath }/logout.yo"><i class="fa fa-unlock fa-lg"></i>로그아웃</a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="${pageContext.request.contextPath }/admin.yo"><i class="fa fa-user-o fa-lg"></i>관리자 페이지</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--header-bottom-->
	<div class="header-bottom">
		<div class="container">
			<div class="row">
				<div class="col-sm-9">
				<!-- 	<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar">??</span>
							<span class="icon-bar">??</span>
							<span class="icon-bar">??</span>
						</button>
					</div> -->
					<div class="mainmenu pull-left">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a href="${pageContext.request.contextPath }/front.yo">Home</a></li>
							<li class="dropdown"><a href="#">공사중<i class="fa fa-angle-down"></i></a>
                                   <ul role="menu" class="sub-menu">
										<li><a href="#">추가 예정</a></li>
										<li><a href="#">추가 예정</a></li>
										<li><a href="#">추가 예정</a></li>
                                   </ul>
                               </li> 
							<li class="dropdown"><a href="#">공사중<i class="fa fa-angle-down"></i></a>
                                   <ul role="menu" class="sub-menu">
										<li><a href="#">추가 예정</a></li>
										<li><a href="#">추가 예정</a></li>
                                   </ul>
                               </li> 
							<li><a href="#">추가 예정</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-3" id="search_goods">
					<div class="search_box pull-right">
						<input type="text" placeholder="상품 검색"/>
					</div>
				</div>
			</div>
			</div>
		</div>
</header>