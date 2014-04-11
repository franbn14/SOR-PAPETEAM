/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ClientCEN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import CEN.ClientCEN;
import CAD.UserCAD;

/**
 *
 * @author fran
 */
@WebService(serviceName = "DarIdClientebyNif")
public class DarIdClientebyNif {

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
    @WebMethod(operationName = "GetID")
    public int GetID(@WebParam(name = "id") int id,@WebParam(name = "nif") String nif) {
        //TODO write your implementation code here:
         ClientCEN cli=ClientCEN.getByNIF(nif);
        if(cli!=null)
            return cli.getId();
        return 0;
    }
}
