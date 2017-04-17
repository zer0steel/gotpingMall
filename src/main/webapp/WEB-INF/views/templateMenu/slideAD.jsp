<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section id="slider">
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div id="slider-carousel" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
						<li data-target="#slider-carousel" data-slide-to="1"></li>
						<li data-target="#slider-carousel" data-slide-to="2"></li>
					</ol>

					<div class="carousel-inner">
						<div class="item active">
							<div class="col-sm-6">
								<h1><span>G</span>-PING MALL</h1>
								<h2>최고급만 취급하는 명품 쇼핑몰</h2>
								<p>의류부터 시작해서 자동차, 비행기까지 안파는게 없습니다. <br>다만 아직 판매를 안할 뿐이죠</p>
								<button type="button" class="btn btn-default get">즉시 보러 가기!</button>
							</div>
							<div class="col-sm-6">
								<img src="${pageContext.request.contextPath }/resources/images/myimg/car1.jpg" class="girl img-responsive" alt="" />
								<img src=""  class="pricing" alt="" />
							</div>
						</div>
						<div class="item">
							<div class="col-sm-6">
								<h1><span>G</span>-PING MALL</h1>
								<h2>100% 배송됨을 확신해줄수 없음</h2>
								<p>가지고 싶은 물건이 있다면 망설이지 마시고 즉시 결제하세요! <br>하지만 배송은 책임지지 못합니다!</p>
								<button type="button" class="btn btn-default get">즉시 보러 가기!</button>
							</div>
							<div class="col-sm-6">
								<img src="resources/images/myimg/IMG_0002.jpg" class="girl img-responsive" alt="" />
								<img src=""  class="pricing" alt="" />
							</div>
						</div>

						<div class="item">
							<div class="col-sm-6">
								<h1><span>G</span>-PING MALL</h1>
								<h2>언젠가 만들어 지겠지</h2>
								<p>오픈기념 회원가입하시면 마일리지 천만포인트를 쏩니다! 지금 가입하세요!</p>
								<button type="button" class="btn btn-default get">즉시 보러 가기!</button>
							</div>
							<div class="col-sm-6">
								<img src="resources/images/myimg/dogSound.jpeg" class="girl img-responsive" alt="" />
								<img src="" class="pricing" alt="" />
							</div>
						</div>

					</div>

					<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
						<i class="fa fa-angle-left"></i>
					</a>
					<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
						<i class="fa fa-angle-right"></i>
					</a>
				</div>

			</div>
		</div>
	</div>
</section>