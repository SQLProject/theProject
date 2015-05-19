package entities;

import java.util.ArrayList;

public class Country extends Location{
	
	CapitalCity capital;
	ArrayList<City> cities;
	

	public Country(String yagoID, String country_name,int id){
		this.id=id;
		this.name=country_name;
		this.yagoID=yagoID;
	}
	
	public Country(String yagoID, String country_name,int id,CapitalCity capital_city){
		this.id=id;
		this.name=country_name;
		this.yagoID=yagoID;
		this.capital=capital_city;
	}
	
	public CapitalCity getCapitalCity(){
		return this.capital;
	}
	
	public void setCapitalCity(CapitalCity capital_city){
		this.capital=capital_city;
	}

	public ArrayList<City> getCities(){
		return this.cities;
	}
	
	public void setCities(ArrayList<City> cities){
		this.cities=cities;
	}
	
	
}
