<?php
	include_once "serverConf.php";
	$error = false;
 	if(count($_POST) > 0){
 		session_start();
		$client = new soapclient("$SERVER_LOCATION/Sor_Servicios/LoginClientes?wsdl");
		$user = $_POST["user"];
		$pass = $_POST["password"];
		$params  = array('Password' => $pass,'nif_dni'=> $user);
		$result = $client->Login($params);
		if($result->return == ""){
			$_SESSION["user"] = $user;
			header('Location: client.php');
		}
		else {
			$client = new soapclient("$SERVER_LOCATION/Sor_Servicios/LoginDesguace?wsdl");
			$params = array('Password' => $pass, 'cif' => $user);
			$result = $client->Login_Des($params);
			if($result->return == ""){
				$_SESSION["user"] = $user;
				header('Location: scrapYard.php');
			}
			else {
				$error = true;
			}
		}
	}
?>
<!DOCTYPE html>
<html>
    <head>
	<title>INDEX</title>
	<meta charset="UTF-8">
	<link type="text/css" href="CSS/general.css" rel="stylesheet"/>
	<link type="text/css" href="CSS/login.css" rel="stylesheet"/>
    </head>
    
    <body>
	<section id="login" class="mainSec">
	    <form method="post" action="index.php">
		<table id="tLogin">
		    <tr>
			<td class="login"> Usuario </td>
			<td class="login"> <input  class="form" type="text" name="user"/> </td>
		    </tr>
		    <tr>
			<td class="login"> Contraseña </td>
			<td class="login"> <input class="form" type="password" name="password"/> </td>
		    </tr>
			<tr>
			<td colspan="2" class="loginButton"> <input type="submit" name="login" class="submitButton" value="Enviar"/> </td>
		    </tr>
		    <tr><td class="lError" colspan="2"><label class="lError"><?php if($error) echo "*$result->return";?></label></td></tr>
		</table>
	    </form>
	    <div id="loginReg">
		<p class="loginReg">¿Aun no te has registrado todavía? <a class="loginReg" href="signUp.php">clica aquí</a></p>
	    </div>
	</section>
    </body>

</html>
