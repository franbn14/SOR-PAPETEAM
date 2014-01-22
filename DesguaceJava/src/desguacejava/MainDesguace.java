/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import CEN.OfferCEN;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Message;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
/**
 *
 * @author Gustavo
 */
public class MainDesguace extends JFrame {
	private JTable tPeticiones;
	private JTextField tfOp1,tfOp2;
        private JTable tOfertas;
        
    public MainDesguace(String cif) 
    {    
        super("Pantalla Principal del Desguace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        JSplitPane spPrincipal = new JSplitPane();
        getContentPane().add(spPrincipal, BorderLayout.CENTER);
        JPanel panelIzq = new JPanel();
        JPanel panelDer = new JPanel();
        panelDer.setMinimumSize(new Dimension(260,10));
        panelIzq.setMinimumSize(new Dimension(230, 10));
        spPrincipal.setLeftComponent(panelIzq);
        spPrincipal.setRightComponent(panelDer);
        panelDer.setLayout(new BorderLayout(0, 0));
        
        JSplitPane spTablas = new JSplitPane();
        spTablas.setDividerSize(10);
        spTablas.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panelDer.add(spTablas, BorderLayout.CENTER);
        
        SpringLayout sl_panelIzq = new SpringLayout();
        panelIzq.setLayout(sl_panelIzq);
        
        tPeticiones = new JTable(5,1);
        JTableHeader th = (JTableHeader)tPeticiones.getTableHeader();
        tPeticiones.setAutoscrolls(false);
        tPeticiones.setBackground(new Color(250, 250, 210));
        tPeticiones.setForeground(new Color(100, 149, 237));
        tPeticiones.setShowVerticalLines(false);
        tPeticiones.setGridColor(Color.LIGHT_GRAY);
        tPeticiones.setCellSelectionEnabled(true);
        tPeticiones.setAutoCreateRowSorter(true);
        tPeticiones.setAutoCreateColumnsFromModel(false);
        JScrollPane srpPeticiones = new JScrollPane(tPeticiones);
        srpPeticiones.setMinimumSize(new Dimension(23, 220));
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setHeaderValue("Peticiones");
        JLabel lblOp1 = new JLabel("Opcion 1");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblOp1, 34, SpringLayout.NORTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblOp1, 20, SpringLayout.WEST, panelIzq);
        panelIzq.add(lblOp1);
        tfOp1 = new JTextField();
        tOfertas = new JTable(5,1);
        JTableHeader th2 = (JTableHeader)tOfertas.getTableHeader();
        tOfertas.setAutoscrolls(false);
        tOfertas.setBackground(new Color(250, 250, 210));
        tOfertas.setForeground(new Color(100, 149, 237));
        tOfertas.setShowVerticalLines(false);
        tOfertas.setGridColor(Color.LIGHT_GRAY);
        tOfertas.setCellSelectionEnabled(true);
        tOfertas.setAutoCreateRowSorter(true);
        tOfertas.setAutoCreateColumnsFromModel(false);  
        TableColumnModel tcm2 = th2.getColumnModel();
        TableColumn tc2 = tcm2.getColumn(0);
        tc2.setHeaderValue("Ofertas");
        
        JScrollPane srpOfertas = new JScrollPane(tOfertas);        
        srpOfertas.setMinimumSize(new Dimension(23, 220));
        tfOp2 = new JTextField();
        spTablas.setLeftComponent(srpPeticiones);
        spTablas.setRightComponent(srpOfertas);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfOp1, 6, SpringLayout.SOUTH, lblOp1);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfOp1, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfOp1, -21, SpringLayout.EAST, panelIzq);
        panelIzq.add(tfOp1);
        tfOp1.setColumns(10);
        
        JLabel lblOp2 = new JLabel("Opcion 2");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblOp2, 32, SpringLayout.SOUTH, tfOp1);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblOp2, 0, SpringLayout.WEST, lblOp1);
        panelIzq.add(lblOp2);
        
        
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfOp2, 18, SpringLayout.SOUTH, lblOp2);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfOp2, 0, SpringLayout.WEST, lblOp1);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfOp2, 0, SpringLayout.EAST, tfOp1);
        panelIzq.add(tfOp2);
        tfOp2.setColumns(10);
        
        Receiver r1 = new Receiver();
        Receiver r2 = new Receiver();
        r1.setTable(tOfertas);
        r2.setTable(tPeticiones);
        r1.setParams(cif+"p", "servidor", cif+"p", cif+"p");
        r1.open("localhost", "61616");
        r2.setParams("pendientes", "servidor", "pendientes", "pendientes");
        r2.open("localhost", "61616");
    }

    }
    
       
   class Receiver implements javax.jms.MessageListener
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
                TextMessage msg = (TextMessage)message;
                System.out.println(msg);
                String offers="";
                try {
                    offers = msg.getText();
                } catch (JMSException ex) {
                    Logger.getLogger(MainDesguace.class.getName()).log(Level.SEVERE, null, ex);
                }
                Gson gson = new Gson();
                java.lang.reflect.Type collectionType = new TypeToken<ArrayList<OfferCEN>>(){}.getType();
                ArrayList<OfferCEN> ofertas = gson.fromJson(offers, collectionType);
                DefaultTableModel tm = ((DefaultTableModel)innerTable.getModel());
                int i = 0;
                for(OfferCEN of : ofertas)
                {
                    tm.setValueAt(of.toString(), i, 0);
                    i++;
                }
       }
       
       public void setTable(JTable t)
       {
           this.innerTable = t;
       }
}
