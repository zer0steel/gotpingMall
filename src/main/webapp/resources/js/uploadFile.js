/**
 * 
 */
var uploadFile = function(setting) {
	'use strict';
	var $dom;
	
	setting = (function() {
		var defaultSetting = createDefaultSetting();
		if( !setting )
			throw new Error('파라메터가 존재하지 않습니다.');
		
		if(typeof setting === 'string') {
			defaultSetting.url = setting;
			return defaultSetting;
		}
		for(var key in setting) {
			if( !defaultSetting.hasOwnProperty(key) )
				throw new Error("존재하지 않는 키값 : " + key)
			defaultSetting[key] = setting[key];
		}
		return defaultSetting;
	}());
	
	function createDefaultSetting() {
		return {
			url : '',
			minCount : 0,
			maxCount : 5,
			multiple : true,
			btnText : '업로드',
			text : '',
			ext : []
		}
	};
	
	function uploadFile( $div ) {
		(function createDOM() {
			$dom = {
				head : $('<div />').addClass('row'),
				body : $('<div />').addClass('row'),
				btn : $('<button />').addClass('btn btn-info btn-sm').text(setting.btnText),
				file : $('<input />').attr({'type' : 'file', 'multiple' : setting.multiple}).css('display', 'none'),
				p : $('<p />').html(setting.text)
			}
			$('<div />').addClass('col-md-10').append( $dom.p ).appendTo( $dom.head );
			$('<div />').addClass('col-md-2').append( $dom.btn, $dom.file ).appendTo( $dom.head );
			$div.append( $dom.head, $dom.body );
		}());
		
		(function addEvent() {
			$dom.btn.click(function() {
				$dom.file.click();
			})
			
			$dom.file.change(function() {
				upload( $(this)[0].files );
			})
		}());
		
		
		return uploadFile;
	};
	
	function upload( files ) {
		if( files.length === 0)
			return;
		
		var formData = new FormData();
		$(files).each(function() {
			formData.append('files',this);
		});
		
		requestUploadFile( formData ).done(function(JSONfiles) {
			createPreview(files);
		});
	};
	
	function createPreview(files) {
		$(files).each(function() {
			var reader = new FileReader();
			reader.onload = function(e) {
				var $img = $('<img />')
				.attr({'alt':'이미지 로딩 실패', 'src':e.target.result, 'width':'100%', 'height':'100%'});
				var $btn = $('<button />').at
				$('<div />').addClass('col-md-2').append( $img ).appendTo( $dom.body );
			}
			reader.readAsDataURL(this);
		});
	}
	
	function requestUploadFile(fileData) {
		return $.ajax({
			url : '/controller/' + setting.url,
			data : fileData,
			type : 'post',
			cache : false,
			processData: false,
			contentType: false
		})
	}
	
	return uploadFile;
}