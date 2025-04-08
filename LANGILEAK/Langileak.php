<?php 
session_start();
?>

<!DOCTYPE html>
<html lang="eu">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Langileak</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="Langileak.css">
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

    <section class="container mt-5">
        <h2 class="mb-4 text-center">Gure Langile Taldea</h2>
        <div class="row">
            <!-- Langilea 1 -->
            <div class="col-md-4 mb-4">
                <div class="card text-center h-100 shadow">
                    <img src="./img/langilea1.jpg" class="card-img-top" alt="Langilea 1">
                    <div class="card-body">
                        <h5 class="card-title">Ane Irazabal</h5>
                        <p class="card-text">Proiektuaren koordinatzailea. Euskara irakaslea 10 urte baino gehiagoko esperientziarekin.</p>
                    </div>
                </div>
            </div>

            <!-- Langilea 2 -->
            <div class="col-md-4 mb-4">
                <div class="card text-center h-100 shadow">
                    <img src="./img/langilea2.jpg" class="card-img-top" alt="Langilea 2">
                    <div class="card-body">
                        <h5 class="card-title">Jon Azkue</h5>
                        <p class="card-text">Gizarte integratzailea. Gazteekin lan egiten du eta dinamika kulturalak antolatzen ditu.</p>
                    </div>
                </div>
            </div>

            <!-- Langilea 3 -->
            <div class="col-md-4 mb-4">
                <div class="card text-center h-100 shadow">
                    <img src="./img/langilea3.jpg" class="card-img-top" alt="Langilea 3">
                    <div class="card-body">
                        <h5 class="card-title">Miren Etxeberria</h5>
                        <p class="card-text">Boluntarioa eta itzultzailea. Gaztelania eta arabieratik euskara itzultzen du.</p>
                    </div>
                </div>
            </div>

            <!-- Añade más trabajadores copiando los bloques de arriba -->
        </div>
    </section>

    <footer class="text-center mt-5 p-4 bg-light">
        <p>© 2025 DevWorks. Ian, Mikel, Anas eta Iker.</p>
    </footer>

</body>

</html>
