/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editorequest.
 */
package Servicios;

import CEN.OfferCEN;
import CEN.RequestCEN;
import CEN.ClientCEN;
import Email.Email;
import Email.EmailFactoria;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.MessagingException;
import logger.ClientLogger;

/**
 *
 * @author fran
 */
@WebService(serviceName = "AceptarOfertas")
public class AceptarOfertas {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "AceptarOfertasDe")
    public String AceptarOfertasDe(@WebParam(name = "idS") String idS) {
        //TODO write your implementation code here:

        if (idS != "" && idS != null) {

            String ids[] = idS.split("\\s+");
            RequestCEN request = null;

            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);

                OfferCEN o = OfferCEN.getByCode(id);
                request = o.getRequest();
                o.update(o.getType(), o.getSize(), o.getSizeUnit(), o.getColor(), o.getAmount(), o.getPrice(), request, o.getScrapyard(), true);
                
                ClientLogger.setLogMessage(6,ClientCEN.getByID(request.getClient().getId()).getNIF(),id+"");
                
                Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.OfertaAceptada, o);
                try {
                    email.send();
                } catch (MessagingException ex) {
                    return "error";
                }
            }

            ArrayList<OfferCEN> ofertas = new ArrayList<>();
            if (request != null) {
                request.update(request.getdeadline(), request.getType(), request.getSize(), request.getSizeUnit(), request.getColor(),
                        request.getAmount(), request.getMaxPrice(), (ClientCEN) request.getClient(), request.isAutoElect(), true, request.isExpired());
                ofertas = OfferCEN.getByRequest(request.getCode());
            }

            //Ahora vamos hacer una pasada sobre el resto de ofertas de este petición para borrarlas y mandar email de rechazo
            for (int i = 0; i < ofertas.size(); i++) {
                if (!ofertas.get(i).isAccepted()) {
                    //Enviamos el email para notificar de el rechazo de las ofertas
                    Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.OfertaRechazada, ofertas.get(i));
                    try {
                        email.send();
                    } catch (MessagingException ex) {
                        return "error";
                    }
                    ofertas.get(i).delete();
                }
            }
            return "ok";
        }

        return "error";

    }
}
