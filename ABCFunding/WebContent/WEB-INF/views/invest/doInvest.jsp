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
					<h1>Investment application</h1>
					
					<!-- Virtual account information -->
					<div class="panel panel-default" id="invest-account-info">
						<div class="panel-heading">My account</div>
						<div class="panel-body">
							<table>
								<tr>
									<td>VANumber:</td>
									<td>${account.accountNo}</td>
								</tr>
								<tr>
									<td>Balance:</td>
									<td>
                                        <span>$ ${account.balance}</span>
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<!-- Investment simulation : Button trigger of modal -->
					<button id="invest-simulation" type="button"
						class="btn btn-default btn-lg glyphicon glyphicon-calendar"
						data-toggle="modal" data-target=".bs-example-modal-lg"
						onclick="checkSimulTable()">Investment simulation</button>

					<!-- Investment simulation : Modal content -->
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
									<h4 class="modal-title" id="myModalLabel">Investment simulation</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<p>
											Max: <strong> $<fmt:formatNumber
													type="currency" value="${investable}" currencySymbol="" />
											</strong>
										</p>
									</div>
									<div class="row">
										<label for="investMoney" class="col-sm-2 control-label">Investment</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="investMoney" name="investMoney"
												placeholder="Enter investment under max">
										</div>
										<div class="col-sm-2">
											<button type="button" class="btn btn-default"
											onclick="startSimulation('${loan.loanDate }')">Calculate</button>
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
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- investment -->
					<form id="invest-form" role="form" action="investSuc.do" method="post">
					<div class="panel panel-default" id="invest-request">
						<div class="panel-body">
							<p>Available: 
								<strong>
                                    $
									<fmt:formatNumber type="currency"
									value="${investable}" currencySymbol="" />
								</strong>
							</p>
							<div class="form-group">
                                <span>$</span>
								<input class="control-form" id="invest-money"
								name="investMoney" type="number"
								min="1" max="${investable}" step="1"
								placeholder="Enter investment"/>
								<input type="hidden" name="loanCode" value="${loan.loanCode}" />
								<input type="hidden" name="title" value="${loan.title}" />
							</div>
						</div>
					</div>
					
					<!-- Invest button -->
					<div id="invest-button2">
						<button class="btn btn-default btn-lg" id="submit-invest">
				        	<span class="glyphicon glyphicon-thumbs-up"></span>Invest
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
    <script src="js/investSimulator.js"></script>
    <script>
    	$(document).ready(function(){
    		$("#submit-invest").click(function(){
    			$("invest-form").submit();
    		});
    	});
    </script>
</body>
</html>