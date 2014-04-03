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
@WebService(serviceName = "DarIdDesguacebyCif")
public class DarIdDesguacebyCif {

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
    @WebMethod(operationName = "getIdDes")
    public int getID(@WebParam(name = "nif") String nif) {
        ScrapYardCEN cli=ScrapYardCEN.getByCIF(nif);
        if(cli!=null)
            return cli.getId();
        return 0;
    }
}
