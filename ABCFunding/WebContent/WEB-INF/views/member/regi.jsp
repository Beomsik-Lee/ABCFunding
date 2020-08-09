<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

    <!-- Regi Section -->
    <section id="addmember" class="addmember-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1>회원가입</h1>
                    <br>
                    <br>
                    <form action="addMember.do" method="post">
                    <table class="addmember-table">
                      <tr>
                        <td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
                        <td><input type="text" name="name" required="required"/></td>
                      </tr>
                      <tr>
                        <td>이&nbsp;&nbsp;메&nbsp;&nbsp;일</td>
                        <td><input type="text" name="email1" required="required"/>
                        	@
                        	<input type="text" name="email2" required="required"/></td>
                      </tr>
                      <tr>
                        <td>비밀번호</td>
                        <td><input type="password" name="pwd" required="required"/></td>
                      </tr>
                      <tr>
                        <td>생년월일</td>
                        <td>
                           <select name="year">
                               <c:forEach var="idx" begin="1964" end="2004">
                                   <option value="${idx}">${idx}</option>
                                </c:forEach>
                           </select>년
                           <select name="month">
                        <c:forEach var="idx" begin="1" end="12">
                           <c:set var="cnt" value="0${idx}" />
                           <option value="${idx<10 ? cnt : idx}">
                           ${idx<10 ? cnt : idx}</option>
                        </c:forEach>
                           </select>월
                           <select name="day">
                                <c:forEach var="idx" begin="1" end="31">
                           <c:set var="cnt" value="0${idx}" />
                           <option value="${idx<10 ? cnt : idx}">
                           ${idx<10 ? cnt : idx}</option>
                        </c:forEach>
                           </select>일
                        </td>
                      </tr>
                      <tr>
                        <td>성&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;별
                          <td>
                            <input type="radio" checked="checked" name="gender" value="남자" />
                               남자&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" name="gender" value="여자" />여자
                          </td>
                      </tr>
                  <tr>
                  <td></td>
                  <td><input type="submit" class="btn btn-primary" name="addmember" value="회원가입하기" />&nbsp;&nbsp;&nbsp;
                  <input type="reset" class="btn btn-primary" name="reset" value="리셋" /></td>
                  </tr>
                  </table>
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