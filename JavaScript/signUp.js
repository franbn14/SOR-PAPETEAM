function formFields(){
    var enterprise = false;
    var scrapyard = true;
    formFields.change = function(entity) {
        enterprise = (entity.value == "enterprise");
        //scrapyard = document.getElementById("radioButton2").checked;
        formFields.showFields();
    }
    
    formFields.onloadPage = function() {
        enterprise = !(document.getElementById("radioButton").checked);
        formFields.showFields();
    }
    
    formFields.showFields = function() {
        var fields = document.getElementsByName("enterprise");
        if(enterprise){
            for(var i  = 0; i < fields.length; i++){
                fields[i].style.display = "none";
            }
            document.getElementById("typeEnterprise").style.display = "block";
            if(document.getElementById("radioButton2").checked){
                document.getElementById("scrapYard").style.display = "block";
            }
            else {
                document.getElementById("scrapYard").style.display = "none";
            }
        }
        else {
            for(var i  = 0; i < fields.length; i++){
                fields[i].style.display = "block";
            }
            document.getElementById("typeEnterprise").style.display = "none";
            document.getElementById("scrapYard").style.display = "none";
        }
    }
}

var ff = formFields();