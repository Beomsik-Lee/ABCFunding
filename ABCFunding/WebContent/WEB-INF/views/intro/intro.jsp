<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <!-- intro Section -->
    <div class="row w-100 h-100 overflow-auto">
        <div class="col-2">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="home-tab" data-toggle="pill" href="#home" role="tab" aria-controls="home" aria-selected="true">Introduce</a>
                <a class="nav-link" id="invest-tab" data-toggle="pill" href="#invest" role="tab" aria-controls="invest" aria-selected="false">Investment Guide</a>
                <a class="nav-link" id="loan-tab" data-toggle="pill" href="#loan" role="tab" aria-controls="loan" aria-selected="false">Loan Guide</a>
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div class="page-header">
                        <h1>What is ABC Funding?</h1>
                    </div>
                    <p>
                        <img src="${pageContext.request.contextPath}/img/intro_bridge.jpg" class="img-rounded" alt="Bridge image">
                    </p>
                    <p>
                        ABC Funding is a P2P financial company
                        that links financing between angel investors
                        and individuals and corporations in need of
                        funds.
                    </p>
                </div>
                <div class="tab-pane fade" id="invest" role="tabpanel" aria-labelledby="invest-tab">
                    <div class="page-header">
                        <h1>Investment Guide</h1>
                    </div>
                    <p>
                        <img
                            src="${pageContext.request.contextPath}/img/investFlow_1.jpg"
                            class="img-rounded" alt="Bridge image">
                    </p>
                </div>
                <div class="tab-pane fade" id="loan" role="tabpanel" aria-labelledby="loan-tab">
                    <div class="page-header">
                        <h1>Loan Guide</h1>
                    </div>
                    <p>
                        <img
                            src="${pageContext.request.contextPath}/img/loanFlow_1.jpg"
                            class="img-rounded" alt="Bridge image">
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>