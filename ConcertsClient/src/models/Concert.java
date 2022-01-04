package models;

import java.util.Calendar;

public class Concert {
	
	private String id;
	private String name;
	private Ticket[] ticketsList;	
	private double price;
	private Calendar date;
	
	public String[] dataToVector() {
		String[] dataVector = {name};
		return dataVector;
	}
	
	public void copyList(Ticket[] ticketsVector){
		for (int i = 0; i < ticketsList.length; i++) {
			ticketsList[i] = ticketsVector[i];
		}
	}
	
	public void showTicketsList() {
		for (int i = 0; i < ticketsList.length; i++) {
			System.out.println(ticketsList[i].getId());
		}
	}
	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public Calendar getDate() {
		return date;
	}
	
	public String getId() {
		return id;
	}

	public Ticket[] getTicketsList() {
		return ticketsList;
	}
}
