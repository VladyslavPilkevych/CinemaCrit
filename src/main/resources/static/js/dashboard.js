let myChart;

function drawChart() {
  'use strict'

  const ctx = document.getElementById('myChart')

  if(myChart){
    myChart.destroy();
  }

  myChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: [
        'Simple Users',
        'Admin Users',
        'Super Users',
        'Total Users',
        'Movies'
      ],
      datasets: [{
        data: [
          document.getElementById('simpleUsersCount').textContent,
          document.getElementById('adminUsersCount').textContent,
          document.getElementById('superUsersCount').textContent,
          document.getElementById('usersCount').textContent,
          document.getElementById('moviesCount').textContent
        ],
        lineTension: 0,
        backgroundColor: '#007bff',
        borderColor: '#007bff',
        borderWidth: 0,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      plugins: {
        legend: {
          display: false
        },
        tooltip: {
          boxPadding: 3
        }
      }
    }
  })
}

const map = new Map();

map.set(document.getElementById('dashboard'), document.getElementById('dashboard-content'));
map.set(document.getElementById('block-users'), document.getElementById('block-users-content'));

const tabContent = document.getElementById('tab-content');

let config = {childList: true, subtree: true};

let callback = function(mutationsList, observer) {
  for(let mutation of mutationsList) {
    if(mutation.type === 'childList'){
      if(document.getElementById('myChart')) {
        drawChart();
        observer.disconnect();
      }
    }
  }
}

let observer = new MutationObserver(callback);

observer.observe(tabContent, config);

function toggleTab() {
  localStorage.setItem('lastTab', event.target.id);
  toggleActiveTab();
}

function toggleActiveTab() {
  let c;
  if((c = localStorage.getItem('lastTab')) === null){
    localStorage.setItem('lastTab', 'dashboard');
    c = 'dashboard';
  }

  map.forEach((value, key) => {
    key.classList.remove('active');
    value.hidden = true;
    value.remove();
    if(key.id === c){
      key.classList.add('active');
      tabContent.appendChild(value);
      value.hidden = false;
    }
  });
}