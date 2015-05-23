package parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import entities.Country;

public class Parser_Tester {

	public static void main(String[] args) {
		TransitiveType_Parser transitive_pars=new TransitiveType_Parser();
		Set<String> nameSet=(transitive_pars.getCountriesMap()).keySet();
		for(int i=0; i<100; i++){
			System.out.println(nameSet);
		}
		nameSet=transitive_pars.getPlayersMap().keySet();
		for(int i=0; i<100; i++){
			System.out.println(nameSet);
		}
		nameSet=transitive_pars.getCoachesMap().keySet();
		for(int i=0; i<100; i++){
			System.out.println(nameSet);
		}
		//Facts_Parser facts_pars= new Facts_Parser(transitive_pars.getCountriesMap(),
			//	transitive_pars.getCitiesMap(),	transitive_pars.getPlayersMap(), transitive_pars.getCoachesMap());
	}

}
