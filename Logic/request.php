<?php
/**
* 
*/
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
		$this->deadline = $deadline;
		$this->type = $type;
		$this->size = $size;
		$this->sizeUnit = $sizeUnit;
		$this->color = $color;
		$this->amount = $amount;
		$this->maxPrice = $maxPrice;
		$this->client = $client;
		$this->autoElect = $autoElect;
		$this->finished = $finished;
	}

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

    public function getDeadline() {
    	return $this->deadline;
    }

    public function setDeadline($deadline) {
    	$this->deadline = $deadline;
    }

    public function getType() {
    	return $this->type;
    }

    public function setType($type) {
    	$this->type = $type;
    }

    public function getSize() {
    	return $this->size;
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

    public function getMaxPrice() {
    	return $this->maxPrice;
    }

    public function setMaxPrice($maxPrice) {
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
}
?>