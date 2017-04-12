<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="resources/js/member/addrFinder.js"></script>

<script type="text/javascript" src="resources/js/member/validation_checker.js"></script>
<script type="text/javascript" src="resources/js/rsa/myRsa.js"></script>
<script type="text/javascript" src="resources/js/member/memberForm.js"></script>
<h2>회원가입</h2>
<form action="join.yo" method="post" id="form-join">
<input type="hidden" id="hidden-pwd" name="pwd">
<input type="hidden" id="hidden-addr" name="addr">
	<table>
	<tr>
		<td>
			<input type="text" placeholder="아이디" id="input-id" name="id" >
			<p id="p-id"></p>
		</td>
	</tr>
	<tr>
		<td>
			<input type="password" placeholder="비밀번호" id="input-pwd" >
			<p id="p-pwd"></p>
		</td>
	</tr>
	<tr>
		<td>
			<input type="password" placeholder="비밀번호 확인" id="input-pwdCheck" disabled="disabled">
			<p id="p-pwdCheck"></p>
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" placeholder="이름" id="input-name" name="name">
			<p id="p-name"></p>
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" placeholder="이메일" id="input-email" name="email">
			<p id="p-email"></p>
		</td>
	</tr>
	<tr>
		<td>
			<input type="text" placeholder="우편번호" id="input-postcode" readonly="readonly">
			<button id="btn-addrFinder" type="button">우편번호 찾기</button>
			<br>
			<input type="text" placeholder="주소" id="input-addr" readonly="readonly">
			<br>
			<input type="text" placeholder="상세 주소" id="input-extraAddr">
		</td>
	</tr>
	<tr>
		<td>
			<button id="btn-join" type="button">회원가입</button>
		</td>
	</tr>
	</table>
</form>