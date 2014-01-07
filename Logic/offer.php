<?php
/**
* 
*/
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
		$this->type = $type;
		$this->size = $size;
		$this->sizeUnit = $sizeUnit;
		$this->color = $color;
		$this->amount = $amount;
		$this->price = $price;
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
    	$this->type = $type;
    }

    public function getSize() {
    	return $this->type;
    }

    public function setSize($size) {
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
    	$this->color = $color;
    }

    public function getAmount() {
    	return $this->amount;
    }

    public function setAmount($amount) {
    	$this->amount = $amount;
    }

    public function getPrice() {
    	return $this->price;
    }

    public function setPrice($price) {
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
}
?>