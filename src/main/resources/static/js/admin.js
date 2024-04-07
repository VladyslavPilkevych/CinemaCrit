const adminHeader = document.getElementById("adminHeaderPart");
const adminLogged = localStorage.getItem('admin');

if (adminLogged && adminLogged?.length > 0 && adminLogged === "admin") {
    noUserDiv.setAttribute("hidden", "true");
    authUserDiv.setAttribute("hidden", "true");
    adminHeader.removeAttribute("hidden");
}
