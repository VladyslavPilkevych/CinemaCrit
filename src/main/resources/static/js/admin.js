const adminHeader = document.getElementById("adminHeaderPart");
const adminLogged = localStorage.getItem('admin');

if (adminLogged && adminLogged?.length > 0 && adminLogged === "admin") {
    if (noUserDiv && authUserDiv) {
        noUserDiv.setAttribute("hidden", "true");
        authUserDiv?.setAttribute("hidden", "true");
    }
    adminHeader?.removeAttribute("hidden");
}

const logoutAdminBtn = document.getElementById("logoutAdmin");
logoutAdminBtn.addEventListener('click', function() {
    localStorage.removeItem('admin');
    location.reload();
});
