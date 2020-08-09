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

    <!-- Custom CSS -->
    <link href="css/scrolling-nav.css" rel="stylesheet">

    <!-- ABC Funding CSS -->
    <link href="css/abcstyle.css" rel="stylesheet">
</head>
<body>
	<section class="login-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div id="invest-detail-title"><h1>${loan.title}</h1></div>
					<!-- 심사평 -->
					<div class="panel panel-default">
						<div class="panel-heading">심사평</div>
						<div class="panel-body">${judge.commentary}</div>
					</div>
					
					<!-- 대출신청현황 -->
					<div id="invest-detail-loan-info">
						<h3>대출신청현황</h3>
						<table id="invest-detail-table" class="table table-bordered">
							<thead>
								<tr>
									<th>금리</th>
									<th>대출신청금</th>
									<th>대출상환방식</th>
									<th>대출기간</th>
									<th>희망상환일</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>6%</td>
									<td>
									<fmt:parseNumber integerOnly="true">
										${loan.loanMoney/10000}
									</fmt:parseNumber>만원
									</td>
									<td>${loan.repayType}</td>
									<td>${loan.loanDate}개월</td>
									<td>${loan.repay}일</td>
								</tr>
							</tbody>
						</table>
						<!-- 신용정보 -->
						<div id="invest-detail-credit">
							<h4>신 용 등 급</h4>
							<img alt="신용등급"
							src="${pageContext.request.contextPath}/img/credit/number${personal.creditRating}.jpg" 
							width="200" height="180"/>
						</div>
						<!-- 개인정보 -->
						<div id="invest-detail-personal">
							<h4>개 인 정 보</h4>
							<table>
								<tr>
									<td>이름</td>
									<td>${personal.name}</td>
								</tr>
								<tr>
									<td>성별</td>
									<td>${personal.gender}</td>
								</tr>
								<tr>
									<td>나이</td>
									<td>${age}세</td>
								</tr>
								<tr>
									<td>연소득</td>
									<td>${loan.salary}만원</td>
								</tr>
							</table>
						</div>
						<!-- 직장정보 -->
						<div id="invest-detail-job">
							<h4>직 장 정 보</h4>
							<table>
								<tr>
									<td>고용형태</td>
									<td>${loan.employType}</td>
								</tr>
								<tr>
									<td>회사규모</td>
									<td>${loan.scale}</td>
								</tr>
								<tr>
									<td>재직기간</td>
									<td>${loan.serve}년</td>
								</tr>
							</table>
						</div>
						<!-- 소개글 -->
						<div id="invest-detail-intro">
							<h3>소개글</h3>
							<img alt="소개글 이미지" src="uploadFile/${loan.fname}" />
							<p>${loan.intro}</p>
						</div>
						<!-- 투자하기 버튼 -->
						<div id="invest-button">
							<!-- 이미 투자했다면 투자버튼을 비활성화 -->
							<c:set var="isAble" value="${isInvested == true ? 'disabled' : ''}" />
							<c:if test="${loan.progress=='펀딩완료'}">
								<c:set var="isAble" value="disabled" />
							</c:if>
							<a href="doInvest.do?loanCode=${loan.loanCode}" 
							class="btn btn-default btn-lg ${isAble}">
					        	<span class="glyphicon glyphicon-thumbs-up"></span>투자하기
					        </a>
					        <c:if test="${isAble == 'disabled'}">
					        	<h4 style="color: red">투자하실 수 없습니다.</h4>
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
    <script src="js/scrolling-nav.js"></script>
</body>
</html>