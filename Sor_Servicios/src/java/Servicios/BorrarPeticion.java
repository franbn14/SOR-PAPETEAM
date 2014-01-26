/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;
import CEN.OfferCEN;
import CEN.RequestCEN;
import Email.Email;
import Email.EmailFactoria;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;


/**
 *
 * @author fran
 */
@WebService(serviceName = "BorrarPeticion")
public class BorrarPeticion {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Borrar")
    public void Borrar(@WebParam(name = "id") int id) {
        RequestCEN request = new RequestCEN();
        request = RequestCEN.getByCode(id);

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
        request.delete();
    }
}
