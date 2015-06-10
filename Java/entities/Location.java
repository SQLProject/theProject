package entities;

public abstract class Location {
	
	String yagoID;
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int id;
	
	public String getName(){
		return this.name;
	}
	
	public String getYagoID(){
		return this.yagoID;
	}

}
