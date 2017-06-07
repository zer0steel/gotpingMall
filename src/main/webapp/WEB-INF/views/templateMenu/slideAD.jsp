<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--banner-starts-->
<div class="bnr" id="home">
	<div  id="top" class="callbacks_container">
		<ul class="rslides" id="slider4">
		    <li>
				<img src="${pageContext.request.contextPath }/resources/template/mall/images/bnr-1.jpg" alt=""/>
			</li>
			<li>
				<img src="${pageContext.request.contextPath }/resources/template/mall/images/bnr-2.jpg" alt=""/>
			</li>
			<li>
				<img src="${pageContext.request.contextPath }/resources/template/mall/images/bnr-3.jpg" alt=""/>
			</li>
		</ul>
	</div>
	<div class="clearfix"> </div>
</div>
<!--banner-ends--> 
<!--Slider-Starts-Here-->
<script type="application/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/ResponsiveSlides.js/1.55/responsiveslides.min.js"></script>
<script>
// You can also use "$(window).load(function() {"
$(function () {
	// Slideshow 4
	$("#slider4").responsiveSlides({
		auto : true,
		pager : true,
		nav : true,
		speed : 1000,
		namespace : "callbacks",
		before : function() {
			$('.events').append("<li>before event fired.</li>");
		},
		after : function() {
			$('.events').append("<li>after event fired.</li>");
		}
	});
});
</script>
<!--End-slider-script-->
<div class="about"> 
	<div class="container">
		<div class="about-top grid-1">
			<div class="col-md-4 about-left">
				<figure class="effect-bubba">
					<img class="img-responsive" src="${pageContext.request.contextPath }/resources/template/mall/images/abt-1.jpg" alt=""/>
					<figcaption>
						<h2>오픈 기념 이벤트</h2>
						<p>지금 가입하시면 마일리지 천만포인트를 쏩니다!</p>	
					</figcaption>
				</figure>
			</div>
			<div class="col-md-4 about-left">
				<figure class="effect-bubba">
					<img class="img-responsive" src="${pageContext.request.contextPath }/resources/template/mall/images/abt-2.jpg" alt=""/>
					<figcaption>
						<h4>Mauris erat augue</h4>
						<p>In sit amet sapien eros Integer dolore magna aliqua</p>	
					</figcaption>			
				</figure>
			</div>
			<div class="col-md-4 about-left">
				<figure class="effect-bubba">
					<img class="img-responsive" src="${pageContext.request.contextPath }/resources/template/mall/images/abt-3.jpg" alt=""/>
					<figcaption>
						<h4>Cras elit mauris</h4>
						<p>In sit amet sapien eros Integer dolore magna aliqua</p>	
					</figcaption>			
				</figure>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>