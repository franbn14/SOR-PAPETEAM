/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ScrapYardCEN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author fran
 */
@WebService(serviceName = "DarNombreCliente")
public class DarNombreCliente {

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
    @WebMethod(operationName = "DarNombreDesguace")
    public String DarNombreDesguace(@WebParam(name = "cif") String cif) {
        //TODO write your implementation code here:
        
        ScrapYardCEN scry=ScrapYardCEN.getByCIF(cif);
        return scry.getName();
       
    }
}
