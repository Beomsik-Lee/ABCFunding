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
					<h1>투자 신청 완료</h1>
					<div id="invest-wrap-border">
						<div id="invest-suc-wrap">
							<fmt:formatNumber type="currency" var="invest" value="${investMoney}" />
							<span>${login.name}님 '${title}'상품에 대한 <br/>${invest}원 투자가<br/>
							성공적으로 완료되었습니다.</span>
							<a href="main.do" class="btn btn-info btn-lg">홈으로 가기</a>
							<a href="myInfoInvest.do" class="btn btn-success btn-lg">투자내역 확인하러 가기</a>
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