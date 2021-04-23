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

<!-- Repayment Schedule Script -->
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
                        <a class="nav-link active" href="myLoanInfoList.do?email=${login.email}">Loan history</a>
                        <a class="nav-link" href="myInfoInvest.do">Investment history</a>
                    </div>
                </div>

				<!-- myInfoloan MainList -->
				<div class="col-md-9">
					<c:forEach var="myloan" items="${loanLists}" varStatus="vs">
						<table class="myLoanTable table table-hover text-center">
							<thead>
								<tr>
									<th>No.</th>
									<th>A loan</th>
									<th>Repay</th>
									<th colspan="2">Period</th>
<!-- 									<th>Progress</th> -->
									<th>Cancel</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value="${vs.count}" /></td>
									<td>$<fmt:formatNumber>${myloan.loanMoney} </fmt:formatNumber></td>
									<td>$<fmt:formatNumber>${myloan.loanMoney} </fmt:formatNumber></td>
									<td colspan="2"><c:out value="${myloan.loanDate}" /> months</td>
<%-- 									<td><c:set var="judgeResult" value="${myloan.result}" /> --%>
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${judgeResult eq 0 }">Audit wait</c:when> --%>
<%-- 											<c:when test="${judgeResult eq 1 }">${myloan.progress}</c:when> --%>
<%-- 											<c:otherwise>Rejected</c:otherwise> --%>
<%-- 										</c:choose></td> --%>
									<td>
										<c:set var="isAble" value="" />
										<c:if test="${myloan.progress eq 'Complete'}">
											<c:set var="isAble" value="disabled" />
										</c:if>
										<a href="loanCancel.do?loanCode=${myloan.loanCode}"
										 class="btn btn-danger ${isAble}">Cancel</a>
									</td>
								</tr>
							</tbody>
						</table>

						<div class="btn-group btn-group-justified" role="group"
							aria-label="...">
							<div class="btn-group" role="group">
								<button class="btn btn-default" type="button"
									data-toggle="collapse" data-target="#collapse_${vs.count}"
									aria-expanded="false" aria-controls="collapse_${vs.count}">
									Repayment Schedule</button>
							</div>
							<div class="btn-group" role="group">
								<button class="btn btn-default" type="button"
									data-toggle="collapse"
									data-target="#collapseDetail_${vs.count}" aria-expanded="false"
									aria-controls="collapseDetail_${vs.count}">Repayment Details</button>
							</div>
						</div>

						<!-- Repayment Schedule -->
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
                                        var interestRate = 0.08;
                                        var loanMoney = ${myloan.loanMoney};
                                        var loanDate = ${myloan.loanDate};
                                        calc(loanMoney, interestRate, loanDate);
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

						<!-- Repayment details -->
						<div class="collapse" id="collapseDetail_${vs.count}">
							<div class="well">
								<table class="myLoanTable table table-hover text-center">
									<tr>
										<td>Rounds</td>
										<td>Repayments</td>
										<td>Interest</td>
										<td>Principal</td>
										<td>Progress</td>
										<td>Recovery</td>
									</tr>
									<c:forEach var="loanTran" items="${loanTranList}">
									<c:if test="${loanLists.get(vs.count-1).loanCode
													== loanTran.loanCode}">
									<tr>
										<td>${loanTran.round}</td>
										<td>
											$<fmt:formatNumber type="number" 
											value="${loanTran.stackRepayRate+loanTran.stackRepayOrigin}" />
										</td>
										<td>$<fmt:formatNumber type="number" value="${loanTran.stackRepayRate}" /></td>
										<td>$<fmt:formatNumber type="number" value="${loanTran.stackRepayOrigin}" /></td>
										<td>${loanTran.progress}</td>
										<td>${loanTran.collectRate}%</td>
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


</body>
</html>