<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Sale</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <script href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="Salmentak.css">

</head>

<body>
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary menua">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="izenburua">
                <a href="index.php">
                    <img src="../HASIERA/img/ChatGPT Image 1 abr 2025, 12_55_45.png" class="img-fluid">
                </a>
            </div>
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <?php
                    if (isset($_SESSION["usuario"])): ?>
                        <?php if ($_SESSION["admin"]): ?>
                            <!-- Opciones de administrador -->
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="index.php">Hasiera</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" href="../LANGILEAK/Langileak.php">Langileak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../SALMENTAK/Salmentak.php">Salmentak</a>
                                </li>
                            </ul>
                            <div class="d-flex align-items-center ms-auto gap-3">
                                <p class="m-0 text-white">
                                    Ongi etorri, <?php echo htmlspecialchars($_SESSION["usuario"]); ?>.
                                </p>
                                <a href="../logout.php" class="btn btn-danger">Itxi Saioa</a>
                            </div>
                        <?php else: ?>
                            <!-- Opciones de usuario -->
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link" aria-current="page" href="../index.php">Hasiera</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../KONTAKTUA/Kontaktua.php">Kontaktua</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../NIRE_PRODUKTUAK/Nire_Produktuak.php">Nire Produktuak</a>
                                </li>
                            </ul>

                            <div class="d-flex align-items-center ms-auto gap-3">
                                <p class="m-0 text-white">
                                    Ongi etorri, <?php echo htmlspecialchars($_SESSION["usuario"]); ?>.
                                </p>
                                <a href="../logout.php" class="btn btn-danger">Itxi Saioa</a>
                            </div>

                        <?php endif; ?>

                    <?php else: ?>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="index.php">Hasiera</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../KONTAKTUA/Kontaktua.php">Kontaktua</a>
                            </li>
                        </ul>
                        <div class="d-flex align-items-center ms-auto gap-3"></div>
                        <button class="btn">
                            <a href="./SAIOA_HASI/Saioa_Hasi.html">
                                <img src="./HASIERA/img/imagen inicio sesion copy.png" class="img-fluid">
                            </a>
                        </button>
                        <p class="m-0 text-white">
                        </p>
                    </div>
                </div>
            <?php endif; ?>
            </div>
            </div>
        </nav>
    </header>

    <div class="container mt-5">
        <h1>Salmenta Guztiak</h1>

        <?php
        // Incluir la conexión a la base de datos
        include("../test_connect_db.php");

        // Conectar a la base de datos
        $link = KonektatuDatuBasera();

        // Obtener todas las ventas de la base de datos
        $sql = "SELECT salmentak.id_salmenta, produktuak.izena AS product_name, bezeroak.izena AS buyer_name, salmentak.data, salmentak.salmenta_prezioa 
                FROM salmentak
                INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu
                INNER JOIN bezeroak ON salmentak.id_saltzaile = bezeroak.id_bezero";
        $result = mysqli_query($link, $sql);

        // Mostrar las ventas en una tabla
        if (mysqli_num_rows($result) > 0) {
            echo "<table class='table'>";
            echo "<tr><th>ID</th><th>Produktu izena</th><th>Eroslea</th><th>Saldutako data</th><th>Saldutako Prezioa</th></tr>";
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr><td>" . $row['id_salmenta'] . "</td><td>" . $row['product_name'] . "</td><td>" . $row['buyer_name'] . "</td><td>" . $row['data'] . "</td><td>" . $row['salmenta_prezioa'] . "</td></tr>";
            }
            echo "</table>";
        } else {
            echo "<h3>Ez dira aurkitu salmentik datu basean.</h3>";
        }

        // Cerrar la conexión a la base de datos
        mysqli_free_result($result);
        mysqli_close($link);
        ?>

        <!-- Formulario para agregar una nueva venta -->
        <h2 class="mt-5">Sartu salmenta berri bat</h2>

        <?php
        // Comprobar si se ha enviado el formulario
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $id_producto = $_POST['id_producto'];  // Obtener el ID del producto
            $id_bezero = $_POST['id_bezero'];  // Obtener el ID del comprador
            $data = $_POST['data'];  // Obtener la fecha de la venta
            $precio = $_POST['precio'];  // Obtener el precio de venta
        
            // Evitar SQL Injection
            $link = KonektatuDatuBasera();
            $id_producto = mysqli_real_escape_string($link, $id_producto);
            $id_bezero = mysqli_real_escape_string($link, $id_bezero);
            $data = mysqli_real_escape_string($link, $data);
            $precio = mysqli_real_escape_string($link, $precio);

            // Preparar la consulta SQL para insertar la venta
            $sql = "INSERT INTO salmentak (id_produktu, id_bezero, data, salmenta_prezioa) VALUES ('$id_producto', '$id_bezero', '$data', '$precio')";

            // Ejecutar la consulta
            if (mysqli_query($link, $sql)) {
                echo "<div class='alert alert-success' role='alert'>
                        Salmenta berria sartu da datu basean!
                      </div>";
            } else {
                echo "<div class='alert alert-danger' role='alert'>
                        Error: " . mysqli_error($link) . "
                      </div>";
            }

            // Cerrar la conexión
            mysqli_close($link);
        }
        ?>

        <form action="" method="POST" style="align-items: left;">
            <div class="form-group">
                <label for="id_producto">Produktu ID:</label>
                <input type="number" id="id_producto" name="id_producto" class="form-control" style="width: 50%;" required>
            </div>
            <div class="form-group">
                <label for="id_bezero">Erosle ID:</label>
                <input type="number" id="id_bezero" name="id_bezero" class="form-control" style="width: 50%;" required>
            </div>
            <div class="form-group">
                <label for="data">Saldutako data:</label>
                <input type="datetime-local" id="data" name="data" class="form-control" style="width: 50%;" required>
            </div>
            <div class="form-group">
                <label for="precio">Saldutako Prezioa:</label>
                <input type="number" id="precio" name="precio" class="form-control" style="width: 50%;" step="0.01" required>
            </div>
            <button type="submit" class="btn btn-primary">Sartu salmenta</button>
        </form>

        <br>
        <a href="../index.php"><button type="button" class="btn btn-secondary">Produktu lista berrikusi</button></a>
    </div>
</body>

</html>