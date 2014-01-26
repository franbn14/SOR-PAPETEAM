/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soramq;

import AutoSelection.Autoselection;
import CEN.ClientCEN;
import CEN.OfferCEN;
import CEN.RequestCEN;
import CEN.ScrapYardCEN;
import Email.Email;
import Email.EmailFactoria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JTable;

/**
 *
 * @author Gustavo
 */
public class AMQFunctions extends Thread{
    static Gson gson;
    static AMQFunctions pr;
    public AMQFunctions()
    {
        gson = new GsonBuilder().setDateFormat("MMM dd, yyyy hh:mm:ss a").create();
    }
    
    public void enviarOfertasPendientesPorDesguace() throws InterruptedException
    {
        ArrayList<AMQPublisher> conexiones = new ArrayList<AMQPublisher>();
        while(true)
        {
            ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
            for(ScrapYardCEN desg : desguaces)
            {
                int ind = 0;
                boolean existe = false;
                for(int j = 0; j < conexiones.size(); j++)
                {
                    if(conexiones.get(j).destino.equals("dynamicTopics/"+desg.getCif()+"p"))
                    {
                        ind = j;
                        existe = true;
                        break;
                    } 
                }
                
                if(!existe)
                {
                    AMQPublisher nueva = new AMQPublisher();
                    nueva.setParams(desg.getCif()+"p", "servidor", desg.getCif()+"p", desg.getCif()+"p");
                    conexiones.add(nueva);
                    nueva.open();
                    ind = conexiones.size()-1;
                }
                
                AMQPublisher conexion = conexiones.get(ind);
                ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
                requests = OfferCEN.getByCIFDesPendientes(desg.getCif());
                String listaJSON = gson.toJson(requests);
                conexion.send(listaJSON, 60000);
            }
            Thread.sleep(10000);
        }
        
    }
    
    public void enviarOfertasAceptadasPorDesguace() throws InterruptedException
    {
        ArrayList<AMQPublisher> conexiones = new ArrayList<AMQPublisher>();
        while(true)
        {
            ArrayList<ScrapYardCEN> desguaces = ScrapYardCEN.getAllScrapYards();
            for(ScrapYardCEN desg : desguaces)
            {
                int ind = 0;
                boolean existe = false;
                for(int j = 0; j < conexiones.size(); j++)
                {
                    if(conexiones.get(j).destino.equals("dynamicTopics/"+desg.getCif()+"f"))
                    {
                        ind = j;
                        existe = true;
                        break;
                    } 
                }
                
                if(!existe)
                {
                    AMQPublisher nueva = new AMQPublisher();
                    nueva.setParams(desg.getCif()+"f", "servidor", desg.getCif()+"f", desg.getCif()+"f");
                    conexiones.add(nueva);
                    nueva.open();
                    ind = conexiones.size()-1;
                }
                
                AMQPublisher conexion = conexiones.get(ind);
                ArrayList<OfferCEN> requests= new ArrayList<OfferCEN> ();
                requests = OfferCEN.getAcceptedByDesNIF(desg.getCif());
                String listaJSON = gson.toJson(requests);
                conexion.send(listaJSON, 60000);
            }
            Thread.sleep(10000);
        }
    }
    
    public void enviarPeticionesPendientes() throws InterruptedException
    {
        AMQPublisher conexion = new AMQPublisher();
        conexion.setParams("pendientes", "servidor", "pendientes", "pendientes");
        conexion.open();
        
        while(true)
        {
            ArrayList<RequestCEN> requests= new ArrayList<RequestCEN> ();
            requests = RequestCEN.getAllOpened();
            String listaJSON = gson.toJson(requests);
            conexion.send(listaJSON, 60000);
            Thread.sleep(10000);
        }
    }
    
    public void start()
    {
        Thread t1 = new Thread(new enviarOfertasAceptadasPorDesguace());
        Thread t2 = new Thread(new enviarOfertasPendientesPorDesguace());
        Thread t3 = new Thread(new enviarPeticionesPendientes());
        Thread t4 = new Thread(new autoselection());
        t1.start();
        t2.start();
        t3.start();
        //t4.start();
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        new AMQFunctions().start();
        new AMQReciver().start();
    }
    
