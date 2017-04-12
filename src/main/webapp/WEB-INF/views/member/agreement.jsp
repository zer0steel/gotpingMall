<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>약관 동의</h2>
<table width="100%">
<tr>
	<td>
		<div style="width: 100%">물건 사도 물건이 안갈수도 있음</div><br>
		<label for="cBox-terms1">
		<input type="checkbox" id="cBox-terms1">GOTPINGMALL 서비스 이용약관 동의(필수)
		</label>
	</td>
</tr>
<tr>
	<td>
		<div style="width: 100%">너의 정보는 나의 것이다.</div><br>
		<label for="cBox-terms2">
		<input type="checkbox" id="cBox-terms2">개인정보 수집 및 이용 동의(필수)
		</label>
	</td>
</tr>
</table>
<button id="btn-agree">동의</button>
<button id="btn-cancle">취소</button>
<script type="text/javascript">
$("#btn-agree").click(function() {
	var term1_agree = $("#cBox-terms1").is(":checked");
	if(term1_agree ==  true && (term1_agree == $("#cBox-terms2").is(":checked"))) {
		location.href = "join.yo";
	}
});
</script>