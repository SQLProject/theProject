package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.City;
import entities.Coach;
import entities.Country;
import entities.Player;

public class Facts_Parser extends abstract_parser{
	
	public Facts_Parser(HashMap<String, Country> countriesMap, HashMap<String,City> citiesSet, 
			HashMap<String,Player> playersMap, HashMap<String,Coach> coachesMap ){
		this.countriesMap= countriesMap;
		this.citiesMap= citiesSet;
		this.coachesMap=coachesMap;
		this.playersMap=playersMap;
		parse_yago_facts();
	}
	
	
	protected void parse_yago_facts(){
		//DEBUG
		System.out.println("**inSide parse_yago_facts function**");
		//DEBUG
		String yagoFacts_file_path = "D:\\yagodata\\yagoFacts.tsv";
		
		//DEBUG
		int countCapitalCities=0;
		int countLanguages=0;
		//DEBUG
		
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
				
				/* find all the capital cities with the proper tag */
				if(line.contains("<hasCapital>")){
					addCapitaltoCountry(line);
					//DEBUG
					countCapitalCities++;
					//DEBUG
				}				
				
				/* find the language for a country */
				if(line.contains("<hasOfficialLanguage>")){
					addLanguagestoCountry(line);
					//DEBUG
					countLanguages++;
					//DEBUG
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
		/*DEBUG*/
		System.out.println("There are "+countCapitalCities+" capital cities");
		System.out.println("There are "+countLanguages+"languages"); 
		/*DEBUG*/	
	}



	protected void addLanguagestoCountry(String line) {
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String language=getTag(line).substring(line.indexOf('_', 0));
		
		/* find the country in the countries list and insert the language */
		Country country=countriesMap.get(country_name);
		if (country==null){
			System.out.println(" The country "+country_name+", for the language "+language+", has not been found ");
			country=new Country(yagoID, country_name, 0); //TODO:ID
		}
		
		country.addLanguage(language);
		countriesMap.put(country_name, country);
	}


	protected void addCapitaltoCountry(String line) {
		
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String capital_name=getTag(line);
		
		/* find the country in the countries list and insert the capital city */
		Country country=countriesMap.get(country_name);
		if (country==null){
			System.out.println(" The country "+country_name+", for the capital city "+capital_name+", has not been found ");
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
	
	

	
}
