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
					<i>후원사 등록 관리</i>
				</div>
				<form method="post" action="earthapply.do">
					<br>
					<fieldset>
						<table class="earthstart" border="1">
							<tr><td colspan="2">
							<table class="detail" >
							<div class="search_area">
						
								<div class="input_box" colspan="3" height=60><br><p>검색</p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원사 분류&nbsp;&nbsp; 
					
									<select id="sponsorCheck" name="sponsorCheck" value='1'
									required="required">
										<option  value='10' selected="selected">&nbsp;&nbsp;&nbsp;기업&nbsp;&nbsp;&nbsp;</option>
										<option value='20'>&nbsp;&nbsp;&nbsp;단체&nbsp;&nbsp;&nbsp;</option>
										<option value='30'>&nbsp;&nbsp;&nbsp;개인&nbsp;&nbsp;&nbsp;</option>
										</select>
							&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원사 검색 &nbsp;
							<input type="text" id="sponsorSearch" name="sponsorsearch"/>
							 <input type="button" value="검색" onclick="findsponsor()"/>
							 <hr>
							
							</div>
							
							<div id="mysponsor" align="center" ></div>
							</div>
							</table>
							<hr>
							<p>등록</p>&nbsp;&nbsp;&nbsp;&nbsp;후원사 분류&nbsp;&nbsp;
							<select id="sponsorCategory" name="sponsorCategory" value='10'
									required="required">
										<option  value='10' selected="selected">&nbsp;&nbsp;&nbsp;기업&nbsp;&nbsp;&nbsp;</option>
										<option value='20'>&nbsp;&nbsp;&nbsp;단체&nbsp;&nbsp;&nbsp;</option>
										<option value='30'>&nbsp;&nbsp;&nbsp;개인&nbsp;&nbsp;&nbsp;</option>
										</select>
								&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후원사 이름 &nbsp;
							<input type="text" id="sponsorname" name="sponsorname"/>
							<input type="button" value="등록" id="sponsorname" name="sponsorname"/>
							<br><br>
							</td></tr>
							
							<tr>
								<td colspan=2 class="goback" align="left">
									<div class="btn2" align="center">
									<br>
									<br>
									<br>
				<a href="sponsorlist.do" ><input type="button" 
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
<script type="text/javascript">

function findsponsor() {
	var sponsorSearch = $('#sponsorSearch').val();
	var sponsorCheck  = $('#sponsorCheck').val();
	$.ajax({
		type: "POST",
		url: "sponsorajax.do",
		data : {sponsorSearch: sponsorSearch , sponsorCheck : sponsorCheck},
		async: true,
		success: function(msg) {
			outputList(msg);
		},
		error: function() {
			alert("데이터가없습니다.");
		}
		
	});
};
function outputList(msg){
	var count=msg.mylist.length;
	var mysponsor=msg.mylist;
	var s = "<table border ='1' class='mysponsortable'>";
	s+="<tr><th>후원자코드</th><th>후원자이름</th><th>선택</th></tr>"
	for(i=0; i<count; i++) {
		
		s+="<tr>";
		s+="<td>";
		if(mysponsor[i].code == 10) {
			s+= "<input type=text name=sponsorcode   value=기업 readonly>";
		}
		else if (mysponsor[i].code == 20 ){

			s+= "<input type=text name=code value=단체  readonly>";
		}		
		else  {
				s+= "<input type=text name=code  value=개인 readonly>";
		}
			
		s+="</td>";
		s+="<th class='textleft'>&nbsp;&nbsp;";
		s+= "<input type=text name=sponsor value="+mysponsor[i].sponsorid + " readonly>";
		s+="</th><th>";
		s+="<a href='earthapply.do?sponsorid="+mysponsor[i].sponsorid+"&sponsorno="+mysponsor[i].code+"'>";
		s+="<input type=button name=checks value=선택></a>";
		s+="</tr>";
	}
	s+="</table><br><br>";
	$("#mysponsor").html(s);
};

</script>

</html>