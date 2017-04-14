/**
 * 
 */
$(".subMenu_level").children("option").each(function() {
	$(this).css("display","none");
});



$("select[name=big], select[name=middle]").click(function() {
	var c = Category.setC_no($(this).val());
	if( $(this).data("menu_level") == Category.BIG  ) {
		$("select[name=small]").attr("disabled", true).val("").children("option").each(function() {
			$(this).css("display","none");
		});
		$("select[name=middle]").val("");
	}
	else {
		$("select[name=small]").val("");
	}
});

$("select[name=big], select[name=middle]").change(function() {
	var c = Category.setC_no($(this).val());
	var sub = ( $(this).data("menu_level") == Category.BIG ) ?
			$("select[name=middle]") : $("select[name=small]");
	c.setSubMenu_levelSelectNode(sub);
});


var Category = {
	BIG : 1, MIDDLE : 2, SMALL : 3,
	/* 분류번호를 저장한다 */
	setC_no : function(c_no) {
		this.c_no = c_no;
		return this;
	},
	
	/* 분류 레벨을 저장한다 */
	setMenu_level : function(menu_level) {
		this.menu_level = menu_level;
		return this;
	},
	
	setCategory : function(category) {
		this.c_no = category.c_no;
		this.menu_level = category.menu_level;
		return this;
	},
	
	/**
	 * 자기자신이 부모 분류인지 확인한다.
	 * @param p_no : 자식 분류가 가지고 있던 부모분류
	 */
	isParents : function(p_no) { return this.c_no == p_no; },
	
	/* 자기자신이 하위 분류인지 확인한다. */
	isSubMenu_level : function() { return this.menu_level > this.BIG && this.menu_level <= this.SMALL; },
	
	/**
	 * 파라메터 menu_level이 바로 아랫단계 분류인지 확인한다.
	 * @param p_no : 비교대상 메뉴레벨
	 */
	checkSuperMenu_level : function(menu_level) { return this.menu_level - 1 == menu_level; },
	
	/**
	 * goods/categorySelectNode.jsp 안에 존재하는 select 노드들을 세팅한다.
	 * subSelectNode 들의 option 에 있는 부모노드 값들을 확인하여 부모노드가 맞다면 해당 option을 보여준다.
	 * @param subSelectNode : select 노드 jquery 객체
	 */
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