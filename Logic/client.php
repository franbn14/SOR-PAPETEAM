<?php
include("user.php");

class Client extends User {
    private $nif;
    private $surname;
    private $DOB;

    public function __construct($nif, $name, $surname, $password, $address, $DOB){
    	parent::__construct($name, $password, $address);
    	$this->nif = $nif;
    	$this->surname = $surname;
    	$this->DOB = $DOB;
    }

    /********** CRUD METHODS **********/

    public function insert() {
    	if($this->id == -1){

    	}
    }

    public function update() {
    	if($this->id != -1){

    	}
    }

    public function Delete() {
    	if($this->id != -1){

    	}
    }
    /******** GETTERS & SETTERS *********/

    public static function getAllClients(){

    }

	public static function getByID() {
		//Content
    }

    public static function getByNIF() {
    	//Content
    }

    public function getDOB() {
    	return $this->DOB;
    }

    public function setDOB($DOB) {
    	$this->DOB = $DOB;
    }

    public function getNIF() {
    	return $this->nif;
    }

    public function setNIF($nif) {
    	$this->nif = $nif;
    }

    public function getSurname() {
    	return $this->surname;
    }

    public function setSurname($surname) {
    	$this->surname = $surname;
    }

    public function string(){
    	echo "$this->nif, $this->name, $this->surname, $this->password, $this->address, $this->DOB";
    }
}

?>