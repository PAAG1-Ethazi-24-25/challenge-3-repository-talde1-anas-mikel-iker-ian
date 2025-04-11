<?php
session_start();
$idioma = $_GET['lang'] ?? 'eu'; // Euskera por defecto
?>
<!DOCTYPE html>
<html lang="<?php echo $idioma === 'en' ? 'en' : 'eu'; ?>">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="index.css">
    <title>Hasiera</title>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary menua">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="izenburua">
            <a href="index.php">
                <img src="./HASIERA/img/ChatGPT Image 1 abr 2025, 12_55_45.png" class="img-fluid">
            </a>
        </div>
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.php">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./PRODUKTUAK/Produktuak.php">Products</a>
                    </li>
                    <?php if (isset($_SESSION["usuario"])): ?>
                        <?php if ($_SESSION["admin"]): ?>
                            <li class="nav-item">
                                <a class="nav-link" href="./LANGILEAK/Langileak.php">Staff</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="./SALMENTAK/Salmentak.php">Sales</a>
                            </li>
                        <?php else: ?>
                            <li class="nav-item">
                                <a class="nav-link" href="./KONTAKTUA/Kontaktua.php">Contact</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="./NIRE_PRODUKTUAK/Nire_Produktuak.php">My Products</a>
                            </li>
                        <?php endif; ?>
                    <?php else: ?>
                        <li class="nav-item">
                            <a class="nav-link" href="./KONTAKTUA/Kontaktua.php">Contact</a>
                        </li>
                    <?php endif; ?>
                </ul>

                <div class="d-flex align-items-center ms-auto gap-3">
                    <?php if (isset($_SESSION["usuario"])): ?>
                        <p class="m-0 text-white">
                            Welcome, <?php echo htmlspecialchars($_SESSION["usuario"]); ?>.
                        </p>
                        <a href="logout.php" class="btn btn-danger">Log Out</a>
                    <?php else: ?>
                        <button class="btn">
                            <a href="./SAIOA_HASI/Saioa_Hasi.html">
                                <img src="./HASIERA/img/imagen inicio sesion copy.png" class="img-fluid">
                            </a>
                        </button>
                    <?php endif; ?>
                    <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Language
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="index.php">Euskera</a></li>
                        <li><a class="dropdown-item" href="indexIngles.php">English</a></li>
                    </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</header>

<section>
    <div class="row">
        <div class="navbar1">
            <div id="carouselExampleSlidesOnly" class="carousel slide col-12 col-s-12 img-fluid" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="./HASIERA/img/mercado_segunda_mano_Eibar.jpg" class="img-fluid">
                    </div>
                    <div class="carousel-item">
                        <img src="./HASIERA/img/rastro-3.jpg" class="img-fluid">
                    </div>
                    <div class="carousel-item">
                        <img src="./HASIERA/img/78881636--1248x906.jpg" class="img-fluid">
                    </div>
                    <div class="carousel-item">
                        <img src="./HASIERA/img/463212931_3928246330727971_8244200252763828714_n.jpg" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="row">
        <div class="hutsik col-1 col-s-12"></div>
        <div class="container col-4 col-s-12">
            <h2>WHO ARE WE?</h2>
            <br>
            <h4>
                Our association was created with the commitment to help young immigrants in the region, supporting them
                in their integration and education by learning Basque and participating in extracurricular activities.
                We know how important it is to feel part of a community, and our goal is to provide resources and tools
                that promote effective and enriching integration.
            </h4>
        </div>
        <div class="hutsik col-1 col-s-12"></div>
        <div class="hutsik col-1 col-s-12"></div>
        <div class="container col-4 col-s-12">
            <img src="./HASIERA/img/rethinkvintage-1.jpg" class="img-fluid">
        </div>
        <div class="hutsik col-1 col-s-12"></div>
    </div>
</section>

<section>
    <div class="row">
        <div class="hutsik col-1"></div>
        <div class="container col-4 col-s-12">
            <img src="./HASIERA/img/Azokaren_kartela.jpeg" class="img-fluid">
        </div>
        <div class="hutsik col-1"></div>
        <div class="hutsik col-1"></div>
        <div class="container col-4 col-s-12">
            <h2>WHAT AND HOW DO WE DO IT?</h2>
            <br>
            <h4>
                Our association helps immigrant youth in Debabarrena with integration and education, focusing especially
                on strengthening Basque language skills. We offer programs adapted to different levels to ease their
                learning and encourage participation in extracurricular activities that promote interaction with other
                young people.

                We collaborate with local schools, counselors, and professionals, providing individual support to young
                people and their families to ensure successful integration and provide them with the necessary tools for
                their development.
            </h4>
        </div>
        <div class="hutsik col-1"></div>
    </div>
</section>

<footer>
    <p>© 2025 DevWorks. Ian, Mikel, Anas and Iker.</p>
</footer>

</body>
</html>