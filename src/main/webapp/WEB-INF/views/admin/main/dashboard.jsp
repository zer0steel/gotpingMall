<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqPlot/1.0.9/jquery.jqplot.min.css">
<div class="col-md-6 col-sm-12 col-xs-12 tile">
	<span></span>
	<h2>일일 방문자수 (세션 생성기준)</h2>
	<canvas id="visitStats"></canvas>
</div>

<script type="application/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.bundle.min.js"></script>
<script type="application/javascript">
(function() {
 	var data = JSON.parse('${visitStats }');
 	var labels = [];
 	var count = [];
	
 	$(data).each(function(i) {
		labels.push(this.dayStr.substring(5,this.dayStr.length));
		count.push(this.count)
 	})
 	
 	var ctx = document.getElementById("visitStats");
	new Chart(ctx, {
		type : "line",
		data : {
			labels : labels,
			datasets:[{
				label : "${period.startDate } ~ ${period.endDate }",
				data : count,
				fill : false,
				borderColor : "rgb(0, 84, 255)",
				lineTension : 0.3
			}]
		},
		options: {
			responsive: true,
			scales: {
		        yAxes: [{
		            ticks: {
		                fixedStepSize: 2
		            }
		        }]
		    }
		}
	});
}())
</script>