package models;

public class Ticket {
	
	private boolean available;
	private String ticketId;
	private String ClientId;

	public boolean isAvailable() {
		return available;
	}

	public String getClientId() {
		return ClientId;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setClientId(String clientId) {
		ClientId = clientId;
	}
	
	public String getId() {
		return ticketId;
	}
}
