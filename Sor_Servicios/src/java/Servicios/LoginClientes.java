/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import CEN.ClientCEN;
import CAD.UserCAD;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logger.ClientLogger;

/**
 *
 * @author fran
 */
@WebService(serviceName = "LoginClientes")
public class LoginClientes {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
       /**
     * Web service operation
     */
    @WebMethod(operationName = "Login")
    public String Login(@WebParam(name = "Password") String Password, @WebParam(name = "nif_dni") String nif_dni) {
        //TODO write your implementation code here:
        String error="";
        ClientCEN cli=ClientCEN.getByNIF(nif_dni);
        int type=1;

        if(cli==null) {
            error="Login incorrecto";
            type=-1;
        }
        else if(cli!=null && !cli.getPassword().equals(Password)) {
            error="Password Incorrecto";         
            type=-1;
        }
        ClientLogger.setLogMessage(type,nif_dni,error);
            
        return error;
    }
}
