<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <!-- login Section -->
    <section class="container text-center">
      <h1 class="pt-5">Sign in</h1>
      <div class="row">
          <form class="col-lg-5 my-5 mx-auto text-left" action="loginAf.do" method="post">
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="pwd">
            </div>
            <div class="form-group text-center">
                <c:if test="${isFail}">
                   <p id="isFail"><strong>It's invalid. Check your email or password.</strong></p>
                </c:if>
                <input id="login-button" class="btn btn-primary" type="submit" name="login" value="Sign in">
                <a href="regi.do" class="btn btn-outline-primary">Sign up</a>
                <a href="searchpwd.do" class="btn btn-outline-primary">Find password</a>
            </div>
          </form>
      </div>
    </section>
</body>
</html>