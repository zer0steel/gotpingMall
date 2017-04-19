var CategoryOption = {
	CHOICE : {
		code : 0,
		msg : "눌러서 선택하세요.",
	},
	CANNOT_CHOICE : {
		code : -1,
		msg : "선택하실수 없습니다."
	},
	NOT_EXIST_SUPER : {
		code : -2,
		msg : "상위분류가 존재하지 않습니다."
	},
	NOT_EXIST_SUB : {
		code : -3,
		msg : "하위분류가 존재하지 않습니다."
	}
}
function createOption(CategoryOption) {
	return $("<option></option>").val(CategoryOption.code).text(CategoryOption.msg).css("display","none");
}
$("select[name=menu_level]").prepend(
	createOption(CategoryOption.CHOICE),
	createOption(CategoryOption.NOT_EXIST_SUB),
	createOption(CategoryOption.CANNOT_CHOICE)
).val(CategoryOption.CHOICE.code);

$("select[name=super_no]").append(
	createOption(CategoryOption.CHOICE),
	createOption(CategoryOption.NOT_EXIST_SUPER),
	createOption(CategoryOption.CANNOT_CHOICE)
);

$("select[name=menu_level]").change(function() {
	var menu_level = $(this).val();
	setSuperCategorySelectBox(menu_level);
});

function setSuperCategorySelectBox(menu_level) {
	var c = Category.setMenu_level(menu_level);
	var superCategoryBox = $("select[name=super_no]");
	
	if( c.isSubMenu_level() ) 
		showSuperCategory(c);
	else if( c.isBigMenu_level() ) {
		var code = CategoryOption.CANNOT_CHOICE.code;
		superCategoryBox.val(code).attr("selected", true).attr("disabled",true);
	}
	else 
		superCategoryBox.val("").attr("selected", true).attr("disabled",true);
}

function showSuperCategory(subCategory) {
	var superCategoryBox = $("select[name=super_no]");
	var categoryCount = 0;
	superCategoryBox.children("option").each(function() {
		var menu_level = $(this).data("menu_level");
		if( subCategory.checkSuperMenu_level( menu_level ) ) {
			$(this).css("display","block");
			categoryCount++;
		}
		else
			$(this).css("display","none");
	});
 	if( categoryCount > 0 ) {
 		var code = CategoryOption.CHOICE.code;
 		superCategoryBox.attr("required","required").removeAttr("disabled").val(code);
 	}
	else {
		var code = CategoryOption.NOT_EXIST_SUPER.code;
		superCategoryBox.attr("disabled",true).val(code);
	}
}