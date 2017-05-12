<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="review-payment">
	<h2>주문상품</h2>
</div>
<section id="cart_items">
	<div class="container">
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">상품</td>
						<td class="description"></td>
						<td class="price">가격</td>
						<td class="quantity">수량</td>
						<td class="total">총금액</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="cart_product">
							<a href="">
								<img src="images/cart/one.png" alt="">
							</a>
						</td>
						<td class="cart_description">
							<h4>
								<a href="">Colorblock Scuba</a>
							</h4>
							<p>Web ID: 1089772</p>
						</td>
						<td class="cart_price">
							<p>$59</p>
						</td>
						<td class="cart_quantity">
							<div class="cart_quantity_button">
								<a class="cart_quantity_up" href=""> + </a>
								<input class="cart_quantity_input" type="text" name="quantity" value="1" autocomplete="off" size="2">
								<a class="cart_quantity_down" href=""> - </a>
							</div>
						</td>
						<td class="cart_total">
							<p class="cart_total_price">$59</p>
						</td>
						<td class="cart_delete">
							<a class="cart_quantity_delete" href="">
								<i class="fa fa-times"></i>
							</a>
						</td>
					</tr>
		
					<tr>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">
							<table class="table table-condensed total-result">
								<tr>
									<td>상품 금액</td>
									<td>$59</td>
								</tr>
								<tr>
									<td>배송료</td>
									<td>$2</td>
								</tr>
								<tr>
									<td>총금액</td>
									<td><span>$61</span></td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</section>