$(document).ready(function () {
    $("#contactForm").submit(function (event) {
        event.preventDefault(); // Evita el envío por defecto

        $(".error-message").hide();
        $("input, textarea").removeClass("error");

        let nombre = $("#nombre").val().trim();
        let email = $("#email").val().trim();
        let mensaje = $("#mensaje").val().trim();
        let valido = true;

        if (nombre === "") {
            $("#nombre").addClass("error").next(".error-message").text("Izena beharrezkoa da.").show();
            valido = false;
        }

        if (email === "" || !email.includes("@") || !email.includes(".")) {
            $("#email").addClass("error").next(".error-message").text("Emaila baliogabea da.").show();
            valido = false;
        }

        if (mensaje === "") {
            $("#mensaje").addClass("error").next(".error-message").text("Mezua idatzi behar duzu.").show();
            valido = false;
        }

        if (valido) {
            $("#successMessage").fadeIn().delay(2000).fadeOut();
            $("#contactForm")[0].reset();
        }
    });
});
