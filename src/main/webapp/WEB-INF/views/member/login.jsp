<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row" id="pwd-container">
		<div class="col-md-4 col-md-offset-4">
			<form method="post" action="#">
				<h2>로그인</h2>
				<input type="text" name="id" id="input-id" placeholder="아이디" required class="form-control input-lg" /> 
				<input type="password" name="pwd" id="input-pwd" placeholder="비밀번호" required class="form-control input-lg"/>
				<button type="button" id="btn-login" class="btn btn-lg btn-primary btn-block">로그인</button>
				<c:if test="${empty lm && 'form.yo' eq uri }">
					<a href="${pageContext.request.contextPath }/order/form.yo" class="btn btn-lg btn-info btn-block">비회원 구매하기</a>
				</c:if>
				<br>
				<p>아직도 회원이 아니십니까? -> <a href="${pageContext.request.contextPath }/agreement.yo">지금 바로 회원가입 하기!</a></p>
				<p>비밀번호를 까먹으셨습니까?!! -> <a href="#">비밀번호 찾으러 가기!</a></p>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/member/login.js"></script>
<script type="text/javascript">
loginUtil({
	$id : $("#input-id"),
	$pwd : $("#input-pwd"),
	$loginBtn : $("#btn-login"),
	href : '${uri }'
});
</script>