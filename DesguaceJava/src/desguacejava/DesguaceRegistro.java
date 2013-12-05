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

/**
 *
 * @author Gustavo
 */
public class DesguaceRegistro extends JFrame{
    
    JTextField t1,t4,t5;
    JPasswordField t2,t3;
    JLabel lb1,err;
    JButton btn;
    public DesguaceRegistro()
    {
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(new JLabel("Nombre:"));
	p1.add(t4 = new JTextField(25));
        p1.add(new JLabel("Direccion:"));
	p1.add(t5 = new JTextField(25));
        p1.add(new JLabel("CIF:"));
        p1.add(t1 = new JTextField(25));
        p1.add(new JLabel("Password:"));
	p1.add(t2 = new JPasswordField(25));
        p1.add(new JLabel("Confirm Password:"));
        p1.add(t3 = new JPasswordField(25));
        lb1 = new JLabel("Formulario de Registro");
        lb1.setFont(new Font("Tahoma",0,30));
        err = new JLabel();
        err.setVisible(false);
        p1.add(err);
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
