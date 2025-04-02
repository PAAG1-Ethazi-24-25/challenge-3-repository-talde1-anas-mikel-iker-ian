<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Sale</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
    <script href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h1>All Sales</h1>

        <?php
        // Incluir la conexión a la base de datos
        include("../test_connect_db.php");

        // Conectar a la base de datos
        $link = KonektatuDatuBasera();

        // Obtener todas las ventas de la base de datos
        $sql = "SELECT salmentak.id_salmenta, produktuak.izena AS product_name, bezeroak.izena AS buyer_name, salmentak.data, salmentak.salmenta_prezioa 
                FROM salmentak
                INNER JOIN produktuak ON salmentak.id_produktu = produktuak.id_produktu
                INNER JOIN bezeroak ON salmentak.id_bezero = bezeroak.id_bezero";
        $result = mysqli_query($link, $sql);

        // Mostrar las ventas en una tabla
        if (mysqli_num_rows($result) > 0) {
            echo "<table class='table'>";
            echo "<tr><th>ID</th><th>Product Name</th><th>Buyer</th><th>Sale Date</th><th>Sale Price</th></tr>";
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr><td>" . $row['id_salmenta'] . "</td><td>" . $row['product_name'] . "</td><td>" . $row['buyer_name'] . "</td><td>" . $row['data'] . "</td><td>" . $row['salmenta_prezioa'] . "</td></tr>";
            }
            echo "</table>";
        } else {
            echo "<h3>No sales found in the database.</h3>";
        }

        // Cerrar la conexión a la base de datos
        mysqli_free_result($result);
        mysqli_close($link);
        ?>

        <!-- Formulario para agregar una nueva venta -->
        <h2 class="mt-5">Insert New Sale</h2>

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
                        Sale successfully added to the database!
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

        <form action="" method="POST">
            <div class="form-group">
                <label for="id_producto">Product ID:</label>
                <input type="number" id="id_producto" name="id_producto" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="id_bezero">Buyer ID:</label>
                <input type="number" id="id_bezero" name="id_bezero" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="data">Sale Date:</label>
                <input type="datetime-local" id="data" name="data" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="precio">Sale Price:</label>
                <input type="number" id="precio" name="precio" class="form-control" step="0.01" required>
            </div>
            <button type="submit" class="btn btn-primary">Insert Sale</button>
        </form>

        <br>
        <a href="../index.php"><button type="button" class="btn btn-secondary">Back to Product List</button></a>
    </div>
</body>
</html>
