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

/**
 *
 * @author Gustavo
 */
public class SorAMQ implements javax.jms.MessageListener{
	Context context = null;
	TopicConnectionFactory factory = null;
	TopicConnection connection = null;
	String factoryName = "ConnectionFactory";
	Topic topic = null;
	TopicSession session_publisher = null;
	TopicSession session_subscriber = null;
	TopicSubscriber subscriber = null;
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
            connection.setClientID(user+"/"+destino+"/"+durablename);
            // create the sessions
            session_publisher = connection.createTopicSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            session_subscriber = connection.createTopicSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            // create the publisher
            publisher = session_publisher.createPublisher(topic);
            subscriber = session_subscriber.createDurableSubscriber(topic, durablename);
            // set message listener
            subscriber.setMessageListener(this);
            // start the connection, to enable message receipt
            connection.start();
        }
        catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        }
	}
	
	public void close() {
		try { 
			    subscriber.close( ); 
			    publisher.close();
			    session_subscriber.unsubscribe(durablename);
				connection.close( );
			    this.factory = null;
			    this.connection = null;
			    this.destino = null;
			    this.session_subscriber = null;
			    this.session_publisher = null;
			    this.subscriber = null;
			    this.publisher = null;
	    }catch (javax.jms.JMSException jmse){
				jmse.printStackTrace( ); 
		}
	}
	
	public void send(Object obj, long timespan)
	{
		try {
			ObjectMessage msg = (ObjectMessage)session_publisher.createObjectMessage((Serializable) obj);
			msg.setStringProperty("channel", channel);
			msg.setStringProperty("user", user);
			publisher.publish(msg,javax.jms.DeliveryMode.PERSISTENT, javax.jms.ObjectMessage.DEFAULT_PRIORITY,timespan);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage msg = (ObjectMessage)message;
			System.out.println(msg.getObject());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
