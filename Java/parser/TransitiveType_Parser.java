package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import entities.*;


public class TransitiveType_Parser extends abstract_parser{
	public static int DEBUG=1;


	public TransitiveType_Parser(){
		this.countriesMap= new HashMap<String,Country>();
		this.citiesMap= new HashMap<String,City>();
		this.footballPlayersMap = new HashMap<String, FootballPlayer>();
		this.coachesMap=new HashMap<String,Coach>();
		this.eventsMap= new HashMap<String,Event>();
		this.teamsMap = new HashMap<String,Team>();
		this.locationMap=new HashMap<String, Location>();
		this.stadiumsMap = new HashMap<String,Stadium>();
		this.awardsMap = new HashMap<String,Award>();
		this.personMap= new HashMap<String, Person>();
//		parse_transitive_type(true);
		parse_transitive_type(false);
	}

	public void parse_transitive_type(boolean createLite){
		String yagoTransitiveType_file_path = config.get_yago_transitive_types_path();
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoTransetiveTypes = new File(yagoTransitiveType_file_path);
		if (yagoTransitiveType_file_path == null || !yagoTransetiveTypes.exists()){
			System.out.println("Can't Open yagoTransitiveType File");
			return;
		}

		BufferedReader br = null;
		BufferedWriter bw = null;
		String line;

		try {
			FileReader fr = new FileReader(yagoTransetiveTypes);
			br = new BufferedReader(fr);
			FileWriter fw = null;

			if (createLite)
			{
				fw = new FileWriter("C:\\yagodata\\yagoTransitiveTypeLite.tsv");
				bw = new BufferedWriter(fw);
			}

			while((line= br.readLine())!= null){
				boolean isUsed = false;
//				if (DEBUG==1){
//					if (countriesMap.size()>20){ //סינון!!!!!!!!!
//						break;}
//				}
				/* find all the countries with the proper tag */
				if(line.contains("<wikicat_Countries>")){
					isUsed = true;
					Country newCountry=getCountryFromLine(line);
					if(newCountry==null) continue;
					countriesMap.put(newCountry.getName(), newCountry);
					locationMap.put(newCountry.getName(), newCountry);
				}
				
				/* find all the cities with the proper tag */
				if(line.contains("<wikicat_Cities>") || line.contains("<wordnet_city_")){
					isUsed = true;
					City newCity=getCityFromLine(line);
					if(newCity==null) continue;
					citiesMap.put(newCity.getName(),newCity);
					locationMap.put(newCity.getName(),newCity);
				}
				
				/* find all basketball teams */
				if(line.contains("<wordnet_basketball_team")){
					isUsed = true;
					Team newTeam = getTeamFromLine(line);
					if(newTeam==null) continue;
					newTeam.setSportField(SportField.BASKETBALL);
					teamsMap.put(newTeam.getTeamName(), newTeam);
				}
				/* find all football teams */
				if(line.contains("<wordnet_football_team")){
					isUsed = true;
					Team newTeam = getTeamFromLine(line);
					if(newTeam==null) continue;
					newTeam.setSportField(SportField.FOOTBALL);
					teamsMap.put(newTeam.getTeamName(), newTeam);
				}
				
				/* find all the sport players */
				if(line.contains("<wordnet_") && line.contains("_player_")){
					isUsed = true;
					FootballPlayer player= getPlayerFromLine(line);
					if(player==null) continue;
					if (line.contains("_football_")) {
						player.setSportField(SportField.FOOTBALL);
						footballPlayersMap.put(player.getName(), player);
						personMap.put(player.getName(), player);
					}
					if (line.contains("_basketball_"))
						player.setSportField(SportField.BASKETBALL);
//					footballPlayersMap.put(player.getName(), player);
					//
				}
				
				/* find all the sport coaches */
				if(line.contains("<wordnet_") && line.contains("_coach_")){
					isUsed = true;
					Coach coach= getCoachFromLine(line);
					if(coach==null) continue;
					if (line.contains("_football_"))
						coach.setSportField(SportField.FOOTBALL);
						coachesMap.put(coach.getName(), coach);
						personMap.put(coach.getName(), coach);
					if (line.contains("_basketball_"))
						coach.setSportField(SportField.BASKETBALL);

//
				}
				
				/* find all the events with the proper tag */
				if(line.contains("<wikicat") && line.contains("competitions")) {
					isUsed = true;
					Event newEvent=getEventFromLine(line);
					if(newEvent==null) continue;
					if (newEvent != null) {
						eventsMap.put(newEvent.getName(),newEvent);
					}

				}
				
				/* find all the stadiums with the proper tag */
				if(line.contains("<wordnet_stadium")) {
					isUsed = true;
					Stadium newStadium=getStadiumFromLine(line);
					if(newStadium==null) continue;
					stadiumsMap.put(newStadium.getName(),newStadium);
				}

				if (isUsed && createLite)
				{
					fw.write(line);
					fw.write('\n');
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		finally{
			try {
				br.close();
				if (createLite)
				{
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	private Coach getCoachFromLine(String line) {
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String coach_name=getTag(line);
		coach_name=isValidEnt(coach_name);
		if (coach_name==null) return null;
		return new Coach(yagoID,coach_name,0); //TODO:ID
	}

	private FootballPlayer getPlayerFromLine(String line) {
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String player_name=getTag(line);
		player_name=isValidEnt(player_name);
		if (player_name==null) return null;
		return new FootballPlayer(yagoID,player_name,0); //TODO:ID
	}

	protected Country getCountryFromLine(String line){
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		country_name=isValidEnt(country_name);
		if (country_name==null) return null;
		return new Country(yagoID,country_name,0);	//TODO:ID	
	}


	protected City getCityFromLine(String line){
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		city_name=isValidEnt(city_name);
		if (city_name==null) return null;
		return new City(yagoID,city_name,0);	//TODO:ID

	}

	protected Team getTeamFromLine(String line){
		String yagoID = getTag(line);
		line = line.substring(line.indexOf('>',0)+1);
		String team_name = getTag(line);
		team_name=isValidEnt(team_name);
		if (team_name==null) return null;
		return new Team(yagoID,team_name,0);
	}

	protected Event getEventFromLine(String line){
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String event_name=getTag(line);
		event_name=isValidEnt(event_name);
		if (event_name==null) return null;
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
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String stadium_name=getTag(line);
		stadium_name=isValidEnt(stadium_name);
		if (stadium_name==null) return null;
		return new Stadium(yagoID,stadium_name,0);	//TODO:ID
	}

}
