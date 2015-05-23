package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.City;
import entities.Coach;
import entities.Country;
import entities.SportField;;
import entities.Player;
import entities.Team;




public class TransitiveType_Parser extends abstract_parser{
		
	public TransitiveType_Parser(){
		this.countriesMap= new HashMap<String,Country>();
		this.citiesMap= new HashMap<String,City>();
		this.playersMap= new HashMap<String,Player>();
		this.coachesMap=new HashMap<String,Coach>();
		parse_transitive_type();
		this.teamsMap = new HashMap<String,Team>();
	}
	
	protected void parse_transitive_type(){
		String yagoTransitiveType_file_path = "D:\\yagodata\\yagoTransitiveType.tsv";
		
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
				
				/* find all the countries with the proper tag */
				if(line.contains("<wikicat_Countries>")){
					Country newCountry=getCountryFromLine(line);
					countriesMap.put(newCountry.getName(), newCountry);
				}
				
				/* find all the cities with the proper tag */
				if(line.contains("<wikicat_Cities>") || line.contains("<wordnet_city_")){
					City newCity=getCityFromLine(line);
					citiesMap.put(newCity.getName(),newCity);
				}
				
					/* find all basketball teams */
				if(line.contains("<wordnet_basketball_team")){
					Team newTeam = getTeamFromLine(line);
					newTeam.setSportField(SportField.BASKETBALL);
					teamsMap.put(newTeam.getTeamName(), newTeam);
				}
					/* find all football teams */
				if(line.contains("<wordnet_football_team")){
					Team newTeam = getTeamFromLine(line);
					newTeam.setSportField(SportField.FOOTBALL);
					teamsMap.put(newTeam.getTeamName(), newTeam);
				}
				
				/* find all the sport players */
				if(line.contains("<wordnet_") && line.contains("_player_")){
					Player player= getPlayerFromLine(line);
					if (line.contains("_football_"))
						player.setSportField(SportField.FOOTBALL);
					if (line.contains("_basketball_"))
						player.setSportField(SportField.BASKETBALL);
					if (line.contains("_tennis_"))
						player.setSportField(SportField.TENNIS);
					playersMap.put(player.getName(), player);
				}
				
				/* find all the sport coaches */
				if(line.contains("<wordnet_") && line.contains("_coach_")){
					Coach coach= getCoachFromLine(line);
					if (line.contains("_football_"))
						coach.setSportField(SportField.FOOTBALL);
					if (line.contains("_basketball_"))
						coach.setSportField(SportField.BASKETBALL);
					if (line.contains("_tennis_"))
						coach.setSportField(SportField.TENNIS);
					coachesMap.put(coach.getName(), coach);
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
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String coach_name=getTag(line);
		return new Coach(yagoID,coach_name,0); //TODO:ID
	}

	private Player getPlayerFromLine(String line) {
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String player_name=getTag(line);
		return new Player(yagoID,player_name,0); //TODO:ID
	}

	protected Country getCountryFromLine(String line){
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		return new Country(yagoID,country_name,0);	//TODO:ID	
	}
	
	
	protected City getCityFromLine(String line){
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		return new City(yagoID,city_name,0);	//TODO:ID
		
	}
	
	protected Team getTeamFromLine(String line){
		String yagoID = getTag(line);
		line = line.substring(line.indexOf('>',0)+1);
		String team_name = getTag(line);
		return new Team(yagoID,team_name,0);
	}

}
