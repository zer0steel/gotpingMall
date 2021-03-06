<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
			<a href="${pageContext.request.contextPath}/front.yo" class="site_title">
				<i class="fa fa-paw"></i>
				<span>GOTPINGMALL!</span>
			</a>
		</div>
		<!-- menu profile quick info -->
		<div class="profile clearfix">
			<div class="profile_info">
				<span>환영합니다,</span>
				<h2>관리자님</h2>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- /menu profile quick info -->

		<br/>
		<div class="clearfix"></div>
		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<h3>General</h3>
				<ul class="nav side-menu">
					<li>
						<a href="#">
							<i class="fa fa-home"></i>서비스현황
							<span class="fa"></span>
						</a>
					</li>
					<li>
						<a>
							<i class="fa fa-edit"></i>상품 관리 
							<span class="fa fa-chevron-down"></span>
						</a>
						<ul class="nav child_menu">
							<li><a href="${pageContext.request.contextPath}/admin/goods/list.yo">상품 목록</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/goods/insert.yo">상품 등록</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/goods/category.yo">분류 / 옵션 관리</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/deal/list.yo">재고 변동 내역</a></li>
						</ul>
					</li>
					<li>
						<a>
							<i class="fa fa-table"></i>주문 관리 
							<span class="fa fa-chevron-down"></span>
						</a>
						<ul class="nav child_menu">
							<li><a href="${pageContext.request.contextPath}/admin/order/newList.yo">신규 주문</a></li>
							<li><a href="${pageContext.request.contextPath}/admin/order/list.yo">전체 주문 목록</a></li>
						</ul>
					</li>
					<li>
						<a>
							<i class="fa fa-desktop"></i>회원 관리 
							<span class="fa fa-chevron-down"></span>
						</a>
						<ul class="nav child_menu">
							<li><a href="${pageContext.request.contextPath}/admin/member/list.yo">회원 관리</a></li>
						</ul>
					</li>
					<li>
						<a>
							<i class="fa fa-bar-chart-o"></i>추가 예정 
							<span class="fa fa-chevron-down"></span>
						</a>
						<ul class="nav child_menu">
						</ul>
					</li>
					<li>
						<a>
							<i class="fa fa-clone"></i>추가 예정  
							<span class="fa fa-chevron-down"></span>
						</a>
						<ul class="nav child_menu">
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons -->
		<div class="sidebar-footer hidden-small">
			<a data-toggle="tooltip" data-placement="top" title="Settings">
				<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</a> 
			<a data-toggle="tooltip" data-placement="top" title="FullScreen">
				<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
			</a> 
			<a data-toggle="tooltip" data-placement="top" title="Lock"> <span
				class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
			</a> 
			<a data-toggle="tooltip" data-placement="top" title="Logout" href="logout.yo">
				<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
			</a>
		</div>
		<!-- /menu footer buttons -->
	</div>
</div>