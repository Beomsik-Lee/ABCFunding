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
<link href="css/eyestyle.css" rel="stylesheet" type="text/css">
<link href="css/contactdetail.css" rel="stylesheet" type="text/css">
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
					<li class="smenu1"><a href="aboutearth.do">This is Earth
							소개</a></li>
					<li class="smenu2"><a href="startearth.do">진행중인 후원</a></li>
					<li class="smenu3"><a href="endearth.do">종료된 후원</a></li>
					<li class="smenu4"><a href="contact.do">후원 신청하기</a></li>
				</ul>
			</div>
			<div id="sponsorcontent">
				<form method="post" action="addEarth.do" enctype="multipart/form-data">
					<div class="maintitle"><i>아래 신청서를 작성해주세요 ♥</i></div>
					<br>
					<fieldset>
						<legend class="necessariness">* 필수 입력사항</legend>
						<table>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성자</td>
								<td><input name="name" type="text" required></td>
							</tr>
							<tr height=37>
								<td></td>
								<td class="memo">소속 회사명이 아닌 작성자님 실명 입력 바랍니다.<br>
								한글 12자, 영문 24자까지 가능합니다.</td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;연락처</td>
								<td><input type="text" name="phone"
									placeholder=" '-' 포함하여 입력" required></td>
							</tr>
							<tr height=20>
								<td></td>
								<td class="memo">꼭 연결 가능한 번호를 남겨주세요!</td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;게시물 잠금번호</td>
								<td><input type="password" name="pw" maxlength="4"
									placeholder="숫자 4자리" required></td>
							</tr>
						</table>
						<br>
						<legend class="necessariness">* 신청서 작성 필수항목</legend>
						<table>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목</td>
								<td><input type="text" name="title" size="50" required></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 목적</td>
								<td><textarea rows="2" cols="50" name="purpose" required></textarea></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여 대상자</td>
								<td><input type="text" size="50" name="condition" required></td>
							</tr>

							<tr height=40>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 참여기간</td>
								<td>시작일:<input type="date" name="earths" required> 종료일:<input
									type="date" name="earthe" required></td>
							</tr>
							<tr height=200>
								<td width=200 valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여방법(상세)</td>
								<td><textarea rows="10" cols="50" name="content" required></textarea></td>
							</tr>
						</table>

						<br>
						<legend class="choice">신청서 작성 선택항목</legend>
						<table class="final">
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표인원</td>
								<td><input type="text" placeholder="숫자만 입력하세요" name="targetno"
									class="only-number" size="14">&nbsp;&nbsp;명</td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표금액</td>
								<td><input type="text" placeholder="숫자만 입력하세요"
									name="targetsum" class="only-number" size="14">&nbsp;&nbsp;원</td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;첨부파일(예.포스터)</td>
								<td><input type="file" name="applysavename"></td>
							</tr>
							<tr height=45>
								<td></td>
								<td class="filenote">파일크기는 1MB이하, JPG 또는 PNG형식의 파일만 가능합니다.
									<br>사이즈는 가로 800이하로 설정해 주시기 바랍니다.(단위:pixel)
								</td>
							</tr>
							<tr height=150>
								<td colspan=2 class="goback">
									<div class="btn2">
										<input type="submit" value="저장하기">&nbsp;&nbsp; <input
											type="reset" value="초기화">&nbsp;&nbsp; <a
											href="contact.do" ><input type="button"
											action="contact.do" value="취소"></a>
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

