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

import javax.swing.*;


/**
 *
 * @author fran
 */
public class DesguaceJava extends JFrame implements ActionListener{

 JButton btn;
	JTextField t1;
	JLabel lb,img;
	JPasswordField t2;
	public DesguaceJava(String titulo)
	{
		super(titulo);
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(new JLabel("User:"));
		p1.add(t1 = new JTextField(25));

		p1.add(new JLabel("Password:"));
		p1.add(t2 = new JPasswordField(25));
		p1.add(lb = new JLabel());
		lb.setVisible(false);
		lb.setForeground(Color.red);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(btn = new JButton("Submint"));
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.add(img = new JLabel());
		img.setIcon(new ImageIcon(getClass().getResource("Desguace.jpg")));
		
        Container c = getContentPane();
        c.add(BorderLayout.NORTH,p3);
        c.add(BorderLayout.CENTER,p1);
        c.add(BorderLayout.SOUTH,p2);
        
       
        btn.addActionListener(this);
	}
	
	public static void main(String args[])
	{
		JFrame des = new DesguaceJava("Desguace-Java");
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
			if("Submint".equals(nameButton))
			{
				String user = t1.getText();
				String pass = t2.getText();
				if(user.equals("") || pass.equals(""))
				{
					lb.setText("User or Password incorrect.");
					lb.setVisible(true);
				}
				else if(user.equals(pass))
				{
					lb.setText("User and Password correct!");
					lb.setVisible(true);
				}
			}
		}
	}
}
