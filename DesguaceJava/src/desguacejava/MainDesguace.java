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
	private JTextField tfDescripcion;
        private JTable tOfertas;
        private JTable tOfertasAceptadas;
        private JTextField tfColor;
        private JTextField tfCantidad;
        private JTextField tfPrecio;
        private int rowSelected = -1;
        private JTextField tfSize;
        private JComboBox cbUnidades;
        private JLabel lblerrSize;
        private JLabel lblerrAmt;
        private JLabel lblerrPrecio;
        private JLabel lblPieza;
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
        JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
        panelIzq.add(lblDescripcion);
        tfDescripcion = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfDescripcion, 110, SpringLayout.NORTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfDescripcion, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfDescripcion, -21, SpringLayout.EAST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblDescripcion, 0, SpringLayout.WEST, tfDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, lblDescripcion, -6, SpringLayout.NORTH, tfDescripcion);
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
        panelIzq.add(tfDescripcion);
        tfDescripcion.setColumns(10);
        
        JLabel lblSize = new JLabel("Tama\u00F1o");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblSize, 18, SpringLayout.SOUTH, tfDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblSize, 0, SpringLayout.WEST, lblDescripcion);
        panelIzq.add(lblSize);
        
        JLabel lblNewOffer = new JLabel("Nueva Oferta");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblNewOffer, 20, SpringLayout.WEST, panelIzq);
        lblNewOffer.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblNewOffer, 23, SpringLayout.NORTH, panelIzq);
        panelIzq.add(lblNewOffer);
        
        JLabel lblColor = new JLabel("Color");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblColor, 0, SpringLayout.WEST, lblDescripcion);
        panelIzq.add(lblColor);
        
        tfColor = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfColor, 6, SpringLayout.SOUTH, lblColor);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfColor, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfColor, -213, SpringLayout.SOUTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfColor, -21, SpringLayout.EAST, panelIzq);
        panelIzq.add(tfColor);
        tfColor.setColumns(10);
        
        JLabel lblCantidad = new JLabel("Cantidad");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblCantidad, 12, SpringLayout.SOUTH, tfColor);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblCantidad, 0, SpringLayout.WEST, lblDescripcion);
        panelIzq.add(lblCantidad);
        
        tfCantidad = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfCantidad, 6, SpringLayout.SOUTH, lblCantidad);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfCantidad, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfCantidad, -21, SpringLayout.EAST, panelIzq);
        panelIzq.add(tfCantidad);
        tfCantidad.setColumns(10);
        
        JLabel lblPrecio = new JLabel("Precio");
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, lblPrecio, -112, SpringLayout.SOUTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfCantidad, -23, SpringLayout.NORTH, lblPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblPrecio, 20, SpringLayout.WEST, panelIzq);
        panelIzq.add(lblPrecio);
        
        tfPrecio = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfPrecio, 6, SpringLayout.SOUTH, lblPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfPrecio, 20, SpringLayout.WEST, panelIzq);
        panelIzq.add(tfPrecio);
        tfPrecio.setColumns(10);
        
        JLabel lblEuro = new JLabel("\u20AC");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblEuro, 51, SpringLayout.SOUTH, tfCantidad);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblEuro, 160, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfPrecio, -6, SpringLayout.WEST, lblEuro);
        panelIzq.add(lblEuro);
        
        JButton btnHacerOferta = new JButton("Hacer Oferta");
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfPrecio, -28, SpringLayout.NORTH, btnHacerOferta);
        sl_panelIzq.putConstraint(SpringLayout.WEST, btnHacerOferta, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, btnHacerOferta, -21, SpringLayout.SOUTH, panelIzq);
        btnHacerOferta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                rowSelected = tPeticiones.getSelectedRow();
                boolean enviada = false,enviar = true;
                if(rowSelected != -1)
                {
                    OfferCEN oferta = (OfferCEN) tPeticiones.getValueAt(rowSelected, 0);
                    lblPieza.setText(oferta.getType());
                    int cifid = getIdDes(cif);
                    String nueva = "";
                    if(!tfPrecio.getText().equals(""))
                    {
                       nueva += tfDescripcion.getText()+",";
                        Double amt = checkNumber(tfCantidad.getText());
                        Double prz = checkNumber(tfPrecio.getText());
                       if(tfSize.getText().equals(""))
                       {
                           nueva += tfSize.getText()+",,";
                       }
                       else
                       {
                           Double sz = checkNumber(tfSize.getText());
                           if(sz == -1)
                           {
                               lblerrSize.setText("El tamaño debe ser un numero");
                               enviar = false;
                           }
                           else
                                nueva += ((sz!=null)?sz:"")+","+cbUnidades.getSelectedIndex()+",";
                       }
                       
                       if(amt == -1)
                       {
                           lblerrAmt.setText("La cantidad debe ser un numero");
                           enviar = false;
                       }
                       else
                           nueva += tfColor.getText()+","+((amt!=null)?amt.intValue():"")+",";
                       
                       if(prz == -1)
                       {
                           lblerrPrecio.setText("El precio debe ser un numero");
                           enviar = false;
                       }
                       else
                            nueva += prz+","+cifid+","+oferta.getCode();
                       
                       if(enviar)
                       {
                            Sender envio = new Sender();
                            envio.setParams("OfferDelivery", cif, "OfferDelivery", "OfferDelivery");
                            //envio.open("192.168.43.56", "61616");
                            envio.open("localhost", "61616");
                            envio.send(nueva, 60000);
                            envio.close();
                            enviada = true;
                       }
                    }
                    else
                    {
                        lblerrPrecio.setText("El precio no puede estar vacio.");
                    }
                    
                    if(enviada)
                    {
                        lblPieza.setText(" ");
                        lblerrAmt.setText(" ");
                        lblerrPrecio.setText(" ");
                        lblerrSize.setText(" ");
                        tfDescripcion.setText("");
                        tfCantidad.setText("");
                        tfColor.setText("");
                        tfPrecio.setText("");
                        tfSize.setText("");
                    }
                }
            }
        });
        panelIzq.add(btnHacerOferta);
        
        tfSize = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfSize, 6, SpringLayout.SOUTH, lblSize);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfSize, 20, SpringLayout.WEST, panelIzq);
        panelIzq.add(tfSize);
        tfSize.setColumns(10);
        
        cbUnidades = new JComboBox();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, cbUnidades, 41, SpringLayout.SOUTH, tfDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfSize, -6, SpringLayout.WEST, cbUnidades);
        sl_panelIzq.putConstraint(SpringLayout.WEST, cbUnidades, 128, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, cbUnidades, 0, SpringLayout.EAST, tfDescripcion);
        String ud = darTodasUnidades();
        Gson gson = new Gson();
        java.lang.reflect.Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> uds = gson.fromJson(ud, collectionType);
        for(String u : uds)
        {
            cbUnidades.addItem(u);
        }
        cbUnidades.setSelectedIndex(0);
        panelIzq.add(cbUnidades);
        
        JLabel lblConcepto = new JLabel("Concepto:");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblConcepto, 24, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblConcepto, 17, SpringLayout.SOUTH, lblNewOffer);
        panelIzq.add(lblConcepto);
        
        lblPieza = new JLabel(" ");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblPieza, 0, SpringLayout.NORTH, lblConcepto);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblPieza, 18, SpringLayout.EAST, lblConcepto);
        panelIzq.add(lblPieza);
        
        lblerrSize = new JLabel(" ");
        lblerrSize.setForeground(Color.RED);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblerrSize, 212, SpringLayout.NORTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, cbUnidades, -5, SpringLayout.NORTH, lblerrSize);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblColor, 6, SpringLayout.SOUTH, lblerrSize);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblerrSize, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, lblerrSize, 0, SpringLayout.EAST, tfDescripcion);
        panelIzq.add(lblerrSize);
        
        lblerrAmt = new JLabel(" ");
        lblerrAmt.setForeground(Color.RED);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblerrAmt, -22, SpringLayout.NORTH, lblPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblerrAmt, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.SOUTH, lblerrAmt, -6, SpringLayout.NORTH, lblPrecio);
        sl_panelIzq.putConstraint(SpringLayout.EAST, lblerrAmt, 0, SpringLayout.EAST, tfDescripcion);
        panelIzq.add(lblerrAmt);
        
        lblerrPrecio = new JLabel(" ");
        lblerrPrecio.setForeground(Color.RED);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblerrPrecio, 6, SpringLayout.SOUTH, tfPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblerrPrecio, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, lblerrPrecio, 0, SpringLayout.EAST, tfDescripcion);
        panelIzq.add(lblerrPrecio);
                
        Receiver r1 = new Receiver();
        Receiver r2 = new Receiver();
        Receiver r3 = new Receiver();
        r1.setTable(tOfertas);
        r2.setTable(tPeticiones);
        r3.setTable(tOfertasAceptadas);
        r1.setParams(cif+"p", "servidor", cif+"p", cif+"p");
        //r1.open("192.168.43.56", "61616");
        r1.open("localhost", "61616");
        r2.setParams("pendientes", "servidor", "pendientes", "pendientes");
        //r2.open("192.168.43.56", "61616");
        r2.open("localhost", "61616");
        r3.setParams(cif+"f", "servidor", cif+"f", cif+"f");
        //r3.open("192.168.43.56", "61616"); 
        r3.open("localhost", "61616");
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
