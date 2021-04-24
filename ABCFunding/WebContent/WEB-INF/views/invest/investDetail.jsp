<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <!-- ABC Funding CSS -->
    <link href="css/abcstyle.css" rel="stylesheet">
</head>
<body>
	<section class="container">
        <h1 class="pt-5 mb-5 text-center">${loan.title}</h1>
        <div class="row">
            <div class="col-lg-12">
                <div>
                    <h2>Loan Information</h2>
                    <table class="table table-bordered mt-4">
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
                </div>
            </div>
        </div>
        <div class="row" style="height: 230px;">
            <div class="col-md-4">
                <div class="card h-100" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Credit Rating</h5>
                        <img alt="credit"
                        src="${pageContext.request.contextPath}/img/credit/number${personal.creditRating}.jpg" 
                        width="200" height="150"/>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Personal Information</h5>
                        <table class="table table-sm table-borderless">
                            <tbody>
                                <tr>
                                    <th class="text-left">Name</th>
                                    <td class="text-right">${personal.name}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Gender</th>
                                    <td class="text-right">${personal.gender}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Age</th>
                                    <td class="text-right">${age}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Salary</th>
                                    <td class="text-right">$${loan.salary}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Personal Information</h5>
                        <table class="table table-sm table-borderless">
                            <tbody>
                                <tr>
                                    <th class="text-left">Name</th>
                                    <td class="text-right">${personal.name}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Gender</th>
                                    <td class="text-right">${personal.gender}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Age</th>
                                    <td class="text-right">${age}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Salary</th>
                                    <td class="text-right">$${loan.salary}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
		<div class="row">
			<div class="col-lg-12">
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
	</section>
</body>
</html>