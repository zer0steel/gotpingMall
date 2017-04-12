/**
 * 
 */
document.write('<script type="text/javascript" src="resources/js/rsa/lib/jsbn.js"></script>');
document.write('<script type="text/javascript" src="resources/js/rsa/lib/rsa.js"></script>');
document.write('<script type="text/javascript" src="resources/js/rsa/lib/prng4.js"></script>');
document.write('<script type="text/javascript" src="resources/js/rsa/lib/rng.js"></script>');
var myRsa = {
	getKey : function() {
		var myrsa = this;
		return new Promise(function(resolve, reject) {
			$.ajax({
				url : "rsakey.yo",
				dataType : "json"
			}).done(function(key) {
				myrsa.modulus = key.modulus;
				myrsa.exponent = key.exponent;
				resolve();
			}).fail(function(err) {
				reject(err);
			})
		})
	},
	encrypt : function(pwd) {
		var myrsa = this;
		return myRsa.getKey().then(function() {
			var rsa = new RSAKey();
			rsa.setPublic(myrsa.modulus, myrsa.exponent);
			return rsa.encrypt(pwd);
		});
	}
}