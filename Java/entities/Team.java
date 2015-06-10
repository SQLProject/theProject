package entities;


public class Team {

	public String getYagoID() {
		return yagoID;
	}

	String yagoID;

	public String getName() {
		return name;
	}

	String name;
	String teamField;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int id;
	SportField sportField;
	City city=null;
	Stadium stadium=null;
	
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
	
	public void setCity(City city)
	{
		this.city=city;
	}
	
	public City getCity()
	{
		return this.city;
	}
	
	public void setStadium(Stadium stadium)
	{
		this.stadium=stadium;
	}
	
	public Stadium getStadium()
	{
		return this.stadium;
	}

}
