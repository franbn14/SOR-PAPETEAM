/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.UnitsCEN;
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
@WebService(serviceName = "DarUnidades")
public class DarUnidades {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarUnidadId")
    public String DarUnidadId(@WebParam(name = "id") int id) {
        return UnitsCEN.getByID(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarTodasUnidades")
    public String DarTodasUnidades() {
        ArrayList<String> units= new ArrayList<String> ();
       units=UnitsCEN.getAll();
        Gson gson = new GsonBuilder().create();
            String listaJSON = gson.toJson(units);
        return listaJSON;
    }
}
