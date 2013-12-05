/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Gustavo
 */
public class MainDesguace extends JFrame{
    
    JScrollPane scroll;
    public MainDesguace()
    {
        super("Pantalla Principal del Desguace");
        scroll = new JScrollPane();
        scroll.setSize(500, 400);
        Container c = getContentPane();
        c.add(BorderLayout.CENTER,scroll);
    }
}
