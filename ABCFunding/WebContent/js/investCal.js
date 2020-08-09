/* 나의 투자내역에서 상환스케쥴을 계산하는 자바스크립트입니다. */

/* 변수 선언 및 초기화 */
var origin = 0;		// 원금
var interest = 0;	// 이자
var payment = 0;	// 상환금
var balance = -999;	// 잔금
var stackOrigin = 0;// 누적원금

/* getter 함수 정의
 * 반올림 후 통화단위로 표시
 */
function getOrigin(){
	document.write(Math.round(origin).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
function getInterest(){
	document.write(Math.round(interest).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
function getPayment(){
	document.write(Math.round(payment).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
function getBalance(){
	document.write(Math.round(balance).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
function getStackOrigin(){
	document.write(Math.round(stackOrigin).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

// 변수초기화 수행하는 함수
function init(){
	origin = 0;
	interest = 0;
	payment = 0;
	balance = -999;
	stackOrigin = 0;
}

// 전체 계산 로직을 수행하는 함수
function calc(investMoney, interestRate, loanDate){
	calPayment(investMoney, interestRate, loanDate); // 상환금 계산
	calInterest(interestRate, investMoney); // 이자 계산
	calOrigin(); // 원금 계산
	calBalance(); // 잔금 계산
	calStackOrigin(); // 누적 원금 계산
}

// 상환금 계산 함수
function calPayment(investMoney, interestRate, loanDate){
	payment = ((investMoney * (interestRate / 12))
			* (Math.pow(1 + interestRate / 12, loanDate)))
			/ (Math.pow(1 + interestRate / 12, loanDate) - 1);
}

// 이자 계산 함수
function calInterest(interestRate, investMoney){
	if(balance == -999) // 초회는 잔금을 투자금으로 설정
		balance = investMoney;
	interest = balance * interestRate / 12;
}

// 원금 계산 함수
function calOrigin(){
	origin = payment - interest;
}

// 잔금 계산 함수
function calBalance(){
	balance -= origin;
}

// 누적 원금 계산 함수
function calStackOrigin(){
	stackOrigin += origin;
}