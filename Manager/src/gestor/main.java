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
        ClientCEN client = new ClientCEN("usuario", "apellido", "pass", "11111111W", "calle falsa", new Date(1991, 11, 14));
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
        } 
    }
}
