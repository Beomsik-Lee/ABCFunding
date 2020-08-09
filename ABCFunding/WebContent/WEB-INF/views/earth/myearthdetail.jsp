<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.hk.abcfund.earth.model.dto.EarthDto" %>
<%
	EarthDto edto = (EarthDto) request.getAttribute("earth");
	String txt = "";
	if (edto.getCode() != 1) {
		txt = "readonly";
	}
	else {
		txt = "required";
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
<link href="css/myearthdetail.css" rel="stylesheet" type="text/css">
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
				<form method="post" action="updateMyearth.do" enctype="multipart/form-data">
					<h3><i>후원 프로젝트 요청내용</i></h3>
					<div class="note">※ 게시물은 '신청완료'일 경우에만 수정이 가능합니다.</div>
					<br>
					<fieldset>
						<legend class="necessariness">* 신청자 인적사항</legend>
						<table>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성자</td>
								<td><input name="name" style="background-color: #F5F6CE"
									type="text" value="${earth.name}" readonly></td>
							</tr>
							<tr height=37>
								<td></td>
								<td class="memo">작성자님 성함은 수정이 불가능합니다.</td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;연락처</td>
								<td><input type="text" style="background-color: #F5F6CE"
									name="phone" placeholder=" '-' 포함하여 입력" value="${earth.phone}"
									<%= txt %>></td>
							</tr>
							<tr height=20>
								<td></td>
								<td class="memo">꼭 연결 가능한 번호를 남겨주세요!</td>
							</tr>
						</table>
						<br>
						<legend class="necessariness">* 신청서 작성 필수항목</legend>
						<table>
							
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목</td>
								<td><input type="text" style="background-color: #F5F6CE"
									name="title" size="50" value="${earth.title}" <%= txt %>></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 목적</td>
								<td><textarea rows="2" cols="50"
										style="background-color: #F5F6CE" name="purpose" <%= txt %>>${earth.purpose}</textarea></td>
							</tr>
							<tr height=35>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여 대상자</td>
								<td><input type="text" size="50"
									style="background-color: #F5F6CE" name="condition"
									value="${earth.condition}" <%= txt %>></td>
							</tr>

							<tr height=50>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원 참여기간</td>
								<td>시작일:<input type="date"
									style="background-color: #F5F6CE" name="earths"
									value="${earth.earths}" <%= txt %>>종료일:<input type="date"
									style="background-color: #F5F6CE" name="earthe"
									value="${earth.earthe}" <%= txt %>></td>
							</tr>
							<tr height=200>
								<td width=200 valign=top>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;참여방법(상세)</td>
								<td><textarea style="background-color: #F5F6CE" rows="10"
										cols="50" name="content" <%= txt %>>${earth.content}</textarea></td>
							</tr>
						</table>

						<br>
						<legend class="choice">신청서 작성 선택항목</legend>
						<table>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표인원</td>
								<td><input type="text" style="background-color: #F5F6CE"
									placeholder="(단위:명)" name="targetno" class="only-number"
									size="10" value="${earth.targetno}" <%= txt %>>&nbsp;&nbsp;명</td>
							</tr>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목표금액</td>
								<td><input type="text" style="background-color: #F5F6CE"
									placeholder="(단위:원)" name="targetsum" class="only-number"
									size="10" value="${earth.targetsum}" <%= txt %>>&nbsp;&nbsp;원</td>
							</tr>
							<br>
							<tr height=30>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기존 첨부파일</td>
								<td ><img class="imgsize" src ="${efile.applypath}"/></td>
							</tr>
							<tr height=40>
							<td></td>
							<td><input type="button" value="첨부파일 삭제" onclick="deletefile('${earth.earthno}');">
							</td>
							</tr>
							<tr height=40>
								<td width=200>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;첨부파일 재첨부</td>								
								<td><input type="file" name="applysavename" onclick="fileAlert('${earth.earthno}');"></td>
							</tr>
							<tr height=45>
								<td></td>
								<td class="filenote">파일크기는 1MB이하, JPG 또는 PNG형식의 파일만 가능합니다.
									<br>사이즈는 가로 800이하로 설정해 주시기 바랍니다.(단위:pixel)
								</td>
							</tr>
						</table>
						<br>
						<legend class="necessariness"></legend>
						<table class="admin">
							<tr height=150>
								<td colspan=2 class="goback">
									<div class="btn2">
									<input type="hidden" name="earthno" value="${earth.earthno}">
									<%
										if (edto.getCode() == 1) {
									%>
										<input type="submit" value="저장하기">&nbsp;&nbsp;
										<input type="reset" value="초기화">&nbsp;&nbsp;
										<input type="button" value="삭제하기" onclick="deletedate('${earth.earthno}');">&nbsp;&nbsp;
									<%
										}
									%> 
										<a href="contact.do" ><input type="button"
											action="contact.do" value="돌아가기"></a>
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
<script type="text/javascript">
function deletedate(earthno){
	var UP;
	UP=confirm("게시글을 정말로 삭제하시겠습니까?");
	if(UP){
		location.href='myearthdelete.do?earthno='+earthno;
	}
	else{
		location.href='myearthdetail.do?earthnum='+earthno;
	}
}

function deletefile(earthno){
	var UP;
	UP=confirm("첨부파일을 정말로 삭제하시겠습니까?");
	if(UP){
		location.href='myearthfiledelete.do?earthno='+earthno;
	}
	else{
		location.href='myearthdetail.do?earthnum='+earthno;		
	}
}

function fileAlert(earthno){
	alert("파일 재첨부시 기존 파일은 삭제됩니다.");
}
</script>
</html>