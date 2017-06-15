<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-title">
	<div class="title_left">
		<h3>전체 주문</h3>
	</div>
</div>
<br />
<div class="row">
	<div class="x_panel">
		<div class="x_title">
			<h2>목록</h2>
			<div class="clearfix"></div>
		</div>
		<div class="x_content">
			<form action="updateStatus.yo" method="post" id="updateStatusForm">
				<div class="form-group">
					<div class="col-md-2 input-group">
						<select class="form-control">
						<c:forEach var="s" items="${status }">
							<option value="${s.code }">${s.kor }</option>
						</c:forEach>
						</select>
						<span class="input-group-btn">
							<button type="submit" class="btn btn-info">일괄 적용</button>
						</span>
					</div>
				</div>
			</form>
			<table id="memberTable" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th><input type="checkbox" id="allSelect"></th>
						<th>주문일</th>
						<th>주문번호</th>
						<th>주문자명</th>
						<th>주문방법</th>
						<th>결제금액</th>
						<th width="140px;">주문상태</th>
						<th>기능</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="p" items="${pay }">
					<tr>
						<td><input type="checkbox" class="orderCheckBox" value="${p.p_no }"></td>
						<td>${p.pay_date }</td>
						<td>${p.order_uid }</td>
						<td>${p.order.buyer }</td>
						<td>${p.p_way.kor }</td>
						<td>${p.pay_amount }</td>
						<td>
							<select class="form-control">
							<c:forEach var="s" items="${status }">
								<option value="${s.code }" <c:if test="${p.status.code eq s.code }">selected</c:if>>${s.kor }</option>
							</c:forEach>
							</select>
						</td>
						<td>
							<button class="btn btn-info btn-sm btn-update" value="${p.p_no }">적용</button>
							<button class="btn btn-info btn-sm">상세보기</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
(function() {
	$('#allSelect').click(function() {
		if($(this).is(':checked'))
			$('.orderCheckBox').prop('checked', true);
		else
			$('.orderCheckBox').prop('checked', false);
	});
	
	function getJSON(p_no, statusCode) {
		return {
			p_no : (p_no instanceof Array) ? p_no : [p_no], 
			statusCode : statusCode
		}
	}
	
	function requestUpdateStatus(data) {
		return $.ajax({
			url : 'updateStatus.yo',
			type : 'post',
			data : data
		});
	}
	
	(function($form) {
		$form.submit(function() {
			return confirmUpdate($form.find('option:selected'));
		})
		
		var confirmUpdate = function($selectedOption) {
			let $orders = $('.orderCheckBox:checked');
			if($orders.length === 0) {
				alert('선택된 주문이 없습니다.')
				return false;
			}
			let alertText = '선택된 주문들의 상태를 ' + $selectedOption.text() + '(으)로 바꿉니다. 계속하시겠습니까?';
			if(confirm(alertText)) {
				let pNoList = getCheckedOrder($orders);
				updateStatus(pNoList, $selectedOption.val());
				return false;
			}
			return false;
		}
		
		var getCheckedOrder = function($orders) {
			let pNoList = [];
			$orders.each(function(i) {
				pNoList.push($(this).val());
			})
			return pNoList;
		}
	}($('#updateStatusForm')))
	
	$('.btn-update').click(function() {
		let statusCode = $(this).parents('tr').find('select').val();
		let p_no = $(this).val();
		updateStatus(p_no, statusCode);
	});
	
	var updateStatus = function(p_no, statusCode) {
		requestUpdateStatus(getJSON(p_no, statusCode)).done(function(result) {
			result === true ? location.href = 'list.yo' : alert('수정에 실패했습니다.');
		}).fail(function(err) {
			alert('에러발생')
			showErr(err);
		});
	}
}());
</script>