/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                if(!t2.getText().equals(t3.getText()))
                {
                    valido = false;
                    if(error.equals(""))
                        error+="los Passwords no coinciden";
                    else
                        error+=", los Passwords no coinciden";
                }
                
                if(valido)
                {
                    error = registro(t1.getText(), t4.getText(), t2.getText(), t5.getText());
                    if(error.equals(""))
                    {
                        error = "Registro completado";
                        err.setText(error);
                        err.setForeground(Color.green);
                        err.setVisible(true);
                        setVisible(false);
                    }
                    else
                    {
                        err.setText(error);
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

    private static String registro(java.lang.String cif, java.lang.String nombre, java.lang.String password, java.lang.String direccion) {
        servicios.RegistroDesguace_Service service = new servicios.RegistroDesguace_Service();
        servicios.RegistroDesguace port = service.getRegistroDesguacePort();
        return port.registro(cif, nombre, password, direccion);
    }

}
