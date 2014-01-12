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
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;

/**
 *
 * @author Gustavo
 */
public class MainDesguace extends JFrame{
	private JTable tPeticiones;
	private JTextField tfOp1;
	private JTextField tfOp2;
    private int lastAdded,lastAdded2;
    private JTable tOfertas;
    public MainDesguace()
    {
        super("Pantalla Principal del Desguace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        lastAdded = 0;
        lastAdded2 = 0;
        JSplitPane spPrincipal = new JSplitPane();
        getContentPane().add(spPrincipal, BorderLayout.CENTER);
        
        JPanel panelIzq = new JPanel();
        JPanel panelDer = new JPanel();
        panelDer.setMinimumSize(new Dimension(260,10));
        panelIzq.setMinimumSize(new Dimension(230, 10));
        spPrincipal.setLeftComponent(panelIzq);
        spPrincipal.setRightComponent(panelDer);
        panelDer.setLayout(new BorderLayout(0, 0));
        
        JSplitPane spTablas = new JSplitPane();
        spTablas.setDividerSize(10);
        spTablas.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panelDer.add(spTablas, BorderLayout.CENTER);
        
        SpringLayout sl_panelIzq = new SpringLayout();
        panelIzq.setLayout(sl_panelIzq);
        
        tPeticiones = new JTable(5,1);
        JTableHeader th = (JTableHeader)tPeticiones.getTableHeader();
        tPeticiones.setAutoscrolls(false);
        tPeticiones.setBackground(new Color(250, 250, 210));
        tPeticiones.setForeground(new Color(100, 149, 237));
        tPeticiones.setShowVerticalLines(false);
        tPeticiones.setGridColor(Color.LIGHT_GRAY);
        tPeticiones.setCellSelectionEnabled(true);
        tPeticiones.setAutoCreateRowSorter(true);
        tPeticiones.setAutoCreateColumnsFromModel(false);
        JScrollPane srpPeticiones = new JScrollPane(tPeticiones);
        srpPeticiones.setMinimumSize(new Dimension(23, 220));
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setHeaderValue("Peticiones");
        JLabel lblOp1 = new JLabel("Opcion 1");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblOp1, 34, SpringLayout.NORTH, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblOp1, 20, SpringLayout.WEST, panelIzq);
        panelIzq.add(lblOp1);
        tfOp1 = new JTextField();
        tfOp1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tm = ((DefaultTableModel)tPeticiones.getModel());
				if(lastAdded2 >= tm.getRowCount())
					tm.setRowCount(lastAdded2+1);
				tm.setValueAt(tfOp1.getText(), lastAdded2, 0);
				lastAdded2++;
			}
		});

        tOfertas = new JTable(5,1);
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
        tc2.setHeaderValue("Ofertas");
        JScrollPane srpOfertas = new JScrollPane(tOfertas);        
        srpOfertas.setMinimumSize(new Dimension(23, 220));
        tfOp2 = new JTextField();
        tfOp2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tm = ((DefaultTableModel)tOfertas.getModel());
				if(lastAdded >= tm.getRowCount())
					tm.setRowCount(lastAdded+1);
				tm.setValueAt(tfOp2.getText(), lastAdded, 0);
				lastAdded++;
			}
		});
        spTablas.setLeftComponent(srpPeticiones);
        spTablas.setRightComponent(srpOfertas);
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfOp1, 6, SpringLayout.SOUTH, lblOp1);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfOp1, 20, SpringLayout.WEST, panelIzq);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfOp1, -21, SpringLayout.EAST, panelIzq);
        panelIzq.add(tfOp1);
        tfOp1.setColumns(10);
        
        JLabel lblOp2 = new JLabel("Opcion 2");
        sl_panelIzq.putConstraint(SpringLayout.NORTH, lblOp2, 32, SpringLayout.SOUTH, tfOp1);
        sl_panelIzq.putConstraint(SpringLayout.WEST, lblOp2, 0, SpringLayout.WEST, lblOp1);
        panelIzq.add(lblOp2);
        
        
        sl_panelIzq.putConstraint(SpringLayout.NORTH, tfOp2, 18, SpringLayout.SOUTH, lblOp2);
        sl_panelIzq.putConstraint(SpringLayout.WEST, tfOp2, 0, SpringLayout.WEST, lblOp1);
        sl_panelIzq.putConstraint(SpringLayout.EAST, tfOp2, 0, SpringLayout.EAST, tfOp1);
        panelIzq.add(tfOp2);
        tfOp2.setColumns(10);
        
        
    }
}
