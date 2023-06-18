function logOut() {
  sessionStorage.removeItem("mail");
  sessionStorage.removeItem("session");
  document.getElementById("resultat").innerHTML = "Logged Out";
  // redirect to login page
  window.location.href = "./login.html";
}

function alta() {
  window.location.href = "./alta.html";
}

function getTable() {
  var http = new XMLHttpRequest();
  let mail = sessionStorage.getItem("mail");
  let session = sessionStorage.getItem("session");
  http.open(
    "GET",
    "http://localhost:8080/farmaciaEntornos/ServletXips?mail=" +
      mail +
      "&session=" +
      session,
    true
  );
  http.send();

  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      let table = this.responseText;
      document.getElementById("resultado").innerHTML = table;
    }
  };
}
