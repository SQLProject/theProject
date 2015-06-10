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
		
		if (date.charAt(5)=='#')
			this.month=0;
		else
			this.month=(date.charAt(5)-'0')*10+(date.charAt(6)-'0');
		
		if (date.charAt(8)=='#')
			this.month=0;
		else
			this.day=(date.charAt(8)-'0')*10+(date.charAt(9)-'0');
	}
	
	protected String getDate(){
		String stringDate=Integer.toString(this.year);
		
		if (this.month!=0 || this.day!=0) //we assume that month&day are both zero or both non-zero
			stringDate=Integer.toString(this.day)+'-'+Integer.toString(this.month)+'-'+stringDate;
		
		return stringDate;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getDay(){
		return this.day;
	}

}
