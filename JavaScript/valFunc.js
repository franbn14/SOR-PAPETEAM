function checkColor(color){
   var expr = new RegExp("#(\\d|[ABCDEFabcdef]){6}");
    if (!expr.test(color)) {
        return false;
    }
    return true; 
}

function checkDNI(dni){
    var expr = new RegExp("\\d{8}[A-Z]{1}");
    if (!expr.test(dni)) {
        return false;
    }
    return true;
}

function checkCIF(cif){
    var expr = new RegExp("[ABCDEFGHKLMNPQS]\\d{8}([a-zA-Z]|\\d)");
    if(!expr.test(cif)){
        return false;
    }
    return true;
}

function checkEmail(email) {
    var expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!expr.test(email)) {
        return false;
    }
    return true;
}

function isEmpty(string) {
    var expr = /\s+/;
    if (string.length < 1) {
        return true;
    }
    else if (expr.test(string)) {
        return true;
    }
    return false;
}

function checkDate(date){
    var expr = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
    var expr3 = /^\d{2,4}\-\d{1,2}\-\d{1,2}$/;
    if (expr.test(date) || expr3.test(date)) {
        return true;
    }
    return false;
}

function isBeforeDate(string, date){
    var d;
    if (string.indexOf("/") > -1) { //dd/mm/yyyy
        d = string.split("/");
        
        if (date.getFullYear() > parseInt(d[2])) {
            return true;
        }
        else if (date.getFullYear() == parseInt(d[2]) && date.getMonth() > parseInt(d[1])) {
            return true;
        }
        else if (date.getFullYear() == parseInt(d[2]) && date.getMonth() == parseInt(d[1]) && date.getDate() > parseInt(d[0])) {
            return true;
        }
    }
    else { //yyyy-mm-dd
        d = string.split("-");
        if (date.getFullYear() > parseInt(d[0])) {
            //alert("sale1");
            return true;
        }
        else if (date.getFullYear() == parseInt(d[0]) && date.getMonth()+1 > parseInt(d[1])) {
            //alert("sale2");
            return true;
        }
        else if (date.getFullYear() == parseInt(d[0]) && date.getMonth()+1 == parseInt(d[1]) && date.getDate() > parseInt(d[2])) {
            //alert("sale3");
            return true;
        }
    }
    return false;
}