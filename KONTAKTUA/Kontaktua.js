$(document).ready(function () {
    $("#contactForm").submit(function (event) {
        event.preventDefault(); // Evita el envío por defecto

        $(".error-message").hide();
        $("input, textarea").removeClass("error");

        let nombre = $("#izena").val().trim();
        let email = $("#emaila").val().trim();
        let mensaje = $("#mezua").val().trim();
        let valido = true;

        if (nombre === "") {
            $("#izena").addClass("error").next(".error-message").text("Izena beharrezkoa da.").show();
            valido = false;
        }

        if (email === "" || !email.includes("@") || !email.includes(".")) {
            $("#emaila").addClass("error").next(".error-message").text("Emaila baliogabea da.").show();
            valido = false;
        }

        if (mensaje === "") {
            $("#mezua").addClass("error").next(".error-message").text("Mezua idatzi behar duzu.").show();
            valido = false;
        }

        if (valido) {
            $("#successMessage").fadeIn().delay(2000).fadeOut();
            $("#contactForm")[0].reset();
        }
    });
});
