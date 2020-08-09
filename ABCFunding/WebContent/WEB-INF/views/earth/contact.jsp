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
<title>Sponsor contact</title>

<link href="css/eyestyle.css" rel="stylesheet" type="text/css">
<link href="css/contact.css" rel="stylesheet" type="text/css">
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
				<div class="sponsorapply">
					<div class="applynote">
						※ 기업, 단체, 개인 누구든지 회원가입 없이 후원 프로젝트를 신청하실 수 있습니다.<br>
						&nbsp;&nbsp;&nbsp;하단 '후원 프로젝트 신청' 을 통해 언제든지 많은 참여해 주시고,<br>
						&nbsp;&nbsp;&nbsp;원활한 후원 프로젝트 진행을 위해 꼭 연락처를 남겨주세요.<br>
						&nbsp;&nbsp;&nbsp;여러분의 사랑을 나누어주세요♥<br>
					</div>
					<a href="contactdetail.do"><input class="applybtn"
						type="button" value="후원 프로젝트 신청" /></a>
				</div>
				<div class="findcontent">
					<div class="findnote">
						※ 아래 검색창으로 작성하신 후원 프로젝트를 열람/수정 가능합니다. (종료된 프로젝트도 검색가능)<br>
						&nbsp;&nbsp;&nbsp;단, 처리상태가 '신청완료'일 때만 수정이 가능합니다.<br>
						<br> &nbsp;&nbsp;검색이 되지 않을 경우 문의주세요. EYE OPENING 지구복지운영팀 이연 전임 02)1234-7979<br>
					</div>
					<div class="search_area">
							<div class="input_box">
								<input type="text" id="searchName" name="searchname" size="10" placeholder="이름으로 검색"/> 
								<input type="button" onclick="findearth()" value="검색" />
							</div>
						</div>
					</div>	
					<div id="myearth"></div>
				</div>
			</div>
	</section>
</body>
<script type="text/javascript">
function findearth(){
	var searchName = $('#searchName').val();
	$.ajax({
		   type: "POST",
			url: "earthajax.do",
			data: "searchname="+searchName,
		   async: true,	
		   success: function(msg){
				outputList(msg);
		   }
	 });
};
function outputList(msg){
	
	var count=msg.mylist.length;
	var myearth=msg.mylist;
	console.log(myearth);
	var s = "<table class='myearthtable'>";
	s+="<tr><th width='10%'>순번</th><th width='60%'>제목</th><th width='15%'>작성자</th><th width='15%'>처리상태</th></tr>";
	for(i=0; i<count; i++){
		s+="<tr>";
		s+="<td>";
		s+=myearth[i].ROWNUM;
		s+="</td>";
		s+="<td class='textleft'>&nbsp;&nbsp;<a href='myearthpw.do?earthno=";
		s+=myearth[i].EARTHNO;
		s+="'>";
		s+=myearth[i].TITLE;
		s+="</a></td>";
		s+="<td>";
		s+=myearth[i].NAME;
		s+="</td>";
		s+="<td>";
		s+=myearth[i].CODENAME;
		s+="</td>";
		s+="</tr>";
	}
	s+="</table>";
	$("#myearth").html(s);
};
	</script>
</html>