package entities;

public class Award {
	public String getName() {
		return name;
	}

	String name;

	public String getYagoId() {
		return yagoId;
	}

	String yagoId;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int id;
	
	public Award(String name, int id)
	{
		this.name=name;
	}

}
