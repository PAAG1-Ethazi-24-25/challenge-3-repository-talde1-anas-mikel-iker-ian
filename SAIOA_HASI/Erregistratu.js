function FormularioaBaieztatu() {
    const mezua = document.getElementById("mezua");
    
    const izena = document.getElementById("izena").value.trim();
    const email = document.getElementById("email").value.trim();
    const telefonoa = document.getElementById("telefonoa").value.trim();
    const helbidea = document.getElementById("helbidea").value.trim();
    const herria = document.getElementById("herria").value.trim();
    const postakodea = document.getElementById("postakodea").value.trim();
    const erabiltzaile = document.getElementById("erabiltzaile").value.trim();
    const pasahitza = document.getElementById("pasahitza").value;
    const pasahitza2 = document.getElementById("pasahitza2").value;
    const sexua = document.querySelector("input[name='sexua']:checked");
  
    if (!izena || !email || !erabiltzaile || !pasahitza || !pasahitza2) {
      mezua.textContent = "Mesedez, bete  eremu denak";
      mezua.style.color = "red";
      return false; 
    }
  
    if (pasahitza !== pasahitza2) {
      mezua.textContent = "Pasahitzak ez datoz bat";
      mezua.style.color = "red";
      return false;
    }
  
    if (!sexua) {
      mezua.textContent = "Mesedez, aukeratu zure sexua";
      mezua.style.color = "red";
      return false;
    }
  
    
    mezua.textContent = "";
    return true;
  }
  