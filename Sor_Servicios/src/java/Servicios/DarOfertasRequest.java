/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.OfferCEN;
import CEN.RequestCEN;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import security.AES;
import security.KeysManager;

/**
 *
 * @author fran
 */
@WebService(serviceName = "DarOfertasRequest")
public class DarOfertasRequest {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarOfertasByRWeb")
    public String DarOfertasByRWeb(@WebParam(name = "idR") int idR) {
        ArrayList<OfferCEN> requests = OfferCEN.getByRequest(idR);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String listaJSON = gson.toJson(requests);
        return listaJSON;
    }
    
    @WebMethod(operationName = "DarOfertasByR")
    public String DarOfertasByR(@WebParam(name = "id") int id, @WebParam(name = "idR") String idR) {
        
        KeysManager km = KeysManager.GetInstance();
        try {
            idR = AES.decrypt(idR, (SecretKey)km.getKey(id));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        int idRInt = Integer.parseInt(idR);
        
        ArrayList<OfferCEN> requests = OfferCEN.getByRequest(idRInt);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String listaJSON = gson.toJson(requests);
        
        try {
            listaJSON = AES.encrypt(listaJSON, (SecretKey)km.getKey(id));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        return listaJSON;
    }
}
