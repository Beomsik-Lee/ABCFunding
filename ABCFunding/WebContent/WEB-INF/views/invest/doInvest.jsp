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
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<section class="login-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h1>투자신청</h1>
					
					<!-- 안내문 -->
					<div id="invest-attention">
						<h2>안내문</h2>
						<div id="invest-attention123">
							<p>1. 세금 : 세법에 의거하여 이자소득에 대해 25%(이자소득세) + 2.5%(주민세) = 총 27.5% 의 세금을 납부합니다.</p>
							<p>2. 수수료 : 2%</p>
							<p>3. 투자위험안내 : 당사는 원금 및 수익을 보장하지 않습니다. 다만, 채권 추심에 도의적 책임을 다합니다.</p>
						</div>
					</div>
					
					<!-- 가상계좌정보 -->
					<div class="panel panel-default" id="invest-account-info">
						<div class="panel-heading">나의 계좌정보</div>
						<div class="panel-body">
							<table>
								<tr>
									<td>가상계좌번호:</td>
									<td>${account.accountNo}</td>
								</tr>
								<tr>
									<td>계좌잔액:</td>
									<td><!-- 통화단위로 형식변경 -->
										<fmt:formatNumber type="currency"
										value="${account.balance}" />원
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<!-- 투자시뮬레이션 Button trigger of modal -->
					<button id="invest-simulation" type="button"
						class="btn btn-default btn-lg glyphicon glyphicon-calendar"
						data-toggle="modal" data-target=".bs-example-modal-lg"
						onclick="checkSimulTable()">투자시뮬레이션</button>

					<!-- 투자시뮬레이션 Modal content -->
					<div class="modal fade bs-example-modal-lg" tabindex="-1"
						role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">투자시뮬레이션</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<p>
											최대 투자가능 금액: <strong> <fmt:formatNumber
													type="currency" value="${investable}" />원
											</strong>
										</p>
									</div>
									<div class="row">
										<label for="investMoney" class="col-sm-2 control-label">투자금</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="investMoney" name="investMoney"
												placeholder="최대 투자가능 금액 이하로 입력해주세요.">
										</div>
										<div class="col-sm-2">
											<button type="button" class="btn btn-default"
											onclick="startSimulation('${loan.loanDate }')">계산하기</button>
										</div>
									</div>
									<div class="row">
										<div id="investTableBox">
											<table id="investTable" class="myLoanTable table table-hover text-center">
											</table>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">닫기</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- 투자신청금 -->
					<form id="invest-form" role="form" action="investSuc.do" method="post">
					<div class="panel panel-default" id="invest-request">
						<div class="panel-body">
							<p>최대 투자가능 금액: 
								<strong>
									<fmt:formatNumber type="currency"
									value="${investable}"/>원
								</strong>
							</p>
							<div class="form-group">
								<input class="control-form" id="invest-money"
								name="investMoney" type="number"
								min="1" max="${investable/10000}" step="1"
								placeholder="투자신청금 입력"/>만원
								<input type="hidden" name="loanCode" value="${loan.loanCode}" />
								<input type="hidden" name="title" value="${loan.title}" />
							</div>
						</div>
					</div>
					
					<!-- 투자하기 버튼 -->
					<div id="invest-button2">
						<button class="btn btn-default btn-lg" id="submit-invest">
				        	<span class="glyphicon glyphicon-thumbs-up"></span>투자신청
				        </button>
			        </div>
			        </form>
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
    <script src="js/investSimulator.js"></script>
    <script>
    	$(document).ready(function(){
    		// 신청버튼을 눌렀을 때 서브밋
    		$("#submit-invest").click(function(){
    			$("invest-form").submit();
    		});
    	});
    </script>
</body>
</html>