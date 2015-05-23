package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Country extends Location{
	
	CapitalCity capital;
	ArrayList<City> cities;
	HashMap<String,Integer> languages;
	

	public Country(String yagoID, String country_name,int id){
		this.id=id;
		this.name=country_name;
		this.yagoID=yagoID;
		this.capital=null;
		this.cities=new ArrayList<City>();
		this.languages=new HashMap<String, Integer>();
		
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
	
	public void addCity(City city){
		this.cities.add(city);
	}
	
	public void addLanguage(String language){
		this.languages.put(language, 0); 	//TODO:ID
	}
	
	public HashMap<String, Integer> getLanguages(){
		return this.languages;
	}
	
	
}
