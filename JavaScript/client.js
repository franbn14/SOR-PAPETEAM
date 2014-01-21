
var items = new Array();
var units = new Array();
var OFFERS = new Array();
var OFFER_SELECTED = 0;

function getNameURLWeb(){
     var sPath = window.location.pathname;
     var sPage = sPath.substring(sPath.lastIndexOf('/') + 1);
     return sPage;
}

function Item (){
	this.code = -1;
	this.piece = "";
	this.num = 0;
	this.size = 0;
	this.sizeUnit = "mm";
	this.color = "#ffffff";
	this.deadline = "";
	this.autoElect = true;

	this.serialize = function(classType){
		var text = 
		"<article id=\"" + this.code +"\" class=\"" + classType + "\" onclick=\"selectedItem(this)\">"+
            "<ul class=\"desc\">"+
                "<li class=\"desc\">Pieza: " + this.piece + "</li>"+
                "<li class=\"desc\">Tamaño: " + this.size + " " + ((this.size=="")? "": units[this.sizeUnit]) + "</li>"+
                "<li class=\"desc\">Color: " + this.color + "</li>"+
                "<li class=\"desc\">Fecha tope: " + this.deadline + "</li>"+
                ((getNameURLWeb() == "client.php")? "<li class=\"desc\">Elección Automatica: " + ((this.autoElect)? "si" : "no") + "</li>" : "")+
            "</ul>"+
            "<p class=\"num\"> " + this.num + " </p>"+
        "</article>";
		return text;
	}
}

function selectedItem(element){

	var list = document.getElementById("itemsList").getElementsByTagName("article");
	for(var i = 0; i < list.length; i++){
		list[i].className = "item";
	}
	element.className = "itemSelected";
	OFFER_SELECTED = 0;
	askOffersByRequest();
}

function SetItems(items, element){
	var text = "";
	for(var i = 0; i < items.length; i++){
		if(i != 0)
			text += items[i].serialize("item");
		else
			text += items[i].serialize("itemSelected");
	}
	document.getElementById(element).innerHTML = (text != "")? text : "<p style='text-align: center'>No se ha realizado ninguna petición</p>";
}

function getSelectedItemID(){
	var items = document.getElementById("itemsList").getElementsByClassName("itemSelected");
	if(items.length > 0){
		return items[0].id;
	}
	else {
		return -1;
	}
}

function offer(){
	this.code = -1;
	this.type = "";
	this.size = -1;
	this.sizeUnit = 0;
	this.color = "#000000";
	this.amount = -1;
	this.price = -1;
	this.request = -1;
	this.scrapyard = -1;
	this.acepted = false;

	this.serialize = function(){
		var HTML = ((this.acepted)? '<div id="areaHeader" style="background-color: #99FF99">' : '<div id="areaHeader">')+
	                    '<label class="ref">ref:' + this.code + '</label>'+
	                    '<p class="areaType">' + this.type + '</p>'+
	                '</div>'+
	                '<ul class="feature">'+
	                    '<li class="desc, feature"><label class="feature">Tamaño: </label> ' + this.size + ' ' + units[this.sizeUnit] + '</li>'+
	                    '<li class="desc, feature"><label class="feature">Color: </label> ' + this.color + '</li>'+
	                    '<li class="desc, feature"><label class="feature">Cantidad: </label> ' + this.amount + '</li>'+
	                    '<li class="desc, feature"><label class="feature">Precio: </label>' + this.price +' €</li>'+
	                    '<li class="desc, feature"><label class="feature">Desguace: </label> ' + this.scrapyard + '</li>'+
	                '</ul>';
        if(getNameURLWeb() == "client.php"){
        	if(this.acepted){
        		HTML += '<button  class="offerButton" onclick="AceptOffer()">Rechazar</button>';
        	}
        	else {
        		HTML += '<button  class="offerButton" onclick="AceptOffer()">Aceptar</button>';
        	}
    	}
	    return HTML;
	}
}


