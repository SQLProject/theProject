package entities;

import java.util.Set;

public abstract class Person {
	
	String yagoID;
	String name;
	int id;
	Date birthDate;
	City birthCity;
	City currentCity;
	SportField sportfield;
	Set<String> teams; 
	Set<String> awards;
	
	
	public Person (String yagoID, String name, int id){
		this.yagoID=yagoID;
		this.name=name;
		this.id=id;
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


