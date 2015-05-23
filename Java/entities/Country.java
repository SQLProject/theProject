package entities;

import java.util.HashMap;

public class Country extends Location{
	
	HashMap<String,Integer> languages;
	

	public Country(String yagoID, String country_name,int id){
		this.id=id;
		this.name=country_name;
		this.yagoID=yagoID;
		this.languages=new HashMap<String, Integer>();
		
	}
	

	
	public void addLanguage(String language){
		this.languages.put(language, 0); 	//TODO:ID
	}
	
	public HashMap<String, Integer> getLanguages(){
		return this.languages;
	}
	
	
}
