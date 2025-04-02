<?php
	session_start();
	unset($_SESSION['usuario']);
	session_write_close();
	header("Location:index.php");
?>