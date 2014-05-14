/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import CEN.OfferCEN;
import CEN.RequestCEN;
import CEN.UserCEN;
import java.util.ArrayList;

/**
 *
 * @author esteve
 */

public class EmailFactoria {
    public static enum tipoEmail {OfertaAceptada, OfertaRechazada, Registro, CadAceptacion, CadNoOffertas, OfertasCad, EmailRecPass};
    
    public static Email getEmail(tipoEmail tipo, Object obj){
        Email email = null;
        switch (tipo){
            case OfertaAceptada: email = new EmailAceptarOferta((OfferCEN) obj); break;
            case OfertaRechazada: email = new EmailRechazarOferta((OfferCEN) obj); break;
            case Registro: email = new EmailRegistro((UserCEN) obj); break;
            case CadAceptacion: email = new EmailCadAceptacion((RequestCEN) obj); break;
            case CadNoOffertas: email = new EmailCadNoOffertas((RequestCEN) obj); break;
            case OfertasCad: email = new EmailOfertasCad((RequestCEN) obj); break;
            case EmailRecPass: 
                ArrayList args = (ArrayList) obj;
                email = new EmailRecPass((UserCEN)args.get(0), (String)args.get(1));
            break;
                
        }
        return email;
    }
}
