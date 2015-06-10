package parser;

import java.util.Collection;
import java.util.HashMap;

import confguration.PropertyConfig;
import entities.*;

public abstract class abstract_parser {
	
	HashMap<String,Country> countriesMap;
	HashMap<String,City> citiesMap;
	HashMap<String, FootballPlayer> footballPlayersMap;
	HashMap<String,Coach> coachesMap;
	HashMap<String,Team> teamsMap;
	HashMap<String,Event> eventsMap;
	HashMap<String,Location> locationMap;
	HashMap<String, Person> personMap;
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
	public HashMap<String, Person> getPersonMap(){return this.personMap;}
	public HashMap<String,City> getCitiesMap(){
		return this.citiesMap;
	}
	
	public HashMap<String, FootballPlayer> getFootballPlayersMap(){
		return this.footballPlayersMap;
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
	
		public HashMap<String,Award> getAwardsMap(){
		return this.awardsMap;
	}
	
	public Collection<Country> getCountriesSet(){
		return this.countriesMap.values();
	}
	
	
	public Collection<City> getCitiesSet(){
		return this.citiesMap.values();
	}
	
	public Collection<FootballPlayer> getPlayersSet(){
		return this.footballPlayersMap.values();
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
		return this.awardsMap.values();
	}
	public static String isValidEnt(String ent){
		ent=ent.replaceAll("_", "0");
		ent=ent.replaceAll(",","0");
		ent=ent.replaceAll("-","0");
		ent=ent.replaceAll(" ", "0");
		
		if(ent.matches("[A-Za-z0-9]+"))
		{
			ent=ent.replaceAll("  ", " ");
			ent=ent.replaceAll("0", " ");
			ent=ent.replaceAll("  ", " ");
			return ent;
		}
		return null;
	}
		
}

