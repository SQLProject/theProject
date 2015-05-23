package entities;

import java.util.HashMap;

public class Country extends Location{
	
	CapitalCity capital;
	HashMap<String,Integer> languages;
	

	public Country(String yagoID, String country_name,int id){
		this.id=id;
		this.name=country_name;
		this.yagoID=yagoID;
		this.capital=null;
		this.languages=new HashMap<String, Integer>();
		
	}
	
	public CapitalCity getCapitalCity(){
		return this.capital;
	}
	
	public void setCapitalCity(CapitalCity capital_city){
		this.capital=capital_city;
	}

	
	public void addLanguage(String language){
		this.languages.put(language, 0); 	//TODO:ID
	}
	
	public HashMap<String, Integer> getLanguages(){
		return this.languages;
	}
	
	
}
