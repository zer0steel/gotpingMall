(function(window){
	'use strict'
	document.write('<script type="text/javascript" src="' + PATH + 'resources/js/rsa/lib/jsbn.js"></script>');
	document.write('<script type="text/javascript" src="' + PATH + 'resources/js/rsa/lib/rsa.js"></script>');
	document.write('<script type="text/javascript" src="' + PATH + 'resources/js/rsa/lib/prng4.js"></script>');
	document.write('<script type="text/javascript" src="' + PATH + 'resources/js/rsa/lib/rng.js"></script>');
	
	var requestPublicKey = function() {
		return $.ajax({
			url : path() + "rsakey.yo",
			dataType : "json"
		});
	}
	
	var encrypt = function(value, publicKey) {
		var rsa = new RSAKey();
		rsa.setPublic(publicKey.modulus, publicKey.exponent);
		return rsa.encrypt(value);
	}
	
	window.encryptValue = function(value) {
		return new Promise((resolve, reject) => {
			requestPublicKey().done(publicKey => {
				return resolve(encrypt(value, publicKey));
			}).fail(err => {
				console.log(err);
				alert('필요한 정보를 생성하지 못했습니다. \n잠시후 다시 시도해 주세요.');
			});
		});
	}
}(window))