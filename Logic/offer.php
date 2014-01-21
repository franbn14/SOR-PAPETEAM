<?php
include_once "valFunc.php";
include_once "exceptions.php";
include_once "serverConf.php";
include_once "Logic/request.php";
include_once "Logic/scrapYard.php";

class Offer {
	private $code;
	private $type;
	private $size;
	private $sizeUnit;
	private $color;
	private $amount;
	private $price;
	private $request;
	private $scrapyard;

	function __construct($type, $size, $sizeUnit, $color, $amount, $price, $request, $scrapyard) {
		$this->code = -1;
		$this->setType($type);
        $this->setSize($size);
		$this->sizeUnit = $sizeUnit;
		$this->setColor($color);
		$this->setAmount($amount);
		$this->setPrice($price);
		$this->request = $request;
		$this->scrapyard = $scrapyard;
	}

	 /********** CRUD METHODS **********/

    public function insert() {
    	if($this->code == -1){

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

    public function getType() {
    	return $this->type;
    }

    public function setType($type) {
        if(isEmpty($type)){
            throw new OfferException("Error el campo pieza no puede ser vacio", 1);
        }
    	$this->type = $type;
    }

    public function getSize() {
    	return $this->type;
    }

    public function setSize($size) {
        if(!isEmpty($size) && is_nan($size)){
            throw new OfferException("Error el campo tamaño debe ser un número", 2);
            
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
            throw new OfferException("Error en el formato del campo color", 3);
        }*/
    	$this->color = $color;
    }

    public function getAmount() {
    	return $this->amount;
    }

    public function setAmount($amount) {
        if(!isEmpty($amount) && is_nan($amount)){
            throw new OfferException("Error el campo cantidad debe ser un número", 4);
        }
    	$this->amount = $amount;
    }

    public function getPrice() {
    	return $this->price;
    }

    public function setPrice($price) {
        if(!isEmpty($price) && is_nan($price)){
            throw new OfferException("Error el campo precio debe ser un número", 5);
        }
    	$this->price = $price;
    }

    public function getRequest() {
    	return $request;
    }

    public function setRequest($request) {
    	$this->request = $request;
    }

    public function getScrapYard() {
    	return $this->scrapyard;
    }

    public function setScrapYard($scrapyard) {
    	$this->scrapyard = $scrapyard;
    }	

    public static function getByRequest($idR){
        $sc = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/DarOfertasRequest?wsdl");  
        $params  = array('idR'=> $idR);
        $result = json_decode($sc->DarOfertasByR($params)->return);
        $offers = array();

        for($i = 0; $i < count($result); $i++){
            $r = $result[$i]->request;
            $sy = $result[$i]->scrapyard;
            $c = $r->client;

            $nif = isset($c->nif)? $c->nif : "";
            $name = isset($c->name)? $c->name : "";
            $surname = isset($c->surname)? $c->surname : "";
            $password = isset($c->password)? $c->password : "";
            $address = isset($c->address)? $c->address : "";
            $DOB = isset($c->DOB)? $c->DOB : "";

            $client = new Client($nif, $name, $surname, $password, $address, changeDate($DOB));
            $client->setID($c->id);

            $deadline = isset($r->deadline)? $r->deadline : "";
            $type = isset($r->type)? $r->type : "";
            $size = isset($r->size)? $r->size : "";
            $sizeUnit = isset($r->sizeUnit)? $r->sizeUnit : "";
            $color = isset($r->color)? $r->color : "";
            $amount = isset($r->amount)? $r->amount : "";
            $maxPrice = isset($r->maxPrice)? $r->maxPrice : "";
            $autoElect = isset($r->autoElect)? $r->autoElect : "";
            $finished = isset($r->finished)? $r->finished : "";

            $request = new Request(changeDate($deadline), $type, $size, $sizeUnit, /*formatColor($color)*/$color, $amount, $maxPrice, $client, $autoElect, $finished);
            $request->setCode($result[$i]->code);

            $cif = isset($sy->cif)? $sy->cif : "";
            $email = isset($sy->email)? $sy->email : "";
            $name = isset($sy->name)? $sy->name : "";
            $password = isset($sy->password)? $sy->password : "";
            $address = isset($sy->address)? $sy->address : "";

            $scrapyard = new ScrapYard($name, $password, $address, $cif, $email);
            $scrapyard->setID($sy->id);

            $type = isset($result[$i]->type)? $result[$i]->type : "";
            $size = isset($result[$i]->size)? $result[$i]->size : "";
            $sizeUnit = isset($result[$i]->sizeUnit)? $result[$i]->sizeUnit : "";
            $color = isset($result[$i]->color)? $result[$i]->color : "";
            $amount = isset($result[$i]->amount)? $result[$i]->amount : "";
            $price = isset($result[$i]->maxPrice)? $result[$i]->maxPrice : "";


            $offer = new Offer($type, $size, $sizeUnit, /*formatColor($color)*/$color, $amount, $price, $request, $scrapyard);
            $offer->code = $result[$i]->code;

            array_push($offers, $offer);
        }

        return $offers;
    }

    public static function acceptOffers($offers) {
        $sc = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/AceptarOfertas?wsdl");  
        $params  = array('idS'=> $offers);
        $result = $sc->AceptarOfertasDe($params)->return;
        if($result != ""){
            throw new OfferException($result, 6);       
        }
    }

    public function toStdClass() {
        $class = new StdClass();
        $class->code = $this->code;
        $class->type = $this->type;
        $class->size = $this->size;
        $class->sizeUnit = $this->sizeUnit;
        $class->color = $this->color;
        $class->amount = $this->amount;
        $class->price = $this->price;
        $class->request = $this->request->toStdClass();
        $class->scrapyard = $this->scrapyard->toStdClass();
        return $class;
    }
}
?>