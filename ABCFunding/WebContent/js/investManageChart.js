/* The chart JS for investment's management */

// Declare variables
var chart1Data = [['Investment No.','Investment Fund','Accumulate Profit']];
var chart2Data = [['Progress', 'Rate']];
function chart1Patch(arr){
	// Parsing string to integer but last element is null
	for(var idx = 0; idx < arr.length; idx++){
		arr[idx] = parseInt(arr[idx]);
	}
	chart1Data.push(arr);
}

function chart2Patch(arr){
	chart2Data.push(arr);
}

// Load google chart
google.charts.load('current', {packages: ['corechart','line']});

// Add callback
google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);

// Callback for create and add data table
function drawChart1(){
	var data = new google.visualization.arrayToDataTable(chart1Data);

    var options = {
      chart: {
        title: 'Each Info',
        subtitle: 'Each investment and accumulate profit'
      },
      width: 700,
      height: 350
    };

    var chart = new google.charts.Line(document.getElementById('invest-chart1'));

    chart.draw(data, options);
}

function drawChart2(){
	var data = google.visualization.arrayToDataTable(chart2Data);
	
	var options = {
	  legend: { position: 'none' },
	  title: 'Rate for total repayments',
	  pieHole: 0.4,
	  width: 400,
      height: 350
	};
	
	var chart = new google.visualization.PieChart(document.getElementById('invest-chart2'));
	chart.draw(data, options);
}