/**
 * 
 */
var validation = {
	checkId : function(id) {
		var idReg = /^[\w\d]{4,12}$/;
		return idReg.test(id);
	},
	checkPwd : function(pwd) {
		var pwdReg = /^(?=.*\d)(?=.*[a-z])(?=.*[a-zA-Z]).{8,30}$/;
		return pwdReg.test(pwd);
	},
	checkEmail : function(email) {
		var emailReg = /([\w\.\-_]+)?\w+@[\w-_]+(\.\w+){1,}/;
		return emailReg.test(email);
	},
	checkName : function(name) {
		var nameReg = /[a-zA-Z가-힣]/;
		return nameReg.test(name);
	},
	getOverlapCount : function(column, value) {
		return $.ajax({
			url : "overlapCheck.yo",
			data : {column:column, value:value},
			cache : false,
			dataType : "text"
		})
	}
};
