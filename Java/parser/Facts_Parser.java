package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.Award;
import entities.City;
import entities.Coach;
import entities.Country;
import entities.Person;
import entities.FootballPlayer;
import entities.Team;
import entities.Event;
import entities.Stadium;

public class Facts_Parser extends abstract_parser{
	
	public Facts_Parser(HashMap<String, Country> countriesMap, HashMap<String,City> citiesSet, 
			HashMap<String, FootballPlayer> playersMap, HashMap<String,Coach> coachesMap, HashMap<String,Team> teamsMap,
			HashMap<String,Award> awardsMap, HashMap<String,Event> eventsMap, HashMap<String,Stadium> stadiumsMap){
		this.countriesMap= countriesMap;
		this.citiesMap= citiesSet;
		this.coachesMap=coachesMap;
		this.footballPlayersMap =playersMap;
		this.teamsMap=teamsMap;
		this.awardsMap=awardsMap;
		this.eventsMap=eventsMap;
		this.stadiumsMap=stadiumsMap;
		parse_yago_facts();
	}
	
	
	protected void parse_yago_facts(){

		String yagoFacts_file_path = config.get_yago_facts_path();
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoFacts = new File(yagoFacts_file_path);
		if (yagoFacts_file_path == null || !yagoFacts.exists()){
			System.out.println("Can't Open yagoFacts File");
			return;
		}
		
		//DEBUG
		System.out.println("Opened the Yago File");
		//DEBUG
		BufferedReader br = null;
		String line;
		
		try {
			FileReader fr = new FileReader(yagoFacts);
			br = new BufferedReader(fr);
	
			while((line= br.readLine())!= null){
				
				/* find all the cities in a country, and connect them */
				if(line.contains("<isLocatedIn>")){
					addCityToCountry(line);
				}
				
				/* find all the capital cities with the proper tag */
				if(line.contains("<hasCapital>")){
					addCapitaltoCountry(line);
				}				
				
				/* find the language for a country */
				if(line.contains("<hasOfficialLanguage>")){
					addLanguagestoCountry(line);
				}
				
				/* find the city the player lives in */
				if(line.contains("<livesIn>")){
					addCurrentCityToPerson(line);
				}
				
				/* find the city the player was born in */
				if(line.contains("<wasBornIn>")){
					addBirthPlaceToPerson(line);
				}
				
				/* find all the teams the player played for */
				if(line.contains("<playsFor>"))
				{
					addPlaysFor(line);
				}
				
				/* find all the teams the coach isAffiliatedTo */
				if(line.contains("<isAffiliatedTo>"))
				{
					addAffiliatedTo(line);
				}
				
				/* find all the awards the person won */
				if(line.contains("<hasWonPrize>"))
				{
					addAwardTo(line);
				}
				
				/* find location for teams, events and stadiums */
				if(line.contains("<isLocatedIn>"))
				{
					addTeamLocation(line);
					addEventLocation(line);
					addStadiumLocation(line);
				}
				
				/* find the stadium of a team */
				if(line.contains("<owns>"))
				{
					addTeamStadium(line);
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
	
	
	private void addBirthPlaceToPerson(String line) {
		addPlaceToPerson(line, true);
	}

	private void addCurrentCityToPerson(String line) {
		addPlaceToPerson(line, false);
	}

	protected void addPlaceToPerson(String line, boolean birthFlag){
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String person_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		
		person_name=isValidEnt(person_name);
		if (person_name==null) return;
		city_name=isValidEnt(city_name);
		if (city_name==null) return;
		
		Person person;
		City cityToAdd=citiesMap.get(city_name);
		if (cityToAdd==null)
			cityToAdd=new City(yagoID, city_name, 0);	//TODO:ID
		
		/*find the person- player or coach and add his current or birth place*/
		if (footballPlayersMap.containsKey(person_name)){
			person= footballPlayersMap.get(person_name);
			if (birthFlag)
				person.setBirthPlace(cityToAdd);
			else
				person.setCurrentPlace(cityToAdd);
			footballPlayersMap.put(person_name, (FootballPlayer)person);
		}
		if (coachesMap.containsKey(person_name)){
			person=coachesMap.get(person_name);
			if (birthFlag)
				person.setBirthPlace(cityToAdd);
			else
				person.setCurrentPlace(cityToAdd);
			coachesMap.put(person_name, (Coach)person);
		}
	}
	
 	protected void addLanguagestoCountry(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		//String language=getTag(line).substring(line.indexOf('_', 0));
		String language=getTag(line);
		/* find the country in the countries list and insert the language */
		country_name=isValidEnt(country_name);
		if (country_name==null) return;
		language=isValidEnt(language);
		if (language==null) return;
		
		
		Country country=countriesMap.get(country_name);
		if (country==null){
			//System.out.println(" The country "+country_name+", for the language "+language+", has not been found ");
			country=new Country(yagoID, country_name, 0); //TODO:ID
		}
		
		country.addLanguage(language);
		//countriesMap.put(country_name, country);
	}

	protected void addCapitaltoCountry(String line) {
		
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String capital_name=getTag(line);
		
		country_name=isValidEnt(country_name);
		if (country_name==null) return;
		capital_name=isValidEnt(capital_name);
		if (capital_name==null) return;
		
		/* find the country in the countries list and insert the capital city */
		Country country=countriesMap.get(country_name);
		if (country==null){
			//System.out.println(" The country "+country_name+", for the capital city "+capital_name+", has not been found ");
			country=new Country(yagoID, country_name, 0); //TODO:ID
		}
		
		City capitalCity;
		if (citiesMap.containsKey(capital_name)){
			capitalCity=citiesMap.get(capital_name);
		}else{
			capitalCity= new City(yagoID,capital_name,0);	//TODO:ID
		}
		
		capitalCity.setCapital();
		capitalCity.setCountry(country);
	}
	
	private void addPlaysFor(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String player_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String team_name=getTag(line);
		
		player_name=isValidEnt(player_name);
		if (player_name==null) return;
		team_name=isValidEnt(team_name);
		if (team_name==null) return;
		
		
		/* check if the player and the team are valid */
		if(footballPlayersMap.containsKey(player_name) && teamsMap.containsKey(team_name))
		{
			footballPlayersMap.get(player_name).setTeams(team_name);
		}
	}
		
	private void addAffiliatedTo(String line) 
	{
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String coach_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String team_name=getTag(line);
		
		coach_name=isValidEnt(coach_name);
		if (coach_name==null) return;
		team_name=isValidEnt(team_name);
		if (team_name==null) return;
		
		/* check if the player and the team are valid */
		if(coachesMap.containsKey(coach_name) && teamsMap.containsKey(team_name))
		{
			coachesMap.get(coach_name).setTeams(team_name);
		}
	}
	
	private void addAwardTo(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String person_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String award_name=getTag(line);
		
		person_name=isValidEnt(person_name);
		if (person_name==null) return;
		award_name=isValidEnt(award_name);
		if (award_name==null) return;
		
		/* check if the player is valid */
		if(footballPlayersMap.containsKey(person_name))
		{
			footballPlayersMap.get(person_name).setAwards(award_name);
			/* add new award if needed */
			if(!(awardsMap.containsKey(award_name)))
			{
				Award newAward = new Award(award_name, 0);
				awardsMap.put(award_name, newAward);
			}
		}
		/* check if the coach is valid */
		if(coachesMap.containsKey(person_name))
		{
			coachesMap.get(person_name).setAwards(award_name);
			/* add new award if needed */
			if(!(awardsMap.containsKey(award_name)))
			{
				Award newAward = new Award(award_name, 0);
				awardsMap.put(award_name, newAward);
			}
		}
	}
	
	private void addTeamLocation(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String team_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		
		team_name=isValidEnt(team_name);
		if (team_name==null) return;
		city_name=isValidEnt(city_name);
		if (city_name==null) return;
		
		
		if(teamsMap.containsKey(team_name) && citiesMap.containsKey(city_name))
		{
			teamsMap.get(team_name).setCity(citiesMap.get(city_name));
		}	
	}
	
	private void addEventLocation(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String event_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String location_name=getTag(line);
		
		event_name=isValidEnt(event_name);
		if (event_name==null) return;
		location_name=isValidEnt(location_name);
		if (location_name==null) return;
		
		if(eventsMap.containsKey(event_name))
		{
			if(citiesMap.containsKey(location_name))
			{
				eventsMap.get(event_name).setLocation(citiesMap.get(location_name));
			}
			else if(countriesMap.containsKey(location_name))
			{
				eventsMap.get(event_name).setLocation(countriesMap.get(location_name));
			}
		}
		
	}
	
	private void addStadiumLocation(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String stadium_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		
		stadium_name=isValidEnt(stadium_name);
		if (stadium_name==null) return;
		city_name=isValidEnt(city_name);
		if (city_name==null) return;
		
		if(stadiumsMap.containsKey(stadium_name) && citiesMap.containsKey(city_name))
		{
			stadiumsMap.get(stadium_name).setCity(citiesMap.get(city_name));
		}	
	}
	
	private void addTeamStadium(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String team_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String stadium_name=getTag(line);
		
		team_name=isValidEnt(team_name);
		if (team_name==null) return;
		stadium_name=isValidEnt(stadium_name);
		if (stadium_name==null) return;
		
		if(teamsMap.containsKey(team_name) && stadiumsMap.containsKey(stadium_name))
		{
			teamsMap.get(team_name).setStadium(stadiumsMap.get(stadium_name));
		}	
	}
	
	protected void addCityToCountry(String line) {

		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		//String country_name=getTag(line).substring(line.indexOf('_', 0));
		String country_name=getTag(line);
		
		city_name=isValidEnt(city_name);
		if (city_name==null) return;
		country_name=isValidEnt(country_name);
		if (country_name==null) return;
		

		/* find the country in the countries list and insert the language */
		Country country=countriesMap.get(country_name);
		if (country==null){
			return;
		}

		City city=citiesMap.get(city_name);
		if (city==null){
			return;
		}

		country.addCity(city);
		//countriesMap.put(country_name, country);
		city.setCountry(country);
		citiesMap.put(city_name,city);
		}		
	
	}
