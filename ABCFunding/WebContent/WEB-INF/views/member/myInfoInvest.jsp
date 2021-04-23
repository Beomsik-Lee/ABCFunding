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
    
<!-- 상환스케쥴 스크립트 -->
<script src="js/investCal.js"></script>

</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body>

	<!-- myInfoloan Section -->
	<section id="myInfoloan" class="myInfo-section">
		<div class="container-fluid">
			<div class="row">

				<!-- myInfoloan SideMenu -->
				<div class="col-md-3">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link" href="myInfo.do?email=${login.email}" >My information</a>
                        <a class="nav-link" href="myLoanInfoList.do?email=${login.email}">Loan history</a>
                        <a class="nav-link active" href="myInfoInvest.do">Investment history</a>
                    </div>
                </div>

				<!-- myInfoInvest MainList -->
				<div class="col-md-9">
					<!-- Loan list -->
					<c:forEach var="myloan" items="${loanList}" varStatus="vs">
						<table class="myLoanTable table table-hover text-center">
							<thead>
								<tr>
									<th>No</th>
									<th>Product</th>
									<th>Goal</th>
									<th>Investment</th>
									<th>Period</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value="${vs.count}" /></td>
									<td>${myloan.title}</td>
									<td>${myloan.loanMoney}</td>
									<td>$${investList.get(vs.count-1).getInvestMoney()}</td>
									<td>${myloan.loanDate} months</td>
									<td>${myloan.repay}</td>
								</tr>
							</tbody>
						</table>
						
						<div class="btn-group btn-group-justified" role="group"
							aria-label="...">
							<div class="btn-group" role="group">
								<button class="btn btn-default" type="button"
									data-toggle="collapse" data-target="#collapse_${vs.count}"
									aria-expanded="false" aria-controls="collapse_${vs.count}">
									Repayments Schedule</button>
							</div>
							<div class="btn-group" role="group">
								<button class="btn btn-default" type="button"
									data-toggle="collapse"
									data-target="#collapseDetail_${vs.count}" aria-expanded="false"
									aria-controls="collapseDetail_${vs.count}">Repayments Details</button>
							</div>
						</div>

						<!-- Each Repayments Schedules -->
						<div class="collapse" id="collapse_${vs.count}">
							<div class="well">
								<!-- Initialize Schedule -->
								<script>init();</script>
								<table class="myLoanTable table table-hover text-center">
									<tr>
										<td>Rounds</td>
                                        <td>Repayments</td>
                                        <td>Principal</td>
                                        <td>Interest</td>
                                        <td>Accumulate Principal</td>
                                        <td>Balance</td>
									</tr>
									<c:forEach begin="1" end="${myloan.loanDate}" step="1" varStatus="idx">
									<!-- Calculate repayments -->
									<script>
										var investMoney = ${investList.get(vs.count-1).getInvestMoney()};
										var interestRate = 0.06;
										var loanDate = ${myloan.loanDate};
										calc(investMoney, interestRate, loanDate);
									</script>
									<tr>
										<td>${idx.count}</td>
										<td><script>getPayment();</script></td>
										<td><script>getOrigin();</script></td>
										<td><script>getInterest();</script></td>
										<td><script>getStackOrigin();</script></td>
										<td><script>getBalance();</script></td>
									</tr>
									</c:forEach>
								</table>
							</div>
						</div>

						<!-- Repayments Details -->
						<div class="collapse" id="collapseDetail_${vs.count}">
							<div class="well">
								<table class="myLoanTable table table-hover text-center">
									<tr>
										<td>Rounds</td>
										<td>Progress</td>
										<td>Profit</td>
										<td>Accumulated</td>
										<td>Recovery</td>
									</tr>
									<c:forEach var="investTran" items="${investTranList}">
									<c:if test="${investList.get(vs.count-1).getInvestSeq()
													== investTran.getInvestSeq()}">
									<tr>
										<td>${investTran.round}</td>
										<td>${investTran.progress}</td>
										<td>$<fmt:formatNumber type="number" value="${investTran.intendProfit}" /></td>
										<td>$<fmt:formatNumber type="number" value="${investTran.stackCollect}" /></td>
										<td>${investTran.collectRate}%</td>
									</tr>
									</c:if>
									</c:forEach>
								</table>
							</div>
						</div>
						<br>
						<br>

					</c:forEach>
				</div>
			</div>
		</div>
	</section>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Scrolling Nav JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/scrolling-nav.js"></script>
	
</body>
</html>