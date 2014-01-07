function formFields(){
    var enterprise = false;
    formFields.change = function(entity) {
        enterprise = (entity.value == "enterprise");
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
        }
        else {
            for(var i  = 0; i < fields.length; i++){
                fields[i].style.display = "block";
            }
            document.getElementById("typeEnterprise").style.display = "none";
        }
    }
}

var ff = formFields();