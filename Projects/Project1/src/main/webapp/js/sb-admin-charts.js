function getTotalAppliedReimbursments() {
  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var amountData = JSON.parse(xhr.responseText);
      var currentMax = 4000;
      for (let w = 0; w < amountData.length; w++) {
        if (amountData[w] > currentMax) {
          currentMax = amountData[w];
        }
        updateChart(amountData[w]);
        changeChartScale(currentMax);
      }
    }
  }
  xhr.open('GET', '/Project1/totalapplied');
  xhr.send();
}

function getTotalApprovedReimbursments() {
  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4 && xhr.status == 200) {
      var amountData2 = JSON.parse(xhr.responseText);
      for (let y = 0; y < amountData2.length; y++) {
        updateChart2(amountData2[y]);
      }
    }
  }
  xhr.open('GET', '/Project1/totalapproved');
  xhr.send();
}

function updateChart(updateWithThis) {
  myLineChart.data.datasets[0].data.push(updateWithThis);
  myLineChart.update();
}

function changeChartScale(maxNumber) {
  myLineChart.options.scales.yAxes[0].ticks.max = maxNumber * 1.20;
  myLineChart.update();
}

function updateChart2(updateWithThis2) {
  myLineChart.data.datasets[1].data.push(updateWithThis2);
  myLineChart.update();
}

// Chart.js scripts
// -- Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';
// -- Area Chart Example
var ctx = document.getElementById("myAreaChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    datasets: [{
      label: "Reimbursements Applied (in $)",
      lineTension: 0.3,
      backgroundColor: "rgba(2,117,216,0.2)",
      borderColor: "rgba(2,117,216,1)",
      pointRadius: 5,
      pointBackgroundColor: "rgba(2,117,216,1)",
      pointBorderColor: "rgba(255,255,255,0.8)",
      pointHoverRadius: 5,
      pointHoverBackgroundColor: "rgba(2,117,216,1)",
      pointHitRadius: 20,
      pointBorderWidth: 2,
      responsive: true,
      data: []
    },
    {
      label: "Reimbursements Approved (in $)",
      lineTension: 0.3,
      backgroundColor: "rgba(242,105,38,0.2)",
      borderColor: "rgba(242,105,38,1)",
      pointRadius: 5,
      pointBackgroundColor: "rgba(242,105,38,1)",
      pointBorderColor: "rgba(255,255,255,0.8)",
      pointHoverRadius: 5,
      pointHoverBackgroundColor: "rgba(2,117,216,1)",
      pointHitRadius: 20,
      pointBorderWidth: 2,
      responsive: true,
      data: []
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'date'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 7
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 4000,
          maxTicksLimit: 5
        },
        gridLines: {
          color: "rgba(0, 0, 0, .125)",
        }
      }],
    },
    legend: {
      display: false
    }
  }
});