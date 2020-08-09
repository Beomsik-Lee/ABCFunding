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

<body>

	<!-- Loan Section -->
	<section id="loan" class="loan-section">
		<div class="container">
            <div class="row">
                <div class="col-lg-12">
                  <h1>대출 신청하기</h1>
                  <br>
                  <br>
                  
                  <form action="loanAf.do" method="post" enctype="multipart/form-data">
                  	<input type="hidden" name="email" value="${login.email}">
                    <table class="loan-table">
                      <tr>
                      	<td colspan="2">개인정보</td>
                      </tr>
                      <tr>
                        <td>
                          이름
                        </td>
                        <td>
                          <input type="text" name="name" value="${login.name}" readonly>
                        </td>
                      </tr>
                      <tr>
                        <td>
                          생년월일
                        </td>
                        <td>
                          <input type="text" name="birth" value="${login.birth}" readonly>
                        </td>
                      </tr>
                      <tr>
                        <td>
                          성별
                        </td>
                        <td>
                          <input type="text" name="gender" value="${login.gender}" readonly>
                        </td>
                      </tr>
                      
                      
                      <tr>
                      	<td colspan="2">
                      	  직장정보
                      	</td>
                      </tr>
                      <tr>
                      	<td>
                      		고용형태
                      	</td>
                      	<td>
                      		<select name="employType">
                      			<option value="정규직">정규직</option>
                      			<option value="비정규직">비정규직</option>
                      			<option value="아르바이트">아르바이트</option>
                      			<option value="무직">무직</option>
                      		</select>
                      	</td>
                      </tr>
                      <tr>
                      	<td>직장규모</td>
                      	<td>
                      		<select name="scale">
                      			<option value="대기업">대기업</option>
                      			<option value="중견기업">중견기업</option>
                      			<option value="중소기업">중소기업</option>
                      			<option value="스타트업">스타트업</option>
                      		</select>
                      	</td>
                      </tr>
                      <tr>
                      	<td>재직기간</td>
                      	<td>
                      		<select name="serve">
                      			<c:forEach var="idx" begin="1" end="50" step="1">
                      			<option value="${idx}">${idx}</option>
                      			</c:forEach>
                      		</select>년
                      	</td>
                      </tr>
                      <tr>
                      	<td>연봉</td>
                      	<td>
                      		<input type="text" name="salary" required="required">만원
                      	</td>
                      </tr>
                      <tr>
                      	<td colspan="2">대출신청 정보</td>
                      </tr>
                      <tr>
                      	<td>금리</td>
                      	<td>
                      		<input type="text" name="interestRate" value="8" readonly>%
                      	</td>
                      </tr>
                      <tr>
                      	<td>상환방식</td>
                      	<td>
                      		<input type="text" name="repayType" value="원리금균등상환" readonly>
                      	</td>
                      </tr>
                      <tr>
                      	<td>대출구분</td>
                      	<td>
                      		<input type="text" name="loanType" value="개인신용대출" readonly>
                      	</td>
                      </tr>
                      <tr>
                      	<td>대출신청금</td>
                      	<td>
                      		<input type="number" name="loanMoney"
                      		required="required" max="50000000">원
                      	</td>
                      </tr>
                      <tr>
                      	<td>대출기간</td>
                      	<td><select name="loanDate">
							<c:forEach var="idx" begin="1" end="12">
								<option value="${idx}">${idx}</option>
							</c:forEach>
								<option value="18">18</option>
								<option value="24">24</option>
							</select>개월
                      	</td>
                      </tr>
                      <tr>
                      	<td>희망상환일</td>
                      	<td>
                      	<select name="repay">
                      		<option value="1">1</option>
								<c:forEach var="idx" begin="5" end="25" step="5">
							<option value="${idx}">${idx}</option>
								</c:forEach>
						</select>일
                      	</td>
                      </tr>
                      
                      <tr>
                      	<td colspan="2">소개글</td>
                      </tr>
                      <tr>
                      	<td>제목</td>
                      	<td>
                      		<input type="text" name="title" size="50" required="required">
                      	</td>
                      </tr>
                      <tr>
                      	<td>내용</td>
                      	<td>
                      		<textarea name="intro" cols="52" rows="10" required="required"></textarea>
                      	</td>
                      </tr>
                      <tr>
                      	<td>이미지파일</td>
                      	<td>
                      		<input type="file" name="uploadfile" required="required">
                      	</td>
                      </tr>
                      <tr>
                      	<td>펀딩기간</td>
                      	<td>
                      	<select name="expiryDate">
							<c:forEach var="idx" begin="1" end="7" step="1">
								<option value="${idx}">${idx}</option>
							</c:forEach>
						</select>일
                      	</td>
                      </tr>
                    </table>
                    <input id="loan-button" class="btn btn-primary" type="submit" name="loan" value="대출신청하기">
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