package parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

import entities.*;


public class TransitiveType_Parser {
	
	
	public static void main(String args[]){
		String yago_file_path = "D:\\yagodata\\yagoTransitiveType.tsv";
		ArrayList<Country> CountriesList=parse_Country(yago_file_path);
		for (Country country: CountriesList){
			country.setCities(parse_Cities(yago_file_path,country.getName()));
		}
		
		
	}
	
	
	public static ArrayList<Country> parse_Country(String yago_file_path) {
		
		ArrayList<Country> CountriesList= new ArrayList<Country>();
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoTansetiveTypes = new File(yago_file_path);
		if (yago_file_path == null || !yagoTansetiveTypes.exists()){
			System.out.println("Can't Open yagoTransitiveType File");
			return CountriesList;
		}
		
		
		BufferedReader br = null;
		String line;
		
		try {
			FileReader fr = new FileReader(yagoTansetiveTypes);
			br = new BufferedReader(fr);

			while((line= br.readLine())!= null){
				if(line.contains("<wikicat_Countries>")){
					CountriesList.add(getCountryNameFromLine(line));
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
		
		return CountriesList;
	}
		
	
	public static Country getCountryNameFromLine(String line){
		
		/* we'll save the yagoId, this is the first tag in the line */
		String yagoID= line.substring((line.indexOf('<',0)+1), (line.indexOf('>',0)+1));
		line=line.substring(line.indexOf('>',0)+1);
		
		/* we'll save the country name, this is the second tag in the line */
		String country_name=line.substring(line.indexOf('<',0), line.indexOf('>',0));
		return new Country(yagoID,country_name,0);		
	}
	
	public static ArrayList<City> parse_Cities(String yago_file_path, String country){
		
		ArrayList<City> CitiesList= new ArrayList<City>();
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoTansetiveTypes = new File(yago_file_path);
		if (yago_file_path == null || !yagoTansetiveTypes.exists()){
			System.out.println("Can't Open yagoTransitiveType File");
			return CitiesList;
		}
		
		
		BufferedReader br = null;
		String line;
		
		try {
			FileReader fr = new FileReader(yagoTansetiveTypes);
			br = new BufferedReader(fr);

			while((line= br.readLine())!= null){
				if(line.contains("<wikicat_Cities_in_"+country+">")){
					CitiesList.add(getCityNameFromLine(line));
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
		
		return CitiesList;
	}
	
	
	public static City getCityNameFromLine(String line){
		/* we'll save the yagoId, this is the first tag in the line */
		String yagoID= line.substring((line.indexOf('<',0)+1), (line.indexOf('>',0)+1));
		line=line.substring(line.indexOf('>',0)+1);
		
		/* we'll save the city name, this is the second tag in the line */
		String city_name=line.substring(line.indexOf('<',0), line.indexOf('>',0));
		//TODO
		//return new City(yagoID,city_name,0);		
		return null;
	}

}
