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
                	<h1>투자하기</h1>
                	
					<!-- 리스트 요소 -->
					<c:forEach items="${loanList}" var="loan">
						<div class="invest-element"">
						<c:set var="href" value="investDetail.do?loanCode=${loan.loanCode}" />
						<c:if test="${loan.progress == '상환중' || loan.progress== '상환완료'}">
							<c:set var="href" value="#" />
						</c:if>
						<a class="invest-click" href="${href}">
							<div class="invest-img-hover">
								<span>자세히 보기</span>
							</div>
						    <img class="ivest-img" src="${pageContext.request.contextPath}/uploadFile/${loan.fname}" alt="이미지" />
						    <!-- 대출정보 텍스트 -->
						    <div class="invest-title">${loan.title}</div>
						    <table class="invest-info">
							    <tr>
								    <td>목표금액</td>
								    <td><!-- 나눗셈 결과를 정수로 변환 -->
								    	<fmt:parseNumber integerOnly="true">
								    		${loan.loanMoney/10000}
								    	</fmt:parseNumber>만원
								    </td>
							    </tr>
						       <tr>
						        <td>상환기간</td>
						        <td>${loan.loanDate}개월</td>
						       </tr>
						       <tr>
						        <td>수익률</td>
						        <td>6%</td>
						       </tr>
						       <tr>
						        <td>참여인원수</td>
						        <td>${loan.jointCount}명</td>
						       </tr>
					      </table>
					      <!-- 진행바 -->
					      <fmt:parseNumber integerOnly="true"
					      var="percent" value="${loan.currentMoney / loan.loanMoney * 100}"/>
					      <div class="progress">
					          <div class="progress-bar" role="progressbar"
					          aria-valuemin="0" aria-valuemax="100"	
					          style="width:${percent}%" aria-valuenow="${percent}">
					            ${percent}%
					          </div>
					      </div>
					      <!-- 진행상태 -->
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