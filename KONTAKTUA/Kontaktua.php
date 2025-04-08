<?php 
session_start();
?>

<!DOCTYPE html>
<html lang="eu">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kontaktua</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="Kontaktua.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
                                    <a class="nav-link" aria-current="page" href="index.php">Hasiera</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" href="../KONTAKTUA/Kontaktua.php">Kontaktua</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../NIRE_PRODUKTUAK/Nire_Produktuak.php">Nire Produktuak</a>
                                </li>
                            </ul>

                            <div class="d-flex align-items-center ms-auto gap-3">
                                <p class="m-0 text-white">
                                    Ongi etorri, <?php echo htmlspecialchars($_SESSION["usuario"]); ?>.
                                </p>
                                <a href="logout.php" class="btn btn-danger">Itxi Saioa</a>
                            </div>

                        <?php endif; ?>

                    <?php else: ?>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="index.php">Hasiera</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../PRODUKTUAK/Produktuak.php">Produktuak</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="../KONTAKTUA/Kontaktua.php">Kontaktua</a>
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
    
        <div class="container">
            <h3>Kontaktuan jartzeko</h3>
            <form id="contactForm">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Izena:</label>
                    <input type="text" id="nombre" class="form-control">
                    <div class="error-message"></div>
                </div>
    
                <div class="mb-3">
                    <label for="email" class="form-label">Emaila:</label>
                    <input type="email" id="email" class="form-control">
                    <div class="error-message"></div>
                </div>
    
                <div class="mb-3">
                    <label for="mensaje" class="form-label">Mezua:</label>
                    <textarea id="mensaje" class="form-control" rows="4"></textarea>
                    <div class="error-message"></div>
                </div>
    
                <button type="submit" class="btn-submit">Bidali</button>
                <p id="successMessage" class="success-message">Formulario enviado correctamente.</p>
            </form>
        </div>
    
        <script src="contacto.js"></script>
    

    <footer>
        <p>© 2025 DevWorks. Ian, Mikel, Anas eta Iker.</p>
    </footer>

    <script src="Kontaktua.js"></script>
</body>

</html>
