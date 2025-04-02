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
							echo "Welcome : $usuario //<img src=yes.jpg width=50 height=50><br>";
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