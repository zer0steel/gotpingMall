<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/orderList.css">
<div class="pages" id="pages">
	<div class="container">
		<div class="typo-top heading">
			<h2>주문 목록</h2>
		</div>
		<div class="typo-bottom">
			<div class="distracted">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-7">
						<form action="orderList.yo" class="form-inline">
							<div class="input-daterange input-group well" id="datepicker">
							    <input type="text" class="form-control" id="startDate" name="startDate" value="${search.startDate }"/>
							    <span class="input-group-addon">~</span>
							    <input type="text" class="form-control" id="endDate" name="endDate" value="${search.endDate }"/>
							    <span class="input-group-btn">
							    	<button class="btn btn-info">검색</button>
							    </span>
							</div>
					    </form>
					</div>
					<div class="col-md-2"></div>
				</div>
				
 				<c:forEach var="pay" items="${payList }">
 				
					<c:if test="${titDate ne pay.month }">
					<div class="col-md-8 col-md-offset-2 titMonth">
						<h4>${pay.month }</h4>
						<c:set var="titDate" value="${pay.month }" />
					</div>
					</c:if>
				
					<div class="well col-md-8 col-md-offset-2">
						<strong style="font-size: 20px;">
							<a href="orderDetail.yo" data-order-id="${pay.order_uid }" class="link-orderDetail">${pay.order.d_name }</a>
						</strong>
						<br />
						<ul>
							<li>${pay.pay_amount } 원</li>
							<li> | </li>
							<li><fmt:formatDate value="${pay.pay_date }" pattern="yyyy.MM.dd"/></li>
						</ul>
						<p>${pay.status.kor }</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="order-detail" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title">주문 상세 정보</h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>

	</div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.kr.min.js"></script>
<script type="application/javascript">
(function() {
	/* let today = new Date();
	let endDate = daysInMonth(today.getFullYear(), today.getMonth() + 1);
	let todayMonth = today.getFullYear() + '.' + (today.getMonth() + 1); */
	
	/* $('#startDate').val(todayMonth + '.1');
	$('#endDate').val(todayMonth + '.' + endDate); */
	
	$('#datepicker').datepicker({
		format: 'yyyy.mm.dd',
		language: 'kr',
		autoClose: true
	});
	
	function daysInMonth(year, month) {
		return new Date(year, month, 0).getDate();
	}
	
	$('.link-orderDetail').click(function(e) {
		e.preventDefault();
		showOrderDetail($(this).data('order-id'));
	});
	
	var showOrderDetail = function(order_uid) {
		requestOrderDetail( order_uid ).done(html => {
			$('.modal-body').html(html);
			$('#order-detail').modal();
		}).fail(err => {
			console.log(err);
			alert('잠시후 다시 시도해 주세요.')
		});
	}
	
	var requestOrderDetail = function(order_uid) {
		return $.ajax({
			url : 'detail.yo',
			data : {order_uid: order_uid}
		});
	}
}())
</script>