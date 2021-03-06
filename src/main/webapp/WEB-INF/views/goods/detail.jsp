<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- FlexSlider -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.6.3/flexslider.min.css" type="text/css" media="screen" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/flexslider/2.6.3/jquery.flexslider.min.js"></script>
<script type="application/javascript">
$(function() {
    var menu_ul = $('.menu_drop > li > ul'),
           menu_a  = $('.menu_drop > li > a');
    
    menu_ul.hide();

    menu_a.click(function(e) {
        e.preventDefault();
        if(!$(this).hasClass('active')) {
            menu_a.removeClass('active');
            menu_ul.filter(':visible').slideUp('normal');
            $(this).addClass('active').next().stop(true,true).slideDown('normal');
        } else {
            $(this).removeClass('active');
            $(this).next().stop(true,true).slideUp('normal');
        }
    });

});
</script>
<div class="single contact">
	<div class="container">
		<div class="single-main">
			<div class="col-md-9 single-main-left">
				<div class="sngl-top">
					<div class="col-md-5 single-top-left">	
						<div class="flexslider">
							<ul class="slides">
								<c:forEach var="i" items="${g.images }">
								<li data-thumb="${pageContext.request.contextPath }/${i.save_path}/${i.save_name}">
									<div class="thumb-image">
										<img src="${pageContext.request.contextPath }/${i.save_path}/${i.save_name}" 
										data-zoom-image="${pageContext.request.contextPath }/${i.save_path}/${i.save_name}" class="img-responsive" alt="실패"/>
									</div>
								</li>
								</c:forEach>
							</ul>
						</div>
						<script>
						$('.flexslider').flexslider({
							animation: "slide",
							controlNav: "thumbnails"
						});
						</script>
					</div>	
					<div class="col-md-7 single-top-right">
						<div class="single-para simpleCart_shelfItem">
							<input type="hidden" id="g_no" value="${g.g_no }">
							<h2>${g.name }</h2>
							<!-- <div class="star-on">
								<ul class="star-footer">
									<li><a href="#"><i></i></a></li>
									<li><a href="#"><i></i></a></li>
									<li><a href="#"><i></i></a></li>
									<li><a href="#"><i></i></a></li>
									<li><a href="#"><i></i></a></li>
								</ul>
								<div class="review">
									<a href="#"></a>
								</div>
								<div class="clearfix"></div>
							</div> -->
							<c:if test="${g.discount_rate > 0 }">
								<p style="text-decoration: line-through;">${g.sell_price } 원</p>
							</c:if>
							<h5 class="item_price">
								<span id="goodsPrice">
									<fmt:formatNumber value="${g.realPrice }" groupingUsed="true"/>
								</span> 원
							</h5>
							
							<div class="available">
								<form action="${pageContext.request.contextPath }/order/form.yo" id="form-option-select">
									<div id="selectOption"></div>
								</form>
							</div>
							<!-- <ul class="tag-men">
								<li><span>TAG</span>
								<span class="women1">: Women,</span></li>
								<li><span>SKU</span>
								<span class="women1">: CK09</span></li>
							</ul> -->
							<a href="#" class="add-cart item_add">장바구니</a>
							<a href="#" class="add-cart item_add" id="btn-buy">구매하기</a>
						</div>
					</div>
					<div class="clearfix"> </div>
				</div>
				<div class="tabs">
					<ul class="menu_drop">
						<li class="item1"><a href="#"><img src="${pageContext.request.contextPath }/resources/template/mall/images/arrow.png" alt="">상세 설명</a>
							<ul>
								<li class="subitem1"><a href="#">${g.detail }</a></li>
							</ul>
						</li>
						<li class="item3"><a href="#"><img src="${pageContext.request.contextPath }/resources/template/mall/images/arrow.png" alt="">상품평 (추가예정)</a>
							<ul>
								<li class="subitem1"><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</a></li>
								<li class="subitem2"><a href="#"> Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore</a></li>
								<li class="subitem3"><a href="#">Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes </a></li>
							</ul>
						</li>
	 				</ul>
				</div>
			</div>
			<div class="col-md-3 single-right">
				<jsp:include page="/WEB-INF/views/templateMenu/sideMenu.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/goods/stock.js?ver=1"></script>
<script type="text/javascript">
(function() {
	var optCnt = goods.selectOption({
		g_no : $('#g_no').val(),
		$root : $('#selectOption'),
		goodsPrice : '${g.realPrice }'
	}).getSelectedOptionCount;
	
	$('#btn-buy').click(function() {
		if(optCnt() < 1) {
			alert('선택된 상품이 없습니다.');
			return;
		}
		$('#form-option-select').submit();
	});
}());
</script>