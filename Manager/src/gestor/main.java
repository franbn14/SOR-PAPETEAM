/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestor;

import CAD.UserCAD;
import CEN.*;
import java.util.Date;

/**
 *
 * @author esteve
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
<<<<<<< HEAD
        
        System.out.println("Usuarios: ");
       ScrapYardCEN cen=ScrapYardCEN.getByCIF("66666666A");
            System.out.println(cen);
        }
    
=======
        
        
        
        /*ClientCEN client = ClientCEN.getByID(1);
        //Date dateLine, String type, Double size, int sizeUnit, String color, Integer amount, Double maxPrice,  ClientCEN client, boolean autoSelec, boolean finished
        RequestCEN r =  new RequestCEN(new Date(2014, 10, 18), "Tornillos cabeza plana", null, 0, null, 10, 10.6, client, true, true);
        r.insert();
        System.out.println(r);
        ScrapYardCEN sy = ScrapYardCEN.getByID(6);
        System.out.println(sy);
        System.out.println(r);
        //String type, Double size, int sizeUnit, String color, Integer amount, Double price, RequestCEN request, ScrapYardCEN scrapyard
        OfferCEN o = new OfferCEN("Tornillos cabeza plana", null, 0, null, 10, 10.6, r, sy);
        o.insert();
        System.out.println(o);
        o.update("Tornillos cabeza rendonda", 6.0, 0, "ff0000", 11, 10.7, r, sy);
        System.out.println(o);
        r.delete();
        o.delete();
        
        
        for(RequestCEN r : RequestCEN.getAllRequests()){
            System.out.println(r);
        }*/
        
        /*System.out.println("-------------TEST CLIENTE---------------");
        ClientCEN client = new ClientCEN("usuario", "apellido", "pass", "11111111W", "calle falsa", new Date(1991, 11, 14));
        client.insert();
        System.out.println(client);
        client.updateNIF("22222222E");
        System.out.println(client);
        client.updateName("pedro");
        System.out.println(client);
        client.updateSurname("gracia");
        System.out.println(client);
        client.updatePassword("nuevaPass");
        System.out.println(client);
        client.updateAddress("calle desconocida");
        System.out.println(client);
        client.updateDOB(new Date(1987, 11, 14));
        System.out.println(client);
        client.update("usuario", "pass", "calle false");
        System.out.println(client);
        client.update("pedro", "apellido", "nuevaPass", "11111112A", "calle desconocida", new Date(1991, 11, 14));
        System.out.println(client);
        
        ClientCEN client2 = ClientCEN.getById(client.getId());
        System.out.println(client2);
        
        ClientCEN client3 = ClientCEN.getByNIF(client.getNIF());
        System.out.println(client3);
        
        ClientCEN client4 = new ClientCEN("usuario", "apellido", "pass", "11111112A", "calle falsa", new Date(1991, 11, 14));
        client4.insert();
        System.err.println("Si, tiene que salir el error...");
        
        System.out.println("TODOS LOS CLIENTES: ");
        for(ClientCEN c : ClientCEN.getAllClients()){
            System.out.println(c);
        }
        
        client.delete();
                
        System.out.println("\n-------------TEST SCRAP YARD ---------------");
        ScrapYardCEN sy = new ScrapYardCEN("desguace", "pass", "calle falsa", "11111113A");
        sy.insert();
        System.out.println(sy);
        sy.updateName("todo piezas");
        System.out.println(sy);
        sy.updatePassword("newPass");
        System.out.println(sy);
        sy.updateAddress("calle desconocida");
        System.out.println(sy);
        sy.updateCIF("11111113B");
        System.out.println(sy);
        sy.update("desguace", "pass", "calle falsa");
        System.out.println(sy);
        sy.update("todo piezas", "newPass", "calle desconocida", "11111113A");
        System.out.println(sy);
        
        ScrapYardCEN sy2 = ScrapYardCEN.getById(sy.getId());
        System.out.println(sy2);
        
        ScrapYardCEN sy3 = ScrapYardCEN.getByCIF(sy.getCif());
        System.out.println(sy3);
        
        ScrapYardCEN sy4 = new ScrapYardCEN("desguace", "pass", "calle falsa", "11111113A");
        sy4.insert();
        System.err.println("Si, tiene que salir el error...");
        
        System.out.println("TODOS LOS DESGUACES: ");
        for(ScrapYardCEN s : ScrapYardCEN.getAllScrapYards()){
            System.out.println(s);
        }
        
        sy.delete();
        
        System.out.println("\n-------------TEST REQUEST/OFFER ---------------");
        
        ClientCEN client5 = new ClientCEN("usuario", "apellido", "pass", "11111111W", "calle falsa", new Date(1991, 11, 14));
        client5.insert();
        System.out.println(client5);
        
        RequestCEN req = new RequestCEN("Tornillos tipo 8", 2, client5);
        req.insert();
        System.out.println(req);
        
        ScrapYardCEN sy5 = new ScrapYardCEN("desguace", "pass", "calle falsa", "11111113A");
        sy5.insert();
        System.out.println(sy5);
        req.update("Tornillos tipo 7", 20, client5);
        System.out.println(req);
        
        System.out.println(req);
        RequestCEN req1 = new RequestCEN("Tuercas tipo 6", 5, client5);
        req1.insert();
        System.out.println(req1); 
        
        OfferCEN offer = new OfferCEN("Una oferta interesante", req, sy5);
        offer.insert();
        System.out.println(offer);
        offer.update("La mejor oferta", req1, sy5);
        System.out.println(offer);
        
        
        System.out.println("TODAS LAS SOLICITUDES: ");
        for(RequestCEN r : RequestCEN.getAllRequests()){
            System.out.println(r);
        }
        
        System.out.println("TODAS LAS OFERTAS: ");
        for(OfferCEN o : OfferCEN.getAllOffers()){
            System.out.println(o);
        }
        
        req.delete();
        req1.delete();
        offer.delete();
        sy5.delete();
        client5.delete();*/
        
 
>>>>>>> 87adf3bd35c091e0ed6698c43d5bfe265fea8e5f
    }

