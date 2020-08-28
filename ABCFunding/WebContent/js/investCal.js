/* The calculation of schedule of repayments */

/* Variable declare */
var origin = 0;		// principal
var interest = 0;	// interest
var payment = 0;	// repayments
var balance = -999;	// balance
var stackOrigin = 0;// accumulated principal

/* Getter define
 * round and show as currency
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

// Initialize
function init(){
	origin = 0;
	interest = 0;
	payment = 0;
	balance = -999;
	stackOrigin = 0;
}

// Calculate the all logic process
function calc(investMoney, interestRate, loanDate){
	calPayment(investMoney, interestRate, loanDate); // calculate the repayments
	calInterest(interestRate, investMoney); // calculate the interest
	calOrigin(); // calculate the principal
	calBalance(); // calculate the balance
	calStackOrigin(); // calculate the accumulated principal
}

// calculate the repayments with investment fund, interest rate, and loan date
function calPayment(investMoney, interestRate, loanDate){
	payment = ((investMoney * (interestRate / 12))
			* (Math.pow(1 + interestRate / 12, loanDate)))
			/ (Math.pow(1 + interestRate / 12, loanDate) - 1);
}

// calculate the interest with interest rate and investment fund
function calInterest(interestRate, investMoney){
	if(balance == -999) // Set balance to investment fund for the first
		balance = investMoney;
	interest = balance * interestRate / 12;
}

// calculate the principal
function calOrigin(){
	origin = payment - interest;
}

// calculate the balance
function calBalance(){
	balance -= origin;
}

// calculate the accumulated principal
function calStackOrigin(){
	stackOrigin += origin;
}