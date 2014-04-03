/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package desguacejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import org.apache.commons.codec.binary.Hex;


/**
 *
 * @author fran
 */
public class DesguaceJava extends JFrame implements ActionListener{

        JButton btn,regbtn;
	JTextField t1;
	JLabel img,lblError;
	JPasswordField t2;
        DesguaceRegistro reg;
        static JFrame des;
	public DesguaceJava(String titulo)
	{
		super(titulo);
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
                c.add(BorderLayout.SOUTH,p2);
                reg = new DesguaceRegistro();
                reg.setVisible(false);
                btn.addActionListener(this);
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
                            System.out.println(pass);
                            String error=loginDes(pass, user);
                            if(error.equals(""))
                            {
                                MainDesguace mdes;
                                mdes = new MainDesguace(t1.getText());
                                mdes.setVisible(false);
                                mdes.setVisible(true);
                                mdes.setSize(700,600);
                                des.setVisible(false);
                            }
                            else
                            {
                                    lblError.setText(error);
                                    lblError.setVisible(true);
                            }
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(DesguaceJava.class.getName()).log(Level.SEVERE, null, ex);
                        }
			}
		}
	}

    private static String loginDes(java.lang.String password, java.lang.String cif) {
        servicios.LoginDesguace_Service service = new servicios.LoginDesguace_Service();
        servicios.LoginDesguace port = service.getLoginDesguacePort();
        return port.loginDes(password, cif);
    }
}
