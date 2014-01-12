/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.net.ssl.ManagerFactoryParameters;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import CEN.*;
/**
 *
 * @author alberto
 */
public class Taller extends JFrame implements ActionListener {

    public Taller() throws HeadlessException {
        super("Taller");
        JButton btLogin, btExit;
	final JTextField t1;
	JLabel lb,img;
	final JPasswordField t2;
	
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(new JLabel("User:"));
        p1.add(t1 = new JTextField(25));
        
        p1.setLayout(new FlowLayout());
        p1.add(new JLabel("Password:"));
        p1.add(t2 = new JPasswordField(25));
        
        p1.add(btLogin = new JButton("Log in"));
        p1.add(btExit = new JButton("Exit"));
        
        Container c = getContentPane();     
        c.add(BorderLayout.CENTER,p1);        
       
        btLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                login(t1.getText(),t2.getText());
            }
        });
        
        btExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("EXIT");
            }
        });        
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame des = new Taller();
        des.setVisible(true);
        des.setSize(400, 500);
        des.setDefaultCloseOperation(EXIT_ON_CLOSE);
        des.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("EXIT");
    }
    
    public void login(String name, String pass) {
        System.out.println(name+" "+pass);
    }
}
