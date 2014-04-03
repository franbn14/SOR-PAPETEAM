/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.OfferCEN;
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
@WebService(serviceName = "DarOfertasRequestOk")
public class DarOfertasRequestOk {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarOfertasByRequestOk")
    public String DarOfertasByRequestOk(@WebParam(name = "idR") int idR) {
          ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
        requests=OfferCEN.getAcceptedByRequest(idR);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String listaJSON = gson.toJson(requests);
        return listaJSON;
    }
}
