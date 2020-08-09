/* 구글차트 사용을 위한 자바스크립트 */

// 변수 선언
var chart1Data = [['투자번호','투자금','누적 수익금']];
var chart2Data = [['진행상황', '해당 비율']];
function chart1Patch(arr){
	// 문자열을 정수로 파싱. 마지막 요소는 null이므로 제외
	for(var idx = 0; idx < arr.length; idx++){
		arr[idx] = parseInt(arr[idx]);
	}
	chart1Data.push(arr);
}

function chart2Patch(arr){
	chart2Data.push(arr);
}

// 라이브러리 로딩
google.charts.load('current', {packages: ['corechart','line']});

// 차트마다 콜백함수 등록
google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);

// 데이터 테이블 생성 및 추가하는 콜백함수
function drawChart1(){
	var data = new google.visualization.arrayToDataTable(chart1Data);

    var options = {
      chart: {
        title: '투자 당 정보',
        subtitle: '각 투자 건수 당 투자금과 누적 수익금'
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
	  title: '전체 상환상태 비율',
	  pieHole: 0.4,
	  width: 400,
      height: 350
	};
	
	var chart = new google.visualization.PieChart(document.getElementById('invest-chart2'));
	chart.draw(data, options);
}