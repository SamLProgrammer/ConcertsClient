package models;

import java.util.ArrayList;
import persistence.JSONManager;

public class ConcertsManager {

	private ArrayList<Concert> concertsList;
	private JSONManager json;
	private String clientId;
	private String clientName;
	
	public ConcertsManager() {
		concertsList = new ArrayList<>();
		json = new JSONManager();
	}

	public Concert findConcertByName(String string) {
		Concert concert = new Concert();
		for (Concert auxConcert : concertsList) {
			if(auxConcert.getName().equalsIgnoreCase(string)) {
				concert = auxConcert;
			}
		}
		return concert;
	}
	
//============================================= UTILITIES ===========================================
	
	public String updateConcertTickets(String string) {
		Concert newConcert = json.decodeConcertJson(string);
		for (Concert concert : concertsList) {
			if(concert.getName().equalsIgnoreCase(newConcert.getName())) {
				concert.copyList(newConcert.getTicketsList());
			}
		}
		return newConcert.getName();
	}
	
	public String encodeStringToJson(String string) {
		return json.encodeStringJson(string);
	}
	
	public ArrayList<Concert> getConcertsList() {
		return concertsList;
	}
	
	public String usersConcertsListToJson() {
		return json.concertsListToJson(concertsList);
	}
	
	public String fullConcertsListToJson() {
		return json.fullDataToJson(concertsList);
	}
	
	public void updateConcertsData(String string) {
		concertsList =  json.decodeConcertsData(string);
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public String getClientName() {
		return clientName;
	}
}

