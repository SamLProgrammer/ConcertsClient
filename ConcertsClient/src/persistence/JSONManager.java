package persistence;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import models.Concert;

public class JSONManager {
	
	public Concert decodeConcertJson(String string) {
		Gson gson = new Gson();
		Concert concert = gson.fromJson(string, Concert.class);
		return concert;
	}
	
	public String concertToJson(Concert concert) {
		String concertsJson = "";
		if(concert != null) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			concertsJson = gson.toJson(concert);
		}
		return concertsJson;
	}
	
	//=========================================== ENCODERS && DECODERS ==============================================
	
	public ArrayList<Concert> decodeConcertsData(String string) {
		ArrayList<Concert> concertsList = new ArrayList<>();
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(string);
		Type type = new TypeToken<ArrayList<Concert>>(){}.getType();
		concertsList =  gson.fromJson(jsonElement, type);
		return concertsList;   
	}
	
	public String fullDataToJson(ArrayList<Concert> concerts) {
		String jsonDataString = "";
		if(concerts != null) {
			Gson json = new GsonBuilder().setPrettyPrinting().create();
			jsonDataString = json.toJson(concerts);
		}
		return jsonDataString;
	}
	
	public String ticketsCodesToJson(ArrayList<String> ticketsCodesList) {
		String jsonDataString = "";
		if(ticketsCodesList != null) {
			Gson json = new GsonBuilder().setPrettyPrinting().create();
			jsonDataString = json.toJson(ticketsCodesList);
		}
		return jsonDataString;
	}
	
	public String concertsListToJson(ArrayList<Concert> concerts) {
		String jsonList = "";
		if(concerts != null) {
			Gson gson = new GsonBuilder()
		            .addSerializationExclusionStrategy(new ExclusionStrategy() {
		                @Override
		                public boolean shouldSkipField(FieldAttributes f) {
		                    return f.getName().toLowerCase().contains("price") ||
		                    	   f.getName().toLowerCase().contains("date");
		                }

		                @Override
		                public boolean shouldSkipClass(Class<?> aClass) {
		                    return false;
		                }
		            })
		            .setPrettyPrinting().create();
			jsonList = gson.toJson(concerts);
		}
		return jsonList;
	}
	
	public String encodeStringJson(String string) {
		Gson gson = new Gson();
		return gson.toJson(string);
	}
}