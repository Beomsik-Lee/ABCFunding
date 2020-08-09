<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="crowding funding">
<meta name="author" content="9age">

<title>${title}</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/scrolling-nav.css" rel="stylesheet">

<!-- ABC Funding CSS -->
<link href="css/abcstyle.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script>

google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawLoanStasticsChart);
function drawLoanStasticsChart() {
  var stackLoanMoney = ${loanManage.stackLoanMoney};
  var stackLoanRepayMoney = ${loanManage.stackLoanRepayMoney};
  var stackLoanNum = ${loanManage.stackLoanNum}	;
  var loanEndNum = ${loanManage.loanEndNum};
  var loanMiddleNum = ${loanManage.loanMiddleNum};
  var loanStandbyNum = stackLoanNum - (loanEndNum + loanMiddleNum);
  
  //파이 그래프
  var data = google.visualization.arrayToDataTable([
    ['항목', '원'],
    ['상환완료금',     stackLoanRepayMoney],
    ['상환예정금',      stackLoanMoney - stackLoanRepayMoney]
  ]);

  var options = {
    title: '대출실행금 상환현황  (단위 : 원)',
    width : 600,
	height : 400,
    is3D: true,
  };

  var chart = new google.visualization.PieChart(document.getElementById('loanStatisticsChart2'));
  chart.draw(data, options);
  
    //막대 누적 그래프
	var data1 = google.visualization.arrayToDataTable([ [ "Element", "건수", {
		role : "style"
	} ], [ "상환완료", loanEndNum, "#b87333" ],
			[ "상환중", loanMiddleNum, "silver" ],
			[ "상환대기", loanStandbyNum, "gold" ] ]);

	var view1 = new google.visualization.DataView(data1);
	view1.setColumns([ 0, 1, {
		calc : "stringify",
		sourceColumn : 1,
		type : "string",
		role : "annotation"
	}, 2 ]);

	var options1 = {
		title : "상환건수 현황  (단위 : 건)",
		width : 600,
		height : 400,
		bar : {
			groupWidth : "95%"
		},
		legend : {
			position : "none"
		},
	};
	var chart1 = new google.visualization.ColumnChart(document
			.getElementById("loanStatisticsChart1"));
	chart1.draw(view1, options1);
}
</script>
</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body>


	<!-- 대출 관리 Section -->
	<section class="adminLoanManage-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<table class="table text-center">
						<thead>
							<tr>
								<th class="text-center">누적대출실행 건수</th>
								<th class="text-center">누적대출금</th>
								<th class="text-center">누적대출상환금</th>
								<th class="text-center">상환완료 건수</th>
								<th class="text-center">상환중인 건수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><c:out value="${loanManage.stackLoanNum}" />건</td>
								<td><fmt:formatNumber type="currency"
										value="${loanManage.stackLoanMoney}" />원</td>
								<td><fmt:formatNumber type="currency"
										value="${loanManage.stackLoanRepayMoney}" />원</td>
								<td><c:out value="${loanManage.loanEndNum}" />건</td>
								<td><c:out value="${loanManage.loanMiddleNum}" />건</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<br> <br>

			<div class="row">
				<div class="col-lg-6">
					<div id="loanStatisticsChart1" style="width: 570px;	height: 350px;"></div>
				</div>

				<div class="col-lg-6">
					<div id="loanStatisticsChart2" style="width: 570px;	height: 350px;"></div>
				</div>

			</div>


		</div>

	</section>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Scrolling Nav JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/scrolling-nav.js"></script>

</body>
</html>