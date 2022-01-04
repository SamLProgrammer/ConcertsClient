package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ConcertsManager;
import network.Client;
import persistence.JSONManager;
import views.ClientFrame;
import views.FormularyDialog;
import views.PurchaseDialog;

public class ClientController implements ActionListener{
	
	private ConcertsManager concertsManager;
	private ClientFrame clientFrame;
	private Client client;
	private JSONManager json;
	private PurchaseDialog purchaseDialog;
	private FormularyDialog formDialog;
	
	public ClientController() {
		formDialog = new FormularyDialog(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case CHECK_SLOTS_BTN:
			checkSlotsAction();
			break;
		case PURCHASE_BTN:
			client.askForTicketsPurchase(json.ticketsCodesToJson(clientFrame.getPurchaseCodesList()),
					json.encodeStringJson(concertsManager.getClientId()));
			break;
		case PURCHASE_DIALOG_OK_BUTTON:
			checkSlotsAction();
			break;
		case ACCEPT_FORMULARY_BUTTON:
			intComponents(formDialog.getTextFieldData(), formDialog.getCodeFieldData());
			formDialog.dispose();
			break;
		case CANCEL_FORMULARY_BUTTON:
			formDialog.dispose();
			break;
		}
	}
	
	private void intComponents(String ip, String code) {
		json = new JSONManager();
		concertsManager = new ConcertsManager();
		clientFrame = new ClientFrame(this);
		client = new Client(new ClientListener(this), ip);
		purchaseDialog = new PurchaseDialog(this);
	}

	private void checkSlotsAction() {
		client.askForTicketsData(concertsManager.encodeStringToJson(clientFrame.getSelectedConcertName()));
		clientFrame.enablePurchaseBtn();
		purchaseDialog.setVisible(false);
	}
	
	public void updateConcertsData(String jsonData) {
		if(!jsonData.equalsIgnoreCase("null")) {
			concertsManager.updateConcertsData(jsonData);
			clientFrame.updateTableData(concertsManager.getConcertsList());
		}
	}

	public void updateTicketsToConcert(String ticketsData) {
		String concertName = concertsManager.updateConcertTickets(ticketsData);
		clientFrame.setSelectedConcert(concertName);
		clientFrame.updateGridPanel(concertsManager.findConcertByName(concertName).getTicketsList());
	}

	public void showPurchaseDialog(String string) {
		purchaseDialog.setAdviceText(string);
		purchaseDialog.setVisible(true);
	}

	public void update30(String concertsDataAsJson) {
		if(!concertsDataAsJson.equalsIgnoreCase("null")) {
			concertsManager.updateConcertsData(concertsDataAsJson);
			clientFrame.updateTableData(concertsManager.getConcertsList());
		}
	}
}
