<?php
include_once "serverConf.php";
include_once "user.php";
include_once "exceptions.php";

class Client extends User {
    private $nif;
    private $surname;
    private $DOB;

    public function __construct($nif, $name, $surname, $password, $address, $DOB){
    	parent::__construct($name, $password, $address);
    	//$this->nif = $nif;
        $this->setNIF($nif);
    	$this->surname = $surname;
    	//$this->DOB = $DOB;
        $this->setDOB($DOB);
    }

    /********** CRUD METHODS **********/

    public function insert() {
    	if($this->id == -1){
            $soap = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/RegistroCliente?wsdl");  
            $params  = array('Nif'=> $this->nif, 'Nombre' => $this->name, 'Direccion' => $this->address, 
                'Password' => $this->password, 'Apellidos' => $this->surname, 'Fecha' => $this->DOB);

            $result = $soap->Registro_Cli($params)->return;
            if($result != ""){
                throw new ClientException($result, 1);
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
        if(!checkDateFormat($DOB)){
            throw new ClientException("Error formato de fecha incorrecto", 2);
        }
        $date = $DOB;
        $expr = "/^\d{1,2}\/\d{1,2}\/\d{2,4}$/";
        if (preg_match($expr, $DOB)){
            list($day, $month, $year) = split('/', $DOB);
            $date = "$day-$month-$year";
        }
    	$this->DOB = $date;
    }

    public function getNIF() {
    	return $this->nif;
    }

    public function setNIF($nif) {
        if(!checkDNI($nif)){
            throw new ClientException("Error DNI incorrecto", 3); 
        }
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

    public static function geIdByNIF($nif) {
        $soap = new soapclient($GLOBALS['SERVER_LOCATION']."/Sor_Servicios/DarIdClientebyNif?wsdl");  
        $params  = array('nif'=> $nif);

        $result = $soap->GetID($params)->return;
        if($result == 0){
            throw new ClientException($result, 4);
        }
        return $result;
    }

    public function toStdClass(){
        $class = new StdClass();
        $class->id = $this->id;
        $class->nif = $this->nif;
        $class->name = $this->name;
        $class->surname = $this->surname; 
        $class->password = $this->password;
        $class->address = $this->address;
        $class->DOB = $this->DOB;
        return $class;
    }
}

?>