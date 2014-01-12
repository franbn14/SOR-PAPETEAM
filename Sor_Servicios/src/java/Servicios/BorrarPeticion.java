/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;
import CEN.RequestCEN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author fran
 */
@WebService(serviceName = "BorrarPeticion")
public class BorrarPeticion {

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
    @WebMethod(operationName = "Borrar")
    public void Borrar(@WebParam(name = "id") int id) {
        RequestCEN r=new RequestCEN();
        r=r.getByCode(id);
        r.delete();
    }
}
