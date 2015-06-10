package entities;

public class Stadium extends Location {
	
	City city = null;
	
	
	public Stadium (String yagoID, String stadium_name,int id){
		this.yagoID=yagoID;
		this.name=stadium_name;
		this.id=id;
	}
	
	public void setCity(City city){
		this.city=city;
	}
	
	public City getCity(){
		return this.city;
	}

}
