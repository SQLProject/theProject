package entities;

public class City extends Location{
	Country country=null;
	Boolean isCapital=false;
	
	
	public City (String yagoID, String city_name,int id){
		this.yagoID=yagoID;
		this.name=city_name;
		this.id=id;
	}
	
	protected void setCountry(Country country){
		this.country=country;
	}
	
	protected Country getCountry(){
		return this.country;
	}
	
	public void setCapital(){
		this.isCapital=true;
	}

}
