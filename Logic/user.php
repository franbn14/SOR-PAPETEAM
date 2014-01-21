<?php

include_once "exceptions.php";
include_once "valFunc.php";

class User {
	protected $id;
	protected $name;
	protected $password;
	protected $address;

	protected function __construct($name, $password, $address){
		$this->id = -1;
		//$this->name = $name;
		$this->setName($name);
		$this->setPass($password);
		$this->address = $address;
	}

	/********** CRUD METHODS **********/
	
	public function insert(){
		if($this->id == -1){
			
		}
	}

	public function delete(){
		if($this->id != -1){
			
		}
	}

	public function update() {
		if($this->id != -1){
			
		}
	}

	/******** GETTERS & SETTERS *********/
	
	public static function getByID($id){
		
	}

	public static function getAll(){

	}

	public function getID() {
		return $this->id;
	}

	public function setID($id) {
		$this->id = $id;
	}

	public function getName() {
		return $this->name;
	}

	public function setName($name) {
		if(!isEmpty($name)){
			$this->name = $name;
		}
		else {
			throw new UserException("El nombre no puede estar vacio", 1);
		}
	}

	public function getPass() {
		return $password;
	}

	public function setPass($pass) {
		if(isEmpty($pass)){
			throw new UserException("Error el campo contraseña no puede estar vacio", 2);
			
		}
		$this->password = $pass;
	}

	public function getAddress() {
		return $address;
	}

	public function setAddress($address) {
		$this->$address = $address;
	}

	public function string(){
		return $this->id . " " . $this->name . " " . $this->password . " " . $this->address;
	}
}

?>