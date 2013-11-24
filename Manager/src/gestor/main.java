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
        
        System.out.println("TODOS LOS CLIENTES: ");
        for(ScrapYardCEN s : ScrapYardCEN.getAllScrapYards()){
            System.out.println(s);
        }
        
        sy.delete();
        
        
        
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
