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
	private JTable table;
	private JTextField tfOp1;
	private JTextField textField;
    private int lastAdded;
    public MainDesguace()
    {
        super("Pantalla Principal del Desguace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        lastAdded=0;
        JSplitPane splitPane = new JSplitPane();
        getContentPane().add(splitPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(230, 10));
        splitPane.setLeftComponent(panel);
        SpringLayout sl_panel = new SpringLayout();
        panel.setLayout(sl_panel);
        
        JLabel lblOp1 = new JLabel("Opcion 1");
        sl_panel.putConstraint(SpringLayout.NORTH, lblOp1, 34, SpringLayout.NORTH, panel);
        sl_panel.putConstraint(SpringLayout.WEST, lblOp1, 20, SpringLayout.WEST, panel);
        panel.add(lblOp1);
        
        table = new JTable(5,1);
        JTableHeader th = (JTableHeader)table.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setHeaderValue("Peticiones");
        table.setAutoscrolls(false);
        table.setBackground(new Color(250, 250, 210));
        table.setForeground(new Color(100, 149, 237));
        table.setShowVerticalLines(false);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setCellSelectionEnabled(true);
        table.setAutoCreateRowSorter(true);
        table.setAutoCreateColumnsFromModel(false);
        JScrollPane scrollPane = new JScrollPane(table);
        splitPane.setRightComponent(scrollPane);
        tfOp1 = new JTextField();
        tfOp1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel tm = ((DefaultTableModel)table.getModel());
				if(lastAdded >= tm.getRowCount())
					tm.setRowCount(lastAdded+1);
				tm.setValueAt(tfOp1.getText(), lastAdded, 0);
				lastAdded++;
			}
		});
        sl_panel.putConstraint(SpringLayout.NORTH, tfOp1, 6, SpringLayout.SOUTH, lblOp1);
        sl_panel.putConstraint(SpringLayout.WEST, tfOp1, 20, SpringLayout.WEST, panel);
        sl_panel.putConstraint(SpringLayout.EAST, tfOp1, -21, SpringLayout.EAST, panel);
        panel.add(tfOp1);
        tfOp1.setColumns(10);
        
        JLabel lblOp2 = new JLabel("Opcion 2");
        sl_panel.putConstraint(SpringLayout.NORTH, lblOp2, 32, SpringLayout.SOUTH, tfOp1);
        sl_panel.putConstraint(SpringLayout.WEST, lblOp2, 0, SpringLayout.WEST, lblOp1);
        panel.add(lblOp2);
        
        textField = new JTextField();
        sl_panel.putConstraint(SpringLayout.NORTH, textField, 18, SpringLayout.SOUTH, lblOp2);
        sl_panel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblOp1);
        sl_panel.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, tfOp1);
        panel.add(textField);
        textField.setColumns(10);
        
    }
}
