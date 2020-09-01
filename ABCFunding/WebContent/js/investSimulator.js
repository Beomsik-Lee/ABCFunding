/**
 * Investment Simulation
 */
// Delete contents when the table have contents
function checkSimulTable() {
	$("#investTable").each(function() { 
		  var count = $(this).children().length;
		  if (count !=0) { 
			  $(this).empty(); 
	  	  }
		});
}

// Create the schedule for investments simulation
function startSimulation(loanDate) {
	$("#investTable").each(function() { 
		  var count = $(this).children().length;
		  if (count !=0) { 
			  $(this).empty(); 
	  	  }
	});
	var investMoney = $("#investMoney").val();
	var loanrate = 0.06; // A loan rate is fixed by 8%
	var repayRate = 0; // Repayment's rate of loan
	var paymentPrincipal = new Array(loanDate); // Payment principal for every month
	var rateMoney = new Array(loanDate); // Monthly profit
	var sumMonthlyPay = new Array(loanDate); // Sum of accumulate principal
	var balance = new Array(loanDate); // balance
	var makeinvestSchedule = ""; // To set HTML code
	var monthlyPay = 0; // monthly repayments
	var j = 1;

	// Redemption by equal installment of principal(monthly)
	monthlyPay = Math.round(((investMoney * (loanrate / 12)) * (Math.pow(1 + loanrate / 12, loanDate)))
			/ (Math.pow(1 + loanrate / 12, loanDate) - 1));

	// Rate of loan repayments
	var repayRateParent = Math.pow(1 + loanrate / 12, loanDate) * loanrate / 12;
	var repayRateChild = Math.pow(1 + loanrate / 12, loanDate) - 1;
	repayRate = repayRateParent / repayRateChild;

	// Set first value
	rateMoney[0] = Math.round(investMoney * loanrate / 12); // money of interest
	paymentPrincipal[0] = monthlyPay - rateMoney[0]; // principal of payments
	sumMonthlyPay[0] = paymentPrincipal[0]; // Sum of payments
	balance[0] = investMoney - paymentPrincipal[0];

	makeinvestSchedule += "<thead><tr><th>Rounds</th><th>Monthly Repayments</th><th>Monthly Principal</th><th>Monthly Profit</th><th>Accumulate repayments</th><th>Balance</th></tr></thead><tbody>";
	makeinvestSchedule += "<tr><td>" + j + "</td>";
	makeinvestSchedule += "<td>"
		+ monthlyPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "Won</td>";
	makeinvestSchedule += "<td>"
		+ paymentPrincipal[0].toString().replace(
				/\B(?=(\d{3})+(?!\d))/g, ",") + "Won</td>";
	makeinvestSchedule += "<td>"
		+ rateMoney[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "Won</td>";
	makeinvestSchedule += "<td>"
		+ sumMonthlyPay[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
		",") + "Won</td>";
	makeinvestSchedule += "<td>"
		+ balance[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
		+ "Won</td>";

	for (var i = 1; i < loanDate; i++) {
		paymentPrincipal[i] = Math.round(monthlyPay - (balance[i - 1])
				* (loanrate / 12)); // monthly principal
		rateMoney[i] = Math.round((balance[i - 1]) * (loanrate / 12)); // monthly profit

		// calculate last round
		if(i+1==loanDate){
			paymentPrincipal[i] = balance[i - 1]; // put previous round's balance to last round's principal of payments
			rateMoney[i] = monthlyPay - paymentPrincipal[i];
		}
		

		sumMonthlyPay[i] = Math.round(sumMonthlyPay[i - 1]
		+ paymentPrincipal[i]); // Accumulate principal
		balance[i] = Math.round(investMoney - sumMonthlyPay[i]); // balance

		makeinvestSchedule += "<tr><td>" + (i + 1) + "</td>"; // rounds	
		makeinvestSchedule += "<td>"
			+ monthlyPay.toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "Won</td>"; // monthly repayments
		makeinvestSchedule += "<td>"
			+ paymentPrincipal[i].toString().replace(
					/\B(?=(\d{3})+(?!\d))/g, ",") + "Won</td>"; // monthly repayments principal
		makeinvestSchedule += "<td>"
			+ rateMoney[i].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "Won</td>"; // monthly repayments profit
		makeinvestSchedule += "<td>"
			+ sumMonthlyPay[i].toString().replace(
					/\B(?=(\d{3})+(?!\d))/g, ",") + "Won</td>"; // Accumulate principal
		makeinvestSchedule += "<td>"
			+ balance[i].toString().replace(/\B(?=(\d{3})+(?!\d))/g,
			",") + "Won</td>"; // balance
		makeinvestSchedule += "</tr></tbody>";
	}
	$("#investTable").append(makeinvestSchedule);
}