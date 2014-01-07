<?php
	include("Logic/request.php");
	$request;
	$units =  array(1, 2, 3, 4, 5, 6, 7, 8);
	if (!empty($_GET)) {
		$request = new Request("11/11/2016", "tornillo", "8", "4", "#ff0000", 40, 13.65, "", true, false);
	}
	if(!empty($_POST)) {
		//validate form and insert into DB
	}
?>

<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Making requests</title>
	<link type="text/css" href="CSS/general.css" rel="stylesheet"/>
	<link type="text/css" href="CSS/forms.css" rel="stylesheet"/>
</head>
<body>
	<section class="mainSec">
		<form action="" method="post">
			<label class="form">Pieza<input class="form" type="text" name="type" id="type" <?php echo "value='".((isset($request))? $request.piece : "")."'"; ?>></label>
			<label class="form" <?php echo "value='".((isset($request))? $request.piece : "")."'"; ?>>Tamaño
			<select class="formCombo" name="sizeUnit" id="sizeUnit">
			<?php for ($i=0; $i < count($units); $i++) { 
				if (isset($request) && $request.sizeUnit == $i) {
					echo "<option value='$i' selected='true'>$i</option>";
				} else {
					echo "<option value='$i'>$i</option>";
				}
			}
			?>	
			</select>
			<input class="formCombo" type="number" name="size" id="size" <?php echo "value='".((isset($request))? $request.sizeUnit : "")."'"; ?>>
			</label>
			<label class="form">Color<input class="form" type="color" name="color" id="color" <?php echo "value='".((isset($request))? $request.color : "")."'"; ?>></label>
			<label class="form">Cantidad<input class="form" type="number" name="amount" id="amount" <?php echo "value='".((isset($request))? $request.amount : "")."'"; ?>></label>
			<label class="form">Precio <input class="form" type="number" name="maxPrice" id="maxPrice" <?php echo "value='".((isset($request))? $request.maxPrice : "")."'"; ?>></label>
			<label class="form">Fecha Tope<input class="form" type="date" name="deadline" id="deadline" <?php echo "value='".((isset($request))? $request.deadline : "")."'"; ?>></label>
			<div class="buttons">
				<input class="submitButton" type="submit" value="Enviar">
				<button class="submitButton" onclick="client.php">Cancelar</button>
			</div>
		</form>
	</section>
</body>
</html>