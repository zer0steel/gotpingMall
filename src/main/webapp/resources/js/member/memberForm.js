(function() {
	document.write('<script type="text/javascript" src="' + PATH + 'resources/js/rsa/rsaUtil.js"></script>');
	const MESSAGE = {
		OK : "사용 가능합니다.",
		ERROR : "잠시후 다시 시도해 주세요.",
		OVERLAP_ID : "이미 사용중인 아이디 입니다.",
		OVERLAP_EMAIL : "이미 사용중인 이메일 입니다.",
		VALIDATION_FAIL_ID : "4 ~ 12, 알파벳과 숫자를 사용하세요.",
		VALIDATION_FAIL_PWD : "8 ~ 30자, 영문자, 숫자를 사용하세요.",
		VALIDATION_FAIL_NAME : "이름을 다시 한번 확인해 주세요.",
		VALIDATION_FAIL_EMAIL : "이메일주소를 다시 한번 확인해 주세요.",
		CHECK_FAIL_PWD : "비밀번호가 일치하지 않습니다.",
		BLANK : "필수 입력 항목"
	};
	var checkId = false, checkPwd = false, checkName = false, checkEmail = false;
	
	$("#input-id").focusout(function() {
		var id = $(this).val();
		checkId = false;
		if(validation.checkId(id)) {
			
			validation.getOverlapCount("id",id).done(function(overlapCnt) {
				if(overlapCnt == 0) {
					$("#p-id").html(MESSAGE.OK);
					checkId = true;
				}
				else
					$("#p-id").html(MESSAGE.OVERLAP_ID);
			}).fail(function(err) {
				alert(MESSAGE.ERROR);
			});
			
		}
		else 
			$("#p-id").html(MESSAGE.VALIDATION_FAIL_ID);
	});
	
	$("#input-pwd").keyup(function() {
		var pwd = $(this).val();
		checkPwd = false;
		if(validation.checkPwd(pwd)) {
			$("#p-pwd").html(MESSAGE.OK);
			$("#input-pwdCheck").removeAttr("disabled","disabled");
		}
		else {
			$("#p-pwd").html(MESSAGE.VALIDATION_FAIL_PWD);
			$("#input-pwdCheck").attr("disabled","disabled");
		}
	});
	
	$("#input-pwdCheck").focusout(function() {
		var pwd = $(this).val();
		checkPwd = false;
		if($("#input-pwd").val() === $(this).val()) {
			$("#p-pwdCheck").html(MESSAGE.OK);
			checkPwd = true;
		}
		else
			$("#p-pwdCheck").html(MESSAGE.CHECK_FAIL_PWD);
	});
	
	$("#input-name").focusout(function() {
		var name = $(this).val();
		checkName = false;
		if(validation.checkName(name)) {
			$("#p-name").html(MESSAGE.OK);
			checkName = true;
		}
		else {
			$("#p-name").html(MESSAGE.VALIDATION_FAIL_NAME);
		}
	});
	
	$("#input-email").focusout(function() {
		var email = $(this).val();
		checkEmail = false;
		if(validation.checkEmail(email)) {
			validation.getOverlapCount("email",email).done(function(overlapCnt) {
				if(overlapCnt == 0) {
					$("#p-email").html(MESSAGE.OK);
					checkEmail = true;
				}
				else
					$("#p-email").html(MESSAGE.OVERLAP_EMAIL);
			})
		}
		else
			$("#p-email").html(MESSAGE.VALIDATION_FAIL_EMAIL);
	});
	
	$("#btn-join").click(function() {
		if(	inputValueCheck() ) {
			var addr = getFullAddr();
			$("#hidden-addr").val(addr);
			encryptValue($("#input-pwd").val()).then(encryptedPwd => {
				$("#hidden-pwd").val(securedPwd);
				$("#form-join").submit();
			})
		}
	});
	
	
	function inputValueCheck() {
		if(!checkId && $("#input-id").val().length == 0) {
			$("#p-id").html(MESSAGE.BLANK);
		}
		if(!checkPwd) {
			if($("#input-pwd").val().length == 0)
				$("#p-pwd").html(MESSAGE.BLANK);
			if($("#input-pwdCheck").val().length == 0)
				$("#p-pwdCheck").html(MESSAGE.BLANK);
		}
		if(!checkEmail && $("#input-email").val().length == 0) {
			$("#p-email").html(MESSAGE.BLANK);
		}
		if(!checkName && $("#input-name").val().length == 0) {
			$("#p-name").html(MESSAGE.BLANK);
		}
		
		if(!checkId || !checkPwd || !checkEmail || !checkName)
			return false;
		else
			return true;
	}
}())
