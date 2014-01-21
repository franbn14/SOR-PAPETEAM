<?php
include_once "Logic/client.php";
include_once "Logic/scrapYard.php";

$error = false;
$message = "Se ha producido un error";

if(count($_POST) > 0){
    $id = $_POST["id"];
    $name = $_POST["name"];
    $surname = $_POST["surname"];
    $password = $_POST["pass"];
    $address = $_POST["address"];
    $DOB = $_POST["DOB"];
    $email = $_POST["email"];

    if($_POST["entity"] == "private" || ($_POST["entity"] == "enterprise" && $_POST["typeEnterprise"] == "garage")){
        try{
            $c = new Client($id, $name, $surname, $password, $address, $DOB);
            $c->insert();
        }
        catch (Exception $e){
            $error = true;
            $message = $e->getMessage();
        }
    }
    else {
        try {
            $sy = new ScrapYard($name, $password, $address, $id, $email);
            $sy->insert();
        }
        catch (Exception $e){
            $error = true;
            $message = $e->getMessage();
        }
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Title of the document</title>
    <meta charset="UTF-8">
    <link type="text/css" href="CSS/general.css" rel="stylesheet"/>
    <link type="text/css" href="CSS/signUp.css" rel="stylesheet"/>
    <script type="application/javascript" src="JavaScript/signUp.js"></script>
    <script type="application/javascript" src="JavaScript/valFunc.js"></script>
    <script type="application/javascript" src="JavaScript/valForms.js"></script>
    <script type="application/javascript" src="JavaScript/showMessage.js"></script>

</head>
<body <?php if($error) echo "onload = 'showMessage(\"$message\", \"index.php\")'"; else echo "onload='formFields.onloadPage()'"; ?>>
    <section id="signIn" class="mainSec">
        <form id="signInForm" method="post" onsubmit="/*return valSignUp(this);*/" action="signUp.php">
            <p>
                <label class="form"> Particular <input id="radioButton" type="radio" name="entity" value="private" checked onchange="formFields.change(this)"></label>
                <label class="form"> Empresa <input type="radio" name="entity" value="enterprise" onchange="formFields.change(this)"/> </label>
            </p>
            <div id="typeEnterprise">
                <label class="form2">Desguace <input id="radioButton2" class="formRadio" type="radio" name="typeEnterprise" value="scrapYard" checked onchange="formFields.showFields()"></label>
                <label class="form2">Taller <input class="formRadio" value="garage" type="radio" name="typeEnterprise" onchange="formFields.showFields()"></label> 
            </div>
            <label name="both">
                <p class="form">nif/cif: </p>
                <input class="form" type="text" name="id" onfocus="this.className = 'form';">
            </label>
            <label name="both">
                <p class="form">Nombre: </p>
                <input class="form" type="text" name="name" onfocus="this.className = 'form';"/>
            </label>
            <label id="scrapYard">
                <p class="form">Email: </p>
                <input class="form" type="text" name="email" onfocus="this.className = 'form';"/>
            </label>
            <label name="enterprise">
                <p class="form">Apellidos: </p>
                <input class="form" type="text" name="surname" onfocus="this.className = 'form';"/>
            </label>
            <label name="enterprise">
                <p class="form">Dirección: </p>
                <input class="form" type="text" name="address" onfocus="this.className = 'form';"/>
            </label>
            <label name="enterprise">
                <p class="form">Fecha de nacimiento: </p>
                <input class="form" type="date" name="DOB" onfocus="this.className = 'form';"/>
            </label>
            <label name="both">
                <p class="form">Contraseña: </p>
                <input alt=""class="form" type="password" name="pass" onfocus="this.className = 'form';"/>
            </label>
            <label name="both">
                <p class="form">Repite contraseña: </p>
                <input class="form" type="password" name="passConf" onfocus="this.className = 'form';"/>
            </label>
            <p class="submitButton"><input class="submitButton" type="submit" name="submitButton" value="Enviar"/></p>
        </form>
        <div id="info">
            <!--<p class="info">If you sign up to this page, you will be able to receive offers related to whatever piece that you are looking for directly from the our list of scrapyards.</p>
            <p class="info">Also, if you are a scrapyard and you are interesting in being part of our list of scrapyards and receiving the requests of our clients, you should to sign up.</p>-->
            <p class="info">Si te registras en esta página, podrás recibir ofertas relacionadas con cualquier pieza que estes buscando directamente desde nuestra lista de desguaces.</p>
            <p class="info">Así mismo, si representas un desguace y estás interesado en formar parte de nuestra lista de desguaces y recibir peticiones de nuestros clientes, no dudes en registrarte.</p>
            
        </div>
    </section>
</body>

</html>