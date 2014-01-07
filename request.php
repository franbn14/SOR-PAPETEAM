<?php
	include("Logic/request.php");
	$request;
	$units =  array(1, 2, 3, 4, 5, 6, 7, 8);
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
			$error = true;
			if($error)
				$message = "No se ha podido guardar los cambios realizados";
			else
				$message = "Los cambios realizados se han guardado correctamente";
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
			$error = true;

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
</head>
<body <?php if(!empty($_POST) || (isset($_GET["delete"]) && isset($_GET["id"]))) echo "onload = 'showMessage(\"$message\", \"client.php\")'" ?>>
	<section class="mainSec">
		<form action=<?php echo "\"request.php?$option\""  ?> method="post">
			<label class="form">Pieza<input class="form" type="text" name="type" id="type" <?php echo "value='".((isset($request))? $request->getType() : "")."'"; ?>></label>
			<label class="form">Tamaño
			<select class="formCombo" name="sizeUnit" id="sizeUnit">
			<?php for ($i=0; $i < count($units); $i++) { 
				if (isset($request) && $request->getSizeUnit() == $i) {
					echo "<option value='$i' selected='true'>$i</option>";
				} else {
					echo "<option value='$i'>$i</option>";
				}
			}
			?>	
			</select>
			<input class="formCombo" type="number" name="size" id="size" <?php echo "value='".((isset($request))? $request->getSize() : "")."'"; ?>>
			</label>
			<label class="form">Color<input class="form" type="color" name="color" id="color" <?php echo "value='".((isset($request))? $request->getColor() : "")."'"; ?>></label>
			<label class="form">Cantidad<input class="form" type="number" name="amount" id="amount" <?php echo "value='".((isset($request))? $request->getAmount() : "")."'"; ?>></label>
			<label class="form">Precio Máximo<input class="form" type="number" name="maxPrice" id="maxPrice" <?php echo "value='".((isset($request))? $request->getMaxPrice() : "")."'"; ?>></label>
			<label class="form">Fecha Tope<input class="form" type="date" name="deadline" id="deadline" <?php echo "value='".((isset($request))? $request->getDeadline() : "")."'"; ?>></label>
			<label class="form">Seleción Automática<input class="formCechBox" type="checkBox" name="autoSelect" id="autoSelect" <?php if(isset($request)) echo "checked"; ?>></label>
			<div class="buttons">
				<input class="submitButton" type="submit" value="Enviar">
				<button class="submitButton" onclick='window.location = "client.php"; return false;'>Cancelar</button>
			</div>
		</form>
	</section>
</body>
</html>