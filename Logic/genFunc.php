<?php 

include_once "Logic/valFunc.php";

function encode($object){
	$aux = array();
	foreach ($object as $o) {
		array_push($aux, $o->toStdClass());
	}
	return json_encode($aux);
}

function changeDate($date) {
	$months = array('Jan' => "01", 'Feb' => "02", "Mar" => "03", "Apr" => "04", "May" => "05", "Jun" => "06",
		"Jul" => "07", "Aug" => "08", "Sep" => "09", "Oct" => "10", "Nov" => "11", "Dec" => "12");

	$aux = split(" |, ", $date);

	return ($aux[2]-1900)."-".$months[$aux[0]]."-".$aux[1];
}

function formatColor($color) {
	if(!isEmpty($color) && $color[0] != '#'){
		return '#'.$color;
	}
	return $color;
}

function getUnits() {
	include_once "serverConf.php";
	$client = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/DarUnidades?wsdl");
	return $client->DarTodasUnidades()->return;
}

?>