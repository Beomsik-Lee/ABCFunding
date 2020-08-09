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
    
<script type="text/javascript">
	$(document).ready(function(){
		// 입금란 숨기기
		$("#depositMoney").hide();
	});
</script>

</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body>

	<!-- myInfo Section -->
	<section id="myInfo" class="myInfo-section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><a href="myInfo.do?email=${login.email}">내 정보</a></li>
						<li role="presentation"><a href="myLoanInfoList.do?email=${login.email}">나의 대출 내역</a></li>
						<li role="presentation"><a href="myInfoInvest.do">나의 투자 내역</a></li>
					</ul>
				</div>
				<div class="col-md-9">
					<h4 class="text-center">개인 기본정보</h4>
					<table class="myInfo-table text-center table table-hover">
						<thead>
							<tr>
								<th class="text-center">이름</th>
								<td><c:out value="${myInfo.name}" /></td>
							</tr>
							<tr>
								<th class="text-center">성별</th>
								<td><c:out value="${myInfo.gender}" /></td>
							</tr>
							<tr>
								<th class="text-center">생년월일</th>
								<td><c:out value="${myInfo.birth}" /></td>
							</tr>
							<tr>
								<th class="text-center">신용등급</th>
								<td><c:out value="${myInfo.creditRating}" /></td>
							</tr>
							<tr>
								<td><input class="btn btn-default" type="button"
									value="비밀번호 변경" onclick="location.href='changePwd.do'"></td>
								<td><input class="btn btn-default" type="button"
									value="회원탈퇴" onclick="location.href='dropMember.do'"></td>
							</tr>
						</thead>
					</table>
					<br><br>
					<h4 class="text-center">가상 계좌정보</h4>
					<form action="doDeposit.do" id="deposit-form" method="post">
						<table class="myInfo-table text-center table table-hover">
							<thead>
								<tr>
									<th class="text-center">가상계좌번호</th>
									<td><c:out value="${myInfo.accountNo}" /></td>
								</tr>
								<tr>
									<th class="text-center">잔액</th>
									<td><c:out value="${myInfo.balance}" />원</td>
								</tr>
								<tr>
									<td colspan="2"  id="depositMoney">
										<input type="number" name="money" placeholder="입금액수 입력" 
										 value="1" required="required"/>원
										<input type="hidden" name="email" value="${login.email}" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<a class="btn btn-default" id="deposit-button">
										예치금 입금하기</a>
									</td>
								</tr>
							</thead>
						</table>
					</form>
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
	
	<script type="text/javascript">
		$(document).ready(function(){
			// 입금버튼 제어를 위한 변수
			var isClicked = false;
			$("#deposit-button").click(function(){
				if(!isClicked){	// 처음은 입력란을 보여준다.
					isClicked = true;
					$("#depositMoney").slideDown("fast");
				} else{	// 입금요청을 위한 서브밋
					alert($("#depositMoney").children("input:first").val()+
							"원 입금합니다.");
					$("#deposit-form").submit();
				}
			});
			
		});
	</script>

</body>
</html>