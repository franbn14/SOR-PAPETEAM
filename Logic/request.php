<?php
include_once "Logic/valFunc.php";
include_once "Logic/exceptions.php";
include_once "serverConf.php";
include_once "Logic/client.php";
include_once "Logic/scrapYard.php";
include_once "Logic/genFunc.php";

class Request {
	private $code;
	private $deadline;
	private $type;
	private $size;
	private $sizeUnit;
	private $color;
	private $amount;
	private $maxPrice;
	private $client;
	private $autoElect;
	private $finished;

	function __construct($deadline, $type, $size, $sizeUnit, $color, $amount, $maxPrice, $client, $autoElect, $finished) {
		$this->code = -1;
		$this->setDeadline($deadline);
		$this->setType($type);
		$this->setSize($size);
		$this->sizeUnit = $sizeUnit;
		$this->setColor($color);
		$this->setAmount($amount);
		$this->setMaxPrice($maxPrice);
		$this->client = $client;
		$this->autoElect = $autoElect;
		$this->finished = $finished;
	}

	 public function insert() {
    	if($this->code == -1){
            $sc = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/NewPeticion?wsdl");  
            $params  = array('tipo'=> $this->type, 'fechaTope' => $this->deadline, 'tamanyo' => $this->size, 'tamUnidad' => $this->sizeUnit, 'color' => $this->color, 
                'cantidad' => $this->amount, 'precioMax' => $this->maxPrice, 'usuario' => $this->client, 'autoElect' => $this->autoElect, 'finalizado' => $this->finished);
            $sc->Insert($params);
    	}
    }

    public function update() {
    	if($this->code != -1){

    	}
    }

    public function Delete() {
    	if($this->code != -1){

    	}
    }

    /******** GETTERS & SETTERS *********/

    public function getCode() {
    	return $this->code;
    }

    public function setCode($code){
        $this->code = $code;
    }

    public function getDeadline() {
    	return $this->deadline;
    }

    public function setDeadline($deadline) {
        if(!checkDateFormat($deadline)){
            throw new RequestException("Error fecha incorrecta", 1);
        }
        $date = $deadline;
        $expr = "/^\d{1,2}\/\d{1,2}\/\d{2,4}$/";
        if (preg_match($expr, $deadline)){
            list($day, $month, $year) = split('/', $deadline);
            $date = "$day-$month-$year";
        }
    	$this->deadline = $date;
    }

    public function getType() {
    	return $this->type;
    }

    public function setType($type) {
        if(isEmpty($type)){
            throw new RequestException("Error el campo pieza no puede ser vacio", 2);
        }
    	$this->type = $type;
    }

    public function getSize() {
    	return $this->size;
    }

    public function setSize($size) {
        if(!isEmpty($size) && is_nan($size)){
            throw new RequestException("Error el campo tamaño debe ser un número", 3);
        }
    	$this->size = $size;
    }

    public function getSizeUnit() {
    	return $this->sizeUnit;
    }

    public function setSizeUnit($sizeUnit) {
    	$this->sizeUnit = $sizeUnit;
    }

    public function getColor() {
    	return $this->color;
    }

    public function setColor($color) {
        /*if(!isEmpty($color) && !checkColor($color)){
            throw new RequestException("Error en el formato del campo Color", 4);
        }*/
    	$this->color = $color;
    }

    public function getAmount() {
    	return $this->amount;
    }

    public function setAmount($amount) {
        if(is_nan($amount)){
            throw new RequestException("Error el campo cantidad debe ser un número", 5);
        }
    	$this->amount = $amount;
    }

    public function getMaxPrice() {
    	return $this->maxPrice;
    }

    public function setMaxPrice($maxPrice) {
        if(!isEmpty($maxPrice) && is_nan($maxPrice)){
            throw new RequestException("Error el campo precio máximo debe ser un número", 6);
            
        }
    	$this->maxPrice = $maxPrice;
    }

    public function getClient() {
    	return $this->client;
    }

    public function setClient($client) {
    	$this->client = $client;
    }

    public function isAutoElect() {
    	return $this->autoElect;
    }

    public function setAutoElect($autoElect) {
    	$this->autoElect = $autoElect;
    }

    public function isFinished() {
    	return $this->finished;
    }

    public function setFinished($finished) {
    	$this->finished = $finished;
    }

    public static function getPendingByNIF($nif){
        $sc = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/DarPeticionesNifP?wsdl");  
        $params  = array('nif'=> $nif);
        $result = json_decode($sc->DarPeticiones($params)->return);
        $requests = array();

        for ($i=0; $i < count($result); $i++) { 
            $c = $result[$i]->client;

            $nif = isset($c->nif)? $c->nif : "";
            $name = isset($c->name)? $c->name : "";
            $surname = isset($c->surname)? $c->surname : "";
            $password = isset($c->password)? $c->password : "";
            $address = isset($c->address)? $c->address : "";
            $DOB = isset($c->DOB)? $c->DOB : "";

            $client = new Client($nif, $name, $surname, $password, $address, changeDate($DOB));
            $client->setID($c->id);

            $deadline = isset($result[$i]->deadline)? $result[$i]->deadline : "";
            $type = isset($result[$i]->type)? $result[$i]->type : "";
            $size = isset($result[$i]->size)? $result[$i]->size : "";
            $sizeUnit = isset($result[$i]->sizeUnit)? $result[$i]->sizeUnit : "";
            $color = isset($result[$i]->color)? $result[$i]->color : "";
            $amount = isset($result[$i]->amount)? $result[$i]->amount : "";
            $maxPrice = isset($result[$i]->maxPrice)? $result[$i]->maxPrice : "";
            $autoElect = isset($result[$i]->autoElect)? $result[$i]->autoElect : "";
            $finished = isset($result[$i]->finished)? $result[$i]->finished : "";

            $reqAux = new Request(changeDate($deadline), $type, $size, $sizeUnit, $color/*formatColor($color)*/, $amount, $maxPrice, $client, $autoElect, $finished);
            $reqAux->code = $result[$i]->code;

            array_push($requests, $reqAux);
        }

        return $requests;
    }

    public function toStdClass() {
        $class = new StdClass();
        $class->code = $this->code;
        $class->deadline = $this->deadline;
        $class->type = $this->type;
        $class->size = $this->size;
        $class->sizeUnit = $this->sizeUnit;
        $class->color = $this->color;
        $class->amount = $this->amount;
        $class->maxPrice = $this->maxPrice;
        $class->client = $this->client->toStdClass();
        $class->autoElect = $this->autoElect;
        $class->finished = $this->finished;
        return $class;
    }
}


?>