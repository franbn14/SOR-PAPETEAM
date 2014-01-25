/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.OfferCEN;
import CEN.RequestCEN;
import CEN.ClientCEN;
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
@WebService(serviceName = "AceptarOfertas")
public class AceptarOfertas {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "AceptarOfertasDe")
    public String AceptarOfertasDe(@WebParam(name = "idS") String idS) {
        //TODO write your implementation code here:
        
       if(idS!="" && idS!=null)
       {
            String ids[]=idS.split("\\s+");
            int j=0; 
            RequestCEN r=new RequestCEN();
             ClientCEN cli=new ClientCEN("", "", "", "", "", null, "");
            while(j<ids.length)
            {
                int id=Integer.parseInt(ids[j]);
                OfferCEN o=OfferCEN.getByCode(id);
                o.update(o.getType(),o.getSize(),o.getSizeUnit(),o.getColor(),o.getAmount(),o.getPrice(),o.getRequest(),o.getScrapyard(),true);
                r=o.getRequest();
               
                /**COdigo de avisar con email por compra aceptada*/
                
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
                    
                    String str_Para 	= o.getScrapyard().getEmail();
                    String str_Asunto = "Aceptación oferta - Compra con éxito";
                    cli=ClientCEN.getByID(r.getClient().getId());
                    String str_Mensaje = "Enhorabuena su oferta cuya referencia es "+o.getCode()+"\n Descripción:"+o.getType()+"\n Sobre peticion con: \n Descripción: "+r.getType()+" \nCliente: "+ r.getClient().getName()+" "+cli.getSurname()+"\n Ha sido aceptada \n Por favor envie la mercancía de la oferta al cliente con: \nCIF/NIF: "+cli.getNIF()+"\nDireccion: "+r.getClient().getAddress();
                    //Obtenemos los destinatarios
                    String destinos[] = str_Para.split(",");

                    // Construimos el mensaje
                    MimeMessage message = new MimeMessage(session);

                    message.setFrom(new InternetAddress( str_De ));


                    Address [] receptores = new Address []{
                        new InternetAddress ( str_De )
                    };
                    //Forma 3

                    int k = 0;
                    while(k<destinos.length){					
                            receptores[k] = new InternetAddress ( destinos[k] ) ;					
                            k++;				
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
                
                /*Fin***/

                 j++;

            }
            r=RequestCEN.getByCode(r.getCode());
        
           r.update(r.getdeadline(),r.getType(),r.getSize(), r.getSizeUnit(), r.getColor(), r.getAmount(), r.getMaxPrice(),cli,r.isAutoElect(), true, r.isExpired());
           //Ahora vamos hacer una pasada sobre el resto de ofertas de este petición para borrarlas y mandar email de rechazo
           ArrayList<OfferCEN> ofertas =OfferCEN.getByRequest(r.getCode());
           for(int l=0;l<ofertas.size();l++)
           {
               if(!ofertas.get(l).isAccepted())
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
                    
                    String str_Para 	= ofertas.get(l).getScrapyard().getEmail();
                    String str_Asunto = "Rechazo de oferta";
                    String str_Mensaje = "Lo sentimos su oferta cuya referencia es "+ofertas.get(l).getCode()+"\n Descripción:"+ofertas.get(l).getType()+"\n Sobre peticion con: \n Descripción: "+r.getType()+" \nCliente: "+ r.getClient().getName()+" "+ClientCEN.getByID(r.getClient().getId()).getSurname()+"\n Ha sido rechazada";
                    //Obtenemos los destinatarios
                    String destinos[] = str_Para.split(",");

                    // Construimos el mensaje
                    MimeMessage message = new MimeMessage(session);

                    message.setFrom(new InternetAddress( str_De ));


                    Address [] receptores = new Address []{
                        new InternetAddress ( str_De )
                    };
                    //Forma 3

                    int m = 0;
                    while(m<destinos.length){					
                            receptores[m] = new InternetAddress ( destinos[m] ) ;					
                            m++;				
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
               ofertas.get(l).delete();
               }
               
               
           }
             return "ok";
       }
        
       return  "error";
      
    }
}
