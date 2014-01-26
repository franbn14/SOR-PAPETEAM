/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import CEN.OfferCEN;
import CEN.UserCEN;

/**
 *
 * @author esteve
 */

public class EmailFactoria {
    public static enum tipoEmail {OfertaAceptada, OfertaRechazada, Registro};
    
    public static Email getEmail(tipoEmail tipo, Object obj){
        Email email = null;
        switch (tipo){
            case OfertaAceptada: email = new EmailAceptarOferta((OfferCEN) obj); break;
            case OfertaRechazada: email = new EmailRechazarOferta((OfferCEN) obj); break;
            case Registro: email = new EmailRegistro((UserCEN) obj); break;
        }
        return email;
    }
}
