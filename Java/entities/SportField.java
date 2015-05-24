package entities;

public enum SportField {
	
	FOOTBALL("Football"),
	BASKETBALL("Basketball"),
	TENNIS("Tennis");
	
	private String sportKind;

    private SportField(String type) {
        this.sportKind = type;
    }

    public String getKind() {
        return sportKind;
    }
	
}
