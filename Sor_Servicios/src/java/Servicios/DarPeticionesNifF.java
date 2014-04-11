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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebService(serviceName = "DarPeticionesNifF")
public class DarPeticionesNifF {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarPeticionesHisWeb")
    public String DarPeticionesHisWeb(@WebParam(name = "nif") String nif) {
        //DarPeticiones que se hayan finalizado al aceptar alguna oferta
        ArrayList<RequestCEN> requests = new ArrayList<RequestCEN>();
        requests = RequestCEN.getAllFinishedByNIF(nif);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String listaJSON = gson.toJson(requests);
        return listaJSON;
    }

    @WebMethod(operationName = "DarPeticionesHis")
    public String DarPeticionesHis(@WebParam(name = "id") int id, @WebParam(name = "nif") String nif) {
        SecretKey key = (SecretKey) KeysManager.GetInstance().getKey(id);
        try {
            nif = AES.encrypt(nif, key);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        //DarPeticiones que se hayan finalizado al aceptar alguna oferta
        ArrayList<RequestCEN> requests = RequestCEN.getAllFinishedByNIF(nif);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String listaJSON = gson.toJson(requests);
        
        try {
            listaJSON = AES.encrypt(listaJSON, key);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        return listaJSON;
    }
}
