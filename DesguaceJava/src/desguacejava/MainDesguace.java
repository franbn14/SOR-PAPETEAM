/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo
 */
public class MainDesguace extends JFrame{
    
    JTable data;
    JPanel menu;
    JTextField t1,t2;
    public MainDesguace()
    {
        super("Pantalla Principal del Desguace");   
        menu = new JPanel();
        t1 = new JTextField(15);
        t2 = new JTextField(15);
        menu.add(BorderLayout.NORTH,new JLabel("Peticion: "));
        menu.add(BorderLayout.NORTH,t1);
        //menu.add(BorderLayout.SOUTH,new JLabel("Buscar: "));
        //menu.add(BorderLayout.SOUTH,t2);
        Integer[][] values = new Integer[2][3];
        values[0][0] = 1;
        values[0][1] = 2;
        values[0][2] = 3;
        values[1][0] = 4;
        values[1][1] = 5;
        values[1][2] = 6;
        String[] cols = {"Una","Dos","Tres"};
        data = new JTable(values,cols);
        data.setAutoscrolls(true);
        data.setGridColor(Color.black);
        Container c = getContentPane();
        c.add(BorderLayout.WEST,menu);
        c.add(BorderLayout.CENTER,data);
    }
}
