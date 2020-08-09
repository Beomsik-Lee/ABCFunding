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
<link href="css/eyeadminstyle.css" rel="stylesheet" type="text/css">
<link href="css/sponsorapply.css" rel="stylesheet" type="text/css">
</head>
<form name="frmForm1" id="_frmFormSearch" method="post" action="">
	<input type="hidden" name="pageNumber" id="_pageNumber"
		value="${(empty pageNumber)?0:pageNumber}" /> <input type="hidden"
		name="recordCountPerPage" id="_recordCountPerPage"
		value="${(empty recordCountPerPage)?10:recordCountPerPage}" />
</form>
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
				<div class="maintitle">
					<i>후원사 등록 관리</i>
				</div>
				<form method="post" action="earthapply2.do">
					<br>
					<fieldset>
						<table class="earthstart">
							<tr>
								<td colspan="6">
									<table class="detail">
										<div class="search_area">

											<tr height=35>
												<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원사명</td>

												<td><input name="sponsorid" type="text"
													value="${sponname}" readonly></td>
											</tr>
											<tr height=35>
												<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원사번호</td>
												<td><input name="${sponno}" type="text"
													<c:if test="${sponno eq '10'}">value="기업"</c:if>
													<c:if test="${sponno eq '20'}">value="단체"</c:if>
													<c:if test="${sponno eq '30'}">value="개인"</c:if> readonly>
													<input name="${sponno}" type="hidden"
													<c:if test="${sponno eq '10'}">value="10"</c:if>
													<c:if test="${sponno eq '20'}">value="20"</c:if>
													<c:if test="${sponno eq '30'}">value="30"</c:if>></td>
											</tr>
											<hr>

										</div>
										</div>
									</table>
									<hr>

								</td>
							</tr>


							<tr class="earthing_topname">

								<th>순번</th>
								<th>후원 프로젝트</th>
								<th>후원 참여기간</th>
								<th>선택</th>
							</tr>
							<c:forEach items="${sponsorlist}" var="sponsor" varStatus="vs"
								begin="0" step="1">
								<tr>
									<td>[${vs.count}]</td>
									<td id='bo' onmouseover="mouseOver('${sponsor.earthno}')"><a
										href='sponsordetail.do?earthno=${sponsor.earthno}'>${sponsor.title}</a></td>
									<td>${sponsor.earths}~ ${sponsor.earthe}</td>
									<td><a href='earthapply2.do?earthno=${sponsor.earthno}&sponsorid=${sponname}'><input type='button' value="선택"></a></td>
								</tr>
							</c:forEach>

							<td colspan=2 class="goback" align="left">
								<div class="btn2" align="center">
									<br> <br> <br>
									<c:forEach var="i" begin="1" step="1"
										end="${totalRecordCount%pageCountPerScreen eq 0 ? 
totalRecordCount/pageCountPerScreen : totalRecordCount/pageCountPerScreen}">
									</c:forEach>
									<jsp:include page="/WEB-INF/views/admineye/paging.jsp"
										flush="false">
										<jsp:param value="${pageNumber}" name="pageNumber" />
										<jsp:param value="${pageCountPerScreen}"
											name="pageCountPerScreen" />
										<jsp:param value="${recordCountPerPage}"
											name="recordCountPerPage" />
										<jsp:param value="${totalRecordCount}" name="totalRecordCount" />
									</jsp:include>
									<br> <br> <br> <a href="sponsorapply.do"
										style="padding-left: 100px"><input type="button"
										value="되돌아가기"></a>
								</div>
							</td>



						</table>

					</fieldset>
				</form>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript">
	function goPage(pageNumber) {
		$("#_pageNumber").val(pageNumber);
		$("#_frmFormSearch").attr("target", "_self").attr("action",
				"earthapply.do?sponsorid=${sponname}&sponsorno=${sponno}")
				.submit();
	}
</script>

</html>