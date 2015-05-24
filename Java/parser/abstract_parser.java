package parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import confguration.PropertyConfig;
import entities.Award;
import entities.City;
import entities.Coach;
import entities.Country;
import entities.Player;
import entities.Team;
import entities.Event;
import entities.Location;
import entities.Stadium;

public abstract class abstract_parser {
	
	HashMap<String,Country> countriesMap;
	HashMap<String,City> citiesMap;
	HashMap<String,Player> playersMap;
	HashMap<String,Coach> coachesMap;
	HashMap<String,Team> teamsMap;
	HashMap<String,Event> eventsMap;
	HashMap<String,Location> locationMap;
	HashMap<String,Stadium> stadiumsMap;
	HashMap<String,Award> awardsMap;

	PropertyConfig config=new PropertyConfig();
	protected static String getTag(String line){		
		return line.substring(line.indexOf('<',0)+1, line.indexOf('>',0));
	}
	
	//TODO: to write function that gets 3 tags and 4 tags from the line
	
	public HashMap<String,Country> getCountriesMap(){
		return this.countriesMap;
	}

	public HashMap<String, Location> getLocationMap(){return this.locationMap;}
	public HashMap<String,City> getCitiesMap(){
		return this.citiesMap;
	}
	
	public HashMap<String,Player> getPlayersMap(){
		return this.playersMap;
	}
	
	public HashMap<String,Coach> getCoachesMap(){
		return this.coachesMap;
	}
	
	public HashMap<String,Team> getTeamsMap(){
		return this.teamsMap;
	}
	
	public HashMap<String,Event> getEventsMap(){
		return this.eventsMap;
	}
	
	public HashMap<String,Stadium> getStadiumsMap(){
		return this.stadiumsMap;
	}
	
		protected HashMap<String,Award> getAwardsMap(){
		return this.awardsMap;
	}
	
	public Collection<Country> getCountriesSet(){
		return this.countriesMap.values();
	}
	
	
	public Collection<City> getCitiesSet(){
		return this.citiesMap.values();
	}
	
	public Collection<Player> getPlayersSet(){
		return this.playersMap.values();
	}
	
	public Collection<Coach> getCoachesSet(){
		return this.coachesMap.values();
	}
	
	public Collection<Team> getTeamsSet(){
		return this.teamsMap.values();
	}
	
	public Collection<Event> getEventsSet(){
		return this.eventsMap.values();
	}
	
	public Collection<Stadium> getStadiumsSet(){
		return this.stadiumsMap.values();
	}
	public Collection<Award> getAwardsSet(){
		return this.AwardsMap.values();
	}
		
}

