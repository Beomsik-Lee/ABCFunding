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
<title>about Earth</title>

<script src="js/jquery-1.12.0.min.js"></script>
<script src="js/jquery.flexslider-min.js"></script>

<link href="css/eyestyle.css" rel="stylesheet" type="text/css">
<link href="css/aboutearth.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section id="sponsor">
		<div id="main">
			<div id="sponsormenu">
				<ul class="smenu">
					<li class="smenu0">This is Earth</li>
					<li class="smenu1"><a href="aboutearth.do">This is Earth
							소개</a></li>
					<li class="smenu2"><a href="startearth.do">진행중인 후원</a></li>
					<li class="smenu3"><a href="endearth.do">종료된 후원</a></li>
					<li class="smenu4"><a href="contact.do">후원 신청하기</a></li>
				</ul>
			</div>

			<div id="sponsorcontent">
				<div class="flexslider">
					<ul class="slides">
						<li><img src="./earthimg/intro.jpg" /></li>
						<li><img src="./earthimg/introearth.jpg" /></li>
						<li><img src="./earthimg/introearth2.jpg" /></li>
						<li><img src="./earthimg/introearth4.jpg" /></li>
						<li><img src="./earthimg/introearth3.jpg" /></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<script>
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide",
				prevText : '≪',
				nextText : '≫'
			});
		});
	</script>
</body>
</html>