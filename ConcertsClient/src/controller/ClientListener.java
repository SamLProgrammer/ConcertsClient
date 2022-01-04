package controller;

public class ClientListener {

	private ClientController controller;
	
	public ClientListener(ClientController controller) {
		this.controller = controller;
	}
	
	public void updateConcertsData(String jsonData) {
		controller.updateConcertsData(jsonData);
	}

	public void updateTicketsToConcert(String ticketsData) {
		controller.updateTicketsToConcert(ticketsData);
	}

	public void callSuccefulPurchaseDialog() {
		controller.showPurchaseDialog("PURCHASE COMPLETED");
	}

	public void callFailedPurchaseDialog() {
		controller.showPurchaseDialog("FAILED PURCHASE");
	}

	public void update30(String readUTF) {
		controller.update30(readUTF);
	}
}
