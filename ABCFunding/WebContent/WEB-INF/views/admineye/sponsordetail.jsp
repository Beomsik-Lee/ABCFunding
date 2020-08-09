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
<title>contact detail</title>
<link href="css/eyeadminstyle.css" rel="stylesheet" type="text/css">
<link href="css/sponsordetail.css" rel="stylesheet" type="text/css">
<script>
	function SetPriceInput(str) {
		str = str.replace(/,/g, '');
		var retValue = "";
		for (i = 1; i <= str.length; i++) {
			if (i > 1 && (i % 3) == 1)
				retValue = str.charAt(str.length - i) + "," + retValue;
			else
				retValue = str.charAt(str.length - i) + retValue;
		}
		return retValue;
	}
</script>

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
				<form method="post" action="updateCode.do">
					<h3><i>후원 프로젝트 요청내용</i></h3>
					<div class="note">※ 신청자 게시물은 수정이 불가합니다. 처리상태만 변경 바랍니다.</div>
					<br>
					<fieldset>
						<legend class="necessariness">신청자 인적사항</legend>
						<table>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성자</td>
								<td><input name="name" style="background-color: #E0ECF8"
									type="text" value="${earth.name}" readonly></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;연락처</td>
								<td><input type="text" style="background-color: #E0ECF8"
									name="phone" placeholder=" '-' 포함하여 입력" value="${earth.phone}"
									readonly></td>
							</tr>
						</table>
						<br>
						<legend class="necessariness">신청서 작성 필수항목</legend>
						<table>
							
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목</td>
								<td><input type="text" style="background-color: #E0ECF8"
									name="title" size="50" value="${earth.title}" readonly></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 목적</td>
								<td><textarea rows="2" cols="50"
										style="background-color: #E0ECF8" name="purpose" readonly>${earth.purpose}</textarea></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여 대상자</td>
								<td><input type="text" size="50"
									style="background-color: #E0ECF8" name="condition"
									value="${earth.condition}" readonly></td>
							</tr>

							<tr height=50>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 참여기간</td>
								<td>시작일:<input type="date"
									style="background-color: #E0ECF8" name="earths"
									value="${earth.earths}" readonly>종료일:<input type="date"
									style="background-color: #E0ECF8" name="earthe"
									value="${earth.earthe}" readonly></td>
							</tr>
							<tr height=200>
								<td width=200 valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여방법(상세)</td>
								<td><textarea style="background-color: #E0ECF8" rows="10"
										cols="50" name="content" readonly>${earth.content}</textarea></td>
							</tr>
						</table>

						<br>
						<legend class="choice">신청서 작성 선택항목</legend>
						<table>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표인원</td>
								<td><input type="text" style="background-color: #E0ECF8"
									placeholder="(단위:명)" name="targetno" class="only-number"
									size="10" value="${earth.targetno}" readonly>&nbsp;&nbsp;명</td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표금액</td>
								<td><input type="text" style="background-color: #E0ECF8"
									placeholder="(단위:원)" name="targetsum" class="only-number"
									size="10" value="${earth.targetsum}" readonly>&nbsp;&nbsp;원</td>
							</tr>
							<br>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;첨부파일(예.포스터)</td>
								<td ><img class="imgsize" src ="./eyeuploadfile/${efile.applyname}"/></td>
							</tr>

						</table>
						<br>
						<legend class="necessariness">* 관리자 처리</legend>
						<table class="admin">
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처리상태</td>
								<td><select name="code" value='5' required="required">
										<option value='1'
											<c:if test="${name eq '신청완료'}">selected="selected"</c:if>>신청완료</option>
										<option value='2'
											<c:if test="${name eq '처리중'}">selected="selected"</c:if>>처리중</option>
										<option value='3'
											<c:if test="${name eq '등록완료'}">selected="selected"</c:if>>등록완료</option>
										<option value='4'
											<c:if test="${name eq '후원종료'}">selected="selected"</c:if>>후원종료</option>
										<option value='5'
											<c:if test="${name eq '마감완료'}">selected="selected"</c:if>>마감완료</option>
								</select></td>
							</tr>
							<tr height=150>
								<td colspan=2 class="goback">
									<div class="btn2">
									<input type="hidden" name="earthno" value="${earth.earthno}">
										<input type="submit" value="저장하기">&nbsp;&nbsp; <input
											type="reset" value="초기화">&nbsp;&nbsp; <a
											href="sponsorlist.do" ><input type="button"
											action="sponsorlist.do" value="취소"></a>
									</div>
								</td>
							</tr>
						</table>
					</fieldset>
				</form>
			</div>
		</div>
	</section>
</body>
</html>

