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

   <!-- 비밀번호 일치 여부 확인  -->
   <script type="text/javascript">
      function pwdCheck() {
         var tempPwd1 = document.getElementById("pwd1").value;  //첫번째 입력한 비밀번호
         var tempPwd2 = document.getElementById("pwd2").value;  //두번째 입력한 비밀번호
         
         if (tempPwd2 == "") {
            document.getElementById("notCorrect").innerHTML = "비밀번호를 입력해주세요.";
            document.getElementById("correct").innerHTML = "";
         } else if (tempPwd1 != tempPwd2) {
            document.getElementById("notCorrect").innerHTML = "입력하신 비밀번호가 서로 일치하지 않습니다. 다시 확인바랍니다.";
            document.getElementById("correct").innerHTML = "";
         } else {
            document.getElementById("notCorrect").innerHTML = "";
            document.getElementById("correct").innerHTML = "입력하신 비밀번호가 서로 일치합니다.";
         }
      }
   </script>
   
   
    <!-- changePwd-section -->
    <section id="changePwd" class="changePwd-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                  <h1>비밀번호 변경</h1>
                  <br>
                  <br>
                  <form action="pwdAfAuthAf.do" method="post">
                    <table class="changePwd-table">
                      <tr>
                        <td>
                          변경할 비밀번호
                        </td>
                        <td>
                          <input type="password" id="pwd1">
                        </td>
                      </tr>
                      <tr>
                        <td>
                          비밀번호 재입력
                        </td>
                        <td>
                          <input type="password" id="pwd2" name="pwd" onkeyup="pwdCheck()">
                        </td>
                      </tr>
                      <tr>
                        <td></td>
                      </tr>
                    </table>
                    <p id="notCorrect"></p>
                    <p id="correct"></p>
                    <br>
                    <input id="changePwd-button" class="btn btn-primary" type="submit" name="chPwd" value="비밀번호 변경완료">
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