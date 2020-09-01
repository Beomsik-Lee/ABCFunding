/* The chart JS for investment's management */

// Declare variables
var investChartData = [['Investment Code', 'Investment Fund', 'Accumulate Principal', 'Accumulate Profit']];
var loanChartData = [['Loan Code', 'Accumulate Repayments', 'Accumulate Repayments Profit',{ role: 'annotation' }]];

function investPatch(arr){
	investChartData.push(arr);
}

function loanPatch(arr){
	// Parsing string to integer but last element is null
	for(var idx = 0; idx < arr.length - 1; idx++){
		arr[idx] = parseInt(arr[idx]);
	}
	loanChartData.push(arr);
}

//Load google chart
google.charts.load('current', {packages: ['corechart','line']});

// Add callback
google.charts.setOnLoadCallback(drawInvestChart);
google.charts.setOnLoadCallback(drawLoanChart);

// Callback for create and add data table
// Chart for investments
function drawInvestChart(){
	var data = google.visualization.arrayToDataTable(investChartData);

     var options = {
    	legend: { position: 'none' },
       chart: {
         title: 'Chart for investments',
         subtitle: 'Principal, Accumulate principal, Accumulate Profit',
       },
      	'width':500,
		'height':350,
		bars: 'horizontal'
     };

     var chart = new google.charts.Bar(document.getElementById('chart_invest'));

     chart.draw(data, options);
}

// Chart for loan
function drawLoanChart(){
	var data = google.visualization.arrayToDataTable(loanChartData);
    
	var options = {	'title':'대출현황 그래프',
					'width':540,
					'height':400,
					legend: { position: 'top', maxLines: 3 },
			        bar: { groupWidth: '75%' },
			        isStacked: true,};
    
	var chart = new google
					.visualization
					.ColumnChart(document.getElementById('chart_loan'));
	
    chart.draw(data, options);
}