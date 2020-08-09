<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


	<!-- Admin JudgeDetail Section -->
    <section id="adminJudge" class="adminJudgeDetail-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                	<h3 class="text-center">심사상세내용</h3>
                	<br>
	    	        <br>
    	    	    <br>
                	<table class="adminJudgeList-table table text-center">
                		<thead>
                			<tr>
	                			<th class="text-center">심사상태</th>
	                			<th class="text-center">상품제목</th>
	                			<th class="text-center">대출신청금</th>
	                			<th class="text-center">대출신청인</th>
	                			<th class="text-center">신용등급</th>
                			</tr>
                		</thead>
                		<tbody>
                			<tr>
	                			<td>심사대기</td>
	                			<td><c:out value="${judgeDetail.title}"/></td>
	                			<td><c:out value="${judgeDetail.loanMoney}"/>만원</td>
	                			<td><c:out value="${judgeDetail.name}"/></td>
	                			<td><c:out value="${judgeDetail.creditRating}"/></td>
                			</tr>
                		</tbody>
                	</table>
               	</div>	
            </div>
            <br>
            <br>
            <br>
            <div class="row">
                <div class="col-lg-6">
                <h4 class="text-center">대출신청정보</h4>
                <table id="judgeDetail"class="adminJudgeDetail-table text-center">
               		<thead>
                		<tr>
	               			<th class="text-center">금리</th>
	               			<td><c:out value="${judgeDetail.interestRate}"/>%</td>
                		</tr>
                		<tr>
	               			<th class="text-center">상환방식</th>
	               			<td><c:out value="${judgeDetail.repayType}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">대출구분</th>
	               			<td><c:out value="${judgeDetail.loanType}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">대출기간</th>
	               			<td><c:out value="${judgeDetail.loanDate}"/>개월</td>
                		</tr>
                		<tr>
	               			<th class="text-center">희망상환일</th>
	               			<td>매월&nbsp;<c:out value="${judgeDetail.repay}"/>일</td>
                		</tr>
                	</thead>
                </table>
                </div>
                <div class="col-lg-6">
                <h4 class="text-center">개인정보</h4>
                <table id="judgeDetail"class="adminJudgeDetail-table text-center">
               		<thead>
                		<tr>
	               			<th class="text-center">이름</th>
	               			<td><c:out value="${judgeDetail.name}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">성별</th>
	               			<td><c:out value="${judgeDetail.gender}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">생년월일</th>
	               			<td><c:out value="${judgeDetail.birth}"/></td>
                		</tr>
                	</thead>
                </table>
                </div>
            </div>
            <br>
            <br>
            <br>
            <div class="row">
            	<div class="col-lg-6">
            	<h4 class="text-center">직장정보</h4>
            	<table id="judgeDetail"class="adminJudgeDetail-table text-center">
               		<thead>
                		<tr>
	               			<th class="text-center">고용형태</th>
	               			<td><c:out value="${judgeDetail.employType}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">회사규모</th>
	               			<td><c:out value="${judgeDetail.scale}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">재직기간</th>
	               			<td><c:out value="${judgeDetail.serve}"/>년</td>
                		</tr>
                		<tr>
	               			<th class="text-center">연소득</th>
	               			<td><c:out value="${judgeDetail.salary}"/>만원</td>
                		</tr>
                	</thead>
                </table>
            	</div>
            	<div class="col-lg-6">
            	<h4 class="text-center">소개글</h4>
            	<table id="judgeDetail" class="adminJudgeDetail-table text-center">
               		<thead>
                		<tr>
	               			<th class="text-center">제목</th>
	               			<td><c:out value="${judgeDetail.title}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">소개글</th>
	               			<td><c:out value="${judgeDetail.intro}"/></td>
                		</tr>
                		<tr>
	               			<th class="text-center">이미지</th>
	               			<td><img src="${pageContext.request.contextPath}/uploadFile/${judgeDetail.fname}" 
	               			alt="소개글의 이미지" width="300px" height="200px"/></td>
                		</tr>
                	</thead>
                </table>
            	</div>
            </div>
			<br>            
			<br>
			<br>
            <div class="row">
            	<div class="col-lg-12">
            	<form action="judgeDetailAf.do" method="post">
            		<div class="form-group">
            			<input type="hidden" name="loanCode" value="${judgeDetail.loanCode}">
            			<input type="hidden" name="email" value="${judgeDetail.email}">
         	   			<h4>심사평</h4>
            			<textarea name="commentary" class="form-control" rows="5" placeholder="심사평을 입력해주세요.">
            			</textarea>
            		</div>
            		<div class="form-group">
            			<h4>심사결과</h4>
	            		<select name="result" class="form-control">
						  <option value="1">승인</option>
						  <option value="-1">거절</option>
						</select>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default col-md-4 col-md-offset-4">심사완료</button>
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
    
</body>
</html>