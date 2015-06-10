package parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import entities.Country;

public class Parser_Tester {

	public static void main(String[] args) {
		TransitiveType_Parser transitive_pars=new TransitiveType_Parser();
		Set<String> nameSet=(transitive_pars.getCountriesMap()).keySet();

		//Facts_Parser facts_pars= new Facts_Parser(transitive_pars.getCountriesMap(),
			//	transitive_pars.getCitiesMap(),	transitive_pars.getFootballPlayersMap(), transitive_pars.getCoachesMap());

		/*test for teams*/
		nameSet=(transitive_pars.getTeamsMap()).keySet();
		for (String team:nameSet)
		{
			System.out.println(team);
		}
	}

}
