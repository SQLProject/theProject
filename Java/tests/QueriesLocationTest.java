package tests;

import db.*;

import java.sql.SQLException;
import java.util.Collection;


public class QueriesLocationTest {


    public static void main(String args[]) throws SQLException {
            System.out.printf("hey");

            ParsersLoad parsersLoad=new ParsersLoad();
            parsersLoad.loadParsers();


            PrintStat.printAllStat(parsersLoad);

            QueriesPerson personLoader=new QueriesPerson();
            QueriesFootballPlayer footballPlayerLoader=new QueriesFootballPlayer();
            QueriesCoach coachLoader=new QueriesCoach();
//            QueriesAward awardLoader=new QueriesAward();
//            QueriesLocation locationLoader=new QueriesLocation();
//            QueriesCountry countryLoader=new QueriesCountry();
//            QueriesCity cityLoader=new QueriesCity();
//            QueriesPerson personLoader=new QueriesPerson();
            QueriesEvent eventLoader=new QueriesEvent();
//            QueriesStadium stadiumLoader=new QueriesStadium();



            personLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getPersonMap().values());
            coachLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getCoachesMap().values());
            footballPlayerLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getFootballPlayersMap().values());

//            awardLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getAwardsMap().values());
            //locationLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getLocationMap().values());
            //countryLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getCountriesMap().values());
            //cityLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getCitiesMap().values());
            //personLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getPersonMap().values());
            eventLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getEventsMap().values());

            //stadiumLoader.load_batch((Collection<?>) parsersLoad.getTransitiveTypeParser().getStadiumsMap().values());
            //MapStringToInt.writeLocationToPropertiesFiles();

    }
}
