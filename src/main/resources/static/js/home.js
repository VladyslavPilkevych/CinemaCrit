const map = new Map();
map.set(document.getElementById('home'), document.getElementById('home-content'));
if (document.getElementById('create') && document.getElementById('create-content')) {
    map.set(document.getElementById('create'), document.getElementById('create-content'));
}
map.set(document.getElementById('movies'), document.getElementById('movies-content'));
if (document.getElementById('users') && document.getElementById('users-content')) {
    map.set(document.getElementById('users'), document.getElementById('users-content'));
}

const tabContent = document.getElementById('tab-content');

function toggleTab() {
    localStorage.setItem('homeLastTab', event.target.id);
    loadLastTab();
}

function loadLastTab() {
    let lastTab = localStorage.getItem('homeLastTab');
    if(!lastTab) {
        lastTab = 'home';
        localStorage.setItem('homeLastTab', lastTab);
    }
    console.log(map);

    map.forEach((value, key) => {
        key.classList.remove('active');
        key.classList.add('text-white');
        value.remove();
        value.hidden = true;
        if(key.id === lastTab) {
            key.classList.remove('text-white');
            key.classList.add('active');
            tabContent.appendChild(value);
            value.hidden = false;
        }
    });
}