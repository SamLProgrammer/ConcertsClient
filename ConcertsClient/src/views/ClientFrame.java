package views;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.Concert;
import models.Ticket;

public class ClientFrame extends JFrame{
	
	private ConcertsTablePanel concertsTablePanel;
	private GridPanelContainer gridPanelContainer;
	
	public ClientFrame(ActionListener listener) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents(listener);
		locate();
		setVisible(true);
	}
	
	private void initComponents(ActionListener listener) {
		setIconImage(new ImageIcon(getClass().getResource("/img/spotlights.png")).getImage());
		concertsTablePanel = new ConcertsTablePanel(listener);
		gridPanelContainer = new GridPanelContainer(listener);
		add(concertsTablePanel, BorderLayout.WEST);
		add(gridPanelContainer, BorderLayout.CENTER);
	}

	private void locate() {
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(width/4, 2*height/3);
		setLocation(width/2 - getWidth()/2, height/2 - getHeight()/2);
		setExtendedState(MAXIMIZED_BOTH);
	}
	
	public void updateTableData(ArrayList<Concert> concertsList) {
		concertsTablePanel.updateRowsTable(concertsList);
	}
	
	public String getSelectedConcertName() {
		return concertsTablePanel.getSelectedConcertName();
	}
	
	public void setSelectedConcert(String concertName) {
		concertsTablePanel.setSelectedConcert(concertName);
	}
	
	public void updateGridPanel(Ticket[] ticketsCodeVector) {
		gridPanelContainer.updateGridPanel(ticketsCodeVector);
	}
	
	public void enablePurchaseBtn() {
		gridPanelContainer.enableBtn();
	}
	
	public ArrayList<String> getPurchaseCodesList() {
		return gridPanelContainer.getPurchasedTicketsList();
	}
}
