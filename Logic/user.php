<?php

class User {
	protected $id;
	protected $name;
	protected $password;
	protected $address;

	protected function __construct($name, $password, $address){
		$this->id = -1;
		$this->name = $name;
		$this->password = $password;
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
		return $this->$id;
	}

	public function getName() {
		return $this->$name;
	}

	public function setName($name) {
		$this->$name = $name;
	}

	public function getPass() {
		return $password;
	}

	public function setPass($pass) {
		$this->$password = $pass;
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