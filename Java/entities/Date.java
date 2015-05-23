package entities;

public class Date {
	
	int year;
	int month;
	int day;
	
	public Date(int year, int month, int day){
		this.year=year;
		this.month=month;
		this.day=day;
	}
	
	public Date(String date){ //YYYY-MM-DD
		this.year=(date.charAt(0)-'0')*1000+(date.charAt(1)-'0')*100+(date.charAt(2)-'0')*10+(date.charAt(3)-'0');
		this.month=(date.charAt(5)-'0')*10+(date.charAt(6)-'0');
		this.day=(date.charAt(8)-'0')*10+(date.charAt(9)-'0');
	}
	
	protected String getDate(){
		return Integer.toString(this.year)+'-'+Integer.toString(this.month)+'-'+Integer.toString(this.day);
	}
	
	protected int getYear(){
		return this.year;
	}
	
	protected int getMonth(){
		return this.month;
	}
	
	protected int getDay(){
		return this.day;
	}

}
