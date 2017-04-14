/**
 * 
 */
(function() {
	$(".subMenu_level").children("option").each(function() {
		$(this).css("display","none");
	});
})();

$("select[name=big], select[name=middle]").change(function() {
	var c = Category.setC_no($(this).val());
	var sub = ( $(this).data("menu_level") == Category.BIG ) ?
			$("select[name=middle]") : $("select[name=small]");
	c.setSubMenu_levelSelectNode(sub);
});


var Category = {
	BIG : 0, MIDDLE : 1, SMALL : 2,
	setC_no : function(c_no) {
		this.c_no = c_no;
		return this;
	},
	setMenu_level : function(menu_level) {
		this.menu_level = menu_level;
		return this;
	},
	isParents : function(p_no) { return this.c_no == p_no; },
	isSubMenu_level : function() { return this.menu_level > this.BIG; },
	checkSuperMenu_level : function(menu_level) { return this.menu_level - 1 == menu_level; },
	setSubMenu_levelSelectNode : function(subSelectNode) {
		if( !(this.c_no > 0) )
			throw new "부모 분류번호를 먼저 지정해야 합니다";
		var c = this;
		var emptyCount = 0;
		subSelectNode.children("option").each(function() {
			var p_no = $(this).data("parent_no");
			if( c.isParents(p_no) ) {
				$(this).css("display","block");
			}
			else {
				$(this).css("display","none");
				emptyCount++;
			}
		})
		if(emptyCount == subSelectNode.children("option").length) {
			subSelectNode.attr("disabled","disabled");
			return;
		}
		subSelectNode.removeAttr("disabled");
	} 
};