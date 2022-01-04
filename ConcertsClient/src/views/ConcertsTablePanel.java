package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.Actions;
import models.Concert;

public class ConcertsTablePanel extends JPanel{

	private JTable dataTable;
	private DefaultTableModel model;
	private ButtonRenderer buttonRenderer;
	private ButtonEditor buttonDelete;
	
	public ConcertsTablePanel(ActionListener listener) {
		setLayout(new BorderLayout());
		String[] headers = {"CONCERT - NAME", "TICKETS"};
		  model = new DefaultTableModel(null,headers){
	            public boolean isCellEditable(int rowIndex , int columnIndex){return columnIndex == 1;}
	        };
	        
	    int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	    int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	    Image img = new ImageIcon(getClass().getResource("/img/ticket.png")).getImage();
	    Image newimg = img.getScaledInstance(width/5, height/5,  Image.SCALE_DEFAULT);
	        
	    buttonRenderer= new ButtonRenderer();
	    buttonRenderer.setIcon(new ImageIcon(newimg));
	    buttonDelete = new ButtonEditor(new JTextField());
	    buttonDelete.getBtn().setActionCommand(Actions.CHECK_SLOTS_BTN.name());
	    buttonDelete.getBtn().addActionListener(listener);
	    
		
		dataTable = new JTable(model);
		JScrollPane scrollerTable = new JScrollPane();
		scrollerTable.setViewportView(dataTable);
		scrollerTable.getViewport().setBackground(Color.decode("#202020"));
		((DefaultTableCellRenderer)dataTable.getDefaultRenderer(Object.class)).setOpaque(false);
		
		dataTable.setRowHeight(40);
		dataTable.setForeground(Color.decode("#EEF3F7"));
		dataTable.setFont(new Font("Oswald", Font.BOLD, 20));
		dataTable.setBackground(Color.decode("#202020"));
		dataTable.setShowVerticalLines(false);
		dataTable.setCellSelectionEnabled(true);
		Font f = new Font("Oswald", Font.BOLD, 21);
	      JTableHeader header = dataTable.getTableHeader();
	      TableColumnModel colMod = header.getColumnModel();
	      TableColumn tabCol;
	      for (int i = 0; i < colMod.getColumnCount(); i++) {
	    	  tabCol = colMod.getColumn(0);
	    	  tabCol.setResizable(true);
		}
	      
	      header.setFont(f);
	      header.setBackground(Color.decode("#262B2C"));
	      header.setForeground(Color.decode("#8F969B"));
		
		add(scrollerTable);
	}
	
	public void updateRowsTable(ArrayList<Concert> concertsList) {
		model.setRowCount(0);
		for (Concert concert : concertsList) {
			model.addRow(concert.dataToVector());	
			dataTable.getColumnModel().getColumn(1).setCellRenderer(buttonRenderer);			
            dataTable.getColumnModel().getColumn(1).setCellEditor(buttonDelete);
		}
	}
	
    public String getSelectedConcertName() {
		String string = "";
             try {
                string = (String)dataTable.getValueAt(dataTable.getSelectedRow(), 0);
           } catch (ArrayIndexOutOfBoundsException e) {
                 System.out.println("Exception Name: " + e.toString());
           }
	    return  string;
	}

	public void setSelectedConcert(String concertName) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if(model.getValueAt(i, 0).equals(concertName)) {
				dataTable.setRowSelectionInterval(i, i);
			}
		}
	}
	
}