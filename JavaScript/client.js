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
		/*var text =
		"<article class=\"item\" onclick=\"selectedItem(this)\">" +
			"<p class=\"desc\">" + this.desc + "</p>"+
			"<p class=\"num\"> " + this.num + " </p>"+
		"</article>";*/
		var text = 
		"<article id=\"" + this.code +"\" class=\"" + classType + "\" onclick=\"selectedItem(this)\">"+
            "<ul class=\"desc\">"+
                "<li class=\"desc\">Pieza: " + this.piece + "</li>"+
                "<li class=\"desc\">Tamaño: " + this.size + " " + ((this.size=="")? "": this.sizeUnit) + "</li>"+
                "<li class=\"desc\">Color: " + this.color + "</li>"+
                "<li class=\"desc\">Fecha tope: " + this.deadline + "</li>"+
                "<li class=\"desc\">Elección Automatica: " + ((this.autoElect)? "si" : "no") + "</li>"+
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
}

function SetItems(items, element){
	var text = "";
	for(var i = 0; i < items.length; i++){
		if(i != 0)
			text += items[i].serialize("item");
		else
			text += items[i].serialize("itemSelected");
	}
	document.getElementById(element).innerHTML = text;
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

var i1 = new Item();
i1.code = 1;
i1.desc = "tornillos de puertas";
i1.num = 40;
i1.size = 8;
i1.sizeUnit = "mm";
i1.color = "#ff0000";
i1.deadline = "12/12/2014";
i1.autoElect = true;

var i2 = new Item();
i2.code = 2;
i2.desc = "tuercas para tornillos de puertas";
i2.num = 40;
i2.size = 8;
i2.sizeUnit = "mm";
i2.color = "#ffff00";
i2.deadline = "18/08/2014";
i2.autoElect = true;

var i3 = new Item();
i3.code = 3;
i3.desc = "puerta";
i3.num = 1;
i3.size = 80;
i3.sizeUnit = "cm";
i3.color = "#ffffff";
i3.deadline = "10/08/2014";
i3.autoElect = false;

var items = new Array(i1, i2, i3);

window.onload = function() {
	SetItems(items, "itemsList");
}

