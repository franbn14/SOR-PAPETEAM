/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;
import CEN.ClientCEN;
import CEN.OfferCEN;
import CEN.RequestCEN;
import Email.Email;
import Email.EmailFactoria;
import java.util.ArrayList;
import javax.crypto.SecretKey;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;
import logger.ClientLogger;
import security.AES;
import security.KeysManager;


/**
 *
 * @author fran
 */
@WebService(serviceName = "BorrarPeticion")
public class BorrarPeticion {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "BorrarWeb")
    public void BorrarWeb(@WebParam(name = "id") int id) {
        RequestCEN request = RequestCEN.getByCode(id);

        //Obtengo las ofertas para avisar a los desguaces que se han rechazado sus ofertas.
        ArrayList<OfferCEN> ofertas = OfferCEN.getByRequest(id);
        
        if (ofertas != null) {
            for (int i = 0; i < ofertas.size(); i++) {
                //Enviamos el email para notificar de el rechazo de las ofertas
                Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.OfertaRechazada, ofertas.get(i));
                try {
                    email.send();
                } catch (MessagingException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        ClientLogger.setLogMessage(3,ClientCEN.getByID(request.getClient().getId()).getNIF(),id+"");
        request.delete();
    }
    
    @WebMethod(operationName = "Borrar")
    public void Borrar(@WebParam(name = "id") int id, @WebParam(name = "idP") String idP) {
        KeysManager km = KeysManager.GetInstance();
        try {
            idP = AES.decrypt(idP, (SecretKey)km.getKey(id));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        
        int idInt = Integer.parseInt(idP);
        
        RequestCEN request = RequestCEN.getByCode(idInt);

        //Obtengo las ofertas para avisar a los desguaces que se han rechazado sus ofertas.
        ArrayList<OfferCEN> ofertas = OfferCEN.getByRequest(idInt);
        
        if (ofertas != null) {
            for (int i = 0; i < ofertas.size(); i++) {
                //Enviamos el email para notificar de el rechazo de las ofertas
                Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.OfertaRechazada, ofertas.get(i));
                try {
                    email.send();
                } catch (MessagingException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        ClientLogger.setLogMessage(3,ClientCEN.getByID(request.getClient().getId()).getNIF(),id+"");
        request.delete();
    }

}
