/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import CEN.OfferCEN;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xpath.internal.axes.OneStepIterator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
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

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
    
       
/**
 *
 * @author Gustavo
 */
public class MainDesguace extends JFrame {
	private JTable tPeticiones;
	private JTextField tfPieza;
        private JTable tOfertas;
        private JTable tOfertasAceptadas;
        private JTextField tfColor;
        private JTextField tfCantidad;
        private JTextField tfPrecio;
        private int rowSelected = -1;
        private JTextField tfSize;
        private JComboBox cbUnidades;
    public MainDesguace(final String cif) 
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
        tPeticiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        srpPeticiones.setMinimumSize(new Dimension(23, 150));
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setHeaderValue("Peticiones");
        JLabel lblPieza = new JLabel("Pieza");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblPieza, 20, SpringLayout.WEST, panelIzq);
        panelIzq.add(lblPieza);
        tfPieza = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfPieza, 6, SpringLayout.SOUTH, lblPieza);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfPieza, 0, SpringLayout.WEST, lblPieza);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfPieza, -21, SpringLayout.EAST, panelIzq);
        tOfertas = new JTable(5,1);
        tOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        tc2.setHeaderValue("Ofertas Pendientes");
        
        tOfertasAceptadas = new JTable(5,1);
        tOfertasAceptadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader th3 = (JTableHeader)tOfertasAceptadas.getTableHeader();
        tOfertasAceptadas.setAutoscrolls(false);
        tOfertasAceptadas.setBackground(new Color(250, 250, 210));
        tOfertasAceptadas.setForeground(new Color(100, 149, 237));
        tOfertasAceptadas.setShowVerticalLines(false);
        tOfertasAceptadas.setGridColor(Color.LIGHT_GRAY);
        tOfertasAceptadas.setCellSelectionEnabled(true);
        tOfertasAceptadas.setAutoCreateRowSorter(true);
        tOfertasAceptadas.setAutoCreateColumnsFromModel(false);  
        TableColumnModel tcm3 = th3.getColumnModel();
        TableColumn tc3 = tcm3.getColumn(0);
        tc3.setHeaderValue("Ofertas Aceptadas");
        
        JScrollPane srpOfertas = new JScrollPane(tOfertas);        
        srpOfertas.setMinimumSize(new Dimension(23, 180));
        JScrollPane srpOfertasAceptadas = new JScrollPane(tOfertasAceptadas);
        srpOfertasAceptadas.setMinimumSize(new Dimension(23, 150));
        
        spTablas.setLeftComponent(srpPeticiones);
        JSplitPane spOfertas = new JSplitPane();
        spOfertas.setOrientation(JSplitPane.VERTICAL_SPLIT);
        spOfertas.setLeftComponent(srpOfertas);
        spOfertas.setRightComponent(srpOfertasAceptadas);
        spTablas.setRightComponent(spOfertas);
        panelIzq.add(tfPieza);
        tfPieza.setColumns(10);
        
        JLabel lblSize = new JLabel("Tama\u00F1o");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblSize, 17, SpringLayout.SOUTH, tfPieza);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblSize, 0, SpringLayout.WEST, lblPieza);
        panelIzq.add(lblSize);
        
        JLabel lblNewOffer = new JLabel("Nueva Oferta");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblNewOffer, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblPieza, 18, SpringLayout.SOUTH, lblNewOffer);
        lblNewOffer.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblNewOffer, 23, SpringLayout.NORTH, panelIzq);
        panelIzq.add(lblNewOffer);
        
        JLabel lblColor = new JLabel("Color");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblColor, 50, SpringLayout.SOUTH, lblSize);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblColor, 0, SpringLayout.WEST, lblPieza);
        panelIzq.add(lblColor);
        
        tfColor = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfColor, 6, SpringLayout.SOUTH, lblColor);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfColor, 0, SpringLayout.WEST, lblPieza);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfColor, 34, SpringLayout.SOUTH, lblColor);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfColor, 0, SpringLayout.EAST, tfPieza);
        panelIzq.add(tfColor);
        tfColor.setColumns(10);
        
        JLabel lblCantidad = new JLabel("Cantidad");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblCantidad, 16, SpringLayout.SOUTH, tfColor);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblCantidad, 0, SpringLayout.WEST, lblPieza);
        panelIzq.add(lblCantidad);
        
        tfCantidad = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfCantidad, 6, SpringLayout.SOUTH, lblCantidad);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfCantidad, 0, SpringLayout.WEST, lblPieza);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfCantidad, 34, SpringLayout.SOUTH, lblCantidad);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfCantidad, 0, SpringLayout.EAST, tfPieza);
        panelIzq.add(tfCantidad);
        tfCantidad.setColumns(10);
        
        JLabel lblPrecio = new JLabel("Precio");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblPrecio, 18, SpringLayout.SOUTH, tfCantidad);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblPrecio, 0, SpringLayout.WEST, lblPieza);
        panelIzq.add(lblPrecio);
        
        tfPrecio = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfPrecio, 6, SpringLayout.SOUTH, lblPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfPrecio, 0, SpringLayout.WEST, lblPieza);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfPrecio, 34, SpringLayout.SOUTH, lblPrecio);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfPrecio, -55, SpringLayout.EAST, tfPieza);
        panelIzq.add(tfPrecio);
        tfPrecio.setColumns(10);
        
        JLabel lblEuro = new JLabel("\u20AC");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblEuro, 6, SpringLayout.NORTH, tfPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblEuro, 6, SpringLayout.EAST, tfPrecio);
        panelIzq.add(lblEuro);
        
        JButton btnHacerOferta = new JButton("Hacer Oferta");
        btnHacerOferta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                rowSelected = tPeticiones.getSelectedRow();
                boolean enviada = false;
                if(rowSelected != -1)
                {
                    OfferCEN oferta = (OfferCEN) tPeticiones.getValueAt(rowSelected, 0);
                    int cifid = getIdDes(cif);
                    String nueva = "";
                    if(!tfPieza.getText().equals(""))
                    {
                       nueva += tfPieza.getText()+",";
                        Double amt = checkNumber(tfCantidad.getText());
                        Double prz = checkNumber(tfPrecio.getText());
                       if(tfSize.getText().equals(""))
                       {
                           nueva += tfSize.getText()+",,";
                       }
                       else
                       {
                           Double sz = checkNumber(tfSize.getText());
                            nueva += ((sz!=-1 && sz!=null)?sz:"")+","+cbUnidades.getSelectedIndex()+",";
                       }
                       
                       nueva += tfColor.getText()+","+((amt!=-1 && amt!=null)?amt.intValue():"")+","+((prz!=-1 && prz!=null)?prz:"")+","+cifid+","+oferta.getCode();
                        Sender envio = new Sender();
                        envio.setParams("OfferDelivery", cif, "OfferDelivery", "OfferDelivery");
                        envio.open("192.168.43.56", "61616");
                        envio.send(nueva, 60000);
                        envio.close();
                        enviada = true;
                    }   
                }
            }
        });
        
        sl_panelIzq.putConstraint(SpringLayout.NORTH, btnHacerOferta, 25, SpringLayout.SOUTH, tfPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, btnHacerOferta, 0, SpringLayout.WEST, lblPieza);
        panelIzq.add(btnHacerOferta);
        
        tfSize = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfSize, 4, SpringLayout.SOUTH, lblSize);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfSize, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfSize, -108, SpringLayout.EAST, panelIzq);
        panelIzq.add(tfSize);
        tfSize.setColumns(10);
        
        cbUnidades = new JComboBox();
        String ud = darTodasUnidades();
        Gson gson = new Gson();
        java.lang.reflect.Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> uds = gson.fromJson(ud, collectionType);
        for(String u : uds)
        {
            cbUnidades.addItem(u);
        }
        cbUnidades.setSelectedIndex(0);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, cbUnidades, -27, SpringLayout.SOUTH, tfSize);
        sl_panelIzq.putConstraint(SpringLayout.WEST, cbUnidades, 6, SpringLayout.EAST, tfSize);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, cbUnidades, 1, SpringLayout.SOUTH, tfSize);
        sl_panelIzq.putConstraint(SpringLayout.EAST, cbUnidades, 0, SpringLayout.EAST, tfPieza);
        panelIzq.add(cbUnidades);
                
        Receiver r1 = new Receiver();
        Receiver r2 = new Receiver();
        Receiver r3 = new Receiver();
        r1.setTable(tOfertas);
        r2.setTable(tPeticiones);
        r3.setTable(tOfertasAceptadas);
        r1.setParams(cif+"p", "servidor", cif+"p", cif+"p");
        r1.open("192.168.43.56", "61616");
        r2.setParams("pendientes", "servidor", "pendientes", "pendientes");
        r2.open("192.168.43.56", "61616");
        r3.setParams(cif+"f", "servidor", cif+"f", cif+"f");
        r3.open("192.168.43.56", "61616");    
    }

    private static int getIdDes(java.lang.String nif) {
        servicios.DarIdDesguacebyCif_Service service = new servicios.DarIdDesguacebyCif_Service();
        servicios.DarIdDesguacebyCif port = service.getDarIdDesguacebyCifPort();
        return port.getIdDes(nif);
    }

    private static String darTodasUnidades() {
        servicios.DarUnidades_Service service = new servicios.DarUnidades_Service();
        servicios.DarUnidades port = service.getDarUnidadesPort();
        return port.darTodasUnidades();
    }
    
    public Double checkNumber(String number) {
        if(number!=null && !number.equals("")) {
            if(!number.matches("[0-9]+([.][0-9]+)?"))
                return -1.0;
            else
                return Double.parseDouble(number);
        }
        return null;
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
                if(ofertas != null)
                {
                    DefaultTableModel tm = ((DefaultTableModel)innerTable.getModel());
                    tm.setNumRows(ofertas.size());
                    int i = 0;
                    for(OfferCEN of : ofertas)
                    {
                        tm.setValueAt(of, i, 0);
                        i++;
                    }
                }
                else
                {
                    DefaultTableModel tm = ((DefaultTableModel)innerTable.getModel());
                    for(int i = 0; i < tm.getRowCount(); i++)
                    {
                        tm.setValueAt("", i, 0);
                    }
                }
       }
       
       public void setTable(JTable t)
       {
           this.innerTable = t;
       }
}

class Sender
{
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
	
	public void open(String host, String port) {
		this.open(this.destino, this.user,this.channel, this.durablename,host,port);
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
            publisher.publish(msg,javax.jms.DeliveryMode.PERSISTENT, javax.jms.ObjectMessage.DEFAULT_PRIORITY,timespan);
        } catch (JMSException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
