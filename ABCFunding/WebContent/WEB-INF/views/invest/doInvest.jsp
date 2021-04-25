<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head></head>
<body>
	<section class="container pt-5">
		<div class="row justify-content-md-center">
			<div class="col-md-auto">
				<h1 class="text-center">Investment application</h1>
				<h3>My account</h3>
                <table class="table table-sm table-borderless">
                    <tbody>
                        <tr>
                            <td class="text-left">VANumber</td>
                            <td class="text-right">${account.accountNo}</td>
                        </tr>
                        <tr>
                            <td class="text-left">Balance</td>
                            <td class="text-right">$${account.balance}</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="button"
                                    class="btn btn-outline-info btn-lg btn-block"
                                    data-toggle="modal" data-target=".bs-example-modal-lg"
                                    onclick="checkSimulTable()">Investment simulation</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <!-- investment -->
                <form id="invest-form" role="form" action="investSuc.do" method="post">
                    <p>Available: 
                        <strong>$${investable}</strong>
                    </p>
                    <div class="form-group">
                        <span>$</span>
                        <input class="control-form"
                        name="investMoney" type="number"
                        min="1" max="${investable}" step="1"
                        placeholder="Enter investment"
                        required="required"/>
                        <input type="hidden" name="loanCode" value="${loan.loanCode}" />
                        <input type="hidden" name="title" value="${loan.title}" />
                    </div>
                
                    <!-- Invest button -->
                    <button class="btn btn-primary btn-lg btn-block" id="submit-invest">Invest</button>
                </form>
                
				<!-- Investment simulation : Modal content -->
				<div class="modal fade bs-example-modal-lg" tabindex="-1"
					role="dialog" aria-labelledby="myLargeModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">Investment simulation</h4>
								<button type="button" class="btn" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
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
										<button type="button" class="btn btn-outline-dark"
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
								<button type="button" class="btn btn-outline-danger"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
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