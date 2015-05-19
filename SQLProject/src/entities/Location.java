package entities;

public abstract class Location {
	
	String yagoID;
	String name;
	int id;
	
	public String getName(){
		return this.name;
	}
	
	public String getYagoID(){
		return this.yagoID;
	}

}
