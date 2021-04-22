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

    <title>${title}</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- ABC Funding CSS -->
    <link href="css/abcstyle.css" rel="stylesheet">
</head>
<body>
	<section class="login-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div id="invest-detail-title"><h1>${loan.title}</h1></div>
					
					<!-- Loan Information -->
					<div id="invest-detail-loan-info">
						<h3>Loan Information</h3>
						<table id="invest-detail-table" class="table table-bordered">
							<thead>
								<tr>
									<th>Interest</th>
									<th>A loan</th>
									<th>Repayment method</th>
									<th>Loan period</th>
									<th>Desired repayment date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>6%</td>
									<td>
                                        <span>$</span>
    									<fmt:parseNumber integerOnly="true">
    										${loan.loanMoney}
    									</fmt:parseNumber>
									</td>
									<td>Equivalence of principal and interest</td>
									<td>${loan.loanDate} months</td>
									<td>${loan.repay}</td>
								</tr>
							</tbody>
						</table>
						<!-- Credit information -->
						<div id="invest-detail-credit">
							<h4>Credit rating</h4>
							<img alt="credit"
							src="${pageContext.request.contextPath}/img/credit/number${personal.creditRating}.jpg" 
							width="200" height="150"/>
						</div>
						<!-- Personal information -->
						<div id="invest-detail-personal">
							<h4>Personal information</h4>
							<table>
								<tr>
									<td>Name</td>
									<td>${personal.name}</td>
								</tr>
								<tr>
									<td>Gender</td>
									<td>${personal.gender}</td>
								</tr>
								<tr>
									<td>Age</td>
									<td>${age}</td>
								</tr>
								<tr>
									<td>Salary</td>
									<td>$ ${loan.salary}</td>
								</tr>
							</table>
						</div>
						<!-- Work information -->
						<div id="invest-detail-job">
							<h4>Work information</h4>
							<table>
								<tr>
									<td>Employment type</td>
									<td>${loan.employType}</td>
								</tr>
								<tr>
									<td>Enterprise size</td>
									<td>${loan.scale}</td>
								</tr>
								<tr>
									<td>Term of employment</td>
									<td>${loan.serve} year</td>
								</tr>
							</table>
						</div>
						<!-- Introduction -->
						<div id="invest-detail-intro">
							<h3>Introduction</h3>
							<img alt="intro" src="uploadFile/${loan.fname}" />
							<p>${loan.intro}</p>
						</div>
						<!-- Invest button -->
						<div id="invest-button">
							<!-- Disable if already invested -->
							<c:set var="isAble" value="${isInvested == true ? 'disabled' : ''}" />
							<c:if test="${loan.progress=='Funding Over'}">
								<c:set var="isAble" value="disabled" />
							</c:if>
							<a href="doInvest.do?loanCode=${loan.loanCode}" 
							class="btn btn-default btn-lg ${isAble}">
					        	<span class="glyphicon glyphicon-thumbs-up"></span>Invest
					        </a>
					        <c:if test="${isAble == 'disabled'}">
					        	<h4 style="color: red">You cannot invest to this anymore.</h4>
					        </c:if>
				        </div>
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
</body>
</html>