package entities;

public class Event {
	
	String yagoID;
	String name;
	int id;
	Location happendIn;
	SportField typeOfSport;
	
	public Event (String yagoID, String event_name,int id, Location happendIn, SportField typeOfSport){
		this.yagoID=yagoID;
		this.name=event_name;
		this.id=id;
		this.happendIn = happendIn;
		this.typeOfSport = typeOfSport;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getYagoID(){
		return this.yagoID;
	}

}
