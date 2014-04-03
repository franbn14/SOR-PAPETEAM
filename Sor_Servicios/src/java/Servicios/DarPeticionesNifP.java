/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.RequestCEN;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;



/**
 *
 * @author fran
 */

@WebService(serviceName = "DarPeticionesNifP")
public class DarPeticionesNifP {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        
        return "";
       
    }

    /**
     * Web service operation
     */
    
    @WebMethod(operationName = "DarPeticiones")
    public String DarPeticiones(@WebParam(name = "nif") String nif) {
       ArrayList<RequestCEN> requests= new ArrayList<RequestCEN> ();
       requests=RequestCEN.getAllOpenedByNIF(nif);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
            String listaJSON = gson.toJson(requests);
        return listaJSON;
    }
}
