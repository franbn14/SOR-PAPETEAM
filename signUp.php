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
</head>
<body onload="formFields.onloadPage()">
    <section id="signIn" class="mainSec">
        <form id="signInForm" method="post" onsubmit="return valSignUp(this);" action="index.php">
            <p>
                <label class="form"> Particular <input id="radioButton" type="radio" name="entity" value="private" checked onchange="formFields.change(this)"></label>
                <label class="form"> Empresa <input type="radio" name="entity" value="enterprise" onchange="formFields.change(this)"/> </label>
            </p>
            <div id="typeEnterprise">
                <label class="form2">Desguace <input class="formRadio" type="radio" name="typeEnterprise" checked></label>
                <label class="form2">Taller <input class="formRadio" type="radio" name="typeEnterprise"></label> 
            </div>
            <label name="both">
                <p class="form">nif/cif: </p>
                <input class="form" type="text" name="id" onfocus="this.className = 'form';">
            </label>
            <label name="both">
                <p class="form">Nombre: </p>
                <input class="form" type="text" name="name" onfocus="this.className = 'form';"/>
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