package entities;

public class Event {
	
	String yagoID;
	String name;

	public void setId(int id) {
		this.id = id;
	}

	int id;

	public Location getLocation() {
		return location;
	}

	Location location;

	public SportField getSportField() {
		return typeOfSport;
	}

	SportField typeOfSport;
	
	public Event (String yagoID, String event_name,int id, Location location, SportField typeOfSport){
		this.yagoID=yagoID;
		this.name=event_name;
		this.id=id;
		this.location = location;
		this.typeOfSport = typeOfSport;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getYagoID(){
		return this.yagoID;
	}
	
	public void setLocation(Location location)
	{
		this.location =location;
	}
	

}
