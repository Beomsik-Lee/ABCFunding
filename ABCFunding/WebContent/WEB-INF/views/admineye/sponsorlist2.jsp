v<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link href="css/eyeadminstyle.css" rel="stylesheet" type="text/css">
<style>
.ssmenu li.sponmenu1 a {
	text-align: center;
	opacity: 0.3;
	padding-right: 10px;
	filter: alpha(opacity = 20);
	color: white;
	text-decoration: none;
	background: #2B6CA3;
}

.smenu li.smenu2 a {
	text-align: center;
	opacity: 0.3;
	padding-right: 10px;
	filter: alpha(opacity = 20);
	color: white;
	text-decoration: none;
	background: #2B6CA3;
}

.earthing_table {
	border-top: 2px solid #8C8C8C;
	border-bottom: 2px solid #8C8C8C;
	font-size: 0.9em;
	text-align: center;
	margin: 15px 0 10px 0;
	color: #191919;
	width: 100%;
}

.earthing_table th {
	border-bottom: 2px solid #8C8C8C;
	background: #EAEAEA;
	height: 30px;
	border-right: 2px solid #8C8C8C;
	line-height: 30px;
	text-align: center;
	letter-spacing: -1px;
}

.earthing_table td {
	border-top: 2px solid #8C8C8C;
	border-right: 2px solid #8C8C8C;
	line-height: 25px;
	text-align: center;
	vertical-align: middle;
	color: #666;
}

.earthing_table .no_border {
	border-right: none;
}

.earthing_table .td_no_border {
	border-right: none;
	color: #FFFFFF;
}

.earthing_table .earthing_title {
	width: 35%;
	padding: 8px;
	text-align: justify;
	word-break: break-all;
}

.earthing_table .resultnumber {
	color: #FFFFFF;
}

.earthing_table .resultnumber:hover {
	color: #FF0000;
	background: #FFFFE4;
	font-size: 1.5em;
}

.earthing_table .td_no_border:hover {
	color: #FF0000;
	background: #FFFFE4;
	font-size: 1.5em;
}

.earthing_table .earthing_title a:link {
	color: #FF007F;
	text-decoration: none;
}

.earthing_table .earthing_title a:hover {
	color: #DB0000;
	font-weight: bold;
}

.earthing_table .earthing_title a:active {
	color: #DB0000;
	font-weight: bold;
	text-decoration: underline;
}

.earthing_table .earthing_title a:visited {
	color: #6799FF;
	text-decoration: none;
}
</style>
</head>
<body>
<form name="frmFormDetail" id="_frmFormDetail" method="post" action="sponsordetail.do">
<form name="frmForm1" id="_frmFormSearch" method="post" action="">
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber}"/>						
	<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?10:recordCountPerPage}"/>						
</form>
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
				<h3 class="welcometext">후원 목록</h3>
				<table class="earthing_table">
					<tr class="earthing_topname">
						<th>순번</th>
						<th>후원 프로젝트</th>
						<th>후원 참여기간</th>
						<th>목표인원</th>
						<th>목표 금액</th>
						<th>처리 상태</th>
					</tr>
					<c:forEach items="${sponsorlist}" var="sponsor" varStatus="vs"
						begin="1" step="1">
						<tr>
							<td>[${sponsor.earthno}]</td>
							<td id='bo' onmouseover="mouseOver('${sponsor.earthno}')" ><a href='sponsordetail.do?earthno=${sponsor.earthno}' >${sponsor.title}</a></td>
							<td>${sponsor.earths} ~ ${sponsor.earthe}</td>
							<td>${sponsor.targetno}</td>
							<td>${sponsor.targetsum}</td>
							<td>${sponsor.code}</td>
						</tr>
				</c:forEach>
				</table>
				<hr />

				<c:forEach var="i" begin="1" step="1"
					end="${totalRecordCount%pageCountPerScreen eq 0 ? 
totalRecordCount/pageCountPerScreen : totalRecordCount/pageCountPerScreen}">
</c:forEach>
				<jsp:include page="/WEB-INF/views/admineye/paging.jsp" flush="false">
					<jsp:param value="${pageNumber}" name="pageNumber" />
					<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen" />
					<jsp:param value="${recordCountPerPage}" name="recordCountPerPage" />
					<jsp:param value="${totalRecordCount}" name="totalRecordCount" />
				</jsp:include>
				<hr />
			</div>
		</div>
	</section>
<script type="text/javascript">
function goPage(pageNumber) {	
	$("#_pageNumber").val(pageNumber) ;
	$("#_frmFormSearch").attr("target","_self").attr("action","sponsorlist.do").submit();
}
</script>
</body>
</html>