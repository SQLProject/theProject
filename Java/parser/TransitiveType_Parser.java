package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.City;
import entities.Coach;
import entities.Country;
import entities.Event;
import entities.SportField;
import entities.Player;
import entities.Team;
import entities.Stadium;
import entities.Location;



public class TransitiveType_Parser extends abstract_parser{
	public static int DEBUG=1;	
	public TransitiveType_Parser(){
		this.countriesMap= new HashMap<String,Country>();
		this.citiesMap= new HashMap<String,City>();
		this.playersMap= new HashMap<String,Player>();
		this.coachesMap=new HashMap<String,Coach>();
		this.eventsMap= new HashMap<String,Event>();
		this.teamsMap = new HashMap<String,Team>();
		this.locationMap=new HashMap<String, Location>();
		this.stadiumsMap = new HashMap<String,Stadium>();
	}
	
	public void parse_transitive_type(){
		String yagoTransitiveType_file_path = config.get_yago_transitive_types_path();
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoTransetiveTypes = new File(yagoTransitiveType_file_path);
		if (yagoTransitiveType_file_path == null || !yagoTransetiveTypes.exists()){
			System.out.println("Can't Open yagoTransitiveType File");
			return;
		}
		
		BufferedReader br = null;
		String line;
		
		try {
			FileReader fr = new FileReader(yagoTransetiveTypes);
			br = new BufferedReader(fr);

			while((line= br.readLine())!= null){
				if (DEBUG==1){
					if (countriesMap.size()>3){
						break;}
				}
				/* find all the countries with the proper tag */
				if(line.contains("<wikicat_Countries>")){
					Country newCountry=getCountryFromLine(line);
					if(newCountry==null) continue;
					countriesMap.put(newCountry.getName(), newCountry);
					locationMap.put(newCountry.getName(), newCountry);
				}
				
				/* find all the cities with the proper tag */
				if(line.contains("<wikicat_Cities>") || line.contains("<wordnet_city_")){
					City newCity=getCityFromLine(line);
					if(newCity==null) continue;
					citiesMap.put(newCity.getName(),newCity);
					locationMap.put(newCity.getName(),newCity);
				}
				
				/* find all basketball teams */
				if(line.contains("<wordnet_basketball_team")){
					Team newTeam = getTeamFromLine(line);
					if(newTeam==null) continue;
					newTeam.setSportField(SportField.BASKETBALL);
					teamsMap.put(newTeam.getTeamName(), newTeam);
				}
				/* find all football teams */
				if(line.contains("<wordnet_football_team")){
					Team newTeam = getTeamFromLine(line);
					if(newTeam==null) continue;
					newTeam.setSportField(SportField.FOOTBALL);
					teamsMap.put(newTeam.getTeamName(), newTeam);
				}
				
				/* find all the sport players */
				if(line.contains("<wordnet_") && line.contains("_player_")){
					Player player= getPlayerFromLine(line);
					if(player==null) continue;
					if (line.contains("_football_"))
						player.setSportField(SportField.FOOTBALL);
					if (line.contains("_basketball_"))
						player.setSportField(SportField.BASKETBALL);
					playersMap.put(player.getName(), player);
				}
				
				/* find all the sport coaches */
				if(line.contains("<wordnet_") && line.contains("_coach_")){
					Coach coach= getCoachFromLine(line);
					if(coach==null) continue;
					if (line.contains("_football_"))
						coach.setSportField(SportField.FOOTBALL);
					if (line.contains("_basketball_"))
						coach.setSportField(SportField.BASKETBALL);
					coachesMap.put(coach.getName(), coach);
				}
				
				/* find all the events with the proper tag */
				if(line.contains("<wikicat") && line.contains("competitions")) {
					Event newEvent=getEventFromLine(line);
					if(newEvent==null) continue;
					if (newEvent != null) {
						eventsMap.put(newEvent.getName(),newEvent);
					}
					
				}
				
				/* find all the stadiums with the proper tag */
				if(line.contains("<wordnet_stadium")) {
					Stadium newStadium=getStadiumFromLine(line);
					if(newStadium==null) continue;
					stadiumsMap.put(newStadium.getName(),newStadium);
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	private Coach getCoachFromLine(String line) {
		if(getTag(line)==null) return null;
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String coach_name=getTag(line);
		return new Coach(yagoID,coach_name,0); //TODO:ID
	}

	private Player getPlayerFromLine(String line) {
		if(getTag(line)==null) return null;
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String player_name=getTag(line);
		return new Player(yagoID,player_name,0); //TODO:ID
	}

	protected Country getCountryFromLine(String line){
		if(getTag(line)==null) return null;
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String country_name=getTag(line);
		return new Country(yagoID,country_name,0);	//TODO:ID	
	}
	
	
	protected City getCityFromLine(String line){
		if(getTag(line)==null) return null;
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String city_name=getTag(line);
		return new City(yagoID,city_name,0);	//TODO:ID
		
	}
	
	protected Team getTeamFromLine(String line){
		if(getTag(line)==null) return null;
		String yagoID = getTag(line);
		line = line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String team_name = getTag(line);
		return new Team(yagoID,team_name,0);
	}
	
	protected Event getEventFromLine(String line){
		if(getTag(line)==null) return null;
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String event_name=getTag(line);
		SportField typeOfSport=null;
		
		if(line.contains("football") || line.contains("soccer")) {
			typeOfSport=SportField.FOOTBALL;
		}
		else if(line.contains("basketball")) {
			typeOfSport=SportField.BASKETBALL;
		}
		else {
			return null;
		}
		
		return new Event(yagoID,event_name,0,null,typeOfSport);	//TODO:ID and HappendIn
	}
	
	protected Stadium getStadiumFromLine(String line){
		if(getTag(line)==null) return null;
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		if(getTag(line)==null) return null;
		String stadium_name=getTag(line);
		return new Stadium(yagoID,stadium_name,0);	//TODO:ID
	}
	



}
