<html>
	<head>
	</head>
	
	<body>
		
		<div>
			<?php
				session_start();
					if(isset($_SESSION['usuario']))
					{
						$usuario = $_SESSION['usuario'];
							 echo "Welcome : $usuario <img src=yes.jpg width=50 height=50><br>";
						?>
+		
						<form action="irten.php">
		
							<input type="submit" value="Sesioa itxi" />
			
						</form>
						
						<?php 
					}
						?>
		</div>
		<h3>Login form:</h3>
		<form action="kontsultatu_erabiltzaileak.php" method="post">
							User: <br> 
							<input type="text" name="usuario" />
							<br><br>
							pass: <br>
							<input type="password" name="pasahitza" />
							<br><br>
							<input type="submit" value="Sartu">	
		</form>
		<br>							
		<?php
						if(isset($_GET["incorrecto"]))
						{
							if($_GET["incorrecto"]=="si")
							{
					?>
								<p style="color:#F00"><b>Something went wrong !</b></p>
								<!-- <img src="stop.jpg"> -->
					<?php
							}
						} 
						
						?>
	</body>
	
	
</html>

<!-- <?php session_start(); ?>

<html>
<head>
    <title>Saioa hasi</title>
</head>
<body>
    <div>
        <?php if (isset($_SESSION['usuario'])): ?>
            <p>Ongi etorri, <?php echo $_SESSION['usuario']; ?>!</p>
            <form action="irten.php">
                <input type="submit" value="Sesioa itxi">
            </form>
        <?php else: ?>
            <h3>Login form:</h3>
            <form action="kontsultatu_erabiltzaileak.php" method="post">
                Erabiltzailea: <input type="text" name="usuario" required><br>
                Pasahitza: <input type="password" name="pasahitza" required><br>
                <input type="submit" value="Sartu">
            </form>
            
            <?php if(isset($_GET["incorrecto"]) && $_GET["incorrecto"] == "si"): ?>
                <p style="color:red;"><b>Errorea: erabiltzailea edo pasahitza okerra!</b></p>
            <?php endif; ?>
            
            <?php if(isset($_GET["erregistratuta"]) && $_GET["erregistratuta"] == "bai"): ?>
                <p style="color:green;"><b>Erregistroa ondo burutu da! Orain saioa hasi.</b></p>
            <?php endif; ?>
        <?php endif; ?>
    </div>
</body>
</html> -->
