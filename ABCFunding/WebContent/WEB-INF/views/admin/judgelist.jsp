<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	
	<!-- Admin JudgeList Section -->
    <section id="adminJudge" class="adminJudgeList-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                	<h3>심사내역리스트</h3>
                	<table class="adminJudgeList-table table text-center table-hover">
                		<thead>
                			<tr>
	                			<th class="text-center">번호</th>
	                			<th class="text-center">심사상태</th>
	                			<th class="text-center">상품제목</th>
	                			<th class="text-center">대출신청금</th>
	                			<th class="text-center">대출신청인</th>
	                			<th class="text-center">신용등급</th>
                			</tr>
                		</thead>
                		<tbody>
                		<c:forEach var="judge" items="${judgeList}" varStatus="vs">
                			<tr class="judge-tr" onclick="location.href='judgeDetail.do?loanCode=${judge.loanCode}'">
	                			<td><c:out value="${vs.count}"/></td>
	                			<td>심사대기</td>
	                			<td><c:out value="${judge.title}"/></td>
	                			<td><c:out value="${judge.loanMoney}"/>만원</td>
	                			<td><c:out value="${judge.name}"/></td>
	                			<td><c:out value="${judge.creditRating}"/></td>
                			</tr>
                		</c:forEach>
                		</tbody>
                	</table>
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