// Da Lu
//520.226
//9/11/2012
//HW 1
//3475130680
//dlu6
//dlu6@jhu.edu



import java.util.StringTokenizer;
import java.text.DecimalFormat;

public class TimeMil implements Time{

	private int hour = 0;
	private int minute = 0; 
	
	// when time is a string
	public TimeMil(String t)
	{
		int a = 0;
		char when = 'a';
		t = t.toLowerCase();
		int index = t.indexOf('a');
		int index1 = t.indexOf('p');
		
		// if there is neither am nor pm
		if (index1 < 0  && index < 0)
		{ 
			hour = Integer.parseInt(t.substring(0,2));
			minute = Integer.parseInt(t.substring(2,4));
		}
		else 
		{
			//check to see if it is am or pm integer a is used to see, if it is pm , add 12 to hour
			if (index < 0)
			{	index = t.indexOf('p');
				when = 'p';
				a = 1;
				}
			t = t.substring(0,index);
			StringTokenizer tok = new StringTokenizer(t, ": ");
			hour = Integer.parseInt(tok.nextToken());
			
			if (hour == 12)
			{
			hour = 0;
			}
			
			if (a==1){
			
			// since 12pm is noon, hour will still be 12 for military time
				if (hour == 12)
				{
				hour = 12;
				}
				else 
				{
					hour = hour + 12;
				}
			}
			
			minute = Integer.parseInt(tok.nextToken());
		}
		// check if it is a valid time, if not set it to zero.
		if (hour >=24 || hour < 0)
		{
			hour = 0;
		}
		if ( minute >=60 || minute < 0){
			minute = 0;
		}
		
		
	}
	// this is for time that has two integers and a character
	public TimeMil(int h1, int t1, char m)
	{
		if ( m == 'p'){
			hour = h1+ 12;
			minute = t1;
		}
		else
		{
			hour = h1;
			minute = t1;
		}
		if (hour >=24 || hour < 0)
		{
			hour = 0;
		}
		if ( minute >=60 || minute < 0){
			minute = 0;
		}
		
	}
	// this is for when the time is an integer
	public TimeMil(int i){
		hour = i/100;
		minute = i%100;
		if (hour >=24 || hour < 0)
		{
			hour = 0;
		}
		if ( minute >=60 || minute < 0){
			minute = 0;
		}
	}
	
	
	
	//convert the time to something readable
	
	public String toString()
	{
		int k = 0;
		String m = minute +"";
		if (minute ==0){
			m = "00";
		}
		if (hour == 0){
			return (hour+12)+":"+ m + "am";
		}
			
		if (hour == 12){
			return hour + ":" + m + "pm";
		}
		if (hour >12){
			k = 1;
		}
		
		if (k == 1){
			 
			return (hour-12) + ":" + m + "pm";
		
		}
		
		else
		
			return (hour) + ":" + m + "am";
		
	}
	

	// add hours to the current time and return the number of extra days
	
	
	public int addHours(int h){
		int extra = 0;
		if ( h>=24) {
			extra = h/24;
			hour = h-24*extra+hour;
			return extra;
		}
		else{
			hour = hour + h;
		if (hour >=24){
			hour = hour-24;
			
		return 1;
		}
		
			return 0;
		}
	}
	
	// add minutes to the current time and return te number of extra days if there is any
	public int addMinutes(int m){
		int extra = 0;
		if ((minute + m)>=60)
		{
			int extrahours = (m+minute)/60;
			minute = (m+minute)%60;
			hour = hour+extrahours;
			if (hour >= 24){
				extra = hour/24;
				hour = hour - 24*extra;
				return extra;
			}
			
			return 0;
		}
		minute = minute + m;
		return 0;
	}
	// get the hour
	public int getHour(){
		if (hour > 12){
			return hour-12;
		}
		if (hour == 0){
			return 12;
		}
		return hour;
	}
	//get the minute
	public int getMinutes(){
		return minute;
	}
	
	//check if it is afternoon
	public boolean isAfternoon(){
		if (hour>12)
			return true;
		else
			return false;
	}
	// check if it is morning
	 public boolean isMorning(){
		 if (hour<=12)
			 return true;
		 else
			 return false;
	 }
	 // return the time in military format(integer)
	 public int military(){
		 
		 return hour*100 + minute;
	 
	 }
	 // return the time in military format as a string
	 public String militaryString(){
		 
		 String m = minute +"";
		 if (minute == 0)
		 {
			  m = minute + "0";
		 }
		 if (hour<10){
			  return "0" + hour + m;
		 }
		 return hour + "" + m;
	
	 }
	 // find the minutes since midnight
	 public int minsSinceMidnight(){
		 if (hour == 0 && minute ==0){
			 return 0;
		 }
		 return hour*60 + minute;
	 }
	 // find the duration of the time
	 public int until(Time t){
		 if (t.isMorning() == isMorning())
		 {
			 if (t.getHour() > getHour())
			 {
				 return (t.getHour() - getHour())*60 + t.getMinutes()-getMinutes();
			 }
			 
			 if (t.getHour() == getHour()){
				 return 0;
			 }
			
			 if (t.getHour() < getHour()){
				
				 return (t.getHour() - getHour())*60 + t.getMinutes()-getMinutes() + 24*60;
				 
			 }
				 
		 }
		 if (isMorning() == true){
			
			 return (t.getHour() - getHour()+ 12)*60 + t.getMinutes()-getMinutes();
		 }
		 if (isMorning() == false){
			 
			 return (t.getHour()-getHour() + 12)*60 + t.getMinutes()-getMinutes();
		 }
		 return 0;
	 }

	 public int compareTo(Object other) {
		
		 
		 if (((TimeMil) other).isMorning() == isMorning()  ){
				
			 if (((TimeMil) other).getHour() == getHour()){
				 if (getMinutes() < ((TimeMil) other).getMinutes() )
				 return -1;
				 if (getMinutes() == ((TimeMil) other).getMinutes() )
				 return 0;
				 if (getMinutes() > ((TimeMil) other).getMinutes() )
				 return 1;
			 }
		
			 if (getHour() < ((TimeMil) other).getHour() ){
			
			return -1;}
			 
			 if (getHour() > ((TimeMil) other).getHour() )
			{
			
			return 1;}
		 } 
			 if (isMorning() == true)
				 return -1;
			 if (isMorning() == false)
				 return 1;
	
		return 0;


	 }
	

	
	
}
