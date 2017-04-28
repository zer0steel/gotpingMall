<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-sm-12">
	<div class="product-details">
		<!--product-details-->
		<div class="col-sm-5">
			<div class="view-product">
				<jsp:include page="include/imageSlider.jsp"></jsp:include>
			</div>
		</div>
		<div class="col-sm-7">
			<div class="product-information">
				<!--/product-information-->
				<img src="images/product-details/new.jpg" class="newarrival" alt="new" />
				<h2>${g.name }</h2>
				<p></p>
				<span>
					<span>${g.sell_price } 원</span>
					<label>수량 : </label>
					<input type="text" value="" />
					<button type="button" class="btn btn-fefault cart">
						<i class="fa fa-shopping-cart"></i>장바구니에 추가하기
					</button>
				</span>
				<p>
					<b>Availability:</b> In Stock
				</p>
				<a href="">
					<img src="images/product-details/share.png" class="share img-responsive" alt="카드" />
				</a>
			</div>
			<!--/product-information-->
		</div>
	</div>
	<!--/product-details-->

	<div class="category-tab shop-details-tab">
		<!--category-tab-->
		<div class="col-sm-12">
			<ul class="nav nav-tabs">
				<li><a href="#details" data-toggle="tab">상세정보</a></li>
				<li><a href="#payInfo" data-toggle="tab">상품 결제 정보</a></li>
				<li class="active"><a href="#reviews" data-toggle="tab">상품후기</a></li>
			</ul>
		</div>
		<div class="tab-content">
			<div class="tab-pane fade" id="details">
				<div class="col-sm-12">
					<div style="width: 100%; height: auto; overflow: auto;">${g.detail }</div>
				</div>
			</div>

			<div class="tab-pane fade" id="payInfo">
				<div class="col-sm-12">
				<h3>상품결제정보</h3>
				<br>
				고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도 있습니다.
				확인과정에서 도난 카드의 사용이나 타인 명의의 주문등 정상적인 주문이 아니라고 판단될 경우 임의로 주문을 보류 또는 취소할 수 있습니다.   
				<br><br>
				무통장 입금은 상품 구매 대금은 PC뱅킹, 인터넷뱅킹, 텔레뱅킹 혹은 가까운 은행에서 직접 입금하시면 됩니다.  
				주문시 입력한 입금자명과 실제입금자의 성명이 반드시 일치하여야 하며, 7일 이내로 입금을 하셔야 하며 입금되지 않은 주문은 자동취소 됩니다. 
				</div>
			</div>


			<div class="tab-pane fade active in" id="reviews">
				<div class="col-sm-12">
					<ul>
						<li><a href=""><i class="fa fa-user"></i>EUGEN</a></li>
						<li><a href=""><i class="fa fa-clock-o"></i>12:41 PM</a></li>
						<li><a href=""><i class="fa fa-calendar-o"></i>31 DEC
								2014</a></li>
					</ul>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna
						aliqua.Ut enim ad minim veniam, quis nostrud exercitation ullamco
						laboris nisi ut aliquip ex ea commodo consequat.Duis aute irure
						dolor in reprehenderit in voluptate velit esse cillum dolore eu
						fugiat nulla pariatur.</p>
					<p>
						<b>Write Your Review</b>
					</p>

					<form action="#">
						<span> <input type="text" placeholder="Your Name" /> <input
							type="email" placeholder="Email Address" />
						</span>
						<textarea name=""></textarea>
						<b>Rating: </b> <img src="images/product-details/rating.png"
							alt="" />
						<button type="button" class="btn btn-default pull-right">
							Submit</button>
					</form>
				</div>
			</div>

		</div>
	</div>
	<!--/category-tab-->

	<div class="recommended_items">
		<!--recommended_items-->
		<h2 class="title text-center">recommended items</h2>

		<div id="recommended-item-carousel" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="item active">
					<div class="col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<img src="images/home/recommend1.jpg" alt="" />
									<h2>$56</h2>
									<p>Easy Polo Black Edition</p>
									<button type="button" class="btn btn-default add-to-cart">
										<i class="fa fa-shopping-cart"></i>Add to cart
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<img src="images/home/recommend2.jpg" alt="" />
									<h2>$56</h2>
									<p>Easy Polo Black Edition</p>
									<button type="button" class="btn btn-default add-to-cart">
										<i class="fa fa-shopping-cart"></i>Add to cart
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<img src="images/home/recommend3.jpg" alt="" />
									<h2>$56</h2>
									<p>Easy Polo Black Edition</p>
									<button type="button" class="btn btn-default add-to-cart">
										<i class="fa fa-shopping-cart"></i>Add to cart
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<img src="images/home/recommend1.jpg" alt="" />
									<h2>$56</h2>
									<p>Easy Polo Black Edition</p>
									<button type="button" class="btn btn-default add-to-cart">
										<i class="fa fa-shopping-cart"></i>Add to cart
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<img src="images/home/recommend2.jpg" alt="" />
									<h2>$56</h2>
									<p>Easy Polo Black Edition</p>
									<button type="button" class="btn btn-default add-to-cart">
										<i class="fa fa-shopping-cart"></i>Add to cart
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<img src="images/home/recommend3.jpg" alt="" />
									<h2>$56</h2>
									<p>Easy Polo Black Edition</p>
									<button type="button" class="btn btn-default add-to-cart">
										<i class="fa fa-shopping-cart"></i>Add to cart
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<a class="left recommended-item-control"
				href="#recommended-item-carousel" data-slide="prev"> <i
				class="fa fa-angle-left"></i>
			</a> <a class="right recommended-item-control"
				href="#recommended-item-carousel" data-slide="next"> <i
				class="fa fa-angle-right"></i>
			</a>
		</div>
	</div>
	<!--/recommended_items-->

</div>