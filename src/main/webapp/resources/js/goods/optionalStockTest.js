var goods = goods || {};
goods.selectOption = (function() {
	'use strict';
	
	var $selectedOptionDiv;
	var userSelect = [];
	var stocks;
	const DEFAULT_OPTION = '';
	
	function selectOption(setting) {
		(function(g_no) {
			return $.ajax({
				url : 'option/stockTest.yo',
				data : {g_no: g_no},
				dataType : 'json'
			});
		}(setting.g_no)).done(info => {
			stocks = info.stocks;
			createSelectTag(setting.$root, info.options);
			addEvent(setting.$root.find('select'));
		})
	}
	
	var optionSearch = function( selectedOption ) {
		for(var s of stocks)
			if(s.combination === selectedOption)
				return s;
		return false;
	}
	
	var createSelectTag = function($root, options) {
		options.forEach( (o, idx) => {
			var $select = $('<select />').addClass('form-control select-option').attr('name','value')
			.append(	$('<option />').html('(필수) 선택하세요.').attr('selected', true).val(DEFAULT_OPTION).css('display','none')	);
			
			var len = o.values.length;
			for(var i = 0; i < len; i++) {
				var optText = o.values[i];
				if(Number(o.extra_costs[i]) !== 0)
					optText += "  (추가금액 : " + o.extra_costs[i] + " 원)"; 
				var $option = $('<option />').val(o.values[i]).html(optText).appendTo( $select );
				if( idx >= 1)
					$option.css('display','none');
			}
			
			$('<div />').addClass('form-group').append(
				$('<label />').addClass('control-label col-md-2').attr('for','value').html(o.o_name),
				$('<div />').addClass('col-md-9').append( $select )
			).appendTo( $root );
		})
		
		$selectedOptionDiv = $('<div />').attr('id','selectedOptions').appendTo( $root );
	}
	
	var addEvent = function($selectTags) {
		$selectTags.each(function(i) {
			$(this).change(function() {
				userSelect[i] = $(this).children('option:selected').val();
				
				createSelectedList();
				
				displayNoneOptionTag($selectTags, i);
				displayBlockOptionTag( $selectTags[i + 1] );
				displaySoldoutOptionTag($selectTags, i);
			});
		});
		
		var displayNoneOptionTag = function($selectTags, super_idx) {
			for(var i = super_idx + 1; i < $selectTags.length; i++) {
				var $select = $($selectTags[i]);
				$select.val(DEFAULT_OPTION);
				$select.children('option').css('display','none');
			}
		}
		
		var displayBlockOptionTag = function(selectTag) {
			if(selectTag == undefined)
				return;
			
			$(selectTag).children('option').css('display','block');
			$(selectTag).children('option:first').css('display','none');
		}
		
		var displaySoldoutOptionTag = function($selectTags, idx) {
			var lastIdx = $selectTags.length - 1;
			if(idx + 1 != lastIdx)
				return;
			
			var tempUserSelect = userSelect;
			var $options = $($selectTags[lastIdx]).children('option');
			for(var i = 1; i < $options.length; i++) {
				tempUserSelect[lastIdx] = $options[i].value;
				var o = optionSearch( tempUserSelect.join(' ') );
				if(o.os_stock === 0) {
					$options[i].innerHTML = $options[i].innerHTML + ' (품절)';
					$options[i].setAttribute('disabled', true);
				}
				else {
					$options[i].innerHTML = $options[i].value;
					$options[i].removeAttribute('disabled');
				}
			}
		}
	}
	
	var createSelectedList = function() {
		var opt = optionSearch( userSelect.join(' ') );
		if( !opt )
			return;
		
		$('<div />').append(
			$('<p />').text(opt.combination),
			$('<input />').attr({'type':'number','name':'stock'})
		).appendTo( $selectedOptionDiv );
	}
	
	return selectOption;
}());