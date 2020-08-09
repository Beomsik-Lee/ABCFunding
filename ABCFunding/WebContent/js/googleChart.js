/* 구글차트 사용을 위한 자바스크립트 */

// 변수 선언
var investChartData = [['투자코드', '투자금', '누적원금', '누적이자']];
var loanChartData = [['대출코드', '누적상환원금', '누적상환이자금',{ role: 'annotation' }]];

function investPatch(arr){
	investChartData.push(arr);
}

function loanPatch(arr){
	// 문자열을 정수로 파싱. 마지막 요소는 null이므로 제외
	for(var idx = 0; idx < arr.length - 1; idx++){
		arr[idx] = parseInt(arr[idx]);
	}
	loanChartData.push(arr);
}

// 라이브러리 로딩
google.charts.load('current', {packages: ['corechart','bar']});

// 차트마다 콜백함수 등록
google.charts.setOnLoadCallback(drawInvestChart);
google.charts.setOnLoadCallback(drawLoanChart);

// 데이터 테이블 생성 및 추가하는 콜백함수
// 투자현황 그래프
function drawInvestChart(){
	var data = google.visualization.arrayToDataTable(investChartData);

     var options = {
    	legend: { position: 'none' },
       chart: {
         title: '투자현황 그래프',
         subtitle: '투자마다 원금, 누적원금, 누적 이자금 표시',
       },
      	'width':500,
		'height':350,
		bars: 'horizontal'
     };

     var chart = new google.charts.Bar(document.getElementById('chart_invest'));

     chart.draw(data, options);
}

// 대출현황 그래프
function drawLoanChart(){
	// 데이터 생성 및 추가
	var data = google.visualization.arrayToDataTable(loanChartData);
    
    // 차트 옵션 설정
	var options = {	'title':'대출현황 그래프',
					'width':540,
					'height':400,
					legend: { position: 'top', maxLines: 3 },
			        bar: { groupWidth: '75%' },
			        isStacked: true,};
    
    // 지정한 div에 적용할 구글 차트 인스턴스 생성
	var chart = new google
					.visualization
					.ColumnChart(document.getElementById('chart_loan'));
    // 데이터 및 옵션 적용
    chart.draw(data, options);
}