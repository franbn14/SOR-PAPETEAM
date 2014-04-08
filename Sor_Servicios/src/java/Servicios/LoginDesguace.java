/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ClientCEN;
import CEN.ScrapYardCEN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import logger.SYLogger;

/**
 *
 * @author fran
 */
@WebService(serviceName = "LoginDesguace")
public class LoginDesguace {

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
    @WebMethod(operationName = "Login_Des")
    public String Login_Des(@WebParam(name = "Password") String Password, @WebParam(name = "cif") String cif) {
        String error="";
        ScrapYardCEN scry=ScrapYardCEN.getByCIF(cif);
        int type=1;
        
        if(scry==null)
        {
            error="CIF incorrecto";
            type = -1;
        }
        else if(scry!=null && !scry.getPassword().equals(Password))
        {
            error="Password Incorrecto";
            type = -1;
        }
            
        SYLogger.setLogMessage(type, cif, error);
        return error;
    }
}
