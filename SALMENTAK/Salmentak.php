<?php
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Langileak</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                                    <a class="nav-link" aria-current="page" href="../index.php">Hasiera</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link " href="../LANGILEAK/Langileak.php">Langileak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" href="../SALMENTAK/Salmentak.php">Salmentak</a>
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
        $sql = "SELECT salmentak.id_salmenta, produktuak.izena AS product_name, (SELECT izena FROM bezeroak WHERE id_bezero = salmentak.id_erosle) AS eroslea, salmentak.data, salmentak.salmenta_prezioa 
                FROM salmentak INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu 
                INNER JOIN bezeroak ON salmentak.id_saltzaile = bezeroak.id_bezero";
        $result = mysqli_query($link, $sql);

        // Mostrar las ventas en una tabla
        if (mysqli_num_rows($result) > 0) {
            echo "<table class='table'>";
            echo "<tr><th>ID</th><th>Produktu izena</th><th>Eroslea</th><th>Saldutako data</th><th>Saldutako Prezioa</th></tr>";
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr><td>" . $row['id_salmenta'] . "</td><td>" . $row['product_name'] . "</td><td>" . $row['eroslea'] . "</td><td>" . $row['data'] . "</td><td>" . $row['salmenta_prezioa'] . "€ </td></tr>";
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
            $id_bezero = $_POST['id_erosle'];  // Obtener el ID del comprador
            $data = $_POST['data'];  // Obtener la fecha de la venta
            $precio = $_POST['precio'];  // Obtener el precio de venta
        
            // Evitar SQL Injection
            $link = KonektatuDatuBasera();
            $id_producto = mysqli_real_escape_string($link, $id_producto);
            $id_bezero = mysqli_real_escape_string($link, $id_bezero);
            $data = mysqli_real_escape_string($link, $data);
            $precio = mysqli_real_escape_string($link, $precio);

            // Preparar la consulta SQL para insertar la venta
            $sql = "INSERT INTO salmentak (id_produktu, id_saltzaile, id_erosle, data, salmenta_prezioa) VALUES ('$id_producto', (SELECT id_saltzaile FROM produktuak where id_produktu='$id_producto') , '$id_bezero', '$data', '$precio')";

            // Ejecutar la consulta
            if (mysqli_errno($link) == 0) {
                echo "<div class='alert alert-warning' role='alert'>
                        Produktu hau dagoeneko salmenten taulan existitzen da!
                      </div>";
            } else {
                echo "<div class='alert alert-danger' role='alert'>
                        Errore egon txertatzean (" . mysqli_errno($link) . ")
                      </div>";
            }

            // Cerrar la conexión
            mysqli_close($link);
        }
        ?>

        <?php
        // Conectar y cargar productos y bezeroak para los desplegables
        $link = KonektatuDatuBasera();

        // productuak koknsulta
        $productos_query = mysqli_query($link, "SELECT id_produktu, izena FROM produktuak");

        // bezeroak koknsulta
        $bezeroak_query = mysqli_query($link, "SELECT id_bezero, izena FROM bezeroak");
        ?>

        <form action="" method="POST" style="text-align: left;">
            <!-- SELECT de productos -->
            <div class="form-group">
                <label for="id_producto">Produktu aukeratu:</label>
                <select id="id_producto" name="id_producto" class="form-control" style="width: 50%;" required>
                    <option value="">Aukeratu produktu bat</option>
                    <?php
                    while ($row = mysqli_fetch_assoc($productos_query)) {
                        echo "<option value='" . $row['id_produktu'] . "'>" . htmlspecialchars($row['izena']) . "</option>";
                    }
                    ?>
                </select>
            </div>

            <!-- erosleak selekta -->
            <div class="form-group">
                <label for="id_erosle">Eroslea aukeratu:</label>
                <select id="id_erosle" name="id_erosle" class="form-control" style="width: 50%;" required>
                    <option value="">Aukeratu erosle bat</option>
                    <?php
                    while ($row = mysqli_fetch_assoc($bezeroak_query)) {
                        echo "<option value='" . $row['id_bezero'] . "'>" . htmlspecialchars($row['izena']) . "</option>";
                    }
                    ?>
                </select>
            </div>

            <div class="form-group">
                <label for="data">Saldutako data:</label>
                <input type="datetime-local" id="data" name="data" class="form-control" style="width: 50%;" required>
            </div>

            <div class="form-group">
                <label for="precio">Saldutako Prezioa:</label>
                <input type="number" id="precio" name="precio" class="form-control" style="width: 50%;" step="0.01">
            </div>

            <br>
            <button type="submit" class="btn btn-primary">Sartu salmenta</button>
        </form>

        <?php
        // Cerrar conexión después del formulario
        mysqli_close($link);
        ?>

        <br>
        <a href="Salmentak.php"><button type="button" class="btn btn-secondary">Produktu lista berrikusi</button></a>
    </div>
</body>

</html>