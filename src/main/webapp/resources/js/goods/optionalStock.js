var goods = goods || {};
goods.selectOption = (function() {
	'use strict';
	var $div, optionalStock,
		userSelect = [];
	
	function constructor( setting ) {
		requestOptionalStocks(setting.g_no)
		.done(stocks => {
			optionalStock = stocks;
		})
		var $select = setting.$select;
		setDefaultOption( $select );
		addEvent( $select );
	}
	
	var requestOptionalStocks = function(g_no) {
		return $.ajax({
			url : 'option/stock.yo',
			data : {g_no: g_no},
			dataType : 'json'
		});
	}
	
	var setDefaultOption = function($select) {
		$select.each(function() {
			$(this).prepend($('<option />').html('(필수) 선택하세요.').attr('selected', true));
		});
/*		optionalStock.then( stocks => {
			for(var s of stocks) {
				if(s.os_stock === 0) {
					console.log('재고 없음');
					console.log(s)
				}
			}
		});*/
	}
	
	var addEvent = function($select) {
		$select.each(function(i) {
			$(this).change(function() {
				userSelect[i] = $(this).children('option:selected').text();
				createSelectedList();
			});
		});
	}
	
	var createSelectedList = function() {
		console.log( optionalStock );
		var selectedOption = userSelect.join(' ');
	/*	optionalStock.then(stocks => {
			for(var s of stocks) {
				if(s.combination === selectedOption) {
					
				}
			}
		});*/
		
	}
	
	return constructor;
}());