function includeItems (data){
	for(var i = 0; i < data.length; i++){
		var it = new Item();
		it.code = data[i]["code"];
		it.piece = data[i]["type"];
		it.num = data[i]["amount"];
		it.size = data[i]["size"];
		it.sizeUnit = data[i]["sizeUnit"];
		it.color = data[i]["color"];
		it.deadline = data[i]["deadline"];
		it.autoElect = data[i]["autoElect"];
		items.push(it);
	}

}

function setOffer(){
	var selItem = getSelectedItemID();
	var place = document.getElementById("offerArea");
	var num = document.getElementById("numOffers");

	if(OFFERS[selItem].length == 0){
		place.innerHTML = "<p>No se ha realizado ninguna oferta</p>";
		num.innerHTML = "0/0";
	}
	else {
		place.innerHTML = OFFERS[selItem][OFFER_SELECTED].serialize();
		num.innerHTML = (OFFER_SELECTED+1)+"/"+OFFERS[selItem].length;
	}
}

function askOffersByRequest(){
	var selItem = getSelectedItemID();
	if(OFFERS[selItem] == undefined){
		var array = new Array();
		$.post("client.php", {option : "getOffersByRequest", idR : selItem}, function(data, textStatus) {
			var ofs = $.parseJSON(data);
			for(var i = 0; i < ofs.length; i++){
				var of = new offer();
				of.code = ofs[i]["code"];
				of.type = ofs[i]["type"];
				of.size = ofs[i]["size"];
				of.sizeUnit = ofs[i]["sizeUnit"];
				of.color = ofs[i]["color"];
				of.amount = ofs[i]["amount"];
				of.price = ofs[i]["price"];
				of.request = ofs[i]["request"];
				of.scrapyard = ofs[i]["scrapyard"]["name"];
				array.push(of);
			}
			OFFERS[selItem] = array;
			document.getElementById("finishButton").style.visibility = (isAnyAcepted(selItem))? "visible" : "hidden";
			setOffer()
		});
	}
	else {
		setOffer();
	}
}

function incOffer(){
	var selItem = getSelectedItemID();
	if(OFFERS[selItem].length-1 > OFFER_SELECTED){
		OFFER_SELECTED++;
		askOffersByRequest();
	}
}

function decOffer(){
	var selItem = getSelectedItemID();
	if(0 < OFFER_SELECTED){
		OFFER_SELECTED--;
		askOffersByRequest();
	}
}

function isAnyAcepted(idR){
	for(var i = 0; i < OFFERS[idR].length; i++){
		if(OFFERS[idR][i].acepted){
			return true;
		}
	}
	return false;
}

function AceptOffer(){
	var selItem = getSelectedItemID();
	OFFERS[selItem][OFFER_SELECTED].acepted = !OFFERS[selItem][OFFER_SELECTED].acepted;
	document.getElementById("finishButton").style.visibility = (isAnyAcepted(selItem))? "visible" : "hidden";
	setOffer();
}

function getAcceptedOffers (idR) {
	var ofs = "";
	for(var i = 0; i < OFFERS[idR].length; i++){
		if(OFFERS[idR][i].acepted){
			ofs += (ofs == "")? OFFERS[idR][i].code : " " + OFFERS[idR][i].code;
		}
	}
	return ofs;
}

function finishRequest () {
	var aceptedOffers = getAcceptedOffers(getSelectedItemID());
	$.post("client.php", {option : "finishRequest", offers : aceptedOffers}, function(data, textStatus) {
		init();
	});
}

function init () {
	items = new Array();
	OFFERS = new Array();
	document.getElementById("offerArea").innerHTML = "";
	$.post("client.php", {option : "getPendingByNIF"}, function(data, textStatus) {
		includeItems($.parseJSON(data));
		SetItems(items, "itemsList");
		var articles = document.getElementById("itemsList").getElementsByTagName("article");
		if(articles.length > 0){
			askOffersByRequest();
		}
	});
}

window.onload = function() {
	if(getNameURLWeb() == "client.php"){
		$.post("client.php", {option : "getUnits"}, function(data, textStatus) {
			units = $.parseJSON(data);
		});

		init();
	}
}

