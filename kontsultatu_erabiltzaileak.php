<?php
	include("test_connect_db.php");
	$usuario=$_POST["usuario"];
	$password=$_POST["pasahitza"];
	$link=KonektatuDatuBasera();
	$result=mysqli_query($link,"select usuario, pasahitza 
	                            from erabiltzaileak
								where usuario = '$usuario' and pasahitza = '$password'");

	if(mysqli_num_rows($result) == 0)
	{
		header("Location:sessioak_sinplea.php?incorrecto=si");
	}
	else{
			session_start();
			$_SESSION['usuario'] = $usuario;
			header("Location:index.php");
            
		}
?>
