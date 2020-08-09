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

    <title>title</title>

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
					<h1>회원조회</h1>
					<table class="table table-hover text-center">
						<tr>
							<td>이름</td>
							<td>성별</td>
							<td>총 투자건수</td>
							<td>총 대출건수</td>
							<td>총 후원건수</td>
							<td>신용등급</td>
						</tr>
						<c:forEach var="member" items="${memberList}">
						<tr class="judge-tr" 
							onclick="location.href='memberDetail.do?email=${member.email}'">
							<td>${member.name}</td>
							<td>${member.gender}</td>
							<td>${member.investCount}</td>
							<td>${member.loanCount}</td>
							<td>${member.supportCount}</td>
							<td>${member.creditRating}</td>
						</tr>
						</c:forEach>
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