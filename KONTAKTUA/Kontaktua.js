$(document).ready(function () {
    $("#contactForm").submit(function (event) {
        event.preventDefault(); // Evita el envío por defecto

        $(".error-message").hide();
        $("input, textarea, select").removeClass("error");

        let izena = $("#izena").val().trim();
        let emaila = $("#emaila").val().trim();
        let telefonoa = $("#telefonoa").val().trim();
        let arrazoia = $("#arrazoia").val();
        let kokapena = $("#kokapena").val().trim();
        let mezua = $("#mezua").val().trim();
        let terminoOnarpena = $("#baldintzak").is(":checked");
        
        let valido = true;

        if (izena === "") {
            $("#izena").addClass("error").after("<div class='error-message'>Izena beharrezkoa da.</div>").show();
            valido = false;
        }

        if (emaila === "" || !emaila.includes("@") || !emaila.includes(".")) {
            $("#emaila").addClass("error").after("<div class='error-message'>Emaila baliogabea da.</div>").show();
            valido = false;
        }

        if (telefonoa !== "" && !/^\d{9}$/.test(telefonoa)) {
            $("#telefonoa").addClass("error").after("<div class='error-message'>Telefono zenbaki baliogabea.</div>").show();
            valido = false;
        }

        if (arrazoia === null) {
            $("#arrazoia").addClass("error").after("<div class='error-message'>Aukeratu kontaktu arrazoia.</div>").show();
            valido = false;
        }

        if (kokapena === "") {
            $("#kokapena").addClass("error").after("<div class='error-message'>Mesedez, idatzi zure kokapena.</div>").show();
            valido = false;
        }

        if (mezua === "") {
            $("#mezua").addClass("error").after("<div class='error-message'>Mezua idatzi behar duzu.</div>").show();
            valido = false;
        }

        if (!terminoOnarpena) {
            $("#baldintzak").addClass("error").after("<div class='error-message'>Baldintzak onartu behar dituzu.</div>").show();
            valido = false;
        }

        if (valido) {
            $("#successMessage").fadeIn().delay(2000).fadeOut();
            $("#contactForm")[0].reset();
        }
    });
});
