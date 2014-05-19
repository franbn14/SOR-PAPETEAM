/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package desguacejava;

//import ScrapYardLogger.SYLogger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.apache.commons.codec.binary.Hex;
import security.AES;



/**
 *
 * @author fran
 */
public class DesguaceJava extends JFrame implements ActionListener{

        JButton btn,regbtn;
	JTextField t1;
	JLabel img,lblError,lblErrRecPass;
	JPasswordField t2;
        DesguaceRegistro reg;
        static JFrame des;
        //SYLogger logger;
        static Comunication com;
	public DesguaceJava(String titulo)
	{
		super(titulo);
                com = Comunication.getInstance();
		setResizable(false);
		JPanel p1 = new JPanel();
		SpringLayout sl_p1 = new SpringLayout();
		p1.setLayout(sl_p1);
		JLabel label = new JLabel("CIF:");
		sl_p1.putConstraint(SpringLayout.NORTH, label, 11, SpringLayout.NORTH, p1);
		sl_p1.putConstraint(SpringLayout.WEST, label, 19, SpringLayout.WEST, p1);
		p1.add(label);
		p1.add(t1 = new JTextField(25));
		sl_p1.putConstraint(SpringLayout.NORTH, t1, -6, SpringLayout.NORTH, label);
		sl_p1.putConstraint(SpringLayout.WEST, t1, 44, SpringLayout.EAST, label);
		sl_p1.putConstraint(SpringLayout.EAST, t1, -45, SpringLayout.EAST, p1);

		JLabel label_1 = new JLabel("Password:");
		sl_p1.putConstraint(SpringLayout.NORTH, label_1, 31, SpringLayout.SOUTH, label);
		sl_p1.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, label);
		p1.add(label_1);
		p1.add(t2 = new JPasswordField(25));
		sl_p1.putConstraint(SpringLayout.EAST, t2, 269, SpringLayout.EAST, label_1);
		sl_p1.putConstraint(SpringLayout.NORTH, t2, -6, SpringLayout.NORTH, label_1);
		sl_p1.putConstraint(SpringLayout.WEST, t2, 5, SpringLayout.EAST, label_1);
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(btn = new JButton("Submit"));
                regbtn = new JButton("Registrar ahora!");
                regbtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reg.setVisible(true);
                        reg.setSize(440, 400);
                        reg.setResizable(true);
                    }
                });
                p2.add(regbtn);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(img = new JLabel());
		img.setIcon(new ImageIcon(getClass().getResource("Desguace.jpg")));
		
                Container c = getContentPane();
                c.add(BorderLayout.NORTH,p3);
                c.add(BorderLayout.CENTER,p1);
                
                lblError = new JLabel("");
                lblError.setVisible(false);
                lblError.setForeground(Color.RED);
                lblError.setHorizontalAlignment(SwingConstants.CENTER);
                sl_p1.putConstraint(SpringLayout.NORTH, lblError, 23, SpringLayout.SOUTH, t2);
                sl_p1.putConstraint(SpringLayout.WEST, lblError, 19, SpringLayout.WEST, p1);
                sl_p1.putConstraint(SpringLayout.EAST, lblError, 24, SpringLayout.EAST, t1);
                p1.add(lblError);
                JButton btnRecPass = new JButton("Recuperar Password");
                     btnRecPass.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(t1.getText().isEmpty())
                        {
                            lblErrRecPass.setVisible(true);
                            lblErrRecPass.setForeground(Color.red);
                            lblErrRecPass.setText("Introduce tu cif y haz click para reestablecer");
                        }
                        else
                        {
                            String err2 = forgetPass(t1.getText());
                            if(err2.isEmpty())
                            {
                                lblErrRecPass.setForeground(Color.green);
                                lblErrRecPass.setText("Se le ha enviado un email");
                            }
                            else
                            {
                                lblErrRecPass.setForeground(Color.red);
                                lblErrRecPass.setText("Cif incorrecto");           
                            }
                        }
                    }
                });
                sl_p1.putConstraint(SpringLayout.NORTH, btnRecPass, 22, SpringLayout.SOUTH, lblError);
                sl_p1.putConstraint(SpringLayout.WEST, btnRecPass, 116, SpringLayout.WEST, p1);
                p1.add(btnRecPass);
                
                lblErrRecPass = new JLabel(" ");
                lblErrRecPass.setEnabled(false);
                sl_p1.putConstraint(SpringLayout.NORTH, lblErrRecPass, 16, SpringLayout.SOUTH, btnRecPass);
                sl_p1.putConstraint(SpringLayout.WEST, lblErrRecPass, 53, SpringLayout.WEST, p1);
                sl_p1.putConstraint(SpringLayout.EAST, lblErrRecPass, -35, SpringLayout.EAST, p1);
                p1.add(lblErrRecPass);
                c.add(BorderLayout.SOUTH,p2);
                reg = new DesguaceRegistro();
                reg.setVisible(false);
                btn.addActionListener(this);
                //logger = new SYLogger();
	}
	
	public static void main(String args[])
	{
		des = new DesguaceJava("Desguace-Java");
		des.setVisible(true);
		des.setSize(400, 500);
		des.setDefaultCloseOperation(EXIT_ON_CLOSE);
		des.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nameButton = e.getActionCommand();
		if(e.getSource() instanceof JButton)
		{
			if("Submit".equals(nameButton))
			{
                        try {
                            String user = t1.getText();
                            
                            MessageDigest md;
                            md = MessageDigest.getInstance("SHA-512");
                            md.update(t2.getText().getBytes());
                            byte[] mb = md.digest();
                            String pass = String.copyValueOf(Hex.encodeHex(mb));
                            String error=loginDes(pass, user);
                            if(error.equals(""))
                            {
                                MainDesguace mdes;
                                //logger.setLogMessage(1, user, "");
                                mdes = new MainDesguace(t1.getText());
                                mdes.addWindowListener(new WindowAdapter() {
                                    public void windowClosing(WindowEvent e)
                                    {
                                        int exitApp= JOptionPane.showConfirmDialog(null, "Realmente quieres dejar la aplicacion?", "SI/NO", JOptionPane.YES_NO_OPTION);
                                        if(exitApp == JOptionPane.YES_OPTION)
                                        {
                                            Comunication com = Comunication.getInstance();
                                            com.Finish();
                                            System.exit(0);
                                        }
                                    }
                                });
                                mdes.setVisible(false);
                                mdes.setVisible(true);
                                mdes.setSize(700,600);
                                des.setVisible(false);
                            }
                            else
                            {
                                    lblError.setText(error);
                                    //logger.setLogMessage(-1, user, error);
                                    lblError.setVisible(true);
                            }
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(DesguaceJava.class.getName()).log(Level.SEVERE, null, ex);
                        }
			}
		}
}


    private static String loginDes(java.lang.String password, java.lang.String cif) {
        try {
                servicios.LoginDesguace_Service service = new servicios.LoginDesguace_Service(new URL(ServiceHandler.getURL("LoginDesguace")));
                servicios.LoginDesguace port = service.getLoginDesguacePort();
                Comunication com = Comunication.getInstance();
                password = AES.encrypt(password, com.getAesKey());
                cif = AES.encrypt(cif, com.getAesKey());
                return AES.decrypt(port.loginDes(com.getID(),password, cif),com.getAesKey());

        } catch (Exception ex) {
            return "Error en la conexion. Intentelo de nuevo.";
        } 
    }

    private static String forgetPass(java.lang.String doc) {
        try {
            servicios.PassManager_Service service = new servicios.PassManager_Service(new URL(ServiceHandler.getURL("PassManager")));
            servicios.PassManager port = service.getPassManagerPort();
            return port.forgetPass(doc);
        } catch (MalformedURLException ex) {
            //Logger.getLogger(DesguaceJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
