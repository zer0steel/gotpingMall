<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#search_goods{display: none;}
</style>
<div class="container">
	<div class="row" id="pwd-container">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<section class="login-form">
				<form method="post" action="#" role="login">
					<h2>로그인</h2>
					<input type="text" name="id" id="input-id" placeholder="아이디" required class="form-control input-lg" /> 
					<input type="password" name="pwd" id="input-pwd" placeholder="비밀번호" required class="form-control input-lg"/>
					<button type="button" id="btn-login" class="btn btn-lg btn-primary btn-block">로그인</button>
					<br>
					<p>아직도 회원이 아니십니까? -> <a href="agreement.yo">지금 바로 회원가입 하기!</a></p>
					<p>비밀번호를 까먹으셨습니까?!! -> <a href="#">비밀번호 찾으러 가기!</a></p>
				</form>
			</section>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>

<script type="text/javascript" src="resources/js/rsa/myRsa.js"></script>
<script type="text/javascript">
$("#btn-login").click(function() {
	var id = $("#input-id").val();
	var pwd = $("#input-pwd").val();
	login(id, pwd);
});

function login(id, pwd) {
	if(lengthCheck(id, pwd)) {
		myRsa.encrypt(pwd).then(function(securedPwd) {
			$.ajax({
				url : "login.yo",
				type : "post",
				data : {id:id, pwd:securedPwd}
			}).done(function(result) {
				if(result == "true") 
					location.href = "front.yo";
				else 
					alert("아이디나 비밀번호가 올바르지 않습니다.");
			}).fail(function(err) {
				alert("잠시후 다시 시도해 주세요.");
			});
		})
	}
}

function lengthCheck(id, pwd) {
	if(id.length == 0) {
		$("#p-msg").html("아이디를 입력해 주세요.")
		return false;
	}
	if(pwd.length == 0) {
		$("#p-msg").html("비밀번호를 입력해 주세요.")
		return false;
	}
	$("#p-pwd").html("")
	return true;
}
</script>