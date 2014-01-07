function showMessage (message, url){
	var head = 
			"<meta charset='UTF-8'>" + 
			"<title>Show Message</title>" + 
			"<link type='text/css' href='CSS/showMessage.css' rel='stylesheet'/>"; 

	var body =		 
			"<div class='showMessage'>" + 
				"<p>" + message + "</p>" + 
				"<span class='showMessage'><button class='showMessage' onclick='window.location = \"" + url + "\"'>Aceptar</button></span>" + 
			"</div>";

	document.getElementsByTagName("head")[0].innerHTML = head;
	document.getElementsByTagName("body")[0].innerHTML = body;
	document.getElementsByTagName("body")[0].className = "showMessage";
}