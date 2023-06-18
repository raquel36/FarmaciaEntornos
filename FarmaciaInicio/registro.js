// Función para obtener la lista de pacientes
function getPatients() {
  var http = new XMLHttpRequest();
  let mail = sessionStorage.getItem("mail");
  let session = sessionStorage.getItem("session");
  http.open(
    "GET",
    "http://localhost:8080/farmaciaEntornos?mail=" +
      mail +
      "&session=" +
      session,
    true
  );
  http.send();

  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      try {
        let patientsMails = JSON.parse(this.responseText);
        let select = document.getElementById("patientSelect");
        patientsMails.forEach((mail) => {
          let option = document.createElement("option");
          option.value = mail;
          option.text = mail;
          select.appendChild(option);
        });
      } catch (e) {
        console.error("Parsing error:", e);
      }
    }
  };
}

// Función para obtener la lista de medicamentos
function getMedicines() {
  var http = new XMLHttpRequest();
  let mail = sessionStorage.getItem("mail");
  let session = sessionStorage.getItem("session");
  http.open(
    "GET",
    "http://localhost:8080/farmaciaEntornos/ServletMedication?mail=" +
      mail +
      "&session=" +
      session,
    true
  );
  http.send();

  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      try {
        let medicines = JSON.parse(this.responseText);
        let select = document.getElementById("medicationSelect");
        medicines.forEach((medicine) => {
          let option = document.createElement("option");
          option.value = medicine.id;
          option.text = medicine.name;
          select.appendChild(option);
        });
      } catch (e) {
        console.error("Parsing error:", e);
      }
    }
  };
}

// Función para enviar las peticiones
function enviar() {
  var http = new XMLHttpRequest();
  let mail = window.sessionStorage.getItem("mail");
  let session = window.sessionStorage.getItem("session");
  let chipId = document.getElementById("chipId").value;
  let mailP = document.getElementById("patientSelect").value;
  let id_medicine = document.getElementById("medicationSelect").value;
  let date = document.getElementById("dateLimit").value;
  http.open(
    "POST",
    "http://localhost:8080/farmaciaEntornos/ServletRelease",
    true
  );
  http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  http.send(
    "mail=" +
      mail +
      "&session=" +
      session +
      "&chipId=" +
      chipId +
      "&mailP=" +
      mailP +
      "&id_medicine=" +
      id_medicine +
      "&date=" +
      date
  );

  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      let message = this.responseText;
      document.getElementById("resultado").innerHTML = message;
    }
  };
}
// Función para digirnos a la página de gestión
function volverGestion() {
  window.location.href = "./gestio.html";
}
