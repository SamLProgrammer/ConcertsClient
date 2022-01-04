package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import controller.Actions;
import models.Ticket;

public class GridPanelContainer extends JPanel{
	
	private GridPanel gridPanel;
	private DCButton purchaseBtn;
	
	public GridPanelContainer(ActionListener listener) {
		setLayout(new BorderLayout());
		initComponents(listener);
	}
	
	private void initComponents(ActionListener listener) {
		setBackground(Color.decode("#696060"));
		gridPanel = new GridPanel();
		purchaseBtn = new DCButton("#00b763");
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		purchaseBtn.setFont(new Font("Oswald", Font.BOLD, height/20));
		purchaseBtn.setForeground(Color.decode("#5454E1"));
		purchaseBtn.setText("PURCHASE");
		purchaseBtn.setEnabled(false);
		purchaseBtn.setActionCommand(Actions.PURCHASE_BTN.name());
		purchaseBtn.addActionListener(listener);
		add(gridPanel, BorderLayout.CENTER);
		add(purchaseBtn, BorderLayout.SOUTH);
	}

	public ArrayList<String> getReservedButtons(JComponent component, ArrayList<String> codesList) {
		if(component instanceof RoundJButton) {
			if(((RoundJButton) component).isReserved()) {
				codesList.add(((RoundJButton) component).getCode());
			}
		}
		if(component.getComponents().length > 0) {
			for (int i = 0; i < component.getComponents().length; i++) {
				JComponent jComponent = (JComponent)component.getComponent(i);
				getReservedButtons(jComponent, codesList);
			}
		}
		return codesList;
	}
	
	public ArrayList<String> getPurchasedTicketsList() {
		ArrayList<String> codesList = new ArrayList<>();
		codesList = getReservedButtons(this,codesList);
		return codesList;
	}

	public void updateGridPanel(Ticket[] ticketsCodeVector) {
		gridPanel.updatePanel(ticketsCodeVector);
	}
	
	public void enableBtn() {
		purchaseBtn.setEnabled(true);
	}
}