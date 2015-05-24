package entities;

import java.util.HashMap;
import java.util.HashSet;

public class Country extends Location{
	
	HashMap<String,Integer> languages;
	HashSet<City>	cities;
	

	public Country(String yagoID, String country_name,int id){
		this.id=id;
		this.name=country_name;
		this.yagoID=yagoID;
		this.languages=new HashMap<String, Integer>();
		this.cities=new HashSet<City>();
	}
	

	
	public void addLanguage(String language){
		this.languages.put(language, 0); 	//TODO:ID
	}
	
	public HashMap<String, Integer> getLanguages(){
		return this.languages;
	}
	
	public void addCity(City cityToAdd){
		this.cities.add(cityToAdd);
	}
	
	public HashSet<City> getCities(){
		return this.cities;
	}
	
	
}
