/**
 * 투자시뮬레이션 자바스크리트 라이브러리
 */
// 기존의 투자 시뮬레이션 테이블이 있으면 내용을 없애는 함수 
function checkSimulTable() {
	$("#investTable").each(function() { 
		  var count = $(this).children().length;
		  if (count !=0) { 
			  $(this).empty(); 
	  	  }
		});
}

// 투자 시뮬레이션 스케쥴을 생성하는 함수 
function startSimulation(loanDate) {
	$("#investTable").each(function() { 
		  var count = $(this).children().length;
		  if (count !=0) { 
			  $(this).empty(); 
	  	  }
	});
	var investMoney = $("#investMoney").val();
	var loanrate = 0.06; //고정금리 8%
	var repayRate = 0; //대출상환율
	var paymentPrincipal = new Array(loanDate); //매달납입원금
	var rateMoney = new Array(loanDate); //매달납입이자금
	var sumMonthlyPay = new Array(loanDate); //납입원금누적계
	var balance = new Array(loanDate); //잔금
	var makeinvestSchedule = ""; // 상환스케쥴 html코드를 담을 변수
	var monthlyPay = 0; //매달상환금
	var j = 1;

	//원리금균등분할상환 공식 (매달상환금)
	monthlyPay = Math.round(((investMoney * (loanrate / 12)) * (Math.pow(1 + loanrate / 12, loanDate)))
			/ (Math.pow(1 + loanrate / 12, loanDate) - 1));

	// 대출상환율
	var repayRateParent = Math.pow(1 + loanrate / 12, loanDate) * loanrate				/ 12; // 분모
	var repayRateChild = Math.pow(1 + loanrate / 12, loanDate) - 1; // 분자
	repayRate = repayRateParent / repayRateChild;

	// 1회값 설정
	rateMoney[0] = Math.round(investMoney * loanrate / 12); // 이자금
	paymentPrincipal[0] = monthlyPay - rateMoney[0];//납입원금
	sumMonthlyPay[0] = paymentPrincipal[0]; // 납입원금계
	balance[0] = investMoney - paymentPrincipal[0];// 잔금

	makeinvestSchedule += "<thead><tr><th>회차수</th><th>매달회수금</th><th>매달회수원금</th><th>매달수익금</th><th>회수원금계</th><th>잔여회수금</th></tr></thead><tbody>";
	makeinvestSchedule += "<tr><td>" + j + "</td>";
	makeinvestSchedule += "<td>"
		+ monthlyPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "원</td>";
	makeinvestSchedule += "<td>"
		+ paymentPrincipal[0].toString().replace(
				/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>";
	makeinvestSchedule += "<td>"
		+ rateMoney[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "원</td>";
	makeinvestSchedule += "<td>"
		+ sumMonthlyPay[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
		",") + "원</td>";
	makeinvestSchedule += "<td>"
		+ balance[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "원</td>";

	for (var i = 1; i < loanDate; i++) {
		paymentPrincipal[i] = Math.round(monthlyPay - (balance[i - 1])
				* (loanrate / 12)); // 매달납입원금
		rateMoney[i] = Math.round((balance[i - 1]) * (loanrate / 12)); // 매달납입이자금

		// 마지막 회차의 보정치를 위한 계산로직
		if(i+1==loanDate){ //마지막 회차인지 체크
			paymentPrincipal[i] = balance[i - 1]; // 마지막 회차의 납입원금과 이전회차 잔금을 동일하게 처리
			rateMoney[i] = monthlyPay - paymentPrincipal[i]; // 기존 매달납입이자금 로직 대신에 해당 로직으로 처리
		}
		

		sumMonthlyPay[i] = Math.round(sumMonthlyPay[i - 1]
		+ paymentPrincipal[i]); // 납입원금계
		balance[i] = Math.round(investMoney - sumMonthlyPay[i]); // 잔금

		makeinvestSchedule += "<tr><td>" + (i + 1) + "</td>"; // 회차수	
		makeinvestSchedule += "<td>"
			+ monthlyPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "원</td>"; // 매달상환금
		makeinvestSchedule += "<td>"
			+ paymentPrincipal[i].toString().replace(
					/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>"; // 매달납입원금
		makeinvestSchedule += "<td>"
			+ rateMoney[i].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "원</td>"; // 매달납입이자금
		makeinvestSchedule += "<td>"
			+ sumMonthlyPay[i].toString().replace(
					/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>"; // 납입원금계
		makeinvestSchedule += "<td>"
			+ balance[i].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "원</td>"; // 잔금
		makeinvestSchedule += "</tr></tbody>";
	}
	$("#investTable").append(makeinvestSchedule);
}