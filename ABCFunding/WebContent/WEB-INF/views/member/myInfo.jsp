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
		// hide deposit layout
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
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" href="myInfo.do?email=${login.email}" >My information</a>
                        <a class="nav-link" href="myLoanInfoList.do?email=${login.email}">Loan history</a>
                        <a class="nav-link" href="myInfoInvest.do">Investment history</a>
                    </div>
                </div>
				<div class="col-md-9">
					<h4 class="text-center">Personal Information</h4>
					<table class="myInfo-table text-center table table-hover">
						<thead>
							<tr>
								<th class="text-center">Name</th>
								<td><c:out value="${myInfo.name}" /></td>
							</tr>
							<tr>
								<th class="text-center">Gender</th>
								<td><c:out value="${myInfo.gender}" /></td>
							</tr>
							<tr>
								<th class="text-center">Birthday</th>
								<td><c:out value="${myInfo.birth}" /></td>
							</tr>
							<tr>
								<th class="text-center">Credit Rating</th>
								<td><c:out value="${myInfo.creditRating}" /></td>
							</tr>
						</thead>
					</table>
					<br><br>
					<h4 class="text-center">Virtual Account</h4>
					<form action="doDeposit.do" id="deposit-form" method="post">
						<table class="myInfo-table text-center table table-hover">
							<thead>
								<tr>
									<th class="text-center">VA Number</th>
									<td><c:out value="${myInfo.accountNo}" /></td>
								</tr>
								<tr>
									<th class="text-center">Balance</th>
									<td>$<c:out value="${myInfo.balance}" /></td>
								</tr>
								<tr>
									<td colspan="2"  id="depositMoney">
										$<input type="number" name="money" placeholder="Enter deposit money" 
										 value="1" required="required"/>
										<input type="hidden" name="email" value="${login.email}" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<a class="btn btn-default" id="deposit-button">
										Deposit</a>
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
	
	<script type="text/javascript">
		$(document).ready(function(){
			var isClicked = false;
			$("#deposit-button").click(function(){
				if(!isClicked){	// Show layout first
					isClicked = true;
					$("#depositMoney").slideDown("fast");
				} else{
					alert("Deposit : $" + $("#depositMoney").children("input:first").val());
					$("#deposit-form").submit();
				}
			});
			
		});
	</script>

</body>
</html>