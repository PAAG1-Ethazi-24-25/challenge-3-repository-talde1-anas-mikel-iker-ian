<?php
include("test_connect_db.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usuario = $_POST["usuario"];
    $email = $_POST["email"];
    $pasahitza = password_hash($_POST["pasahitza"], PASSWORD_BCRYPT);

    $konexioa = KonektatuDatuBasera();
    
    $stmt = $konexioa->prepare("INSERT INTO erabiltzaileak (usuario, email, pasahitza) VALUES (?, ?, ?)");
    $stmt->bind_param("sss", $usuario, $email, $pasahitza);
    
    if ($stmt->execute()) {
        header("Location: sessioak_sinplea.php?erregistratuta=bai");
    } else {
        echo "Errorea erregistratzean: " . $konexioa -> error;
    }

    $stmt -> close();
    $konexioa -> close();
}
?>

<form action="erregistro_berria.php" method="post">
    Erabiltzailea: <input type="text" name="usuario" required><br>
    Email: <input type="email" name="email" required><br>
    Pasahitza: <input type="password" name="pasahitza" required><br>
    <input type="submit" value="Erregistratu">
</form>
