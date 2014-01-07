<?php 
    //Comentado para probar 
    /*session_start();
    if(!isset($_SESSION["user"])){
        header("Location: index.php");
    }*/
?>
<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
    <link type="text/css" href="CSS/general.css" rel="stylesheet"/>
    <link type="text/css" href="CSS/content.css" rel="stylesheet"/>
    <script type="text/javascript" src="JavaScript/client.js"></script>
</head>
<body>
	<section id="itemsArea">
        <h1 class="area">PETICIONES</h1>
        <div id="itemsList" class="area">
            <article class="item">
                <ul class="desc">
                    <li class="desc">Pieze: torinillo</li>
                    <li class="desc">Tamaño: 8 mm</li>
                    <li class="desc">Color: rojo</li>
                    <li class="desc">Fecha tope: 12/12/2014 </li>
                </ul>
                <p class="num"> 50 </p>
            </article>
        </div>
        <div class="areaFooter">
            
        </div>
    </section>
    <section id="showArea">
        <h1 class="area">OFERTAS</h1>
        <div class="area">
            
        </div>
        <div class="areaFooter">
            <button class="area" onclick=""><</button>
            <label id="numOffers">1/10</label>
            <button class="area" onclick="">></button>
           <div class="buttonsFooter">
           	<button class="items" onclick='window.location = "offer.php?new"'>Nueva Oferta</button>
           	<button class="items" onclick='window.location = "offer.php?update"' style="display:none;">Editar</button>
           	<button class="items" onclick='window.location = "offer.php?delete"'>Borrar</button>
           </div>
        </div>
    </section>
</body>
</html>