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
        ClientCEN cliente = new ClientCEN("usuario", "apellido", "pass", "11111111W", "calle falsa", new Date(1991, 11, 14));
        cliente.insert();
        cliente.updateDNI("22222222E");
        ScrapYardCEN sy = new ScrapYardCEN("desguace", "mypass", "perdido", "12345678J");
        sy.insert();
        
        System.out.println("Usuarios: ");
        for(ClientCEN c : ClientCEN.getAllClients()){
            System.out.println(c);
        }
        
        GarageCEN garage = new GarageCEN("Taller Manos a la Obra", "1234", "Calle del taller", "123456789");
        garage.insert();
                
        for(GarageCEN g : GarageCEN.getAllGarages()){
            System.out.println(g);
        }
        
        RequestCEN req=new RequestCEN("Tornillos",2,cliente);
        req.insert();
        
        /*RequestCEN req1=new RequestCEN("Tuercas",5,garage);
        req1.insert();*/
        
        /*OfferCEN offer = new OfferCEN("Una oferta interesante", req, sy);
        offer.insert();*/
        
        System.out.println("Solicitudes: ");
        for(RequestCEN r : RequestCEN.getAllRequests()){
            System.out.println(r);
        }
        
        /*System.out.println("Ofertas: ");
        for(OfferCEN o : OfferCEN.getAllOffers()){
            System.out.println(o);
        }*/
    }
}
