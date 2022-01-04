package views;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import models.Ticket;

public class GridPanel extends JPanel{
	
	private Ticket[] ticketsCodeVector;
	
	public GridPanel() {
	}
	
	public void updatePanel(Ticket[] ticketsCodeVector) {
		this.ticketsCodeVector = ticketsCodeVector;
		setBackground(Color.decode("#696060"));
		removeAll();
		initGrid();
		repaint();
		updateUI();
	}
	
	public void initGrid() {
		setLayout(new GridLayout((int)(ticketsCodeVector.length/10) + 1,10));
		for (int i = 0; i < ticketsCodeVector.length; i++) {
				RoundJButton button;
			 if(ticketsCodeVector[i].isAvailable()) {
				 button = new RoundJButton("#99A802", ticketsCodeVector[i].isAvailable(), 
						 ticketsCodeVector[i].getId());
			 }
			 else {
				 button = new RoundJButton("#d3363f", ticketsCodeVector[i].isAvailable(), 
						 ticketsCodeVector[i].getId());
				 button.setText(ticketsCodeVector[i].getId());
				 button.setEnabled(false);
			 }
			 this.add(button);
		}
	}
} 
