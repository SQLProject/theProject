package entities;

public class Stadium extends Location {
	
	City city = null;
	
	
	public Stadium (String yagoID, String city_name,int id){
		this.yagoID=yagoID;
		this.name=city_name;
		this.id=id;
	}
	
	public void setCity(City city){
		this.city=city;
	}
	
	protected City getCity(){
		return this.city;
	}

}
