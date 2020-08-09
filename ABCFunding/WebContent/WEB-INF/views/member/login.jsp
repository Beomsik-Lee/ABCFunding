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

    <!-- login Section -->
    <section id="login" class="login-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                  <h1>로그인하기</h1>
                  <br>
                  <br>
                  <form action="loginAf.do" method="post">
                    <table class="login-table">
                      <tr>
                        <td>
                          이메일
                        </td>
                        <td>
                          <input type="text" name="email" placeholder="abc@abcfunding.co.kr">
                        </td>
                      </tr>
                      <tr>
                        <td>
                          비밀번호
                        </td>
                        <td>
                          <input type="password" name="pwd">
                        </td>
                      </tr>
                      <tr>
                        <td></td>
                      </tr>
                    </table>
                    <c:if test="${isFail}">
                       <p id="isFail"><strong>로그인 정보가 정확하지 않습니다. 다시 확인바랍니다.</strong></p>
                    </c:if>
                    <input id="login-button" class="btn btn-primary" type="submit" name="login" value="로그인하기">
                    <a href="regi.do" class="btn btn-primary">회원가입하기</a>
                    <a href="searchpwd.do" class="btn btn-primary">비밀번호찾기</a>
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