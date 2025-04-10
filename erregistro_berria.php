<?php
include("test_connect_db.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $izena = $_POST["izena"];
    $email = $_POST["email"];
    $telefonoa = $_POST["telefonoa"];
    $helbidea = $_POST["helbidea"];
    $herria = $_POST["herria"];
    $postakodea = $_POST["postakodea"];
    $erabiltzailea = $_POST["erabiltzailea"];
    $pasahitza = $_POST["pasahitza"];

    $konexioa = KonektatuDatuBasera();
    
    $stmt = $konexioa->prepare("INSERT INTO bezeroak (izena, email, telefonoa, helbidea, herria, posta_kodea, erablitzaile_izena, pasahitza) VALUES (?, ?, ?,?,?,?,?,?)");
    $stmt->bind_param("ssssssss", $izena, $email, $telefonoa, $helbidea, $herria, $postakodea, $erabiltzailea, $pasahitza);
    
    if ($stmt->execute()) {
        header("Location: SAIOA_HASI/Saioa_Hasi.html");
    exit();
    } else {
        echo "Errorea erregistratzean: " . $konexioa -> error;
    }

    $stmt -> close();
    $konexioa -> close();
}
?>
