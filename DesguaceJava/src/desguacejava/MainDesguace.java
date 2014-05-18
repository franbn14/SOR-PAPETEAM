/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import CEN.OfferCEN;
import CEN.RequestCEN;
import ScrapYardLogger.SYLogger;
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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import security.AES;
import servicios.DarUnidadId;
    
       
/**
 *
 * @author Gustavo
 */
public class MainDesguace extends JFrame {
	private JTable tPeticiones;
	private JTextField tfDescripcion;
        private JTable tOfertas;
        private JTable tOfertasAceptadas;
        private JTextField tfCantidad;
        private JTextField tfPrecio;
        private int rowSelected = -1;
        private JLabel lblSze;
        private JLabel lblerrAmt;
        private JLabel lblerrPrecio;
        private JLabel lblPieza;
        private JTextField tfColor;
        SYLogger logger;
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
        
        //logger = new SYLogger();
        JSplitPane spTablas = new JSplitPane();
        spTablas.setDividerSize(10);
        spTablas.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panelDer.add(spTablas, BorderLayout.CENTER);
        
        SpringLayout sl_panelIzq = new SpringLayout();
        panelIzq.setLayout(sl_panelIzq);
        lblSze = new JLabel(" ");
        tPeticiones = new JTable(5,1);
        tPeticiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader th = (JTableHeader)tPeticiones.getTableHeader();
        tPeticiones.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent e) 
            {
                RequestCEN oferta = (RequestCEN) tPeticiones.getValueAt(tPeticiones.getSelectedRow(), 0);
                lblPieza.setText(oferta.getType());
                lblSze.setText(oferta.getSize().toString()+" "+darUnidadId(oferta.getSizeUnit()));
            }
        });
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
        
        JLabel lblCantidad = new JLabel("Cantidad");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblCantidad, 0, SpringLayout.WEST, lblDescripcion);
        panelIzq.add(lblCantidad);
        
        tfCantidad = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfCantidad, 6, SpringLayout.SOUTH, lblCantidad);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfCantidad, 0, SpringLayout.WEST, lblDescripcion);
        //sl_panelIzq.putConstraint(SpringLayout.SOUTH, tfCantidad, -182, SpringLayout.SOUTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfCantidad, 0, SpringLayout.EAST, tfDescripcion);
        panelIzq.add(tfCantidad);
        tfCantidad.setColumns(10);
        
        JLabel lblPrecio = new JLabel("Precio");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblPrecio, 0, SpringLayout.WEST, lblDescripcion);
        panelIzq.add(lblPrecio);
        
        tfPrecio = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfPrecio, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfPrecio, -76, SpringLayout.EAST, panelIzq);
        panelIzq.add(tfPrecio);
        tfPrecio.setColumns(10);
        
        JLabel lblEuro = new JLabel("\u20AC");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblEuro, 6, SpringLayout.EAST, tfPrecio);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfPrecio, -6, SpringLayout.NORTH, lblEuro);
        panelIzq.add(lblEuro);
        
        JButton btnHacerOferta = new JButton("Hacer Oferta");
        sl_panelIzq.putConstraint(SpringLayout.WEST, btnHacerOferta, 0, SpringLayout.WEST, lblDescripcion);
        btnHacerOferta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                rowSelected = tPeticiones.getSelectedRow();
                boolean enviada = false,enviar = true;
                if(rowSelected != -1)
                {
                    RequestCEN oferta = (RequestCEN) tPeticiones.getValueAt(rowSelected, 0);
                    int cifid = getIdDes(cif);
                    lblPieza.setText(oferta.getType());
                    String nueva = "";
                    if(!tfPrecio.getText().equals("") && cifid != -1)
                    {
                       nueva += tfDescripcion.getText()+",";
                        Double amt = checkNumber(tfCantidad.getText());
                        Double prz = checkNumber(tfPrecio.getText());
                       
                        nueva += ((oferta.getSize()!=null)?oferta.getSize():"")+","+oferta.getSizeUnit()+",";
                       if(amt != null && amt == -1)
                       {
                           lblerrAmt.setText("La cantidad debe ser un numero");
                           enviar = false;
                       }
                       else
                           nueva += tfColor.getText()+","+((amt!=null)?amt.intValue():"")+",";
                       
                       if(prz != null && prz == -1)
                       {
                           lblerrPrecio.setText("El precio debe ser un numero");
                           enviar = false;
                       }
                       else
                            nueva += prz+","+cifid+","+oferta.getCode();
                       
                       if(enviar)
                       {
                            Sender envio = new Sender();
                            //logger.setLogMessage(2, cif, nueva);
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
                        if(cifid == -1)
                            lblerrPrecio.setText("Error en la conexion. Intentelo mas tarde.");
                        else
                            lblerrPrecio.setText("El precio no puede estar vacio.");
                    }
                    
                    if(enviada)
                    {
                        lblPieza.setText(" ");
                        lblerrAmt.setText(" ");
                        lblerrPrecio.setText(" ");
                        lblSze.setText(" ");
                        tfDescripcion.setText("");
                        tfCantidad.setText("");
                        tfColor.setText("");
                        tfPrecio.setText("");
                    }
                }
            }
        });
        panelIzq.add(btnHacerOferta);
        String ud = darTodasUnidades();
        Gson gson = new Gson();
        java.lang.reflect.Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> uds = gson.fromJson(ud, collectionType);
        
        JLabel lblConcepto = new JLabel("Concepto:");
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblConcepto, 24, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblConcepto, 17, SpringLayout.SOUTH, lblNewOffer);
        panelIzq.add(lblConcepto);
        
        lblPieza = new JLabel(" ");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblPieza, 0, SpringLayout.NORTH, lblConcepto);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblPieza, 18, SpringLayout.EAST, lblConcepto);
        panelIzq.add(lblPieza);
        
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblColor, 15, SpringLayout.SOUTH, lblSze);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblSze, 6, SpringLayout.SOUTH, lblSize);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblSze, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, lblSze, 0, SpringLayout.EAST, tfDescripcion);
        lblSze.setForeground(Color.BLACK);
        panelIzq.add(lblSze);
        
        lblerrAmt = new JLabel(" ");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblerrAmt, 12, SpringLayout.SOUTH, tfCantidad);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblEuro, 34, SpringLayout.SOUTH, lblerrAmt);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblPrecio, 6, SpringLayout.SOUTH, lblerrAmt);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblerrAmt, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, lblerrAmt, 0, SpringLayout.EAST, tfDescripcion);
        lblerrAmt.setForeground(Color.RED);
        panelIzq.add(lblerrAmt);
        
        lblerrPrecio = new JLabel(" ");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblerrPrecio, 6, SpringLayout.SOUTH, tfPrecio);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, btnHacerOferta, 6, SpringLayout.SOUTH, lblerrPrecio);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblerrPrecio, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, lblerrPrecio, 0, SpringLayout.EAST, tfDescripcion);
        lblerrPrecio.setForeground(Color.RED);
        panelIzq.add(lblerrPrecio);
        
        tfColor = new JTextField();
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblCantidad, 6, SpringLayout.SOUTH, tfColor);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfColor, 6, SpringLayout.SOUTH, lblColor);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfColor, 0, SpringLayout.WEST, lblDescripcion);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfColor, 0, SpringLayout.EAST, tfDescripcion);
        tfColor.setColumns(10);
        panelIzq.add(tfColor);
                
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
        try {
            servicios.DarIdDesguacebyCif_Service service = new servicios.DarIdDesguacebyCif_Service(new URL(ServiceHandler.getURL("DarIdDesguacebyCif")));
            servicios.DarIdDesguacebyCif port = service.getDarIdDesguacebyCifPort();
            Comunication com = Comunication.getInstance();
            nif = AES.encrypt(nif, com.getAesKey());
            return port.getIdDes(com.getID(),nif);
        } catch (Exception ex) {
            return -1;
        }
    }

    private static String darTodasUnidades() {
        try {
            servicios.DarUnidades_Service service = new servicios.DarUnidades_Service(new URL(ServiceHandler.getURL("DarUnidades")));
            servicios.DarUnidades port = service.getDarUnidadesPort();
            return port.darTodasUnidades();
        } catch (MalformedURLException ex) {
            //Logger.getLogger(MainDesguace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    private static String darUnidadId(int id) {
        try {
            servicios.DarUnidades_Service service = new servicios.DarUnidades_Service(new URL(ServiceHandler.getURL("DarUnidades")));
            servicios.DarUnidades port = service.getDarUnidadesPort();
            return port.darUnidadId(id);
        } catch (MalformedURLException ex) {
            //Logger.getLogger(MainDesguace.class.getName()).log(Level.SEVERE, null, ex);
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
                String offers="";
                String prop = "";
                try {
                    offers = msg.getText();
                    prop = msg.getStringProperty("channel");
                } catch (JMSException ex) {
                    Logger.getLogger(MainDesguace.class.getName()).log(Level.SEVERE, null, ex);
                }
                Gson gson = new Gson();
                java.lang.reflect.Type collectionType = new TypeToken<ArrayList<OfferCEN>>(){}.getType();
                java.lang.reflect.Type collectionType2 = new TypeToken<ArrayList<RequestCEN>>(){}.getType();
                ArrayList<RequestCEN> peticiones = null;
                ArrayList<OfferCEN> ofertas = null;
                
                if(prop.equals("pendientes"))
                {
                    peticiones = gson.fromJson(offers, collectionType2);
                    if(peticiones != null)
                    {
                        DefaultTableModel tm = ((DefaultTableModel)innerTable.getModel());
                        tm.setNumRows(peticiones.size());
                        int i = 0;
                        for(RequestCEN of : peticiones)
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
                else
                {
                    ofertas = gson.fromJson(offers, collectionType);  
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
            System.out.println(msg);
            publisher.publish(msg,javax.jms.DeliveryMode.PERSISTENT, javax.jms.ObjectMessage.DEFAULT_PRIORITY,timespan);
        } catch (JMSException ex) {
            Logger.getLogger(Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
