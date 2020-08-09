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
					<h1>회원탈퇴</h1>
					<!-- 리스트가 비어 있지 않다면 회원탈퇴 거부 표시 -->
					<c:if test="${not empty remainList}">
						<img alt="회원탈퇴 거부" src="img/deny.jpg" />
						<h3 style="color:red">아직 상환해야 할 대출이 있습니다.</h3>
						<p>상환해야 할 건수는 <strong>${remainList.size()}</strong>개입니다.</p>
					</c:if>
					
					<!-- 리스트가 비어 있으면 회원탈퇴 가능 -->
					<c:if test="${empty remainList}">
						<h4>탈퇴를 위해 비밀번호를 입력해 주세요.</h4>
						<form action="dropMemberAf.do" method="post" id="drop-form">
							<table id="drop-table">
								<tr>
									<td>
										<input class="form-control" name="pwd" 
										placeholder="비밀번호 입력" type="password"/>
										<span id="drop-pwd-check"></span>
									</td>
								</tr>
								<tr>
									<td>
										<input type="hidden" id="loginPwd" value="${login.pwd}" />
										<a class="btn btn-danger btn-lg">회원탈퇴</a>
									</td>
								</tr>
							</table>
						</form>
					</c:if>
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
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		// 비밀번호 확인 표시
    		var isCorrect = false;
    		$(".form-control").keyup(function(){
    			if($(this).val() == $("#loginPwd").val()){
    				$("#drop-pwd-check")
    				.text("비밀번호가 일치합니다.")
    				.css("color", "blue");
    				isCorrect = true;
    			} else{
    				$("#drop-pwd-check")
    				.text("비밀번호가 일치하지 않습니다.")
    				.css("color", "red");
    				isCorrect = false;
    			}
    		});
    		
    		// 비밀번호 변경을 눌렀을 때 비밀번호가 일치면 변경처리요청
    		$(".btn").click(function(){
    			if(isCorrect){
    				alert("회원탈퇴 합니다.");
    				$("#drop-form").submit();
    			}
    		});
    	});
    </script>
</body>
</html>