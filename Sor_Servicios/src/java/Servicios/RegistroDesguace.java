/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import CEN.ScrapYardCEN;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.Date;

//Para manejo de JavaMail
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 *
 * @author fran
 */
@WebService(serviceName = "RegistroDesguace")
public class RegistroDesguace {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro")
    public String Registro(@WebParam(name = "Cif") String Cif, @WebParam(name = "Nombre") String Nombre, @WebParam(name = "Password") String Password, @WebParam(name = "Direccion") String Direccion,@WebParam(name = "Email") String Email) {
        //TODO write your implementation code here:
        
        
        String error="";
         ScrapYardCEN scry=ScrapYardCEN.getByCIF(Cif);
         
        if(scry!=null)
            error="Error: CIF ya registrado";
        else
        {
               

    
            ScrapYardCEN scry2=new ScrapYardCEN(Nombre, Password, Direccion, Cif,Email);
            scry2.insert();
            
            /*Codigo de enviar email*/
                        //Enviamos el email para notificar de su registro
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
                    String str_Para 	= Email;
                    String str_Asunto = "Registro Completado";
                    String str_Mensaje = "Enhorabuena, tu registro se ha completado";
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
                         //FIn de codigo email
        
        }
        return error;
    }
}
