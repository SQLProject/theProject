package parser;

import java.util.HashMap;

import entities.City;
import entities.Coach;
import entities.Country;
import entities.Player;
import entities.Team;


public abstract class abstract_parser {
	
	HashMap<String,Country> countriesMap;
	HashMap<String,City> citiesMap;
	HashMap<String,Player> playersMap;
	HashMap<String,Coach> coachesMap;
	HashMap<String,Team> teamsMap;

	
	protected static String getTag(String line){		
		return line.substring(line.indexOf('<',0)+1, line.indexOf('>',0));
	}
	
	//TODO: to write function that gets 3 tags and 4 tags from the line
	
	protected HashMap<String,Country> getCountriesMap(){
		return this.countriesMap;
	}
	
	
	protected HashMap<String,City> getCitiesMap(){
		return this.citiesMap;
	}
	
	protected HashMap<String,Player> getPlayersMap(){
		return this.playersMap;
	}
	
	protected HashMap<String,Coach> getCoachesMap(){
		return this.coachesMap;
	}
	
	protected HashMap<String,Team> getTeamsSet(){
		return this.teamsMap;
	}
		
}

