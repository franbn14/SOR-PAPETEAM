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
        
        System.out.println("-------------TEST CLIENTE---------------");
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
        
        System.out.println("TODOS LOS CLIENTES: ");
        for(ClientCEN c : ClientCEN.getAllClients()){
            System.out.println(c);
        }
        
        ClientCEN client4 = new ClientCEN("usuario", "apellido", "pass", "11111112A", "calle falsa", new Date(1991, 11, 14));
        client4.insert();
        System.err.println("Si, tiene que salir error...");
        
        client.delete();
                
        System.out.println("\n-------------TEST SCRAP YARD ---------------");
        /*ClientCEN client = new ClientCEN("usuario", "apellido", "pass", "11111111W", "calle falsa", new Date(1991, 11, 14));
        client.insert();
        client.updateNIF("22222222E");
        
        ScrapYardCEN sy = new ScrapYardCEN("desguace", "mypass", "perdido", "12345678J");
        sy.insert();
        
        System.out.println("Usuarios: ");
        for(ClientCEN c : ClientCEN.getAllClients()){
            System.out.println(c);
        }
        for(ScrapYardCEN s : ScrapYardCEN.getAllScrapYards()){
            System.out.println(s);
        } 
        
        RequestCEN req=new RequestCEN("Tornillos tipo 8",2,client);
        req.insert();
        
        RequestCEN req1=new RequestCEN("Tuercas tipo 6",5,client);
        req1.insert();
                        
        OfferCEN offer = new OfferCEN("Una oferta interesante", req, sy);
        offer.insert();
        
        System.out.println("Solicitudes: ");
        for(RequestCEN r : RequestCEN.getAllRequests()){
            System.out.println(r);
        }
        
        System.out.println("Ofertas: ");
        for(OfferCEN o : OfferCEN.getAllOffers()){
            System.out.println(o);
        }
        
        client.delete();
                 
        System.out.println("Usuarios: ");
        for(ClientCEN c : ClientCEN.getAllClients()){
            System.out.println(c);
        }
        for(ScrapYardCEN s : ScrapYardCEN.getAllScrapYards()){
            System.out.println(s);
        } */
    }
}
