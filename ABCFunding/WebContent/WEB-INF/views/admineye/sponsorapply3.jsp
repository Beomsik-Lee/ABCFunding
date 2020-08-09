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
					<i>후원 프로젝트 등록 관리</i>
				</div>
				<form method="post" action="#">
					<br>
					<fieldset>
						<table class="earthstart" border="1">
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원사</td>
								<td><input name="sponsorid" type="text" value="${sponname}" readonly></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목</td>
								<td><input type="text" name="title" size="50" required></td>
							</tr>
							<tr height=45>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 목적</td>
								<td><textarea rows="2" cols="50" name="purpose" required></textarea></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여 대상자</td>
								<td><input type="text" size="50" name="condition" required></td>
							</tr>
							<tr height=40>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 참여기간</td>
								<td>시작일:<input type="date" name="earths" required>종료일:<input
									type="date" name="earthe" required></td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표인원</td>
								<td><input type="text" placeholder="(단위:명)" name="targetno"
									class="only-number" align="right" size="10" value="0">&nbsp;&nbsp;명</td>
							</tr>
							
							<tr><td colspan="2">
							<table class="detail" border="2">
							<tr height=30>
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기부금액 유무&nbsp;&nbsp; 
									<select name="donationcheck" value='2'
									required="required">
										<option value='1' selected="selected">N</option>
										<option value='2'>Y</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기부금액 계산기준&nbsp;&nbsp; <select
									name="donationcheck" value='2' required="required">
										<option value='1' selected="selected">1일</option>
										<option value='2'>일주일</option>
										<option value='3'>한달</option>
								</select>
								</td>
							</tr>
							<tr height=30>
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표금액&nbsp;&nbsp;<input
									type="text" placeholder="(단위:원)" name="targetsum"
									class="only-number" size="10" value="0">&nbsp;&nbsp;원
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;최대기부금액&nbsp;&nbsp;<input
									type="text" placeholder="(단위:원)" name="targetsum"
									class="only-number" size="10" value="0">&nbsp;&nbsp;원
								</td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기부금액 입력단명</td>
								<td><input type="text" name="title" size="50" required></td>
							</tr>
							<tr height=30>
								<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기부 파일
									유무&nbsp;&nbsp; <select name="donationcheck" value='2'
									required="required">
										<option value='1' selected="selected">N</option>
										<option value='2'>Y</option>
								</select>
								</td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기부파일 입력단명</td>
								<td><input type="text" name="title" size="50" required></td>
							</tr>
							</table>
							</td></tr>
							
							<tr height=200>
								<td width=200 valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여방법(상세)</td>
								<td><textarea rows="10" cols="50" name="content" required></textarea></td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;첨부파일(예.포스터)</td>
								<td><input type="file"></td>
							</tr>
							<tr height=45>
								<td></td>
								<td class="filenote">파일크기는 1MB이하, JPG 또는 PNG형식의 파일만 가능합니다.
									<br>사이즈는 가로 800이하로 설정해 주시기 바랍니다.(단위:pixel)
								</td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처리상태</td>
								<td><select name="code" value='5' required="required">
										<option value='1'>신청완료</option>
										<option value='2'>처리중</option>
										<option value='3' selected="selected">등록완료</option>
										<option value='4'>후원종료</option>
										<option value='5'>마감완료</option>
								</select></td>
							</tr>
							<tr height=150>
								<td colspan=2 class="goback">
									<div class="btn2">
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