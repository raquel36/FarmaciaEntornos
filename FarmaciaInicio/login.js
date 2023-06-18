function enviar() {
  var http = new XMLHttpRequest();

  let mail = document.getElementById("mail").value;
  let pass = document.getElementById("pass").value;
  http.open(
    "GET",
    "http://localhost:8080/farmaciaEntornos/Login?mail=" +
      mail +
      "&pass=" +
      pass,
    true
  );

  http.send();

  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      let session = this.responseText;
      if (session != "0") {
        window.sessionStorage.setItem("mail", mail);
        window.sessionStorage.setItem("session", session);
        document.getElementById("resultado").innerHTML = "Loggin Correcta";

        window.location.href = "./gestion.html";
      } else {
        document.getElementById("resultado").innerHTML = "Loggin Incorrecta";
      }
    }
  };
}
