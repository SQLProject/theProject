package tests;

import db.ParsersLoad;
import entities.*;

/**
 * Created by Kobi on 09/06/2015.
 */
public class PrintStat {

    public static void printAllStat(ParsersLoad parsersLoad)
    {
        printEventStat(parsersLoad);
        printPersonStat(parsersLoad);
        printTeamStat(parsersLoad);
        printStadiumStat(parsersLoad);
        printCitiesStat(parsersLoad);
    }

    public static void printTeamStat(ParsersLoad parsersLoad){
        int stadium=0;
        int city=0;
        int total=0;
        for(Team team:parsersLoad.getTransitiveTypeParser().getTeamsMap().values()){
            total++;
            if (team.getCity()!=null){city++;}
            if(team.getStadium()!=null){stadium++;}
        }
        System.out.println("Team Stat:");
        System.out.println("Stadium="+ stadium +" City="+city +" Total="+total);
    }

    public static void printStadiumStat(ParsersLoad parsersLoad){
        int idCityOfstadium=0;
        int total=0;
        for (Stadium stadium:parsersLoad.getTransitiveTypeParser().getStadiumsMap().values()){
            total++;
            if (stadium.getCity()!=null){idCityOfstadium++;}
        }
        System.out.println("Stadium Stat:");
        System.out.println("IdCityOfStadium="+ idCityOfstadium +" Total="+total);
    }

    public static void printEventStat(ParsersLoad parsersLoad){
        int locationOfEvent=0;
        int sportfield=0;
        int total=0;
        for (Event event:parsersLoad.getTransitiveTypeParser().getEventsMap().values()){
            total++;
            if(event.getLocation()!=null){locationOfEvent++;}
            if(event.getSportField()!=null){sportfield++;}
        }
        System.out.println("Event Stat:");
        System.out.println("LocationOfEvent="+locationOfEvent +" SportField="+sportfield +" Total="+total);
    }

    public static void printPersonStat(ParsersLoad parsersLoad){
        int birthYear=0;
        int birthCity=0;
        int currentCity=0;
        int sportField=0;
        int total=0;
        for (Person person:parsersLoad.getTransitiveTypeParser().getPersonMap().values()){
            total++;
            if (person.getBirthDate()!=null){birthYear++;}
            if (person.getCurrentCity()!=null){currentCity++;}
            if (person.getBirthPlace()!=null) {birthCity++;}
            if (person.getSportfield()!=null) {sportField++;}
        }
        System.out.println("Person Stat:");
        System.out.println("BirthYear="+birthYear + " BirthCity="+birthCity+" CurrentCity="+currentCity+ " SportField="+ sportField +" Total="+total);
    }

    public static void printCitiesStat(ParsersLoad parsersLoad){

        int count=0;
        int total=0;
        for (City city:parsersLoad.getTransitiveTypeParser().getCitiesMap().values()){
            total++;
            if (city.getCountry()!=null) {count++;}
        }
        System.out.println("City Stat:");
        System.out.println("Number Of Cities with Country="+count +" Total="+total);
    }
}
