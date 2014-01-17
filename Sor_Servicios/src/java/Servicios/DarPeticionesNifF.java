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
@WebService(serviceName = "DarPeticionesNifF")
public class DarPeticionesNifF {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarPeticionesHis")
    public String DarPeticionesHis(@WebParam(name = "nif") String nif) 
    {      
        //DarPeticiones que se hayan finalizado al aceptar alguna oferta
         ArrayList<RequestCEN> requests= new ArrayList<RequestCEN> ();
       requests=RequestCEN.getAllFinishedByNIF(nif);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
            String listaJSON = gson.toJson(requests);
        return listaJSON;
    }
}
