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
	<script type="text/javascript" src="JavaScript/valFunc.js"></script>
	<script type="text/javascript" src="JavaScript/valForms.js"></script>
</head>
<body>
	<section class="mainSec">
		<form action="" method="post" id="offerForm" onsubmit="return valOffer(this);">
			<label class="form">Pieza<input onfocus="this.className = 'form';" class="form" type="text" name="type" id="type" <?php echo "value='".((isset($request))? $request.piece : "")."'"; ?>></label>
			<label for="size" class="form" <?php echo "value='".((isset($request))? $request.piece : "")."'"; ?>>Tamaño
			<select onfocus="this.className = 'formCombo';" class="formCombo" name="sizeUnit" id="sizeUnit">
			<?php for ($i=0; $i < count($units); $i++) { 
				if (isset($request) && $request.sizeUnit == $i) {
					echo "<option value='$i' selected='true'>$i</option>";
				} else {
					echo "<option value='$i'>$i</option>";
				}
			}
			?>	
			</select>
			<input onfocus="this.className = 'formCombo';" class="formCombo" type="number" name="size" id="size" <?php echo "value='".((isset($request))? $request.sizeUnit : "")."'"; ?>>
			</label>
			<label class="form">Color<input onfocus="this.className = 'form';" class="form" type="color" name="color" id="color" <?php echo "value='".((isset($request))? $request.color : "")."'"; ?>></label>
			<label class="form">Cantidad<input onfocus="this.className = 'form';" class="form" type="number" name="amount" id="amount" <?php echo "value='".((isset($request))? $request.amount : "")."'"; ?>></label>
			<label class="form">Precio <input onfocus="this.className = 'form';" class="form" type="number" name="price" id="price" <?php echo "value='".((isset($request))? $request.maxPrice : "")."'"; ?>></label>
			<div class="buttons">
				<input class="submitButton" type="submit" value="Enviar">
				<button class="submitButton" onclick="client.php">Cancelar</button>
			</div>
		</form>
	</section>
</body>
</html>