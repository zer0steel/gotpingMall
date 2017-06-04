document.write('<script type="text/javascript" src="' + PATH + 'resources/js/rsa/rsaUtil.js"></script>');

var loginUtil = (function() {
	
	function loginUtil(setting) {
		addEvent(setting);
		return loginUtil;
	}
	
	var requestLogin = function(id,pwd) {
		return $.ajax({
			url : PATH + "login.yo",
			type : "post",
			data : {id:id, pwd:pwd}
		});
	}
	
	var validationCheck = function(id, pwd) {
		if(id.length === 0)
			alert("아이디를 입력해 주세요.");
		else if(pwd.length === 0)
			alert("비밀번호를 입력해 주세요.");
		else
			return true;
		return false;
	}
	
	var addEvent = function(setting) {
		let $idTag = setting.$id;
		let $pwdTag = setting.$pwd;
		let href = setting.href;
		
		setting.$loginBtn.click(function() {
			executeLoginProcess($idTag.val(), $pwdTag.val());
		});
		
		var executeLoginProcess = function(id, pwd) {
			if(!validationCheck(id, pwd))
				return;
			
			encryptValue(pwd).then(encryptedPwd => {
				requestLogin(id, encryptedPwd).done(result => {
					if(result == 'true')
						location.href = href;
					else
						alert("아이디나 비밀번호가 올바르지 않습니다.");
				}).fail(err => {
					console.log(err);
					alert('로그인도중 에러가 발생했습니다. \n잠시후 다시 시도해 주세요.');
				});
			});
		}
	}
	
	return loginUtil;
}())