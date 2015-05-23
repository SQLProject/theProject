package entities;

public abstract class Person {
	
	String yagoID;
	String name;
	int id;
	Date birthDate;
	City birthCity;
	
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
	

}


