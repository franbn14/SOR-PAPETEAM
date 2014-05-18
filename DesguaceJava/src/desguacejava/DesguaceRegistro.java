/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import ScrapYardLogger.SYLogger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import security.AES;

/**
 *
 * @author Gustavo
 */
public class DesguaceRegistro extends JFrame{
    
    JTextField t1,t4,t5;
    JPasswordField t2,t3;
    JLabel lb1;
    JButton btn;
    private JTextField textField;
    String password;
    //SYLogger logger;
    public DesguaceRegistro()
    {
    	setResizable(false);        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        SpringLayout sl_p1 = new SpringLayout();
        p1.setLayout(sl_p1);
        JLabel label = new JLabel("Nombre:");
        sl_p1.putConstraint(SpringLayout.NORTH, label, 36, SpringLayout.NORTH, p1);
        sl_p1.putConstraint(SpringLayout.WEST, label, 9, SpringLayout.WEST, p1);
        p1.add(label);
	p1.add(t4 = new JTextField(25));
	sl_p1.putConstraint(SpringLayout.NORTH, t4, -6, SpringLayout.NORTH, label);
	sl_p1.putConstraint(SpringLayout.WEST, t4, 16, SpringLayout.EAST, label);
        JLabel label_1 = new JLabel("Direccion:");
        sl_p1.putConstraint(SpringLayout.NORTH, label_1, 29, SpringLayout.SOUTH, label);
        sl_p1.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, label);
        p1.add(label_1);
	p1.add(t5 = new JTextField(25));
	sl_p1.putConstraint(SpringLayout.NORTH, t5, -6, SpringLayout.NORTH, label_1);
	sl_p1.putConstraint(SpringLayout.EAST, t5, 0, SpringLayout.EAST, t4);
        JLabel label_2 = new JLabel("CIF:");
        sl_p1.putConstraint(SpringLayout.WEST, label_2, 10, SpringLayout.WEST, p1);
        p1.add(label_2);
        p1.add(t1 = new JTextField(25));
        sl_p1.putConstraint(SpringLayout.NORTH, t1, 12, SpringLayout.SOUTH, t5);
        sl_p1.putConstraint(SpringLayout.NORTH, label_2, 6, SpringLayout.NORTH, t1);
        sl_p1.putConstraint(SpringLayout.WEST, t1, 0, SpringLayout.WEST, t4);
        JLabel label_3 = new JLabel("Password:");
        sl_p1.putConstraint(SpringLayout.NORTH, label_3, 23, SpringLayout.SOUTH, label_2);
        sl_p1.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label);
        p1.add(label_3);
	p1.add(t2 = new JPasswordField(25));
	sl_p1.putConstraint(SpringLayout.NORTH, t2, -6, SpringLayout.NORTH, label_3);
	sl_p1.putConstraint(SpringLayout.WEST, t2, 7, SpringLayout.EAST, label_3);
        JLabel label_4 = new JLabel("Confirm Password:");
        sl_p1.putConstraint(SpringLayout.SOUTH, label_4, -78, SpringLayout.SOUTH, p1);
        sl_p1.putConstraint(SpringLayout.WEST, label_4, 10, SpringLayout.WEST, p1);
        p1.add(label_4);
        p1.add(t3 = new JPasswordField(25));
        sl_p1.putConstraint(SpringLayout.NORTH, t3, -6, SpringLayout.NORTH, label_4);
        sl_p1.putConstraint(SpringLayout.WEST, t3, 6, SpringLayout.EAST, label_4);
        sl_p1.putConstraint(SpringLayout.EAST, t3, -47, SpringLayout.EAST, p1);
        JLabel lblEmail = new JLabel("Email");
        sl_p1.putConstraint(SpringLayout.NORTH, lblEmail, 20, SpringLayout.SOUTH, label_4);
        sl_p1.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, label);
        p1.add(lblEmail);
        
        textField = new JTextField();
        sl_p1.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, t3);
        sl_p1.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, t4);
        sl_p1.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, t4);
        p1.add(textField);
        textField.setColumns(10);
        
        final JLabel err = new JLabel("");
        err.setHorizontalTextPosition(SwingConstants.CENTER);
        err.setHorizontalAlignment(SwingConstants.CENTER);
        err.setVisible(false);
        sl_p1.putConstraint(SpringLayout.WEST, err, 9, SpringLayout.WEST, p1);
        sl_p1.putConstraint(SpringLayout.SOUTH, err, -10, SpringLayout.SOUTH, p1);
        sl_p1.putConstraint(SpringLayout.EAST, err, 430, SpringLayout.WEST, p1);
        p1.add(err);
        //logger = new SYLogger();
        lb1 = new JLabel("Formulario de Registro");
        lb1.setFont(new Font("Tahoma",0,30));
        btn = new JButton("Registrar");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valido = true;
                String error = "";
                if(t1.getText().equals(""))
                {
                    valido = false;
                    error += "CIF vacio";
                }
                else
                {
                    String cif = t1.getText();
                    cif = cif.toUpperCase();
                    if(cif.length() != 9)
                        valido = false;
                    else
                    {
                        String letters = "ABCDEFGHKLMNPQS";
                        String letter = cif.substring(0,1);
                        String digits = cif.substring(1);
                        
                        if(letters.indexOf(letter) == -1)
                            valido = false;
                        else
                        {
                            if(!digits.matches("[0-9]{8}"))
                                valido = false;
                        }
                    }
                    
                    if(!valido)
                        error += "formato del CIF incorrecto";
                }
                
                if(t2.getText().equals("") && t3.getText().equals(""))
                {
                    valido = false;
                    if(error.equals(""))
                        error+="los Passwords no pueden ser vacios";
                    else
                        error+=", los Passwords no pueden ser vacios";
                }
                else if(!t2.getText().equals(t3.getText()))
                {
                    valido = false;
                    if(error.equals(""))
                        error+="los Passwords no coinciden";
                    else
                        error+=", los Passwords no coinciden";
                }
                else
                {
                    String pass = t2.getText();
                    MessageDigest md;
                    if(!pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"))
                    {
                       valido = false;
                       if(error.equals(""))
                            error+="formato incorrecto en Passwords";
                        else
                            error+=", formato incorrecto en Passwords";
                    }   
                    else
                    {
                        try {
                            md = MessageDigest.getInstance("SHA-512");
                            md.update(pass.getBytes());
                            byte[] mb = md.digest();
                            password = String.copyValueOf(Hex.encodeHex(mb));
                           // System.out.println(password);
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(DesguaceRegistro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                if(textField.getText().equals(" "))
                {
                    valido = false;
                    if(error.equals(""))
                        error+="el email no puede ser vacio";
                    else
                        error+=", el email no puede ser vacio";
                }
                else
                {
                    if(!textField.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
                    {
                        valido = false;
                        if(error.equals(""))
                            error+="formato del email incorrecto. 'ejemplo@ejemplo.com'";
                        else
                            error+=", formato del email incorrecto. 'ejemplo@ejemplo.com'";  
                    }
                }
                
                if(valido)
                {
                    error = registro(t1.getText(), t4.getText(), password, t5.getText(), textField.getText());
                    if(error.equals(""))
                    {
                        error = "Registro completado";
                        err.setText(error);
                        //logger.setLogMessage(3, t1.getText(), "");
                        err.setForeground(Color.green);
                        err.setVisible(true);
                        setVisible(false);
                        Comunication com = Comunication.getInstance();
                        com.Finish();
                    }
                    else
                    {
                        err.setText(error);
                        //logger.setLogMessage(-2, t1.getText(), "");
                        err.setForeground(Color.red);
                        err.setVisible(true);
                    }
                }
                else
                {
                    error = "Errores: "+error;
                    err.setText(error);
                    err.setForeground(Color.red);
                    err.setVisible(true);
                }
            }
        });
        p3.add(btn);
        p2.add(lb1);
        Container c = getContentPane();
        c.add(BorderLayout.NORTH,p2);
        c.add(BorderLayout.CENTER,p1);
        c.add(BorderLayout.SOUTH,p3);
        
    }

    private static String registro(java.lang.String cif, java.lang.String nombre, java.lang.String password, java.lang.String direccion, java.lang.String email) {
        try {
            servicios.RegistroDesguace_Service service = new servicios.RegistroDesguace_Service(new URL(ServiceHandler.getURL("RegistroDesguace")));
            servicios.RegistroDesguace port = service.getRegistroDesguacePort();
            Comunication com = Comunication.getInstance();
            cif = AES.encrypt(cif, com.getAesKey());
            nombre = AES.encrypt(nombre, com.getAesKey());
            password = AES.encrypt(password, com.getAesKey());
            direccion = AES.encrypt(direccion, com.getAesKey());
            email = AES.encrypt(email, com.getAesKey());
            return port.registro(com.getID(),cif, nombre, password, direccion, email);
        } catch (Exception ex) {
            return "Error en la conexion. Intentelo de nuevo.";
        }
    }

}
