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
<link href="css/sponsorlist.css" rel="stylesheet" type="text/css">
</head>
<body>
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
				<div class="memo">※ 전산 오류 시 담당자 : EYE OPENING 서버관리팀 박찬성 전임 02)1234-5882</div>
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
						begin="0" step="1">
						<tr>
							<td>[${sponsor.earthno}]</td>
							<td id='bo' onmouseover="mouseOver('${sponsor.earthno}')" ><a href='sponsordetail.do?earthno=${sponsor.earthno}' >${sponsor.title}</a></td>
							<td>${sponsor.earths} ~ ${sponsor.earthe}</td>
							<td>${sponsor.targetno}</td>
							<td>${sponsor.targetsum}</td>
							<td><c:if test="${sponsor.code eq '1'}">신청완료</c:if>
								<c:if test="${sponsor.code eq '2'}">처리중</c:if>
								<c:if test="${sponsor.code eq '3'}">등록완료</c:if>
								<c:if test="${sponsor.code eq '4'}">후원종료</c:if>
								<c:if test="${sponsor.code eq '5'}">마감완료</c:if>	
							</td>
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