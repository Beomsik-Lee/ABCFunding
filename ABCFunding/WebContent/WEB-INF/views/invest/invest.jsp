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

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body>

    <section class="login-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                	<h1>Investment</h1>
					<c:forEach items="${loanList}" var="loan">
						<div class="invest-element"">
						<c:set var="href" value="investDetail.do?loanCode=${loan.loanCode}" />
<%-- 						<c:if test="${loan.progress == 'repaying' || loan.progress== 'repayCom'}"> --%>
<%-- 							<c:set var="href" value="#" /> --%>
<%-- 						</c:if> --%>
						<a class="invest-click" href="${href}">
							<div class="invest-img-hover">
								<span>Detail</span>
							</div>
						    <img class="ivest-img" src="${pageContext.request.contextPath}/uploadFile/${loan.fname}" alt="image" />
						    <!-- Loan information -->
						    <div class="invest-title">${loan.title}</div>
						    <table class="invest-info">
							    <tr>
								    <td>Goal</td>
								    <td>
                                        <span>$</span>
								    	<fmt:parseNumber integerOnly="true">
								    		${loan.loanMoney/10000}
								    	</fmt:parseNumber>
								    </td>
							    </tr>
						       <tr>
						        <td>Repayment period</td>
						        <td>${loan.loanDate}months</td>
						       </tr>
						       <tr>
						        <td>Rate of return</td>
						        <td>6%</td>
						       </tr>
						       <tr>
						        <td>Participants</td>
						        <td>${loan.jointCount}</td>
						       </tr>
					      </table>
					      <!-- Progress bar -->
					      <fmt:parseNumber integerOnly="true"
					      var="percent" value="${loan.currentMoney / loan.loanMoney * 100}"/>
					      <div class="progress">
					          <div class="progress-bar" role="progressbar"
					          aria-valuemin="0" aria-valuemax="100"	
					          style="width:${percent}%" aria-valuenow="${percent}">
					            ${percent}%
					          </div>
					      </div>
					      <!-- progress -->
					      <span class="progress-stat"><strong>${loan.progress}</strong></span>
						</a>
						</div>
					</c:forEach>
                </div>
            </div>
        </div>
    </section>
    
    <script type="text/javascript">
	$(document).ready(function(){
		$("a").mouseenter(function(){
			$(this).children("div:first").stop(true,true);
			$(this).children("div:first").animate({opacity:'0.85'}, 300);
		});
		$("a").mouseleave(function(){
			$(this).children("div:first").stop(true,true);
			$(this).children("div:first").animate({opacity:'0'}, 300);
		});
	
	});
	</script>
   
    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Scrolling Nav JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/scrolling-nav.js"></script>

</body>
</html>