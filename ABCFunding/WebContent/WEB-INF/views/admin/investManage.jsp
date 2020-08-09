<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="crowding funding">
    <meta name="author" content="9age">

    <title>title</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/scrolling-nav.css" rel="stylesheet">

    <!-- ABC Funding CSS -->
    <link href="css/abcstyle.css" rel="stylesheet">
    
    <!-- Google Chart Library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript" src="js/investManageChart.js"></script>
	<script type="text/javascript">
		// 투자정보 차트 데이터 붙이기
		<c:forEach var="chart1Data" items="${investManageList}">
			chart1Patch(["${chart1Data.investSeq}",
					"${chart1Data.investMoney}",
					"${chart1Data.stackProfit}"]);
		</c:forEach>
	
		// 상환비율 차트 데이터 붙이기
		var payed = 0;
		var paying = 0;
		var etc = 0;
		<c:forEach var="chart2Data" items="${investManageList}">
			<c:choose>
				<c:when test="${chart2Data.progress == '상환완료'}">
					payed++;
				</c:when>
				<c:when test="${chart2Data.progress == '상환중'}">
					paying++;
				</c:when>
				<c:otherwise>
					etc++;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		chart2Patch(['상환완료',payed]);
		chart2Patch(['상환중', paying]);
		chart2Patch(['기타', etc]);
	</script>
</head>
<body>
	<section class="login-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>투자관리</h1>
					<!-- 투자관리 데이터 테이블 -->
					<table class="table table-hover text-center">
						<tr>
							<td>총 투자건수</td>
							<td>누적 투자금</td>
							<td>누적 수익금</td>
							<td>상환중</td>
							<td>상환완료</td>
						</tr>
						<tr>
							<td>
								<fmt:formatNumber type="number" value="${investManageList.size()}" />건
							</td>
							<td>
								<c:set var="stackInvestMoney" value="0" />
								<c:forEach var="investData" items="${investManageList}">
									<c:set var="stackInvestMoney" 
									value="${stackInvestMoney + investData.investMoney}" />
								</c:forEach>
								<fmt:formatNumber type="currency" value="${stackInvestMoney}" />원
							</td>
							<td>
								<c:set var="stackProfit" value="0" />
								<c:forEach var="investData" items="${investManageList}">
									<c:set var="stackProfit" 
									value="${stackProfit + investData.stackProfit}" />
								</c:forEach>
								<fmt:formatNumber type="currency" value="${stackProfit}" />원
							</td>
							<td>
								<c:set var="paying" value="0" />
								<c:forEach var="investData" items="${investManageList}">
									<c:if test="${investData.progress == '상환중'}">
										<c:set var="paying" value="${paying+1}" />
									</c:if>
								</c:forEach>
								<fmt:formatNumber type="number" value="${paying}" />건
							</td>
							<td>
								<c:set var="payed" value="0" />
								<c:forEach var="investData" items="${investManageList}">
									<c:if test="${investData.progress == '상환완료'}">
										<c:set var="payed" value="${payed+1}" />
									</c:if>
								</c:forEach>
								<fmt:formatNumber type="number" value="${payed}" />건
							</td>
						</tr>
					</table>
					<!-- 투자 차트1 -->
					<div id="invest-chart1"></div>
					<!-- 투자 차트2 -->
					<div id="invest-chart2"></div>					
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