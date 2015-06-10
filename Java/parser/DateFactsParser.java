package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.Coach;
import entities.Date;
import entities.Event;
import entities.Person;
import entities.FootballPlayer;

public class DateFactsParser extends abstract_parser{
	
	public DateFactsParser(HashMap<String, FootballPlayer> playersMap,HashMap<String,Coach> coachesMap
			,HashMap<String,Event> eventsMap){
		this.footballPlayersMap = new HashMap<String, FootballPlayer>();
		this.coachesMap=new HashMap<String,Coach>();
		this.eventsMap= new HashMap<String,Event>();
	}
	
	protected void parse_dateFacts(){
		String yagoDateFacts_file_path = config.get_yago_date_facts_path();
		
		/*try to open the yagoTansetiveTypes file*/
		File yagoDateFacts = new File(yagoDateFacts_file_path);
		if (yagoDateFacts_file_path == null || !yagoDateFacts.exists()){
			System.out.println("Can't Open yagoDateFacts File");
			return;
		}

		
		BufferedReader br = null;
		String line;
		
		try {
			FileReader fr = new FileReader(yagoDateFacts);
			br = new BufferedReader(fr);

			while((line= br.readLine())!= null){
				
				/* find all the birth dates for players or coaches */
				if(line.contains("<wasBornOnDate>")){
					addBirthDateToPerson(line);
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
	
	protected void addBirthDateToPerson(String line){
	
		/* get the the parsed info from the line */
		String yagoID=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		String person_name=getTag(line);
		line=line.substring(line.indexOf('>',0)+1);
		line=line.substring(line.indexOf('>',0)+1);
		String date=getTag(line).substring((line.indexOf('"',0)+1), line.indexOf('"',1)+1);
		
		person_name=isValidEnt(person_name);
		if(person_name==null) return;
		date=isValidEnt(date);
		if(date==null) return;
		
		Person person;
		Date birthDate=new Date(date);
		
		/*find the person- player or coach and add his current or birth place*/
		if (footballPlayersMap.containsKey(person_name)){
			person= footballPlayersMap.get(person_name);
			person.setBirthDate(birthDate);
			footballPlayersMap.put(person_name, (FootballPlayer)person);
		}
		if (coachesMap.containsKey(person_name)){
			person=coachesMap.get(person_name);
			person.setBirthDate(birthDate);
			coachesMap.put(person_name, (Coach)person);
		}
	}
		
	
}
