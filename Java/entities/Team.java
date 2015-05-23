package entities;


public class Team {
	
	String yagoID;
	String name;
	String teamField;
	int id;
	SportField sportField;
	
	public Team (String yagoID, String name, int id){
		this.yagoID = yagoID;
		this.name=name;
		this.id=id;
	}
	
	public void setSportField(SportField sportField)
	{
		this.sportField=sportField;
	}
	
	public String getTeamName()
	{
		return this.name;
	}
	

}
