<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.account-main input {
	width: 100% !important;
}
</style>
<div class="account">
	<div class="container">
		<div class="account-main">
		
			<div class="col-md-6 account-left">
				<h3>로그인</h3>
				<div class="account-bottom">
					<form method="post" action="#">
						<input type="text" name="id" placeholder="아이디" required/> 
						<input type="password" name="pwd" placeholder="비밀번호" required/>
						<button type="button" id="btn-login" class="btn btn-lg btn-primary btn-block">로그인</button>
					</form>
				</div>
			</div>
			
			<div class="col-md-6 account-right account-left">
				<h3>비회원 주문확인</h3>
				<div class="account-bottom">
					<form method="post" action="#">
						<input type="text" name="email" placeholder="이메일" required value="test@test.yo"/> 
						<input type="text" name="order_uid" placeholder="주문번호" required value="1496573786132"/>
						<button type="button" id="btn-orderConfirm" class="btn btn-lg btn-primary btn-block">주문확인하기</button>
					</form>
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
(function() {
	$('#btn-orderConfirm').click(function() {
		checkOrder($('input[name=email]').val(), $('input[name=order_uid]').val());
	})
	
	var checkOrder = function(email, order_uid) {
		if(!validationCheck(email, order_uid))
			return;
		requestCheckOrder(email,order_uid).done(function(result) {
			alert(result)
			if(result === true) {
				location.href = 'orderDetail.yo?order_uid=' + order_uid
			}
			else {
				alert('이메일 주소나 주문번호가 틀렸습니다.');
			}
		});
	}
	
	var validationCheck = function(email, order_uid) {
		if(!email)
			alert('이메일주소가 입력되지 않았습니다.');
		else if(!order_uid)
			alert('주문번호가 입력되지 않았습니다.');
		else
			return true;
		return false;
	}
	
	var requestCheckOrder = function(email, order_uid) {
		return $.ajax({
			url : 'checkOrder.yo',
			type : 'get',
			data : {email : email, order_uid : order_uid}
		});
	}
}())
</script>