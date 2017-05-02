var goods = goods || {};
goods.option = function() {
	'use strict';
	var $tbody, goodsOptions;
	
	/*
	 * 생성자함수 
	 * @param $table : jquery 테이블
	 */
	function option( $table ) {
		createDOM( $table );
		$tbody.on('click', 'a', function() {
			createOptionValueTr( $(this) );
		});
		$tbody.on('click', '.btn-delete', function() {
			deleteOptionValueTr( $(this) );
		});
	};
	
	/*
	 * 테이블 헤더와 바디를 만든다.
	 */
	function createDOM( $table ) {
		var $tr = $('<tr />');
		$('<td />').css('width','15%').html('옵션 이름').appendTo( $tr );
		$('<td />').css('width','35%').html('항목').appendTo( $tr );
		$('<td />').css('width','*').html('추가 가격').appendTo( $tr );
		$('<td />').css('width','10%').html('필수').appendTo( $tr );
		$('<td />').css('width','10%').html('기능').appendTo( $tr );
		$('<thead />').append( $tr ).appendTo( $table );
		
		$tbody = $('<tbody />').appendTo( $table );
	}
	
	/*
	 * 옵션 데이터 리스트를 받는다.
	 */
	option.setOptionData = function( options ) {
		$tbody.empty();
		goodsOptions = options;
		createOptionTr();
	};
	
	/*
	 * 입력되어 있는 옵션들을 json객체리스트로 반환한다.
	 */
	option.getOptionList = function() {
		var option;
		var options = [];
		
		$tbody.find('tr').each(function(idx) {
			var $tr = $(this);
			
			if($tr.attr('class') == 'option') 
				option = createOption( $tr );
			else {
				option.values.push( $tr.find('input[name=value]').val() );
				option.extra_costs.push( $tr.find('input[name=extra_cost]').val() );
				
				var nextTr = $tr.next()[0];
				if($(nextTr).attr('class') != 'value')
					options.push( option );
			}
		});
		
		return options;
	};
	
	option.setTestButton = function() {
		$tbody.parent().find('thead td').each(function() {
			if( $(this).text() == '기능' ) {
				var $button = $('<button />').text('테스트').click(function() {
					console.log( option.getOptionList() );
				});
				$(this).html( $button );
			}
		});
	};
	
	function createOption( $tr ) {
		return {
			o_no : $tr.find('input[name=o_no]').val(),
			required : $tr.find('input[type=checkbox]').is(':checked'),
			extra_costs : [],
			values : []
		}
	};
	
	function optionCheck() {
		var optCount = $tbody.find('tr.option').length;
		if(goodsOptions == null) {
			alert('분류를 지정하셔야 합니다.');
			return false;
		}
		if(goodsOptions.length == 0) {
			/*alert('옵션이 존재하지 않습니다.')*/
			return false;
		}
		if(goodsOptions.length <= optCount) {
			// 최대 옵션 종류의 개수보다 더 많은 옵션을 추가하려고 할때 실행된다.
			alert('더이상 추가할수 없습니다.')
			return false;
		}
		return true;
	};
	
	function createOptionTr() {
		if( !optionCheck() )
			return;
		$(goodsOptions).each(function() {
			var $tr = $('<tr />').attr('class', 'option').appendTo( $tbody );
			$('<td />').append( 
				$('<input />').attr({'type':'hidden', 'name':'o_no'}).val(this.o_no),
				$('<input />').attr({'type':'text', 'readonly':true}).addClass('form-control').val(this.o_name)
			).appendTo( $tr );
			$('<td />').append( $('<a />').addClass('btn btn-info btn-sm').text('항목 추가') ).appendTo( $tr );
			$('<td />').appendTo( $tr );
			$('<td />').append( $('<input />').attr({'type':'checkbox'})).appendTo( $tr );
			$('<td />').appendTo( $tr );
		});
	};
	
	function createOptionValueTr( $a ) {
		var $tr = $('<tr />').attr('class', 'value');
		$('<td />').appendTo( $tr );
		$('<td />').append($('<input />').attr({'type':'text', 'name':'value'})).appendTo( $tr );
		$('<td />').append($('<input />').attr({'type':'number', 'name':'extra_cost'}).val(0)).appendTo( $tr );
		$('<td />').appendTo( $tr );
		$('<td />').append( $('<button />').addClass('btn btn-danger btn-warning btn-delete').html('삭제') ).appendTo( $tr );
		$a.parents('tr').after( $tr );
	};
	
	function deleteOptionValueTr( $btn ) {
		$btn.parents('tr').remove();
	};
	
	return option;
};