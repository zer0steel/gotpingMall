var goods = goods || {};
goods.selectOption = (function() {
	'use strict';
	
	var $table;
	var userSelect = [];
	var optInfo, goodsPrice;
	var arraySize = 0;
	var updateMode = false;
	const ATTR = {
		DATA : {
			EXTRA_COST : 'extraCost',
			OPTION_INFO : 'info',
			PRICE : 'price'
		},
		CLASS : {
			SELECT_OPTION : 'select-option',
			PRICE : 'goodsPrice'
		}
	}
	const DEFAULT_OPTION = '';
	
	function selectOption(setting) {
		goodsPrice = Number(setting.goodsPrice);
		updateMode = setting.updateMode;
		(function(g_no) {
			return $.ajax({
				url : location.protocol + '//' + location.host + '/controller/goods/option/stock.yo',
				data : {g_no: g_no},
				dataType : 'json'
			});
		}(setting.g_no)).done(info => {
			console.log(info)
			optInfo = info;
			createSelectTag(setting.$root, info.options);
			addEvent(setting.$root.find('select'), setting.onStockChange);
		});
		
		return selectOption;
	}
	
	selectOption.getSelectedOptionCount = function() {
		return arraySize;
	}
	
	selectOption.setGoodsPrice = function(price) {
		goodsPrice = Number(price);
	}
	
	selectOption.getOptionCount = function() {
		return optInfo.options.length;
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
		
		let stocks = optInfo.stocks;
		for(var s of stocks)
			if(s.combination === selectedOption) {
				s.extraCost = extraCost;
				return s;
			}
		return false;
	}
	
	var getStockCount = function() {
		let stockCount = 0;
		$('.' + ATTR.CLASS.SELECT_OPTION).find('input[type=number]').each(function() {
			stockCount += Number($(this).val());
		});
		return stockCount;
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
					$option.data(ATTR.DATA.EXTRA_COST, d.extra_cost);
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
	
	var createSelectedList = function($selectTags, onStockChange) {
		let opt = optionSearch( userSelect );
		if( !opt )
			return;
		
		if( !overlapCheck(opt) ) {
			addEvent.resetSelectTag($selectTags);
			return;
		}
		
		let price = goodsPrice + opt.extraCost;
		$('<tr />').addClass(ATTR.CLASS.SELECT_OPTION).data(ATTR.DATA.OPTION_INFO, opt).append(
			$('<td />').html(opt.combination + ' &nbsp;&nbsp;').data('combination', opt.combination),
			$('<td />').append(
				$('<input />').attr({'type':'number','name':'details[' + arraySize + '].change_amount'}).css('width','50px').val(1),
				$('<input />').attr({'type':'hidden','name':'details[' + arraySize + '].g_no'}).val(opt.g_no),
				$('<input />').attr({'type':'hidden','name':'details[' + arraySize + '].s_no'}).val(opt.s_no),
				$('<input />').attr({'type':'hidden','name':'details[' + arraySize + '].combination'}).val(opt.combination)
			),
			$('<td />').addClass(ATTR.CLASS.PRICE).data('price', price).setPriceInHtml(price),
			$('<td />').html($('<i />').addClass('fa fa-window-close-o').css('cursor','pointer'))
		).appendTo( $table );
		arraySize++;
		addEvent.resetSelectTag($selectTags);
		
		onStockChange(getStockCount());
		
		function overlapCheck(opt) {
			let $tr = $table.find('.' + ATTR.CLASS.SELECT_OPTION);
			for(let o of $tr) {
				if(opt.combination === $(o).children('td:first').data('combination')) {
					alert('이미 선택하신 옵션입니다.');
					return false;
				}
			}
			return true;
		}
	}
	
	var addEvent = function($selectTags, onStockChange) {
		if(typeof onStockChange !== 'function') {
			onStockChange = function(){};
		}
		
		$selectTags.each(function(i) {
			$(this).change(function() {
				setUserSelect($(this), $selectTags, i);
				createSelectedList($selectTags, onStockChange);
				
				displayNoneOptionTag($selectTags, i);
				displayBlockOptionTag( $selectTags[i + 1] );
				if(!updateMode)
					displaySoldoutOptionTag($selectTags, i);
			});
		});
		
		$table.on('change', 'input[type=number]', function() {
			let amount = $(this).val();
			modifyPrice(amount, $(this).parents('.' + ATTR.CLASS.SELECT_OPTION));
			
			onStockChange(getStockCount());
		});
		
		$table.on('click', 'i.fa', function() {
			$(this).parents('.' + ATTR.CLASS.SELECT_OPTION).empty();
			arraySize--;
			
			onStockChange(getStockCount());
		});
		
		var modifyPrice = function(amount, $parentTag) {
			if( !validationCheck(amount, $parentTag) )
				return;
			
			let $priceTag = $parentTag.find('.' + ATTR.CLASS.PRICE);
			let price = Number($priceTag.data('price')) * amount;
			$priceTag.setPriceInHtml(price);
			
			// 상품 수량 유효성 검사
			function validationCheck(amount, $parentTag) {
				let $inputAmount = $parentTag.find('input[type=number]');
				if( amount < 1) {
					alert('최소 주문 수량은 1개입니다.');
					$inputAmount.val(1);
					return false;
				}
				
				let opt = $parentTag.data(ATTR.DATA.OPTION_INFO);
				if(!updateMode && amount > opt.amount) {
					alert('선택하신 상품 옵션의 수량이 부족합니다.');
					$inputAmount.val(opt.amount);
					return false;
				}
				
				return true;
			}
		}
		
		var setUserSelect = function($selectTag, $selectTags, i) {
			let $selectedOption = $selectTag.children('option:selected');
			userSelect[i] = option($selectedOption.val(), $selectedOption.data(ATTR.DATA.EXTRA_COST));
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
				if(o.amount === 0) {
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
	
	$.fn.setPriceInHtml = function(price) {
		return $(this).html('&nbsp;&nbsp; ' + Number(price) + ' 원 &nbsp;&nbsp;');
	}
	
	return selectOption;
}());