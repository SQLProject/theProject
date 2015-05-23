package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.CapitalCity;
import entities.City;
import entities.Country;

public class Facts_Parser extends abstract_parser{
	
	public Facts_Parser(HashMap<String, Country> countriesMap, HashMap<String,City> citiesSet ){
		this.countriesMap= countriesMap;
		this.citiesMap= citiesSet;
	}
	
	
	protected void parse_yago_facts(){
		//DEBUG
		System.out.println("**inSide parse_yago_facts function**");
		//DEBUG
		String yago_file_path = "D:\\yagoFacts.tsv";
		
		//DEBUG
		int countCapitalCities=0;
		int countLanguages=0;
		int countCityToCOuntry=0;
		//DEBUG
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoFacts = new File(yago_file_path);
		if (yago_file_path == null || !yagoFacts.exists()){
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
				
				if(line.contains("<isLocatedIn>")){
					addCityToCountry(line);
					//DEBUG
					countCityToCOuntry++;
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
		System.out.println("There are "+countCityToCOuntry+"languages"); 
		/*DEBUG*/	
	}

	
	
	
	

	protected void addCityToCountry(String line) {
		
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line).substring(line.indexOf('_', 0));
		
		/* find the country in the countries list and insert the language */
		Country country=countriesMap.get(country_name);
		if (country==null){
			System.out.println(" The country "+country_name+", for the city "+city_name+", has not been found ");
			country=new Country(yagoID, country_name, 0); //TODO:ID
		}
		
		City city=citiesMap.get(city_name);
		if (city==null){
			System.out.println(" The city "+city_name+" has not been found in the citiesMap");
			city=new City(yagoID,city_name,0); //TODO:ID
		}
		
		country.addCity(city);
		countriesMap.put(country_name, country);
			
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
		
		CapitalCity capitalCity;
		if (citiesMap.containsKey(capital_name)){
			capitalCity=(CapitalCity)citiesMap.get(capital_name);
		}else{
			capitalCity= new CapitalCity(yagoID,capital_name,0);	//TODO:ID
		}
		
		country.setCapitalCity(capitalCity);
		countriesMap.put(country_name, country);
	}
	
	

	
}
