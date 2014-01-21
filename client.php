<?php 
    //Comentado para probar 
    /*session_start();
    if(!isset($_SESSION["user"])){
        header("Location: index.php");
    }*/
    include_once "Logic/request.php";
    include_once "Logic/offer.php";

    if(isset($_POST["option"])){
        if($_POST["option"] == "getPendingByNIF"){
            echo encode(/*Request::getPendingByNIF($_SESSION["user"])*/Request::getPendingByNIF("11111111A"));
        }
        if($_POST["option"] == "getUnits"){
            echo getUnits();
        }
        if($_POST["option"] == "getOffersByRequest"){
            echo encode(Offer::getByRequest($_POST["idR"]));
        }
        if($_POST["option"] == "finishRequest"){
            try{
                Offer::acceptOffers($_POST["offers"]);
            }
            catch (Exception $e){
                echo $e->getMessage();
            }
            echo " ";
        }
    }
    else {
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Client Area</title>
        <meta charset="UTF-8">
        <link type="text/css" href="CSS/general.css" rel="stylesheet"/>
        <link type="text/css" href="CSS/content.css" rel="stylesheet"/>
        <script type="text/javascript" src="JavaScript/jquery.js"></script>
        <script type="text/javascript" src="JavaScript/client.js"></script>
    </head>

    <body>
        <section id="itemsArea">
            <h1 class="area">PETICIONES</h1>
            <div id="itemsList" class="area">
            <!--<article id="1" class="itemSelected">
                <ul class="desc">
                    <li class="desc">Pieza: tuerca </li>
                    <li class="desc">Tamaño: 20 mm </li>
                    <li class="desc">Color: #ff0000 </li>
                    <li class="desc">Fecha tope: 2014-02-03</li>
                    <li class="desc">Elección Automatica: si </li>
                </ul>
                <div class="finishButton">
                    <button class="finishButton" onclick="alert('pressed')">finalizar</button>
                </div>
                <p class="num"> 50 </p>
            </article>-->
            </div>
            <div class="areaFooter">
                <button class="items" onclick='window.location = "request.php?new"'>Nueva Peticion</button>
                <button id="finishButton" class="items" style="visibility: hidden" onclick='finishRequest()'>Finalizar</button>
                <button class="items" onclick='window.location = "request.php?update&id=" + getSelectedItemID()"' style="display: none">Editar</button>
                <button class="items" onclick='window.location = "request.php?delete&id=" + getSelectedItemID()'>Borrar</button>

            </div>
        </section>
        <section id="showArea">
            <h1 class="area">OFERTAS</h1>
            <div class="area" id="offerArea">
                <!--<div id="areaHeader">
                    <label class="ref">ref:1</label>
                    <p class="areaType">Puerta</p>
                </div>
                <ul class="feature">
                    <li class="desc, feature"><label class="feature">Tamaño: </label> 50 cm</li>
                    <li class="desc, feature"><label class="feature">Color: </label> <div id="squareColor"></div></li>
                    <li class="desc, feature"><label class="feature">Cantidad: </label> 20</li>
                    <li class="desc, feature"><label class="feature">Precio: </label>27.3 €</li>
                    <li class="desc, feature"><label class="feature">Desguace: </label> TodoPiezas</li>
                </ul>
                <button class="offerButton" onclick="document.getElementById('areaHeader').style.backgroundColor = '#99FF99'">Aceptar</button>-->
            </div>
            <div class="areaFooter">
                <button class="area" onclick="decOffer()"><</button>
                <label id="numOffers"></label>
                <button class="area" onclick="incOffer()">></button>
            </div>
        </section>
    </body>
</html>
<?php } ?>