    class enviarOfertasAceptadasPorDesguace implements Runnable
    {
        public void run()
        {
            try {
                pr = new AMQFunctions();
                pr.enviarOfertasAceptadasPorDesguace();
            } catch (InterruptedException ex) {
                Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class enviarOfertasPendientesPorDesguace implements Runnable
    {
        public void run()
        {
            try {
                pr = new AMQFunctions();
                pr.enviarOfertasPendientesPorDesguace();
            } catch (InterruptedException ex) {
                Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class enviarPeticionesPendientes implements Runnable
    {
        public void run()
        {
            try {
                pr = new AMQFunctions();
                pr.enviarPeticionesPendientes();
            } catch (InterruptedException ex) {
                Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class autoselection implements Runnable
    {
        public void run() {
            while(true)
            {
                ArrayList<RequestCEN> requests = RequestCEN.getExpired();
                for(RequestCEN req : requests)
                {
                    if(req.isAutoElect())
                    {
                        ArrayList<OfferCEN> offers =  OfferCEN.getByRequest(req.getCode());
                        AutoSelection.Autoselection auto = new Autoselection(offers, req);
                        String ids = auto.getBest();
                        if(ids != null && !ids.equals(""))
                        {
                            AceptarOfertasDe(ids);
                            Email e = EmailFactoria.getEmail(EmailFactoria.tipoEmail.Registro, req.getClient());
                            e.send();
                        }
                        else
                        {
                            Email e = EmailFactoria.getEmail(EmailFactoria.tipoEmail.Registro, req.getClient());
                            e.send();
                        }
                    }
                    else
                    {
                        Email e = EmailFactoria.getEmail(EmailFactoria.tipoEmail.Registro, req.getClient());
                        e.send();
                    }
                }
                
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AMQFunctions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public String AceptarOfertasDe(String idS) {
        //TODO write your implementation code here:
        if (idS != "" && idS != null) {

            String ids[] = idS.split("\\s+");
            RequestCEN request = null;

            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);

                OfferCEN o = OfferCEN.getByCode(id);
                request = o.getRequest();
                o.update(o.getType(), o.getSize(), o.getSizeUnit(), o.getColor(), o.getAmount(), o.getPrice(), request, o.getScrapyard(), true);
                Email email = EmailFactoria.getEmail(EmailFactoria.tipoEmail.OfertaAceptada, o);
                try {
                    email.send();
                } catch (Exception ex) {
                    return "error";
                }
            }

            ArrayList<OfferCEN> ofertas = new ArrayList<OfferCEN>();
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
                    } catch (Exception ex) {
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
}

class AMQReciver extends Thread implements javax.jms.MessageListener
{
    Context context = null;
	TopicConnectionFactory factory = null;
	TopicConnection connection = null;
        Topic topic = null;
	String factoryName = "ConnectionFactory";
	TopicSession session_subscriber = null;
	TopicSubscriber subscriber = null;
        String destino;
	String user;
	String channel;
	String durablename;
        JTable innerTable;
        
           public void setParams(String destino, String user,String conv, String durname) {
            this.destino=destino;
            this.user=user;
            this.channel=conv;
            this.durablename=durname;
        }
    
    public void open(String host, String port) {
		this.open(this.destino, this.user,this.channel, this.durablename, host, port);
	}
                
        public void open(String dname, String user,String conv, String durname, String host, String port) {
            try {
                // create the JNDI initial context
                Properties env = new Properties( );
                // ActiveMQ
                env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
                env.put(Context.PROVIDER_URL, "tcp://"+host+":"+port);
                context = new InitialContext(env);

                this.destino = "dynamicTopics/"+dname;
                this.user = user;
                this.channel = conv;
                this.durablename = durname;

                // look up the ConnectionFactory
                factory = (TopicConnectionFactory)context.lookup(factoryName);
                // look up the Destination
                topic = (Topic) context.lookup(destino);
                // create the connection
                connection = factory.createTopicConnection();
                // setId
                connection.setClientID(durablename);
                connection.start();
                session_subscriber = connection.createTopicSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
                subscriber = session_subscriber.createDurableSubscriber(topic, durablename);
                subscriber.setMessageListener(this);
                
            }catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        }
        }
        
        public void close() {
		try { 
			    subscriber.close( ); 
			    session_subscriber.unsubscribe(durablename);
				connection.close( );
			    this.factory = null;
			    this.connection = null;
			    this.destino = null;
			    this.session_subscriber = null;
			    this.subscriber = null;
	    }catch (javax.jms.JMSException jmse){
				jmse.printStackTrace( ); 
		}
	}
        
       @Override
        public void onMessage(Message message) {
        try {
            String oferta = ((TextMessage)message).getText();
            String[] json = oferta.split(",");
            OfferCEN insert = new OfferCEN(json[0],
                    (!json[1].equals(""))?Double.parseDouble(json[1]):null,
                    (!json[2].equals(""))?Integer.parseInt(json[2]):null,
                    (!json[3].equals(""))?json[3]:null,
                    (!json[4].equals(""))?Integer.parseInt(json[4]):null,
                    (!json[5].equals(""))?Double.parseDouble(json[5]):null,
                    RequestCEN.getByCode(Integer.parseInt(json[7])),
                    ScrapYardCEN.getByID(Integer.parseInt(json[6])), false);
            insert.insert();
            
        } catch (JMSException ex) {
            Logger.getLogger(AMQReciver.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       
       public void start()
       {
           AMQReciver reciver = new AMQReciver();
           reciver.setParams("OfferDelivery", "server", "OfferDelivery", "OfferDelivery");
           reciver.open("localhost", "61616");
       }
}
