<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
input[name=id], input[name=pwd] {
	width: 100% !important;
}
</style>
<div class="account">
	<div class="container">
	
		<div class="account-top heading">
			<h2>로그인</h2>
		</div>

		<div class="account-main">
			<div class="col-md-6 col-md-offset-3 account-left">
				<div class="account-bottom">
					<form method="post" action="#">
						<input type="text" name="id" placeholder="아이디" required/> 
						<input type="password" name="pwd" placeholder="비밀번호" required/>
						<button type="button" id="btn-login" class="btn btn-primary btn-lg btn-block">로그인</button>
						<c:if test="${empty lm && 'form.yo' eq uri }">
							<a href="${pageContext.request.contextPath }/order/form.yo?noMember=1" class="btn btn-info btn-lg btn-block">비회원 구매하기</a>
						</c:if>
					</form>
				</div>
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<hr />
						<a href="#">아이디 찾기</a> | 
						<a href="#">비밀번호 찾기</a> | 
						<a href="${pageContext.request.contextPath }/agreement.yo">회원가입</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/login.js"></script>
<script type="text/javascript">
loginUtil({
	$id : $("input[name=id]"),
	$pwd : $("input[name=pwd]"),
	$loginBtn : $("#btn-login"),
	href : '${uri }'
});
</script>