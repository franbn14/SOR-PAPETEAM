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
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author fran
 */
@WebService(serviceName = "DarOfertasSeleccionadas")
public class DarOfertasSeleccionadas {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DarOfertasSelection")
    public String DarOfertasSelection(@WebParam(name = "idR") int idR) {
        ArrayList<OfferCEN> sel= new ArrayList<OfferCEN> ();
        RequestCEN r=new RequestCEN();
        r=r.getByCode(idR);
        sel=OfferCEN.AutoSelection(r);
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
        String listaJSON = gson.toJson(sel);
        return listaJSON;
    }
}
