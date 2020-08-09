<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String message = request.getParameter("message");
	if (message != null && message.length() != 0) {
%>
		<script>alert('<%=message%>')</script>
<%
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="keywords" content="This is Earth">
<meta name="description" content="This is Earth">
<meta name="author" content="EYE OPENING">
<title>Earth main</title>
<link href="css/eyestyle.css" rel="stylesheet" type="text/css">
<link href="css/myearthpw.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section id="sponsor">
		<div id="main">
			<div id="sponsormenu">
				<ul class="smenu">
					<li class="smenu0">This is Earth</li>
					<li class="smenu1" id="smenu1"><a href="aboutearth.do">This
							is Earth 소개</a></li>
					<li class="smenu2"><a href="startearth.do">진행중인 후원</a></li>
					<li class="smenu3"><a href="endearth.do">종료된 후원</a></li>
					<li class="smenu4"><a href="contact.do">후원 신청하기</a></li>
				</ul>
			</div>

			<div id="sponsorcontent">
				<div class="note">
					게시물 비밀번호를 입력해주세요.<br> 비밀번호가 기억이 안나실 경우 연락주세요. 
					 EYE OPENING	지구복지운영팀 이연 전임 02)1234-7979
				</div>
				<form method="post" action="myearthpwconfirm.do?earthno=${earth.earthno}">
				<div class="btn">
						비밀번호&nbsp;&nbsp;<input type="password" name="pw" maxlength="4" size="8"
						placeholder="숫자 4자리" required><input type="submit" value="확인">&nbsp;&nbsp; 
						<a href="contact.do"><input
						type="button" value="뒤로가기"></a>
				</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>