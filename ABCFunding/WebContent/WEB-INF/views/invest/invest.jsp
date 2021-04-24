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
        <h1 class="pt-5 mb-5 text-center">Investment</h1>
        <div class="row">
            <c:forEach items="${loanList}" var="loan">
                <div class="col-sm-4">
                    <fmt:parseNumber integerOnly="true"
                        var="percent" value="${loan.currentMoney / loan.loanMoney * 100}"/>
                    <div class="card" style="width: 18rem;">
                        <img src="${pageContext.request.contextPath}/uploadFile/${loan.fname}" class="card-img-top" alt="image" height="120">
                        <div class="card-body">
                            <h5 class="card-title">${loan.title}</h5>
                            <table class="table table-sm table-borderless">
                                <tbody>
                                    <tr>
                                        <th class="text-left">Goal</th>
                                        <td class="text-right">$${loan.loanMoney}</td>
                                    </tr>
                                    <tr>
                                        <th class="text-left">Repayment period</th>
                                        <td class="text-right">${loan.loanDate} months</td>
                                    </tr>
                                    <tr>
                                        <th class="text-left">Rate of return</th>
                                        <td class="text-right">6%</td>
                                    </tr>
                                    <tr>
                                        <th class="text-left">Participants</th>
                                        <td class="text-right">${loan.jointCount}</td>
                                    </tr>
                                    <tr>
                                        <th class="text-left">Progress</th>
                                        <td class="text-right">${loan.progress}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="progress mx-0">
                                <div class="progress-bar" role="progressbar"
                                    aria-valuemin="0" aria-valuemax="100" 
                                    style="width:${percent}%" aria-valuenow="${percent}">
                                    ${percent}%
                                </div>
                            </div>
                            <div class="text-center">
                                <a href="investDetail.do?loanCode=${loan.loanCode}" class="btn btn-primary">Detail</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</body>
</html>