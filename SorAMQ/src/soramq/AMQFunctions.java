/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soramq;

import CEN.OfferCEN;
import CEN.RequestCEN;
import CEN.ScrapYardCEN;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class AMQFunctions {
    static Gson gson;
    
    public AMQFunctions()
    {
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
    }
    
    public void enviarOfertasPendientesPorDesguace()
    {
        ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
        for(ScrapYardCEN desg : desguaces)
        {
            AMQPublisher conexion = new AMQPublisher();
            conexion.setParams(desg.getCif()+"p", "servidor", desg.getCif()+"p", desg.getCif()+"p");
            conexion.open();
            ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
            requests = OfferCEN.getByCIFDesPendientes(desg.getCif());
            String listaJSON = gson.toJson(requests);
            conexion.send(listaJSON, 60000);
        }
        
    }
    
    public void enviarOfertasAceptadasPorDesguace()
    {
        ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
        
        for(ScrapYardCEN desg : desguaces)
        {
            AMQPublisher conexion = new AMQPublisher();
            conexion.setParams(desg.getCif()+"f", "servidor", desg.getCif()+"f", desg.getCif()+"f");
            conexion.open();
            ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
            requests = OfferCEN.getAcceptedByDesNIF(desg.getCif());
            String listaJSON = gson.toJson(requests);
            conexion.send(listaJSON, 60000);
        }
        
    }
    
    public void enviarPeticionesPendientes()
    {
        AMQPublisher conexion = new AMQPublisher();
        conexion.setParams("pendientes", "servidor", "pendientes", "pendientes");
        conexion.open();
        ArrayList<RequestCEN> requests= new ArrayList<RequestCEN> ();
        requests = RequestCEN.getAllOpened();
        String listaJSON = gson.toJson(requests);
        conexion.send(listaJSON, 60000);
    }
    
    public static void main(String[] args) {
           AMQFunctions pr = new AMQFunctions();
          pr.enviarOfertasAceptadasPorDesguace();
          pr.enviarOfertasPendientesPorDesguace();
          pr.enviarPeticionesPendientes();
    }
}
