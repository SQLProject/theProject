package entities;

public abstract class Person {
	
	String yagoID;
	String name;
	int id;
	Date birthDate;
	City birthCity;
	City currentCity;
	SportField sportfield;
	
	
	public Person (String yagoID, String name, int id){
		this.yagoID=yagoID;
		this.name=name;
		this.id=id;
	}
	
	protected void setBirthDate(String date){
		this.birthDate=new Date(date);
	}
	
	protected void setBirthPlace(City birthCity){
		this.birthCity=birthCity;
	}
	
	protected Date getBirthDate(){
		return this.birthDate;
	}
	
	protected City getBirthPlace(){
		return this.birthCity;
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

}


