package entities;

import java.util.HashSet;
import java.util.Set;

public abstract class Person {

	public String getYagoID() {
		return yagoID;
	}

	String yagoID;
	String name;

	public void setId(int id) {
		this.id = id;
	}

	int id;
	Date birthDate;
	City birthCity;

	public City getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(City currentCity) {
		this.currentCity = currentCity;
	}

	City currentCity;

	public SportField getSportfield() {
		return sportfield;
	}

	SportField sportfield;
	Set<String> teams; 
	Set<String> awards;
	
	
	public Person (String yagoID, String name, int id){
		this.yagoID=yagoID;
		this.name=name;
		this.id=id;
		this.teams = new HashSet<String>();
		this.awards = new HashSet<String>();
	}
	
	public void setBirthDate(String date){
		this.birthDate=new Date(date);
	}
	
	public void setBirthPlace(City birthCity){
		this.birthCity=birthCity;
	}
	
	public void setCurrentPlace(City currentCity){
		this.currentCity=currentCity;
	}
	
	public Date getBirthDate(){
		return this.birthDate;
	}
	
	public City getBirthPlace(){
		return this.birthCity;
	}
	
	protected City getCurrentPlace(){
		return this.currentCity;
	}
	
	public String getName(){
		return this.name;
	}
	
	protected SportField getSportField(){
		return this.sportfield;
	}
	
	public void setSportField(SportField sportfield){
		this.sportfield=sportfield;
	}
	
	public void setTeams(String team)
	{
		this.teams.add(team);
	}
	
	public void setAwards(String award)
	{
		this.awards.add(award);
	}
	
	public void setBirthDate(Date birthDate){
		this.birthDate=birthDate;
	}
	

}


