<?php
    if (isset($_POST['delete_product'])) {
        $id_produktu = $_POST['id_produktu'];
        

        include("../test_connect_db.php");
        $link = KonektatuDatuBasera();
        
        $deleteProductQuery = "DELETE FROM produktuak WHERE id_produktu = ?";
        $stmt = mysqli_prepare($link, $deleteProductQuery);
        mysqli_stmt_bind_param($stmt, "i", $id_produktu);
        mysqli_stmt_execute($stmt);
        
        header("Location: Produktuak.php");
        exit();
    }
?>