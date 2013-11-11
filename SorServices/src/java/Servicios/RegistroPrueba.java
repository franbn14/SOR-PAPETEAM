/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroPrueba")
public class RegistroPrueba {

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
    @WebMethod(operationName = "RegistroUser")
    public String RegistroUser(@WebParam(name = "texto") String texto) {
        //TODO write your implementation code here:
        return texto;
    }
}
