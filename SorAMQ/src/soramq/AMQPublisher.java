/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soramq;

import CEN.RequestCEN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.swing.JTable;

/**
 *
 * @author Gustavo
 */
public class AMQPublisher{ 
	Context context = null;
	TopicConnectionFactory factory = null;
	TopicConnection connection = null;
	String factoryName = "ConnectionFactory";
	Topic topic = null;
	TopicSession session_publisher = null;
	TopicPublisher publisher = null;
	String destino;
	String user;
	String channel;
	String durablename;
        
	public void setParams(String destino, String user,String conv, String durname) {
            this.destino=destino;
            this.user=user;
            this.channel=conv;
            this.durablename=durname;
	}
	
	public void open() {
		this.open(this.destino, this.user,this.channel, this.durablename);
	}
	
	public void open(String dname, String user,String conv, String durname) {
        try {
            // create the JNDI initial context
            Properties env = new Properties( );
            // ActiveMQ
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
            
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
            connection.setClientID(user+"/"+durablename);
            // create the sessions
            session_publisher = connection.createTopicSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            // create the publisher
            publisher = session_publisher.createPublisher(topic);
        }
        catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        }
	}
	
	public void close() {
		try {  
			    publisher.close();
				connection.close( );
			    this.factory = null;
			    this.connection = null;
			    this.destino = null;
			    this.session_publisher = null;
			    this.publisher = null;
	    }catch (javax.jms.JMSException jmse){
				jmse.printStackTrace( ); 
		}
	}
	
	public void send(String obj, long timespan)
	{
		try {
			TextMessage msg = (TextMessage)session_publisher.createTextMessage(obj);
                        System.out.println(msg.getText());
			msg.setStringProperty("channel", channel);
			msg.setStringProperty("user", user);
			publisher.publish(msg,javax.jms.DeliveryMode.PERSISTENT, javax.jms.ObjectMessage.DEFAULT_PRIORITY,timespan);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
