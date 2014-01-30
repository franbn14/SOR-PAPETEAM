/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

import CEN.ClientCEN;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import CEN.OfferCEN;
import CEN.RequestCEN;
import CEN.UserCEN;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author esteve
 */
public class Email {
    protected String remitente = "sorteampape2stopbloqueos@gmail.com";//"sorteampape@gmail.com";
    protected String remitentePass = "NF8VGUD5";
    protected ArrayList<String> destinatarios = new ArrayList<>(); 
    protected Properties propiedades; // Propiedades de la conexión
    protected String asunto;
    protected String mensaje;
    
    public Email(){
        propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
    }
    
    public void send() throws AddressException, MessagingException{
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(propiedades);

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));


        Address[] receptores = new Address[]{
            new InternetAddress(remitente)
        };

        for(int i = 0; i < destinatarios.size(); i++){
            receptores[i] = new InternetAddress(destinatarios.get(i));
        }


        //receptores.
        message.addRecipients(Message.RecipientType.TO, receptores);
        message.setSubject(asunto);
        message.setText(mensaje);

        // Lo enviamos.
        Transport t = session.getTransport("smtp");
        t.connect(remitente, remitentePass);
        t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

        // Cierre de la conexion.
        t.close();
    }
}

class EmailAceptarOferta extends Email {
    public EmailAceptarOferta(OfferCEN offer){
        super();
        
        String surname = "";
        if(offer.getRequest().getClient().getClass() == ClientCEN.class){
            ClientCEN client = (ClientCEN) offer.getRequest().getClient();
            surname = (client.getSurname() != null)? client.getSurname() : "";
        }
        
        asunto =    "Aceptación oferta - Venta con éxito"; 
        
        mensaje =   "\n\nEnhorabuena su oferta cuya referencia es " + offer.getCode() + "\n" +
                    "\tPieza:" + offer.getRequest().getType() + "\n" +
                    "\tObservaciones: " + ((offer.getType() != null)? offer.getType() : "") +"\n" +
                    "\tPrecio: " + offer.getPrice() + "€\n" +
                    "\tCantidad: " + offer.getAmount() + " unidades\n" + 
                    "\nSobre peticion con:\n" +
                    "\tCliente: " + offer.getRequest().getClient().getName() + " " + surname + "\n" +
                    "\nHa sido aceptada.\n" +
                    "\tCIF/NIF: " + offer.getRequest().getClient().getId() + "\n" +
                    "\nPor favor envie la mercancía de la oferta al cliente con:\n" +
                    "\tDireccion: " + offer.getRequest().getClient().getAddress();
        
        destinatarios.add(offer.getScrapyard().getEmail());
    }
}

class EmailRechazarOferta extends Email {
    public EmailRechazarOferta(OfferCEN offer) {
        super();       
        String surname = "";
        if(offer.getRequest().getClient().getClass() == ClientCEN.class){
            ClientCEN client = (ClientCEN) offer.getRequest().getClient();
            surname = (client.getSurname() != null)? client.getSurname() : "";
        }
        
        asunto =    "Rechazo de oferta"; 
        
        mensaje =   "\n\nLo sentimos su oferta cuya referencia es " + offer.getCode() + "\n" +
                    "\tPieza:" + offer.getRequest().getType() + "\n" +
                    "\tObservaciones: " + ((offer.getType() != null)? offer.getType() : "") +"\n" +
                    "\tPrecio: " + offer.getPrice() + "€\n" +
                    "\tCantidad: " + offer.getAmount() + " unidades\n" + 
                    "\nSobre peticion con:\n" +
                    "\tCliente: " + offer.getRequest().getClient().getName() + " " + surname + "\n" +
                    "\tCIF/NIF: " + offer.getRequest().getClient().getId() + "\n" +
                    "\nHa sido rechazada.\n";
        
        destinatarios.add(offer.getScrapyard().getEmail());
    }
}


class EmailRegistro extends Email {
    public EmailRegistro(UserCEN user) {
        super();       
     
        asunto =    "Registro Completado"; 
        
        mensaje =   "\nEnhorabuena, su registro se ha completado.";
        
        destinatarios.add(user.getEmail());
    }
}

class EmailCadAceptacion extends Email {
    public EmailCadAceptacion(RequestCEN request) {
        super();       
     
        asunto =    "Aceptdado automatico"; 
        
        mensaje =   "La peticion: \n" + 
                    "\tPieza: " + request.getType() + "\n"+
                    "\tTamaño: " + ((request.getSize() != null)? request.getSize() + " " + request.getSizeUnitString() : "") + "\n" +
                    "\tColor: " + ((request.getColor() != null)? request.getColor() : "") + "\n" +
                    "\tCantidad: " + request.getAmount() + "\n" +
                    "\tPrecio máximo: " + ((request.getMaxPrice() != null)? request.getMaxPrice() : "") + "€\n"+
                    "\nHa caducado. \n" +
                    "\nNuestro sistema ha elegido productos por usted, podrá comprobar los productos selecionados en el historial de nuestra web/aplicación.\n";
        
        destinatarios.add(request.getClient().getEmail());
    }
}

class EmailCadNoOffertas extends Email {
    public EmailCadNoOffertas(RequestCEN request) {
        super();       
     
        asunto =    "Autoseleción sin resultados"; 
        
        mensaje =   "La peticion: \n" + 
                    "\tPieza: " + request.getType() + "\n"+
                    "\tTamaño: " + ((request.getSize() != null)? request.getSize() + " " + request.getSizeUnitString() : "") + "\n" +
                    "\tColor: " + ((request.getColor() != null)? request.getColor() : "") + "\n" +
                    "\tCantidad: " + request.getAmount() + "\n" +
                    "\tPrecio máximo: " + ((request.getMaxPrice() != null)? request.getMaxPrice() : "") + "€\n"+
                    "\nHa caducado. \n" + 
                    "\nSentimos informale que no se han podido selecionar ninguan oferta.\n";
        
        destinatarios.add(request.getClient().getEmail());
    }
}

class EmailOfertasCad extends Email {
    public EmailOfertasCad(RequestCEN request) {
        super();       
     
        asunto =    "Ofertas caducadas"; 
        
        mensaje =   "La peticion: \n" + 
                    "\tPieza: " + request.getType() + "\n"+
                    "\tTamaño: " + ((request.getSize() != null)? request.getSize() + " " + request.getSizeUnitString() : "") + "\n" +
                    "\tColor: " + ((request.getColor() != null)? request.getColor() : "") + "\n" +
                    "\tCantidad: " + request.getAmount() + "\n" +
                    "\tPrecio máximo: " + ((request.getMaxPrice() != null)? request.getMaxPrice() : "") + "€\n"+
                    "\nHa caducado. \n";
        
        destinatarios.add(request.getClient().getEmail());
    }
}
