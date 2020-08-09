<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="keywords" content="This is Earth">
<meta name="description" content="This is Earth">
<meta name="author" content="EYE OPENING">
<title>Earth main</title>
<script>
	$(document).ready(function() {
		$(".smenu ul").hide();
		$(".smenu li a").mouseover(function(event) {
			$(this).siblings("ul").toggle("fast", "swing");
			$(".smenu ul").show();
		});
		$(".smenu li a").mouseleave(function(event) {
			$(this).siblings("ul").toggle("fast", "swing");
			$(".smenu ul").hide();
		});
	});
</script>
<link href="css/eyeadminstyle.css" rel="stylesheet" type="text/css">
<link href="css/adminmain.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section id="sponsor">
		<div id="main">
			<div id="sponsormenu">
				<ul class="smenu">
					<li class="smenu0">This is Earth</li>
					<li class="smenu1"><a href="sponsorManage.do">후원 관리</a></li>
					<ul class="ssmenu">
						<li class="sponmenu1"><a href="sponsorlist.do">후원 목록</a></li>
						<li class="sponmenu2"><a href="sponsorapply.do">후원 등록</a></li>
						<li class="sponmenu3"><a href="sponsorenddate.do">후원 마감</a></li>
					</ul>
				</ul>
				<ul class="smenu2">
					<li class="smenu21"><a href="sponsoring.do">진행중인 후원</a></li>
				</ul>
				<ul class="smenu3">
					<li class="smenu31"><a href="sponsorend.do">종료된 후원</a></li>
				</ul>
			</div>

			<div id="sponsorcontent">
			</div>
		</div>
	</section>
</body>
</html>