const noUserDiv = document.getElementById("noUserDiv");
const authUserDiv = document.getElementById("authUserDiv");
const authUserName = document.getElementById("authUserName");
const userLogged = localStorage.getItem('user');

if (userLogged && userLogged?.length > 0) {
    noUserDiv.setAttribute("hidden", "true");
    authUserDiv.removeAttribute("hidden");
    authUserName.innerText = userLogged;
} else {
    console.log(userLogged);
    noUserDiv.removeAttribute("hidden");
    authUserDiv.setAttribute("hidden", "true");
}

const logoutBtn = document.getElementById("logout");
logoutBtn.addEventListener('click', function() {
    localStorage.removeItem('user');
    location.reload();
});