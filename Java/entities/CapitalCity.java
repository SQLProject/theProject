package entities;

public class CapitalCity extends City{

	public CapitalCity(String yagoID, String city_name, int id) {
		super(yagoID, city_name,id);
	}
	
	public CapitalCity(String yagoID, String city_name, int id, Country country) {
		super(yagoID, city_name,id);
		this.country=country;
	}
	
	
}
