/**
 * 나의 대출 내역의 상환스케쥴과 상환상세내역 자바스크립트 라이브러리
 */

//상환스케쥴 생성 함수
function callLoanSchedule(loanMoney, loanDate, loanSubList) {
	var loanrate = 0.08; //고정금리 8%
	var repayRate = 0; //대출상환율
	var paymentPrincipal = new Array(loanDate); //매달납입원금
	var rateMoney = new Array(loanDate); //매달납입이자금
	var sumMonthlyPay = new Array(loanDate); //납입원금누적계
	var balance = new Array(loanDate); //잔금
	var makeLoanSchedule = ""; // 상환스케쥴 html코드를 담을 변수
	var monthlyPay = 0; //매달상환금
	var j = 1;

	//원리금균등분할상환 공식 (매달상환금)
	monthlyPay = Math.round(((loanMoney * (loanrate / 12)) * (Math.pow(1 + loanrate / 12, loanDate)))
			/ (Math.pow(1 + loanrate / 12, loanDate) - 1));

	// 대출상환율
	var repayRateParent = Math.pow(1 + loanrate / 12, loanDate) * loanrate				/ 12; // 분모
	var repayRateChild = Math.pow(1 + loanrate / 12, loanDate) - 1; // 분자
	repayRate = repayRateParent / repayRateChild;

	// 1회값 설정
	rateMoney[0] = Math.round(loanMoney * loanrate / 12); // 이자금
	paymentPrincipal[0] = monthlyPay - rateMoney[0];//납입원금
	sumMonthlyPay[0] = paymentPrincipal[0]; // 납입원금계
	balance[0] = loanMoney - paymentPrincipal[0];// 잔금

	makeLoanSchedule += "<thead><tr><th>회차수</th><th>매달상환금</th><th>납입원금</th><th>이자금</th><th>납입원금계</th><th>잔금</th></tr></thead><tbody>";
	makeLoanSchedule += "<tr><td>" + j + "</td>";
	makeLoanSchedule += "<td>"
		+ monthlyPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "원</td>";
	makeLoanSchedule += "<td>"
		+ paymentPrincipal[0].toString().replace(
				/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>";
	makeLoanSchedule += "<td>"
		+ rateMoney[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "원</td>";
	makeLoanSchedule += "<td>"
		+ sumMonthlyPay[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
		",") + "원</td>";
	makeLoanSchedule += "<td>"
		+ balance[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "원</td>";

	for (var i = 1; i < loanDate; i++) {
		paymentPrincipal[i] = Math.round(monthlyPay - (balance[i - 1])
				* (loanrate / 12)); // 매달납입원금
		rateMoney[i] = Math.round((balance[i - 1]) * (loanrate / 12)); // 매달납입이자금

		// 마지막 회차의 보정치를 위한 계산로직
		if (paymentPrincipal[i] > balance[i - 1]) { // 마지막 회차의 납입원금이 이전회차 잔금보다 큰지 체크
			paymentPrincipal[i] = balance[i - 1]; // 크면, 마지막 회차의 납입원금과 이전회차 잔금을 동일하게 처리
			rateMoney[i] = monthlyPay - paymentPrincipal[i]; // 기존 매달납입이자금 로직 대신에 해당 로직으로 처리
		}

		sumMonthlyPay[i] = Math.round(sumMonthlyPay[i - 1]
		+ paymentPrincipal[i]); // 납입원금계
		balance[i] = Math.round(loanMoney - sumMonthlyPay[i]); // 잔금

		makeLoanSchedule += "<tr><td>" + (i + 1) + "</td>"; // 회차수	
		makeLoanSchedule += "<td>"
			+ monthlyPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "원</td>"; // 매달상환금
		makeLoanSchedule += "<td>"
			+ paymentPrincipal[i].toString().replace(
					/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>"; // 매달납입원금
		makeLoanSchedule += "<td>"
			+ rateMoney[i].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "원</td>"; // 매달납입이자금
		makeLoanSchedule += "<td>"
			+ sumMonthlyPay[i].toString().replace(
					/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>"; // 납입원금계
		makeLoanSchedule += "<td>"
			+ balance[i].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "원</td>"; // 잔금
		makeLoanSchedule += "</tr></tbody>";
	}
	$("#" + loanSubList).append(makeLoanSchedule);
}

// 심사결과를 받기전에 상환상세내역에서 호출되는 함수
function beforeGetApproval(loanDetailID){
	$("#" + loanDetailID).append("<thead><tr><th>현재 심사중입니다.</th></tr>");
}

// 심사결과가 승인인 상환상세내역에서 호출되는 함수
function getApproval(loanDetailID){
	var makeLoanDetail = "";
	makeLoanDetail += "<thead><tr><th>대출실행금</th><th>누적상환금</th><th>누적상환이자금</th><th>누적상환원금</th><th>상환진행상황</th><th>회수비율</th><th>현재회차수</th></tr></thead>";
	makeLoanDetail += "<tbody><tr><td colspan=7>대출실행 이후 해당 정보들이 생성됩니다.</td></tr></tbody>";
	$("#" + loanDetailID).append(makeLoanDetail); 
}

//심사결과가 거절인 상환상세내역에서 호출되는 함수
function getReject(loanDetailID){
	$("#" + loanDetailID).append("<thead><tr><th>해당건은 심사거절로 인해 안내해 드릴 정보가 없습니다.</th></tr>");
}