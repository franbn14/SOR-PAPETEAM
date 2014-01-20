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
import java.util.ArrayList;

/**
 *
 * @author Gustavo
 */
public class PruebaAMQ {
    static Gson gson;
    
    public PruebaAMQ()
    {
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
    }
    
    public static void enviarOfertasPendientesPorDesguace()
    {
        ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
        
        for(ScrapYardCEN desg : desguaces)
        {
            SorAMQ conexion = new SorAMQ();
            conexion.setParams(desg.getCif()+"p", "servidor", desg.getCif()+"p", desg.getCif()+"p");
            conexion.open();
            ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
            requests = OfferCEN.getByCIFDesPendientes(desg.getCif());
            String listaJSON = gson.toJson(requests);
            conexion.send(listaJSON, 60000);
        }
        
    }
    
    public static void enviarOfertasAceptadasPorDesguace()
    {
        ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
        
        for(ScrapYardCEN desg : desguaces)
        {
            SorAMQ conexion = new SorAMQ();
            conexion.setParams(desg.getCif()+"f", "servidor", desg.getCif()+"f", desg.getCif()+"f");
            conexion.open();
            ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
            requests = OfferCEN.getAcceptedByDesNIF(desg.getCif());
            String listaJSON = gson.toJson(requests);
            conexion.send(listaJSON, 60000);
        }
        
    }
    
    public static void enviarPeticionesPendientes()
    {
        SorAMQ conexion = new SorAMQ();
        conexion.setParams("pendientes", "servidor", "pendientes", "pendientes");
        conexion.open();
        ArrayList<RequestCEN> requests= new ArrayList<RequestCEN> ();
        requests = RequestCEN.getAllOpened();
        String listaJSON = gson.toJson(requests);
        conexion.send(listaJSON, 60000);
    }
    
    public static void main(String[] args) {
           PruebaAMQ pr = new PruebaAMQ();
          enviarPeticionesPendientes();
    }
}
