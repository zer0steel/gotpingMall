var goods = goods || {};
goods.category = (function() {
	'use strict';
	$.fn.disableSelectTag = function() {
		$(this).attr('disabled', true).find('option').each(function() {
			$(this).css('display', 'none');
		});
		return this;
	}
	
	var setting = {
		$root : null,
		selectSize : 1,
		onChange : function() {}
	};
	
	const ATTR = {
		DATA : {
			SUPER : 'super_no'
		}
	}
	
	var setup = function(inputOption) {
		for(let key in setting) {
			if(inputOption.hasOwnProperty(key)) {
				setting[key] = inputOption[key];
			}
		}
	}
	
	var requestDetailCategory = function(c_no) {
		if(!c_no)
			return function() {};
		return $.ajax({
			url : "category/detail.yo",
			data : {c_no : c_no },
			type : "post",
			dataType : "json"
		}).done;
	}
	/*
	 *	@param 	$root :	최상단 노드
	 *			selectSize : select 태그 보여질 크기
	 *			onChange : function(category) 선택된 값이 변경되면 호출
	 *						category : 선택된 분류 정보
	 */
	function category(settingOption) {
		setup(settingOption);
		(function requestCategory() {
			return $.ajax({
				url : location.protocol + '//' + location.host + '/controller/goods/category.yo',
				dataType : 'json'
			})
		}()).done(map => {
			let selects = createTag(map);
			addEvent(selects);
		}).fail(err => {
			console.log(err);
			alert('에러! 관리자에게 문의하세요.');
		});
		return category;
	}
	
	var createTag = function(map) {
		let $root = setting.$root;
		return [
			createTag(map.BIG),
			createTag(map.MIDDLE),
			createTag(map.SMALL)
		]
		
		function createTag(levelVO) {
			let level = levelVO.levels;
			let $select = $('<select />').addClass('form-control').attr({'size':setting.selectSize}).data('level', level.code);
			for(let category of levelVO.categories) {
				let $option = $('<option />').text(category.title).val(category.c_no).data(ATTR.DATA.SUPER, category.super_no);
				$select.append( $option );
			}
			
			if(level.code > 1)
				$select.disableSelectTag();
			
			$('<div />').addClass('col-md-4').append(
					$('<p />').text(level.korName),
					$select
			).appendTo( $root );
			return $select;
			
		}
	}
	
	var addEvent = function(selects) {
		$(selects).each(index => {
			$(selects[index]).click(function() {
				if(!$(this).val())
					return;
				
				onChange($(this), index);
				$(selects[index + 1]).val('');
				$(selects[index + 2]).val('').attr('disabled', true).disableSelectTag();
			});
		});
		
		function onChange($select, index) {
			showSubCategory($select.val(), selects[index + 1]);
			callOnChange($select.find('option:selected'));
		}
		
		function callOnChange($selectedOption) {
			let done = requestDetailCategory($selectedOption.val())
			done(category => {
				category.subLevel = getSubLevel;
				setting.onChange(category);
				
				function getSubLevel() {
					return Number(this.levels.code) + 1;
				}
			});
		}
		
		function showSubCategory(c_no, subSelectTag) {
			if(!subSelectTag) return;
			
			let optCount = 0;
			$(subSelectTag).find('option').each(function() {
				let superNo = Number($(this).data(ATTR.DATA.SUPER));
				let display = 'none';
				if(Number(c_no) === superNo) {
					display = 'block';
					optCount++;
				}
				$(this).css('display', display);
			});
			
			$(subSelectTag).attr('disabled', optCount === 0);
		}
	}
	return category;
}());