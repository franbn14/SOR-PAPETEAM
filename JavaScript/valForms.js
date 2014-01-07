function valSignUp(suf){
    var correct = true;
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
    if(isEmpty(suf.id.value)){
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