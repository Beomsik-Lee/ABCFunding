<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-white border-bottom shadow-sm">
   <div class="h5 my-0 mr-md-auto font-weight-normal"><a href="main.do" class="text-dark">ABC Funding</a></div>
   <nav class="my-2 my-md-0 mr-md-3">
      <a class="p-2 text-dark" href="intro.do">Features</a>
      <c:if test="${empty login}">
      <a class="p-2 text-dark" href="login.do">Sign in</a>
      </c:if>
      <c:if test="${not empty login}">
      <a class="p-2 text-dark" href="invest.do">Investment</a>
      <a class="p-2 text-dark" href="loan.do">Loan</a>
      <a class="p-2 text-dark" href="myInfo.do">My Info</a>
      <a class="p-2 text-dark" href="logout.do">Sign out</a>
      </c:if>
   </nav>
   <c:if test="${empty login}">
   <a class="btn btn-outline-primary" href="regi.do">Sign up</a>
   </c:if>
</header>
</body>
</html>