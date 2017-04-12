<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="resources/js/rsa/myRsa.js"></script>
<script type="text/javascript">
window.onload = function() {
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
					if(result == "true") {
						$("#p-msg").html("테스트")
					}
					else {
						$("#p-msg").html("아이디나 비밀번호가 올바르지 않습니다.")
					}
				}).fail(function(err) {
					$("#p-msg").html("잠시후 다시 시도해 주세요.");
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
}
</script>
<h2>로그인</h2>
<input type="text" placeholder="아이디" name="id" id="input-id"><br>
<input type="password" placeholder="비밀번호" name="pwd" id="input-pwd">
<p id="p-msg"></p>
<button id="btn-login">로그인</button>
