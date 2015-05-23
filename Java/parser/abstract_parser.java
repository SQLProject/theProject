package parser;

import java.util.HashMap;

import entities.City;
import entities.Country;


public abstract class abstract_parser {
	
	HashMap<String,Country> countriesMap;
	HashMap<String,City> citiesMap;

	
	protected static String getTag(String line){		
		return line.substring(line.indexOf('<',0)+1, line.indexOf('>',0));
	}
	
	protected HashMap<String,Country> getCountriesMap(){
		return this.countriesMap;
	}
	
	
	protected HashMap<String,City> getCitiesSet(){
		return this.citiesMap;
	}
		
}

