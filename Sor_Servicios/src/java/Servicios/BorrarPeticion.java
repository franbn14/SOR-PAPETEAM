/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;
import CEN.ClientCEN;
import CEN.OfferCEN;
import CEN.RequestCEN;
import java.util.ArrayList;
import java.util.Properties;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


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
        RequestCEN r=new RequestCEN();
        r=r.getByCode(id);
        //Obtengo las ofertas para avisar a los desguaces que se han rechazado sus ofertas.
        ArrayList<OfferCEN> ofertasR=OfferCEN.getByRequest(id);
        if(ofertasR !=null)
        {
            		
                        
            for(int i=0;i<ofertasR.size();i++)
            {
                
                    /*Codigo de enviar email*/
                        //Enviamos el email para notificar de el rechazo de las ofertas
                         try
                {
                    // Propiedades de la conexión
                    Properties props = new Properties();
                    props.setProperty("mail.smtp.host",  "smtp.gmail.com");
                    props.setProperty("mail.smtp.starttls.enable", "true");
                    props.setProperty("mail.smtp.port", "587");
                    props.setProperty("mail.smtp.auth", "true");

                    // Preparamos la sesion
                    Session session = Session.getDefaultInstance(props);

                    //Recoger los datos
                    String str_De = "sorteampape@gmail.com";
                    String str_PwRemitente 	= "NF8VGUD5";
                    
                    String str_Para 	= ofertasR.get(i).getScrapyard().getEmail();
                    String str_Asunto = "Rechazo de oferta";
                    String str_Mensaje = "Lo sentimos su oferta cuya referencia es "+ofertasR.get(i).getCode()+"\n Descripción:"+ofertasR.get(i).getType()+"\n Sobre peticion con: \n Descripción: "+r.getType()+" \nCliente: "+ r.getClient().getName()+" "+ClientCEN.getByID(r.getClient().getId()).getSurname()+"\n Ha sido rechazada";
                    //Obtenemos los destinatarios
                    String destinos[] = str_Para.split(",");

                    // Construimos el mensaje
                    MimeMessage message = new MimeMessage(session);

                    message.setFrom(new InternetAddress( str_De ));


                    Address [] receptores = new Address []{
                        new InternetAddress ( str_De )
                    };
                    //Forma 3

                    int j = 0;
                    while(j<destinos.length){					
                            receptores[j] = new InternetAddress ( destinos[j] ) ;					
                            j++;				
                    }


                    //receptores.
                    message.addRecipients(Message.RecipientType.TO, receptores);        
                    message.setSubject( str_Asunto );        
                    message.setText( str_Mensaje );

                    // Lo enviamos.
                    Transport t = session.getTransport("smtp");
                    t.connect(str_De, str_PwRemitente);
                    t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

                    // Cierre de la conexion.
                    t.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();

                    }

            }
        
        }
        r.delete();
    }
}
