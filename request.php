<?php
	include("Logic/request.php");
	session_start();
	$usr_nif = /*$_SESSION["user"]*/ '11111111A';
	$request;
	$units =  json_decode(getUnits());
	$error = false;
	$message = "";
	$option = "";

	if (isset($_GET["new"])) {
		$option = "new";
	} else  if (isset($_GET["update"])) {
		$option = "update";
	} else if(isset($_GET["delete"])) {
		$option = "delete";
	}
	
	if(!empty($_POST)) {
		if(isset($_GET["update"])){
			$error = true;
			if($error)
				$message = "No se ha podido guardar los cambios realizados";
			else
				$message = "Los cambios realizados se han guardado correctamente";
		}
		else if(isset($_GET["new"])){
			try {
				$client = Client::geIdByNIF($usr_nif);
				$request  = new Request($_POST["deadline"], $_POST["type"], $_POST["size"], $_POST["sizeUnit"], $_POST["color"], 
					$_POST["amount"], $_POST["maxPrice"], $client, (isset($_POST["autoSelect"]))? true : false, false);
				$request->insert();
			}
			catch (Exception $e){
				$error = true;
				$message = $e->getMessage();
			}
			header("Location: client.php");
			/*if($error)
				$message = "No se ha podido guardar los cambios realizados";
			else
				$message = "Los cambios realizados se han guardado correctamente";*/
		}
	}
	else if (!empty($_GET)) {
		if(isset($_GET["delete"]) && isset($_GET["id"])){
			//remove from DB
			$error = true;
			if($error)
				$message = "La petición no se ha podido borrar";
			else
				$message = "La petición se ha borrado correctamente";
		}
		else if(isset($_GET["update"])){
			$request = new Request("11/11/2016", "tornillo", "8", "4", "#ff0000", 40, 13.65, "", false, false);
		}
		else if(isset($_GET["new"])){

		}
	}
?>

<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Making requests</title>
	<link type="text/css" href="CSS/general.css" rel="stylesheet"/>
	<link type="text/css" href="CSS/forms.css" rel="stylesheet"/>
	<script type="text/javascript" src="JavaScript/showMessage.js"></script>
	<script type="text/javascript" src="JavaScript/valFunc.js"></script>
	<script type="text/javascript" src="JavaScript/valForms.js"></script>
</head>
<body <?php /*if(!empty($_POST) || (isset($_GET["delete"]) && isset($_GET["id"]))) echo "onload = 'showMessage(\"$message\", \"client.php\")'" */
if($error) echo "onload = 'showMessage(\"$message\", \"client.php\")'" 
?>>
	<section class="mainSec">
		<form id="requestForm" action=<?php echo "\"request.php?$option\""  ?> method="post" onsubmit = "/*return valRequest(this);*/">
			<label class="form">Pieza<input onfocus="this.className = 'form';"class="form" type="text" name="type" id="type" <?php echo "value='".((isset($request))? $request->getType() : "")."'"; ?>></label>
			<label class="form" for="size">Tamaño
			<select class="formCombo" name="sizeUnit" id="sizeUnit">
			<?php for ($i=0; $i < count($units); $i++) { 
				if (isset($request) && $request->getSizeUnit() == $i) {
					echo "<option value='$i' selected='true'>".$units[$i]."</option>";
				} else {
					echo "<option value='$i'>".$units[$i]."</option>";
				}
			}
			?>	
			</select>
			<input onfocus="this.className = 'formCombo';"class="formCombo" type="number" name="size" id="size" <?php echo "value='".((isset($request))? $request->getSize() : "")."'"; ?>>
			</label>
			<label class="form">Color<input onfocus="this.className = 'form';"class="form" type="color" name="color" id="color" <?php echo "value='".((isset($request))? $request->getColor() : "")."'"; ?>></label>
			<label class="form">Cantidad<input onfocus="this.className = 'form';"class="form" type="number" name="amount" id="amount" <?php echo "value='".((isset($request))? $request->getAmount() : "")."'"; ?>></label>
			<label class="form">Precio Máximo<input onfocus="this.className = 'form';"class="form" type="number" name="maxPrice" id="maxPrice" <?php echo "value='".((isset($request))? $request->getMaxPrice() : "")."'"; ?>></label>
			<label class="form">Fecha Tope<input onfocus="this.className = 'form';"class="form" type="date" name="deadline" id="deadline" <?php echo "value='".((isset($request))? $request->getDeadline() : "")."'"; ?>></label>
			<label class="form">Seleción Automática<input "class="formCheckBox" type="checkBox" name="autoSelect" id="autoSelect" <?php if(isset($request)) echo "checked"; ?>></label>
			<div class="buttons">
				<input class="submitButton" type="submit" value="Enviar">
				<button class="submitButton" onclick='window.location = "client.php"; return false;'>Cancelar</button>
			</div>
		</form>
	</section>
</body>
</html>