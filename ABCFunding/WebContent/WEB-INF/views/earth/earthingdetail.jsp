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
<link href="css/eyestyle.css" rel="stylesheet" type="text/css">
<link href="./bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="css/earthingdetail.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
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
			<div id="gototop"><img src="./earthimg/top.jpg"></div>
			<div id="sponsorcontent">
				<table class="detailtable">
					<tr>
						<td class="textcenter">후원제목</td>
						<td>&nbsp;금연도 하고 소방설비 후원에 참여하세요</td>
						<td class="textcenter">후원사</td>
						<td>&nbsp;(주)배트맨</td>
					</tr>
					<tr>
						<td class="textcenter">후원목적</td>
						<td colspan="3">&nbsp;담배꽁초로 인한 사고예방 및 소방설비 지원</td>
					</tr>
					<tr>
						<td class="textcenter">참여기간</td>
						<td colspan="3">&nbsp;2016.07.01~2016.08.31</td>
					</tr>
					<tr>
						<td class="textcenter">대상자</td>
						<td colspan="3">&nbsp;일반 국민 중 흡연자</td>
					</tr>
					<tr>
						<td class="textcenter">목표인원</td>
						<td>&nbsp;200명</td>
						<td class="textcenter">목표금액</td>
						<td>&nbsp;5,000,000원</td>
					</tr>
					<tr>
						<td colspan="4"><img src="./earthimg/batmansponsor.jpg"></td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;아래 댓글 작성으로 후원에 참여하실 수 있습니다.^^<br>
							<br>&nbsp;*참여방법*<br> &nbsp;1.작성자, 생년월일, 금연시 1일 저축금액을
							입력한다.<br>&nbsp;2.금연을 위한 다짐 한마디를 작성한다.<br>&nbsp;3.후원하기를
							누른다!^^
						</td>
					</tr>
				</table>
				<hr />
				<table id="commentTable" class="table table-condensed"></table>
				<table class="table table-condensed">
					<tr>
						<td><span class="form-inline" role="form">
								<p>
								<div class="form-group">
									<input type="text" id="commentName" name="commentName"
										class="form-control col-lg-2" data-rule-required="true"
										placeholder="이름" maxlength="12">
								</div>
								<div class="form-group">
									<input type="text" id="commentBirth" name="commentBirth"
										class="form-control col-lg-2" data-rule-required="true"
										placeholder="생년월일" maxlength="8">
								</div>
								<div class="form-group">
									<input type="text" id="commentPrice" name="commentPrice"
										class="form-control col-lg-2" data-rule-required="true"
										placeholder="금액(숫자)만 입력" maxlength="5">
								</div>
									<button type="button" id="commentSubmit" name="commentSubmit"
										class="btn btn-default">♥후원하기♥</button>
								</div>
								</p> <textarea id="commentText" class="form-control col-lg-12"
									style="width: 100%" rows="4"></textarea>
						</span></td>
					</tr>
				</table>		
				<script>
					$(function() {
						//depth1의 댓글을 다는 이벤트
						$("#commentSubmit")
								.click(
										function(event) {

											//ajax로 저장하고 성공하면 저장한 데이터를 가져와 넣어야 하는데 여기서는 테스트라 그냥 입력값을 가져옴
											var pName = $("#commentName");
											var pBirth = $("#commentBirth");
											var pPrice = $("#commentPrice");
											var pText = $("#commentText");

											if ($.trim(pName.val()) == "") {
												alert("이름을 입력하세요.");
												pName.focus();
												return;
											} else if ($.trim(pBirth.val()) == "") {
												alert("생년월일을 입력하세요.");
												pBirth.focus();
												return;
											} else if ($.trim(pPrice.val()) == "") {
												alert("금액을 입력하세요.");
												pPrice.focus();
												return;
											} else if ($.trim(pText.val()) == "") {
												alert("내용을 입력하세요.");
												pText.focus();
												return;
											}

											var commentText = '<tr id="r1" name="commentParentCode">'
													+ '<td colspan=2>'
													+ '<strong>'
													+ pName.val()
													+ '</strong> '
													+ '(생년월일 :'
													+ pBirth.val()
													+ ')'
													+ '&nbsp; ♥1일 기부금액 :'
													+ pPrice.val()
													+ '원♥'
													+ '&nbsp; <small><a style="cursor:pointer;" name="pDel">삭제</a></small><p>'
													+ pText.val().replace(
															/\n/g, "<br>")
													+ '</p>'
													+ '</td>'
													+ '</tr>';
											//테이블의 tr자식이 있으면 tr 뒤에 붙인다. 없으면 테이블 안에 tr을 붙인다.
											if ($('#commentTable').contents()
													.size() == 0) {
												$('#commentTable').append(
														commentText);
											} else {
												$('#commentTable tr:last')
														.after(commentText);
											}

											$("#commentName").val("");
											$("#commentBirth").val("");
											$("#commentPrice").val("");
											$("#commentText").val("");

										});
						//삭제링크를 눌렀을때 해당 댓글을 삭제하는 이벤트
						$(document).on("click", "table#commentTable a",
								function() {//동적으로 버튼이 생긴 경우 처리 방식

									if ($(this).attr("name") == "pDel") {
										if (confirm("정말 삭제하시겠습니까??") == true) { //확인
											$(this).parent().parent().remove();
										} else { //취소
											return;
										}
									}
								});
					});
				</script>
				<hr />
			</div>
		</div>
	</section>
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(function() {
 $(window).scroll(function() {
  if($(this).scrollTop() != 0) {
   $('#gototop').fadeIn(); 
  } else {
   $('#gototop').fadeOut();
  }
 });
 
 $('#gototop').click(function() {
  $('body,html').animate({scrollTop:0},800);
 }); 
});
</script>
</html>