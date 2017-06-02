<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#search_goods{display: none;}
</style>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h2>회원가입</h2>
			<form action="join.yo" method="post" id="form-join">
				<input type="hidden" id="hidden-pwd" name="pwd">
				<input type="hidden" id="hidden-addr" name="addr">
				<input type="text" placeholder="아이디" id="input-id" name="id" class="form-control">
				<p id="p-id"></p>
				<input type="password" placeholder="비밀번호" id="input-pwd" class="form-control">
				<p id="p-pwd"></p>
				<input type="password" placeholder="비밀번호 확인" id="input-pwdCheck" disabled class="form-control">
				<p id="p-pwdCheck"></p>
				<input type="text" placeholder="이름" id="input-name" name="name" class="form-control">
				<p id="p-name"></p>
				<input type="text" placeholder="이메일" id="input-email" name="email" class="form-control">
				<p id="p-email"></p>
				<div class="input-group">
					<input type="text" placeholder="우편번호" id="input-postcode" readonly class="form-control" name="address.postCode">
					<span class="input-group-btn">
						<button id="btn-addrFinder" type="button" class="btn btn-info">우편번호 찾기</button>
					</span>
				</div>
				<br>
				<input type="text" placeholder="주소" id="input-addr" readonly class="form-control" name="address.base">
				<br>
				<input type="text" placeholder="상세 주소" id="input-extraAddr" class="form-control" name="address.extra">
				<br>
				<button id="btn-join" type="button" class="btn btn-success">회원가입</button>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="resources/js/member/addrFinder.js"></script>

<script type="text/javascript" src="resources/js/member/validation_checker.js"></script>
<script type="text/javascript" src="resources/js/member/memberForm.js"></script>