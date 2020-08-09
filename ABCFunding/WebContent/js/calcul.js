/**
 * 원리금균등분할상환 계산
 */
/*
function calcul() {
	var loanmoney = document.getElementById("loanMoney").value; //대출신청금
	var loanrate = 0.08; //고정금리 8%
	var loanperiod = document.getElementById("loanPeriod").value; //대출기간(개월수 단위)
	var monthlyPay = 0; //매달상환금
	var paymentPrincipal = 0; //매달납입원금
	var rateMoney = 0; //매달납입이자금
	var sumMonthlyPay = 0; //납입원금누적계
	var balance = 0; //잔금
	var i,j; //for문을 돌리기위한 변수
	
	//원리금균등분할상환 공식 (매달상환금)
	monthlyPay = Math.round(((loanmoney * (loanrate / 12)) * (Math.pow(
			1 + loanrate / 12, loanperiod))) / (Math.pow(1 + loanrate / 12, loanperiod) - 1));

	// 테이블 한행에 출력할 대출정보 Array
	var tableRow = [ monthlyPay, paymentPrincipal, rateMoney, sumMonthlyPay, balance ];

	document.getElementById("result").innerHTML = tableRow;

	
	// 결과 출력 table
		// table head start
	var x = document.createElement("TABLE");
	x.setAttribute("id", "myTable");
	x.setAttribute("border", "1px solid black");
	document.getElementById("resultDisplay").appendChild(x);

	var y = document.createElement("TH");
	y.setAttribute("id", "myTh");

	document.getElementById("myTable").appendChild(y);

	var title=["회차","매월상환금","납입원금","이자금","납입원금누적계","잔금"];
		
	var z,t;
	for (i=0;i<6;i++){
		z = document.createElement("TD");
		t = document.createTextNode(title[i]);
		z.appendChild(t);
		document.getElementById("myTh").appendChild(z);
	}
		// table head end
	
		// table body start
	for (i=0;i<loanperiod;i++){
	var row = document.createElement("TR");
	row.setAttribute("id","myTr");
	document.getElementById("myTable").appendChild(row);
	
	var rowTd = document.createElement("TD");
	var rowTdtext = document.createTextNode(eval(i+1));
	rowTd.appendChild(rowTdtext);
	document.getElementById("myTr").appendChild(rowTd);
	}
	
	/*for (i=0;i<loanperiod;i++){
		row = document.createElement("TR");
		row.setAttribute("id", "myTr");
		rowz = document.createElement("TD");
		test = document.createTextNode("test");
		rowz.appendChild(test);
		document.getElementById("myTable").appendChild(rowz);
		for (j=0;j<6;j++){
			z = document.createElement("TD");
			t = document.createTextNode(title[i]);
			z.appendChild(t);
			document.getElementById("myTh").appendChild(z);
		}
	}
}
*/

$(document).ready(function(){
	$("#addScheduleTable").click(function(){
		$(".loan-calcul > tr:last").append("<tr><td>회차수</td><td>매달상환금</td><td>납입원금</td>" +
				"<td>이자금</td><td>납입원금계</td><td>잔금</td></tr>");
	});
});

	
