<?php 
session_start();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="Produktuak.css">
    <title>Produktuak</title>
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
                <a href="../index.php">
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
                                    <a class="nav-link active" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../LANGILEAK/Langileak.php">Langileak</a>
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
                                <a class="nav-link " aria-current="page" href="../index.php">Hasiera</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../KONTAKTUA/Kontaktua.php">Kontaktua</a>
                            </li>
                        </ul>
                        <div class="d-flex align-items-center ms-auto gap-3"></div>
                        <button class="btn">
                            <a href="../SAIOA_HASI/Saioa_Hasi.html">
                                <img src="../HASIERA/img/imagen inicio sesion copy.png" class="img-fluid">
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

    <section class="container mt-5">
    <h2>PRODUKTUAK</h2>
    <br>

    <?php
        include("../test_connect_db.php");

        $link = KonektatuDatuBasera();

        $isAdmin = isset($_SESSION["admin"]) && $_SESSION["admin"];

        if ($isAdmin) {

            $sql = "SELECT 
                        produktuak.argazkia AS argazkia, 
                        produktuak.izena AS izena, 
                        produktuak.deskribapena AS deskribapena, 
                        produktuak.prezioa AS prezioa, 
                        kategoriak.izena AS kategoria, 
                        produktuak.egoera AS egoera, 
                        salmentak.id_erosle, 
                        produktuak.id_produktu
                    FROM produktuak
                    LEFT JOIN salmentak ON produktuak.id_produktu = salmentak.id_produktu
                    INNER JOIN kategoriak ON produktuak.id_kategoria = kategoriak.id_kategoria";
        } else {

            $sql = "SELECT produktuak.argazkia AS argazkia, 
                           produktuak.izena AS izena, 
                           produktuak.deskribapena AS deskribapena, 
                           produktuak.prezioa AS prezioa, 
                           kategoriak.izena AS kategoria, 
                           produktuak.egoera AS egoera, 
                           salduta, 
                           produktuak.id_produktu
                    FROM produktuak 
                    INNER JOIN kategoriak ON produktuak.id_kategoria = kategoriak.id_kategoria 
                    WHERE salduta = 0";
        }

        $result = mysqli_query($link, $sql);

        if (mysqli_num_rows($result) > 0) {
            echo "<div class='row' style='justify-content: space-around;'>";
            while ($row = mysqli_fetch_assoc($result)) {
            
                $erosleId = $isAdmin ? $row['id_erosle'] : null;
                $dataErosle = $isAdmin ? "data-erosle='" . $erosleId . "'" : "";
            
                echo "<div class='col-12 col-md-6 col-xl-4' style='margin-bottom: 10px'>
                        <div class='producto card' $dataErosle>";
            
                if ($row['argazkia'] == null) {
                    echo "<img src='./img/no_available.jpg' class='card-img-top'>";
                } else {
                    echo "<img src='./img/" . $row['argazkia'] . "' class='card-img-top'>";
                }
            
                echo    "<div class='card-body'>
                            <h5 class='card-title'>" . $row['izena'] . ": " . $row['prezioa'] . "€</h5>
                            <p class='descripcion' style='display:none;'>" . $row['deskribapena'] . "<br> <strong>egoera:</strong> ". $row['egoera'] ."</p>";
                            
                // Botón de eliminación para admins
                if ($isAdmin) {
                    echo "<form action='deleteProduktua.php' method='POST'>
                            <input type='hidden' name='id_produktu' value='" . $row['id_produktu'] . "' />
                            <button type='submit' name='delete_product' class='btn btn-danger'>Eliminar Producto</button>
                          </form>";
                }

                echo    " </div>
                    </div>
                </div>";
            }
            echo "</div>";
        } else {
            echo "<h3>Ez dira aurkitu produktuak datu basean.</h3>";
        }
    ?>  
</section>

<footer>
    <p>© 2025 DevWorks. Ian, Mikel, Anas eta Iker.</p>
</footer>

<script>
        $(document).ready(function () {
            
            $(".producto").click(function () {
                $(this).find(".descripcion").slideToggle();
            });

            <?php if (isset($_SESSION["admin"]) && $_SESSION["admin"]) : ?>
                
                $(".producto").each(function () {
                    let erosle = $(this).attr("data-erosle");
                    if (erosle && erosle !== "") {
                        $(this).css("background-color", "#f94848");
                    } else {
                        $(this).css("background-color", "#79f64e");
                    }
                });
            <?php endif; ?>
        });
    </script>

</body>

</html>