package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import entities.City;
import entities.Coach;
import entities.Country;
import entities.Date;
import entities.Event;
import entities.Person;
import entities.Player;
import entities.SportField;
import entities.Team;

public class DateFactsParser extends abstract_parser{
	
	public DateFactsParser(HashMap<String,Player> playersMap,HashMap<String,Coach> coachesMap
			,HashMap<String,Event> eventsMap){
		this.playersMap= new HashMap<String,Player>();
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
		
		Person person;
		Date birthDate=new Date(date);
		
		/*find the person- player or coach and add his current or birth place*/
		if (playersMap.containsKey(person_name)){
			person=playersMap.get(person_name);
			person.setBirthDate(birthDate);
			playersMap.put(person_name, (Player)person);
		}
		if (coachesMap.containsKey(person_name)){
			person=coachesMap.get(person_name);
			person.setBirthDate(birthDate);
			coachesMap.put(person_name, (Coach)person);
		}
	}
		
	
}
