<?php

function checkColor($color) {
    $expr = "/#(\d|[ABCDEFabcdef]){6}/";
    if (!preg_match($expr, $color)) {
        return false;
    }
    return true;
}

function checkDNI($dni) {
    $expr = "/\d{8}[A-Z]{1}/";
    if (!preg_match($expr, $dni)) {
        return false;
    }
    return true;
}

function checkCIF($cif) {
    //$expr = "/[ABCDEFGHKLMNPQS]\d{7}([a-zA-Z]|\d)/";
    $expr = "/[ABCDEFGHKLMNPQS]\d{8}/";
    if (!preg_match($expr, $cif)) {
        return false;
    }
    return true;
}  

function checkDateFormat($date) {
    $expr1 = "/^\d{1,2}\/\d{1,2}\/\d{2,4}$/";
    $expr2 = "/^\d{2,4}\-\d{1,2}\-\d{1,2}$/";
    if (preg_match($expr1, $date) || preg_match($expr2, $date)) {
        return true;
    }
    return false;
}

function checkEmail($email) {
    $expr = "/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/";
    if (!preg_match($expr, $email)) {
        return false;
    }
    return true;
}

function isEmpty($string){
    $expr = "/^\s+$/";
    if(strlen($string) < 1)
        return true;
    else if(preg_match($expr, $string)){
        return true;
    }
    return false;
}

?>