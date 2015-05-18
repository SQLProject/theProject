package entities;

public class Country {
	
	int id;
	String name;
	CapitalCity capital;
	City cities[];
	
	public Country(int id, String country_name){
		this.id=id;
		this.name=country_name;
	}
	
	public Country(int id, String country_name,CapitalCity capital_city){
		this.id=id;
		this.name=country_name;
		this.capital=capital_city;
	}
	
	public String getCountryName(){
		return this.name;
	}
	
	public CapitalCity getCapitalCity(){
		return this.capital;
	}
	
	public void setCapitalCity(CapitalCity capital_city){
		this.capital=capital_city;
	}

	public City[] getCities(){
		return this.cities;
	}
	
	public void setCities(City[] cities){
		this.cities=cities;
	}
	
	
}
