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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo
 */
public class AMQFunctions extends Thread{
    static Gson gson;
    static AMQFunctions pr;
    public AMQFunctions()
    {
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
    }
    
    public void enviarOfertasPendientesPorDesguace() throws InterruptedException
    {
        ArrayList<AMQPublisher> conexiones = new ArrayList<AMQPublisher>();
        while(true)
        {
            ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
            for(ScrapYardCEN desg : desguaces)
            {
                int ind = 0;
                boolean existe = false;
                for(int j = 0; j < conexiones.size(); j++)
                {
                    if(conexiones.get(j).destino.equals("dynamicTopics/"+desg.getCif()+"p"))
                    {
                        ind = j;
                        existe = true;
                        break;
                    } 
                }
                
                if(!existe)
                {
                    AMQPublisher nueva = new AMQPublisher();
                    nueva.setParams(desg.getCif()+"p", "servidor", desg.getCif()+"p", desg.getCif()+"p");
                    conexiones.add(nueva);
                    nueva.open();
                    ind = conexiones.size()-1;
                }
                
                AMQPublisher conexion = conexiones.get(ind);
                ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
                requests = OfferCEN.getByCIFDesPendientes(desg.getCif());
                String listaJSON = gson.toJson(requests);
                conexion.send(listaJSON, 60000);
            }
            Thread.sleep(10000);
        }
        
    }
    
    public void enviarOfertasAceptadasPorDesguace() throws InterruptedException
    {
        ArrayList<AMQPublisher> conexiones = new ArrayList<AMQPublisher>();
        while(true)
        {
            ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
            for(ScrapYardCEN desg : desguaces)
            {
                int ind = 0;
                boolean existe = false;
                for(int j = 0; j < conexiones.size(); j++)
                {
                    if(conexiones.get(j).destino.equals("dynamicTopics/"+desg.getCif()+"f"))
                    {
                        ind = j;
                        existe = true;
                        break;
                    } 
                }
                
                if(!existe)
                {
                    AMQPublisher nueva = new AMQPublisher();
                    nueva.setParams(desg.getCif()+"f", "servidor", desg.getCif()+"f", desg.getCif()+"f");
                    conexiones.add(nueva);
                    nueva.open();
                    ind = conexiones.size()-1;
                }
                
                AMQPublisher conexion = conexiones.get(ind);
                ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
                requests = OfferCEN.getAcceptedByDesNIF(desg.getCif());
                String listaJSON = gson.toJson(requests);
                conexion.send(listaJSON, 60000);
            }
            Thread.sleep(10000);
        }
    }
    
    public void enviarPeticionesPendientes() throws InterruptedException
    {
        AMQPublisher conexion = new AMQPublisher();
        conexion.setParams("pendientes", "servidor", "pendientes", "pendientes");
        conexion.open();
        
        while(true)
        {
            ArrayList<RequestCEN> requests= new ArrayList<RequestCEN> ();
            requests = RequestCEN.getAllOpened();
            String listaJSON = gson.toJson(requests);
            conexion.send(listaJSON, 60000);
            Thread.sleep(10000);
        }
    }
    
    public void start()
    {
        Thread t1 = new Thread(new enviarOfertasAceptadasPorDesguace());
        Thread t2 = new Thread(new enviarOfertasPendientesPorDesguace());
        Thread t3 = new Thread(new enviarPeticionesPendientes());
        t1.start();
        t2.start();
        t3.start();
    }
    
    public static void main(String[] args) throws InterruptedException {
        new AMQFunctions().start();
    }
    
    class enviarOfertasAceptadasPorDesguace implements Runnable//extends Thread
    {
        public void run()
        {
            try {
                pr = new AMQFunctions();
                pr.enviarOfertasAceptadasPorDesguace();
            } catch (InterruptedException ex) {
                Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class enviarOfertasPendientesPorDesguace implements Runnable//extends Thread
    {
        public void run()
        {
            try {
                pr = new AMQFunctions();
                pr.enviarOfertasPendientesPorDesguace();
            } catch (InterruptedException ex) {
                Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class enviarPeticionesPendientes implements Runnable//extends Thread
    {
        public void run()
        {
            try {
                pr = new AMQFunctions();
                pr.enviarPeticionesPendientes();
            } catch (InterruptedException ex) {
                Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
