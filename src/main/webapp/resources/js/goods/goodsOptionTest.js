var goods = goods || {};
goods.option = (function() {
	'use strict';
	var $table, goodsOptions;
	// goodsOption parameter : o_no, c_no, o_name
	/*
	 * 생성자함수 
	 * @param $table : jquery 테이블
	 */
	function option( $table ) {
		createDOM( $table );
		addEvent( $table );
		return option;
	};
	
	/*
	 * 테이블 헤더와 바디를 만든다.
	 */
	function createDOM( $emptyTable ) {
		var $tr = $('<tr />');
		$('<td />').css('width','15%').html('옵션 이름').appendTo( $tr );
		$('<td />').css('width','35%').html('항목').appendTo( $tr );
		$('<td />').css('width','*').html('추가 가격').appendTo( $tr );
		$('<td />').css('width','10%').html('기능').appendTo( $tr );
		$('<thead />').append( $tr ).appendTo( $emptyTable );
		$table = $emptyTable;
	}
	
	function addEvent( $table ) {
		$table.on('click', 'a', function() {
			createOptionValueTr( $(this).parents('tbody') );
		});
		$table.on('click', '.btn-delete', function() {
			deleteOptionValueTr( $(this) );
		});
	}
	
	/*
	 * 옵션 데이터 리스트를 받는다.
	 */
	option.setOptionData = function( options ) {
		$table.find('tbody').empty();
		goodsOptions = options;
		createOptionTr();
	};
	
	function createOption( $tr ) {
		return {
			o_no : $tr.find('input[name=o_no]').val(),
			extra_costs : [],
			values : []
		}
	};
	
	function optionCheck() {
		if(goodsOptions.length == 0) {
			return false;
		}
		return true;
	};
	
	function createOptionTr() {
		if( !optionCheck() )
			return;
		$(goodsOptions).each(function(i) {
			let $tbody = $('<tbody />').addClass('option').data({'index': i, 'optCount': 0}).appendTo( $table );
			let $tr = $('<tr />').appendTo( $tbody );
			$('<td />').text(this.o_name).append( 
				$('<input />').attr({'type':'hidden', 'name':'list['+ i +'].o_no'}).val(this.o_no),
				$('<input />').attr({'type':'hidden', 'name':'list['+ i +'].o_name'}).val(this.o_name)
			).appendTo( $tr );
			$('<td />').append( $('<a />').addClass('btn btn-info btn-sm').text('항목 추가') ).appendTo( $tr );
			$('<td />').appendTo( $tr );
			$('<td />').appendTo( $tr );
		});
	};
	
	function createOptionValueTr( $tbody ) {
		let index = $tbody.data('index');
		let optCount = $tbody.data('optCount');
		let $tr = $('<tr />').addClass('value');
		$('<td />').appendTo( $tr );
		$('<td />').append($('<input />').attr({'type':'text', 'name':'list['+ index +'].details['+ optCount +'].value'})).appendTo( $tr );
		$('<td />').append($('<input />').attr({'type':'number', 'name':'list['+ index +'].details['+ optCount +'].extra_cost'}).val(0)).appendTo( $tr );
		$('<td />').append( $('<button />').addClass('btn btn-danger btn-warning btn-delete').html('삭제') ).appendTo( $tr );
		$tbody.append( $tr );
		
		$tbody.data('optCount', ++optCount);
	};
	
	function deleteOptionValueTr( $btn ) {
		$btn.parents('tr').remove();
	};
	
	return option;
}());