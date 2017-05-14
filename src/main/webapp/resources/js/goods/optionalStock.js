var goods = goods || {};
goods.selectOption = (function() {
	'use strict';
	
	var $table;
	var userSelect = [];
	var stocks, goodsPrice;
	var arraySize = 0;
	const DEFAULT_OPTION = '';
	const EXTRA_COST = 'extraCost';
	const OPTION_INFO = 'info';
	const SELECT_OPTION = '.select-option';
	
	function selectOption(setting) {
		goodsPrice = Number(setting.goodsPrice);
		
		(function(g_no) {
			return $.ajax({
				url : 'option/stock.yo',
				data : {g_no: g_no},
				dataType : 'json'
			});
		}(setting.g_no)).done(info => {
			stocks = info.stocks;
			createSelectTag(setting.$root, info.options);
			addEvent(setting.$root.find('select'));
		});
		
		return selectOption;
	}
	
	var option = function(value, extraCost) {
		return {
			value : value,
			extraCost : extraCost ? Number(extraCost) : 0
		};
	}
	
	var optionSearch = function( selectedCombination ) {
		let extraCost = 0;
		let selectedOption = selectedCombination.map(opt => {
			extraCost += opt.extraCost;
			return opt.value
		}).join(' ');
		
		for(var s of stocks)
			if(s.combination === selectedOption) {
				s.extraCost = extraCost;
				return s;
			}
		return false;
	}
	
	selectOption.getSelectedOptionCount = function() {
		return arraySize;
	}
	
	var createSelectTag = function($root, options) {
		options.forEach( (o, idx) => {
			let $select = $('<select />').addClass('form-control select-option')
			.append(	$('<option />').html('(필수) 선택하세요.').attr('selected', true).val(DEFAULT_OPTION).css('display','none')	);
			
			for(var d of o.details) {
				let optText = d.value;
				var $option = $('<option />').val(d.value);
				if(Number(d.extra_cost) !== 0) {
					optText += "  (추가금액 : " + d.extra_cost + " 원)"; 
					$option.data(EXTRA_COST, d.extra_cost);
				}
				$option.data('text', optText).html(optText).appendTo( $select );
				if( idx >= 1)
					$option.css('display','none');
			}
			
			$('<div />').addClass('form-group').append(
				$('<label />').addClass('control-label col-md-2').html(o.o_name),
				$('<div />').addClass('col-md-9').append( $select )
			).appendTo( $root );
		})
		
		$table = $('<table />').addClass('table').css('max-width', '90%');
		$('<div />').attr('id','selectedOptions').css({'max-height':'150px', 'overflow-y':'scroll'})
		.append( $table ).appendTo( $root );
	}
	
	var addEvent = function($selectTags) {
		$selectTags.each(function(i) {
			$(this).change(function() {
				setUserSelect($(this), $selectTags, i);
				createSelectedList($selectTags);
				
				displayNoneOptionTag($selectTags, i);
				displayBlockOptionTag( $selectTags[i + 1] );
				displaySoldoutOptionTag($selectTags, i);
			});
		});
		
		$table.on('change', 'input[type=number]', function() {
			let amount = $(this).val();
			modifyPrice(amount, $(this).parents(SELECT_OPTION));
		});
		
		$table.on('click', 'i.fa', function() {
			$(this).parents(SELECT_OPTION).empty();
			arraySize--;
		});
		
		var modifyPrice = function(amount, $parentTag) {
			if( !validationCheck(amount, $parentTag) )
				return;
			
			let $priceTag = $parentTag.find('.price');
			let price = $parentTag.data('price') * amount;
			$priceTag.setPriceInHtml(price);
			
			// 상품 수량 유효성 검사
			function validationCheck(amount, $parentTag) {
				let $inputAmount = $parentTag.find('input[type=number]');
				if( amount < 1) {
					alert('최소 주문 수량은 1개입니다.');
					$inputAmount.val(1);
					return false;
				}
				
				let opt = $parentTag.data(OPTION_INFO);
				if( amount > opt.os_stock) {
					alert('선택하신 상품 옵션의 수량이 부족합니다.');
					$inputAmount.val(opt.os_stock);
					return false;
				}
				
				return true;
			}
		}
		
		var setUserSelect = function($selectTag, $selectTags, i) {
			let $selectedOption = $selectTag.children('option:selected');
			userSelect[i] = option($selectedOption.val(), $selectedOption.data(EXTRA_COST));
			if(i + 1 < $selectTags.length)
				userSelect[i + 1] = DEFAULT_OPTION;
		};
		
		/*
		 * 이벤트가 발생한 tag보다 아래에 위치한 tag들의 option들을 display none으로 설정하고
		 * 기본값으로 선택해놓는다.
		 */
		var displayNoneOptionTag = function($selectTags, super_idx) {
			for(let i = super_idx + 1; i < $selectTags.length; i++) {
				let $select = $($selectTags[i]);
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
			let lastIdx = $selectTags.length - 1;
			if(idx + 1 != lastIdx)
				return;
			
			let tempUserSelect = userSelect;
			let options = $($selectTags[lastIdx]).children('option');
			for(var i = 1; i < options.length; i++) {
				tempUserSelect[lastIdx] = option(options[i].value);
				var o = optionSearch( tempUserSelect );
				let $opt = $(options[i]);
				if(o.os_stock === 0) {
					$opt.text($opt.val() + ' (품절)');
					$opt.attr('disabled', true)
				}
				else {
					$opt.text($opt.data('text'));
					$opt.removeAttr('disabled')
				}
				
				if(o === false)
					throw new Error('옵션조합 검색 결과가 존재하지 않음.');
			}
		}
		
		addEvent.resetSelectTag = function($selectTags) {
			$selectTags.each(function(i) {
				$(this).val(DEFAULT_OPTION);
				if(i >= 1)
					$(this).children('option').css('display','none');
			});
		}
	}
	
	var createSelectedList = function($selectTags) {
		let opt = optionSearch( userSelect );
		if( !opt )
			return;
		
		if( !overlapCheck(opt) ) {
			addEvent.resetSelectTag($selectTags);
			return;
		}
		
		let price = goodsPrice + opt.extraCost;
		$('<tr />').addClass('select-option').data(OPTION_INFO, opt).append(
			$('<td />').html(opt.combination + ' &nbsp;&nbsp;').data('combination', opt.combination),
			$('<td />').append(
				$('<input />').attr({'type':'number','name':'list[' + arraySize + '].os_stock'}).css('width','50px').val(1),
				$('<input />').attr({'type':'hidden','name':'list[' + arraySize + '].os_no'}).val(opt.os_no),
				$('<input />').attr({'type':'hidden','name':'list[' + arraySize + '].combination'}).val(opt.combination),
				$('<input />').attr({'type':'hidden','name':'list[' + arraySize + '].g_no'}).val(opt.g_no)
			),
			$('<td />').addClass('price').setPriceInHtml(price),
			$('<td />').html($('<i />').addClass('fa fa-window-close-o').css('cursor','pointer'))
		).appendTo( $table );
		arraySize++;
		addEvent.resetSelectTag($selectTags);
		
		function overlapCheck(opt) {
			let $tr = $table.children(SELECT_OPTION);
			for(let o of $tr)
				if(opt.combination === $(o).children('td:first').data('combination')) {
					alert('이미 선택하신 옵션입니다.');
					return false;
				}
			return true;
		}
	}
	
	$.fn.setPriceInHtml = function(price) {
		return $(this).html('&nbsp;&nbsp; ' + price + ' 원 &nbsp;&nbsp;').data('price', price);
	}
	
	return selectOption;
}());