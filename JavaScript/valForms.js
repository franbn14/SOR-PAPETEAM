function valSignUp(suf){
    var correct = true;

    var fields = document.getElementById("signInForm").getElementsByClassName("formError");
    for (var i = 0; i < fields.length; i++) {
        fields[i].className = "form";
    }
        

    //alert("entra");
    if(document.getElementById("radioButton").checked){ //private   
        if(isEmpty(suf.surname.value)){
            suf.surname.className = "formError";
            //alert(1);
            correct = false;
        }
       
        if(!checkDate(suf.DOB.value) || !isBeforeDate(suf.DOB.value, new Date())){
            suf.DOB.className = "formError";
            //alert(2);
            correct = false;
        }
    }

    if(document.getElementById("radioButton2").checked){
        if(!checkEmail(suf.email.value)) {
            suf.email.className = "formError";
            correct = false;
        }      
    }

    if(!((checkDNI(suf.id.value) && document.getElementById("radioButton").checked) 
        || (checkCIF(suf.id.value) && !document.getElementById("radioButton").checked))){
        suf.id.className = "formError";
        //alert(3);
        correct = false;
    }
    if(isEmpty(suf.name.value)){
        suf.name.className = "formError";
        //alert(4);
        correct = false;
    }
    if(isEmpty(suf.address.value)){
        suf.address.className = "formError";
        //alert(5);
        correct = false;
    }
    if(isEmpty(suf.pass.value) || suf.pass.value.length < 8){
        suf.pass.className = "formError";
        suf.passConf.className = "formError";
        //alert(6);
        correct = false;
    }
    if(suf.pass.value != suf.passConf.value){
        suf.passConf.className = "formError";
        //alert(7);
        correct = false;
    }
    return false;
}

function valLogin(lf){
    if(checkDNI(lf.user.value) || checkCIF(lf.user.value)){
        return true;
    }
    return false;
}

function valRequest(rf){
    var correct = true;

    var fields = document.getElementById("requestForm").getElementsByClassName("formError");
    for (var i = 0; i < fields.length; i++) {
        fields[i].className = "form";
    }

    var specialField = document.getElementById("requestForm").getElementsByClassName("formComboError");
    if(specialField.length > 0){
        specialField[0].className = "formCombo";
    }

    if(isEmpty(rf.type.value)){
        rf.type.className = "formError";
        //alert("1");
        correct = false;
    }

    if(!isEmpty(rf.size.value) && isNaN(rf.size.value)){
        rf.size.className = "formComboError";
        //alert("2");
        correct = false;
    }

    if(!isEmpty(rf.color.value) && !checkColor(rf.color.value)){
        rf.color.className = "formError";
        //alert("3");
        correct = false;
    }

    if(isNaN(rf.amount.value)){
        rf.amount.className = "formError";
        //alert("4");
        correct = false;
    }

    if(!isEmpty(rf.maxPrice.value) && isNaN(rf.maxPrice.value)){
        rf.maxPrice.className = "formError";
        //alert("5");
        correct = false;
    }

    if(!checkDate(rf.deadline.value)){
        rf.deadline.className = "formError";
        //alert("6");
        correct = false;
    }

    return correct;
}


function valOffer(of){
    var correct = true;

    var fields = document.getElementById("offerForm").getElementsByClassName("formError");
    for (var i = 0; i < fields.length; i++) {
        fields[i].className = "form";
    }

    var specialField = document.getElementById("offerForm").getElementsByClassName("formComboError");
    if(specialField.length > 0){
        specialField[0].className = "formCombo";
    }

    if(isEmpty(of.type.value)){
        of.type.className = "formError";
        //alert("1");
        correct = false;
    }

    if(!isEmpty(of.size.value) && isNaN(of.size.value)){
        of.size.className = "formComboError";
        //alert("2");
        correct = false;
    }

    if(!isEmpty(of.color.value) && !checkColor(of.color.value)){
        of.color.className = "formError";
        //alert("3");
        correct = false;
    }

    if(isNaN(of.amount.value)){
        of.amount.className = "formError";
        //alert("4");
        correct = false;
    }

    if(!isEmpty(of.price.value) && isNaN(of.price.value)){
        of.price.className = "formError";
        //alert("5");
        correct = false;
    }

    return correct;
}