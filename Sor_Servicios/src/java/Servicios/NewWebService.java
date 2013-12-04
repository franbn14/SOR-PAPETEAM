/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;



/**
 *
 * @author fran
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * 
     *
     **/
    @WebMethod(operationName = "operation")
    public JSONObject operation(@WebParam(name = "nif") String nif) {
        //TODO write your implementation code here:
        JSONObject pilot = new JSONObject();
        pilot.put( "firstName", "John");
        pilot.put( "lastName", "Adams");
       
        return null;
    }
}
