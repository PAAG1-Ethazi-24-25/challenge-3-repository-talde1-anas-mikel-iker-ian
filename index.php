<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous">
        </script>
    <link rel="stylesheet" href="index.css">
    <title>Hasiera</title>
</head>

<body>
    <header>
        <?php
        session_start();

        if (isset($_SESSION['usuario'])) {
            // Si el usuario ha iniciado sesión
            $usuario = $_SESSION['usuario'];
            $kargua = $_SESSION['kargua'];  // Suponiendo que el rol se guarda en la sesión
        
            echo "Welcome : $usuario";

            // Opciones generales para todos los usuarios 
            echo '<a href="index.php">Produktuak</a><br>';
            echo '<a href="PRODUKTUAK\Produktuak.php">Produktuak</a><br>';
            echo '<a href="KONTAKTUA\Kontaktua.php">Produktuak</a><br>';

            // Si el usuario es un administrador, mostramos más opciones
            if ($kargua == 'administraria') {
                echo '<a href="insert beers/insert.php">Insert beer</a><br>';
                echo '<a href="delete beers/deleteLang.php">Delete beer</a><br>';
                echo '<a href="manage_users/manage.php">Manage users</a><br>'; // Ejemplo de opción de administrador
            }
            // Si el usuario es normal, mostramos solo las opciones básicas
            elseif ($rol == 'normala') {
                echo '<a href="update_profile.php">Update Profile</a><br>'; // Opción para usuarios normales
            }
            // Opción para cerrar sesión
            echo '<form action="irten.php"><br>
                <input type="submit" value="Logout" />
            </form>';
        } else {
            // Si el usuario no ha iniciado sesión, mostramos solo la opción de login
            echo '<a href="sessioak_sinplea.php">Login</a>';
        }
        ?>
        <nav class="navbar navbar-expand-lg bg-body-tertiary menua">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="izenburua">
                <a href="./index.html">
                    <img src="./HASIERA/img/ChatGPT Image 1 abr 2025, 12_55_45.png" class="img-fluid">
                </a>
            </div>
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="./index.html">Hasiera</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./PRODUKTUAK/Produktuak.html">Produktuak</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./KONTAKTUA/Kontaktua.html">Kontaktua</a>
                        </li>
                    </ul>
                    <button class="btn">
                        <a href="./SAIOA_HASI/Saioa_Hasi.html">
                            <img src="./HASIERA/img/imagen inicio sesion copy.png" class="img-fluid">
                        </a>
                    </button>
                </div>
            </div>
        </nav>
    </header>
    <section>
        <div class="row">
            <div class="navbar1">
                <div id="carouselExampleSlidesOnly" class="carousel slide col-12 col-s-12 img-fluid"
                    data-bs-ride="carousel">
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
                            <img src="./HASIERA/img/463212931_3928246330727971_8244200252763828714_n.jpg"
                                class="img-fluid">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section>
        <div class="row">
            <div class="hutsik col-1 col-s-12">
            </div>
            <div class="container col-4 col-s-12">
                <h2>NOR GARA?</h2>
                <br>
                <h4>Gure elkartea bailarako gazte etorkinei laguntzeko konpromisoarekin sortu zen, euskara ikasiz eta
                    eskolaz kanpoko jardueretan parte hartuz gizarteratzen eta hezten laguntzeko. Badakigu zein
                    garrantzitsua den komunitate baten parte sentitzea, eta gure helburua da integrazio eraginkorra eta
                    aberasgarria sustatuko duten baliabideak eta tresnak eskuratzea.</h4>
            </div>
            <div class="hutsik col-1 col-s-12">
            </div>
            <div class="hutsik col-1 col-s-12">
            </div>
            <div class="container col-4 col-s-12">
                <img src="./HASIERA/img/rethinkvintage-1.jpg" class="img-fluid">
            </div>
            <div class="hutsik col-1 col-s-12">
            </div>
        </div>
    </section>
    <section>
        <div class="row">
            <div class="hutsik col-1">
            </div>
            <div class="container col-4 col-s-12">
                <img src="./HASIERA/img/Azokaren_kartela.jpeg" class="img-fluid">
            </div>
            <div class="hutsik col-1">
            </div>
            <div class="hutsik col-1">
            </div>
            <div class="container col-4 col-s-12">
                <h2>ZER ETA NOLA EGITEN DUGU?</h2>
                <br>
                <h4>
                    Gure elkartean Debabarreneko gazte etorkinei gizarteratzen eta hezten laguntzen diegu, euskara
                    indartzeko ikuspegi berezi batekin. Maila desberdinetara egokitutako programak eskaintzen ditugu,
                    haien ikaskuntza errazteko eta beste gazte batzuekiko interakzioa bultzatzen duten eskolaz kanpoko
                    jardueretan parte har dezaten sustatzeko.

                    Tokiko eskola, orientatzaile eta profesionalekin batera lan egiten dugu, gazteei eta haien familiei
                    banakako laguntza emanez integrazio arrakastatsua ziurtatzeko eta haien garapenerako beharrezkoak
                    diren tresnak eskaintzeko.
                </h4>
            </div>
            <div class="hutsik col-1">
            </div>
        </div>
    </section>

    <footer>
        <p>© 2025 DevWorks. Ian, Mikel, Anas eta Iker.</p>
    </footer>

</body>

</html>