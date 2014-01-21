<?php 
include_once "user.php";
include_once "valFunc.php";
include_once "exceptions.php";

class ScrapYard extends User {
	
	private $cif;
	private $email;

	function __construct($name, $password, $address, $cif, $email) {
		parent::__construct($name, $password, $address);
		//$this->cif = $cif;
		$this->setCIF($cif);
		$this->setEmail($email);
	}

	/********** CRUD METHODS **********/

	public function insert() {
		if($this->id == -1){
            $soap = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/RegistroDesguace?wsdl");  
            $params  = array('Cif'=> $this->cif, 'Nombre' => $this->name, 'Password' => $this->password,
            	'Direccion' => $this->address, 'Email' => $this->email);

            $result = $soap->Registro($params)->return;
            if($result != ""){
                throw new scrapyardException($result);
            }
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
		if(!checkCIF($cif)){
			throw new scrapyardException("Error CIF incorrecto", 1);
		}
		$this->cif = $cif;
	}

	public function getEmail() {
		return $this->email;
	}

	public function setEmail($email) {
		if(!checkEmail($email)){
			throw new scrapyardException("Error email incorrecto", 2);
		}
		else{
			$this->email = $email;
		}
	}

	public function string() {
		return "$this->name, $this->password, $this->address, $this->cif";
	}

	public function toStdClass(){
        $class = new StdClass();
        $class->id = $this->id;
        $class->cif = $this->cif;
        $class->name = $this->name;
        $class->password = $this->password;
        $class->address = $this->address;
        $class->email = $this->email;
        return $class;
    }
}

?>