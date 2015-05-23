package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.City;
import entities.Country;



public class TransitiveType_Parser extends abstract_parser{
		
	public TransitiveType_Parser(){
		this.countriesMap= new HashMap<String,Country>();
		this.citiesMap= new HashMap<String,City>();
		parse_transitive_type();
	}
	
	protected void parse_transitive_type(){
		//DEBUG
		//System.out.println("**inSide parse_transitive_type function**");
		//DEBUG
		String yago_file_path = "D:\\yadodata\\yagoTransitiveType.tsv";
		
		//DEBUG		
		//int countCountry=0;
		//int countCity=0;
		//DEBUG
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoTansetiveTypes = new File(yago_file_path);
		if (yago_file_path == null || !yagoTansetiveTypes.exists()){
			System.out.println("Can't Open yagoTransitiveType File");
			return;
		}
		
		//DEBUG
		//System.out.println("Opened the Yago File");
		//DEBUG
		BufferedReader br = null;
		String line;
		
		try {
			FileReader fr = new FileReader(yagoTansetiveTypes);
			br = new BufferedReader(fr);

			while((line= br.readLine())!= null){
				
				/* find all the countries with the proper tag */
				if(line.contains("<wikicat_Countries>")){
					Country newCountry=getCountryFromLine(line);
					countriesMap.put(newCountry.getName(), newCountry);
					//DEBUG
					//countCountry++;
					//DEBUG
				}
				
				/* find all the cities with the proper tag */
				if(line.contains("<wikicat_Cities>") || line.contains("<wordnet_city_")){
					City newCity=getCityFromLine(line);
					citiesMap.put(newCity.getName(),newCity);
					//DEBUG
					//countCity++;
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
		//DEBUG
		//System.out.println("There are "+countCountry+" countries");
		//System.out.println("There are "+countCity+" cities");
		//DEBUG
	}
	
	
	protected Country getCountryFromLine(String line){
		//DEBUG
		//System.out.println(line);
		//DEBUG
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String country_name=getTag(line);
		//DEBUG
		//System.out.println("The country yagoID is: "+yagoID+"\t The country name is: "+country_name);
		//DEBUG
		return new Country(yagoID,country_name,0);	//TODO:ID	
	}
	
	
	protected City getCityFromLine(String line){
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String city_name=getTag(line);
		//DEBUG
		//System.out.println("The city yagoID is:"+yagoID+"\t The city name is:"+city_name);
		//DEBUG
		return new City(yagoID,city_name,0);	//TODO:ID
		
	}

}
