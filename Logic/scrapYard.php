<?php 
	/**
	* 
	*/
class ScrapYard extends User {
	
	private $cif;

	function __construct($name, $password, $address, $cif) {
		parent::__construct($name, $password, $address);
		$this->cif = $cif;
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

	public function getCIF() {
		return $this->cif;
	}

	public function setCIF($cif) {
		$this->cif = $cif;
	}

	public function string() {
		return "$this->name, $this->password, $this->address, $this->cif";
	}
}

?>