package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import controller.ClientListener;
import controller.Requests;

public class Client{
	
	private Socket socket; 
	private DataOutputStream outputStream;
	private DataInputStream inputStream;
	private boolean clientUp;
	private ClientListener clientListener;
	
	public Client(ClientListener clientListener, String ip) {
		this.clientListener = clientListener;
		initComponents(ip);
		updateConcertsList();
		initialDataRequest();
	}
	
	private void initComponents(String ip) {
		try {
			socket = new Socket(ip, 3000);
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			clientUp = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateConcertsList() {	
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(clientUp) {
					try {
						String string = inputStream.readUTF();
						switch (Requests.valueOf(string)) {
						case SERVER_TO_CLIENT_CONCERTS:
							clientListener.updateConcertsData(inputStream.readUTF());
							break;
						case SERVER_TICKETS_TO_CLIENT:
							clientListener.updateTicketsToConcert(inputStream.readUTF());
							break;
						case PURCHASE_COMPLETED:
							clientListener.callSuccefulPurchaseDialog();
							break;
						case PURCHASE_ERROR:
							clientListener.callFailedPurchaseDialog();
						case UPDATE_30:
							clientListener.update30(inputStream.readUTF());
							break;
						case FULL_DATA_TO_ADMIN:
							inputStream.readUTF();
							break;
						default:
							break;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void initialDataRequest() {
		try {
			outputStream.writeUTF(Requests.ASK_FOR_CLIENT_CONCERTS.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void askForTicketsData(String concertName) {
		try {
			outputStream.writeUTF(Requests.CLIENT_ASK_FOR_TICKETS.toString());
			outputStream.writeUTF(concertName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void askForTicketsPurchase(String ticketsCodesAsJson, String clientCodeAsJson) {
		try {
			outputStream.writeUTF(Requests.ASK_FOR_TICKETS_PURCHASE.toString());
			outputStream.writeUTF(ticketsCodesAsJson);
			outputStream.writeUTF(clientCodeAsJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}