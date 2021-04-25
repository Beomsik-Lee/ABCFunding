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
        <div class="row mb-5" style="height: 230px;">
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
                        <h5 class="card-title">Work information</h5>
                        <table class="table table-sm table-borderless">
                            <tbody>
                                <tr>
                                    <th class="text-left">Employment type</th>
                                    <td class="text-right">${loan.employType}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Enterprise size</th>
                                    <td class="text-right">${loan.scale}</td>
                                </tr>
                                <tr>
                                    <th class="text-left">Term of employment</th>
                                    <td class="text-right">${loan.serve} year</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div>
                    <h2>Introduction</h2>
                    <img class="img-fluid" alt="intro" src="uploadFile/${loan.fname}" />
                    <p>${loan.intro}</p>
                    <div class="text-center">
                        <!-- Disable if already invested -->
                        <c:set var="isAble" value="${isInvested == true ? 'disabled' : ''}" />
                        <c:if test="${loan.progress=='Funding Over'}">
                            <c:set var="isAble" value="disabled" />
                        </c:if>
                        <a href="doInvest.do?loanCode=${loan.loanCode}" class="btn btn-primary btn-lg ${isAble}">Invest</a>
                        <c:if test="${isAble == 'disabled'}">
                            <h4 style="color: red">You cannot invest this any more.</h4>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
	</section>
</body>
</html>