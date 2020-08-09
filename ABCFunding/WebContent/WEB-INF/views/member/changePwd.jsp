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
   
    <!-- changePwd-section -->
    <section id="changePwd" class="changePwd-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                  <h1>비밀번호 변경</h1>
                  <br>
                  <br>
                  <form action="pwdAf.do" id="pwdForm" method="post">
                    <table class="changePwd-table">
                      <tr>
                        <td>
                          현재 비밀번호
                        </td>
                        <td>
                          <input type="password" id="currentPwd" >
                        </td>
                      </tr>
                      <tr>
                      	<td colspan="2">
                        	<p id="pwdP" style="text-align:center"></p>
                        </td>
                      </tr>
                      <tr>
                        <td>
                          변경할 비밀번호
                        </td>
                        <td>
                          <input type="password" name="pwd" required="required">
                        </td>
                      </tr>
                      <tr>
                        <td></td>
                      </tr>
                    </table>
                    <a id="pwdAnchor" class="btn btn-primary">비밀번호 변경완료</a>
                    <input type="hidden" id="loginPwd" value="${login.pwd}"/>
                    <input type="hidden" name="email" value="${login.email}"/>
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
    
    <script type="text/javascript">
    	$(document).ready(function(){
    		// 비밀번호 확인 표시
    		var isCorrect = false;
    		$("#currentPwd").keyup(function(){
    			if($(this).val() == $("#loginPwd").val()){
    				$("#pwdP")
    				.text("비밀번호가 일치합니다.")
    				.css("color", "blue");
    				isCorrect = true;
    			} else{
    				$("#pwdP")
    				.text("비밀번호가 일치하지 않습니다.")
    				.css("color", "red");
    				isCorrect = false;
    			}
    		});
    		
    		// 비밀번호 변경을 눌렀을 때 비밀번호가 일치면 변경처리요청
    		$("#pwdAnchor").click(function(){
    			if(isCorrect){
    				alert("비밀번호 변경");
    				$("#pwdForm").submit();
    			}
    		});
    	});
    </script>

</body>
</html>