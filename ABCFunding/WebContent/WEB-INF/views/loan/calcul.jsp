<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<!-- Calcul Section -->
	<section class="calcul-section">
		<table class="calcul-table">
			<tr>
				<td>대출신청금</td>
				<td><input type="text" id="loanMoney" />원</td>
			</tr>
			<tr>
				<td>대출금리</td>
				<td>8%</td>
			</tr>
			<tr>
				<td>대출기간</td>
				<td><select id="loanPeriod">
						<c:forEach var="idx" begin="1" end="12">
							<option value="${idx}">${idx}</option>
						</c:forEach>
						<option value="18">18</option>
						<option value="24">24</option>
				</select>개월</td>
			</tr>
		</table>
		<button onclick="calcul()" class="btn btn-primary">계산하기</button>
		<p id="result"></p>
		<div id="resultDisplay"></div>
	</section>

	<!-- 원리금균등분할상환 계산 java script -->
	<script src="js/calcul.js"></script>

</body>
</html>