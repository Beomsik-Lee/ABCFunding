<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script src="js/myloaninfo.js"></script>

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

</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body>

	<!-- myInfoloan Section -->
	<section id="myInfoloan" class="myInfo-section">
		<div class="container-fluid">
			<div class="row">

				<!-- myInfoloan SideMenu -->
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><a
							href="myInfo.do?email=${login.email}">내 정보</a></li>
						<li role="presentation"><a href="myLoanInfoList.do?email=${login.email}">나의 대출
								내역</a></li>
						<li role="presentation"><a href="myInfoInvest.do">나의 투자 내역</a></li>
					</ul>
				</div>

				<!-- myInfoloan MainList -->
				<div class="col-md-9">
					<c:forEach var="myloan" items="${loanLists}" varStatus="vs">
						<table class="myLoanTable table table-hover text-center">
							<thead>
								<tr>
									<th>번호</th>
									<th>대출신청금액</th>
									<th>상환예정금</th>
									<th colspan="2">대출신청기간</th>
									<th>진행상태</th>
									<th>대출취소</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value="${vs.count}" /></td>
									<td><fmt:formatNumber>${myloan.loanMoney} </fmt:formatNumber>원</td>
									<td><fmt:formatNumber>${myloan.loanMoney} </fmt:formatNumber>원</td>
									<td colspan="2"><c:out value="${myloan.loanDate}" />개월</td>
									<td><c:set var="judgeResult" value="${myloan.result}" />
										<c:choose>
											<c:when test="${judgeResult eq 0 }">심사결과대기</c:when>
											<c:when test="${judgeResult eq 1 }">${myloan.progress}</c:when>
											<c:otherwise>심사거절</c:otherwise>
										</c:choose></td>
									<td>
										<c:set var="isAble" value="" />
										<c:if test="${myloan.progress eq '상환완료'}">
											<c:set var="isAble" value="disabled" />
										</c:if>
										<a href="loanCancel.do?loanCode=${myloan.loanCode}"
										 class="btn btn-danger ${isAble}">취소</a>
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
									상환스케쥴</button>
							</div>
							<div class="btn-group" role="group">
								<button class="btn btn-default" type="button"
									data-toggle="collapse"
									data-target="#collapseDetail_${vs.count}" aria-expanded="false"
									aria-controls="collapseDetail_${vs.count}">상환상세내역</button>
							</div>
						</div>

						<!-- 각 대출 내역의 상환스케쥴 -->
						<div class="collapse" id="collapse_${vs.count}">
							<div class="well">
								<table class="myLoanTable table table-hover text-center"
									id="loanSubList_${vs.count}">
									<script type="text/javascript">
										var loanMoney = "<c:out value="${myloan.loanMoney}" />";
										var loanDate = "<c:out value="${myloan.loanDate}" />";
										var loanSubList = "loanSubList_"
												+ "<c:out value="${vs.count}" />";
										callLoanSchedule(loanMoney, loanDate,
												loanSubList);
									</script>
								</table>
							</div>
						</div>

						<!-- 각 대출 내역의 상환상세내역 -->
						<div class="collapse" id="collapseDetail_${vs.count}">
							<div class="well">
								<table class="myLoanTable table table-hover text-center">
									<tr>
										<td>회차 수</td>
										<td>누적상환금</td>
										<td>누적상환이자금</td>
										<td>누적상환원금</td>
										<td>진행상황</td>
										<td>회수비율</td>
									</tr>
									<c:forEach var="loanTran" items="${loanTranList}">
									<c:if test="${loanLists.get(vs.count-1).loanCode
													== loanTran.loanCode}">
									<tr>
										<td>${loanTran.round}</td>
										<td>
											<fmt:formatNumber type="number" 
											value="${loanTran.stackRepayRate+loanTran.stackRepayOrigin}" />
										</td>
										<td><fmt:formatNumber type="number" value="${loanTran.stackRepayRate}" /></td>
										<td><fmt:formatNumber type="number" value="${loanTran.stackRepayOrigin}" /></td>
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

	<!-- Scrolling Nav JavaScript -->
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/scrolling-nav.js"></script>

</body>
</html>