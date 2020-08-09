<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />
<!DOCTYPE HTML>
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
<c:if test="${empty login}">
<header>
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
<header>
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