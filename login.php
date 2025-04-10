<?php
session_start();
include("test_connect_db.php"); 

$conn = KonektatuDatuBasera();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usuario = $_POST["usuario"];
    $contraseña = $_POST["contraseña"];

    $stmt = $conn->prepare("SELECT * FROM erabiltzaileak WHERE erabiltzailea = ?");
    $stmt->bind_param("s", $usuario);
    $stmt->execute();
    $resultado = $stmt->get_result();

    if ($resultado->num_rows == 1) {
        $usuario_bd = $resultado->fetch_assoc();
        if ($contraseña === $usuario_bd["pasahitza"]) {
            $_SESSION["usuario"] = $usuario_bd["erabiltzailea"];
            $_SESSION["admin"] = ($usuario_bd["administratzailea"] == "bai");
            header("Location: index.php"); // o donde quieras redirigir
            exit;
        } else {
            echo "Contraseña incorrecta.";
        }
    } else {
        echo "Usuario no encontrado.";
    }
}
?>