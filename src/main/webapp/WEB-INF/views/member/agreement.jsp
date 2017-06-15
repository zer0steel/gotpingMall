<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#search_goods{display: none;}
</style>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h2>약관 동의</h2>
			<div class="form-control" style="overflow: scroll; height: 100px;">
				약관1
			</div>
			<label for="cBox-terms1">
				<input type="checkbox" id="cBox-terms1">GOTPINGMALL 서비스 이용약관 동의(필수)
			</label>
			<br />
			<br />
			<div class="form-control" style="overflow: scroll; height: 100px;">
				약관2<br />
				비밀번호는 암호화되어 안전하게 보관되니 관리자도 모릅니다.
			</div>
			<label for="cBox-terms2">
				<input type="checkbox" id="cBox-terms2">개인정보 수집 및 이용 동의(필수)
			</label>
			<br />
			<br />
			<button id="btn-agree" class="btn btn-info form-control">동의</button>
			<br />
			<br />
		</div>
		<div class="col-md-4"></div>
	</div>
</div>

<script type="text/javascript">
$("#btn-agree").click(function() {
	var term1_agree = $("#cBox-terms1").is(":checked");
	var term2_agree = $("#cBox-terms2").is(":checked");
	if(term1_agree ==  true && term2_agree == true) {
		location.href = "join.yo";
	}
	else {
		alert("약관에 동의하셔야 가입하실 수 있습니다.")
	}
});
</script>