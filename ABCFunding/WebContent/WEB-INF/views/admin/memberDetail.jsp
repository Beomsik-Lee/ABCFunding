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
	<script type="text/javascript" src="js/googleChart.js"></script>
	<script type="text/javascript">
		// 투자 차트 데이터 붙이기
		<c:forEach var="invest" items="${investData}">
			investPatch(["${invest.investSeq}", "${invest.investMoney}", 
			      		"${invest.stackOrigin}", "${invest.stackInterest}"]);
		</c:forEach>
		
		// 대출 차트 데이터 붙이기
		<c:forEach var="loan" items="${loanData}">
			loanPatch(["${loan.loanCode}", "${loan.stackRepayOrigin}", 
		      			"${loan.stackRepayRate}", '']);
		</c:forEach>
	</script>
</head>
<body>
	<section class="login-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>회원 상세조회</h1>
					<!-- 개인정보 -->
					<div id="personal-data">
						<span>이름: ${member.name}</span>
						<span>성별: ${member.gender}</span>
						<span>나이: ${member.year}세</span>
						<span>이메일: ${member.email}</span>
						<span>신용등급: ${member.creditRating}등급</span>
						<span>회원분류: 일반회원</span>
					</div>
					
					<!-- 투자정보 -->
					<div id="invest-data">
						<table class="detail-data-table">
							<tr>
								<td>총 투자건수</td>
								<td><b>${investList.size()}</b>건</td>
							</tr>
							<tr>
								<td>상환중인 건수</td>
								<td><b>${payingCount}</b>건</td>
							</tr>
							<tr>
								<td>누적 투자금</td>
								<td><!-- 누적 투자금 계산 -->
									<c:set var="stackInvestMoney" value="0" />
									<c:forEach var="invest" items="${investList}">
										<c:set var="stackInvestMoney" value="${stackInvestMoney + invest.investMoney}" />
									</c:forEach>
									<b><fmt:formatNumber type="number" value="${stackInvestMoney}" /></b>원
								</td>
							</tr>
							<tr>
								<td>총 수익금</td>
								<td><!-- 총 수익금 -->
									<c:set var="totalProfit" value="0" />
									<c:forEach var="investTran" items="${investTranList}">
										<c:set var="totalProfit" value="${totalProfit + investTran.intendProfit}" />
									</c:forEach>
									<b><fmt:formatNumber type="number" value="${totalProfit}" /></b>원
								</td>
							</tr>
						</table>
						
						<!-- 투자 그래프 -->
						<div id="chart_invest"></div>
					</div>
					
					<div id="loan-data">
						<table class="detail-data-table">
							<tr>
								<td>총 대출건수</td>
								<td><b>${loanList.size()}</b>건</td>
							</tr>
							<tr>
								<td>총 승인건수</td>
								<td><!-- 총 승인건수 계산 -->
									<c:set var="auth" value="0"/>
									<c:forEach var="loan" items="${loanList}" varStatus="vs">
										<c:if test="${loan.progress != '심사요청'}">
											<c:set var="auth" value="${vs.count}" />
										</c:if>
									</c:forEach>
									<b>${auth}</b>건
								</td>
							</tr>
							<tr>
								<td>누적 대출금</td>
								<td><!-- 누적 대출금 계산 -->
									<c:set var="stackLoanMoney" value="0" />
									<c:forEach var="loan" items="${loanList}">
										<c:set var="stackLoanMoney" value="${stackLoanMoney + loan.loanMoney}" />
									</c:forEach>
									<b><fmt:formatNumber type="number" value="${stackLoanMoney}" /></b>원
								</td>
							</tr>
							<tr>
								<td>누적 상환금</td>
								<td><!-- 누적 상환금 계산 -->
									<c:set var="stackPayment" value="0" />
									<c:forEach var="loanTran" items="${loanTranList}">
										<c:set var="stackPayment"
										value="${stackPayment + loanTran.stackRepayRate + loanTran.stackRepayOrigin}" />
									</c:forEach>
									<b><fmt:formatNumber type="number" value="${stackPayment}" /></b>원
								</td>
							</tr>
						</table>
						
						<!-- 대출 그래프 -->
						<div id="chart_loan"></div>
					</div>
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