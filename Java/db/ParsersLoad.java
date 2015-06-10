package db;

import parser.Facts_Parser;
import parser.TransitiveType_Parser;

/**
 * Created by Kobi on 24/05/2015.
 */
public class ParsersLoad {
    public TransitiveType_Parser getTransitiveTypeParser() {
        return transitiveTypeParser;
    }

    TransitiveType_Parser transitiveTypeParser;

    public Facts_Parser getFactsParser() {
        return factsParser;
    }

    Facts_Parser factsParser;

    public void loadParsers(){
        transitiveTypeParser =new TransitiveType_Parser();
        factsParser=new Facts_Parser(transitiveTypeParser.getCountriesMap(),transitiveTypeParser.getCitiesMap(),
                transitiveTypeParser.getFootballPlayersMap(),transitiveTypeParser.getCoachesMap(),transitiveTypeParser.getTeamsMap(),
                transitiveTypeParser.getAwardsMap(),transitiveTypeParser.getEventsMap(),transitiveTypeParser.getStadiumsMap());
    }


}
