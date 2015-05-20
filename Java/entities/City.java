package entities;

public class City extends Location{
	Country country;
	
	
	public City (String city_name,Country country){
		this.name=city_name;
		this.country=country;
	}


}
