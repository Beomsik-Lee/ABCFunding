<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-white border-bottom shadow-sm">
   <h5 class="my-0 mr-md-auto font-weight-normal"><a href="#" class="text-dark">ABC Funding</a></h5>
   <nav class="my-2 my-md-0 mr-md-3">
      <a class="p-2 text-dark" href="#">Features</a>
      <a class="p-2 text-dark" href="#">Investment</a>
      <a class="p-2 text-dark" href="#">Loan</a>
      <a class="p-2 text-dark" href="#">Sign in</a>
   </nav>
   <a class="btn btn-outline-primary" href="#">Sign up</a>
</header>
<c:if test="${empty login}">
<header style="display: none;">
   <!-- Navigation -->
   <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
         <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
               data-target=".navbar-ex1-collapse">
               <span class="sr-only">Toggle navigation</span> <span
                  class="icon-bar"></span> <span class="icon-bar"></span> <span
                  class="icon-bar"></span>
            </button>
            <a class="navbar-brand page-scroll" href="main.do">ABC Funding<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small>&amp;This is Earth</small></a>
         </div>

         <!-- Collect the nav links, forms, and other content for toggling -->
         <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
               <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
               <li class="hidden"><a class="page-scroll" href="#page-top"></a>
               </li>
               <li><a class="page-scroll" href="intro.do">소개하기</a></li>
               <li><a class="page-scroll" href="#" onclick="alert('로그인이 필요합니다.')" >투자하기</a></li>
               <li><a class="page-scroll" href="#" onclick="alert('로그인이 필요합니다.')" >대출신청</a></li>
<!--                <li><a class="page-scroll" href="sponsor.do">후원하기</a></li> -->
               <li><a class="page-scroll" href="regi.do">회원가입</a></li>
               <li><a class="page-scroll" href="login.do">로그인</a></li>
            </ul>
         </div>
         <!-- /.navbar-collapse -->
      </div>
      <!-- /.container -->
   </nav>
</header>
</c:if>
<c:if test="${not empty login}">
<header style="display: none;">
   <!-- Navigation -->
   <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
         <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
               data-target=".navbar-ex1-collapse">
               <span class="sr-only">Toggle navigation</span> <span
                  class="icon-bar"></span> <span class="icon-bar"></span> <span
                  class="icon-bar"></span>
            </button>
            <a class="navbar-brand page-scroll" href="main.do">ABC Funding<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small>&amp;This is Earth</small></a>
         </div>

         <!-- Collect the nav links, forms, and other content for toggling -->
         <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
               <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
               <li class="hidden"><a class="page-scroll" href="#page-top"></a>
               </li>
               <li><a class="page-scroll" href="intro.do">소개하기</a></li>
               <li><a class="page-scroll" href="invest.do">투자하기</a></li>
               <li><a class="page-scroll" href="loan.do">대출신청</a></li>
<!--                <li><a class="page-scroll" href="sponsor.do">후원하기</a></li> -->
               <li><a class="page-scroll" href="myInfo.do">내정보</a></li>
               <li><a class="page-scroll" href="logout.do">로그아웃</a></li>
            </ul>
         </div>
         <!-- /.navbar-collapse -->
      </div>
      <!-- /.container -->
   </nav>
</header>
</c:if>
</body>
</html>