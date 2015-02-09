/** Short driver program to illustrate use of Time.java interface with
    TimeMil.java implementation for homework 1
*/

public class TimeMain {
    public static void main(String[] args) {
    
        Time start = new TimeMil("9:30 am");
        Time end = new TimeMil("12:00p");
        Time depart = new TimeMil("0500");  // 5 am
        //not 0500 - in java this is octal (base 8)
        Time arrive = new TimeMil(1430);  // 2:30 pm
        Time other = new TimeMil(500);
        Time bad = new TimeMil("23:60am");
        System.out.println("bad time " + bad);
        bad = new TimeMil(-1399);
        System.out.println("bad time " + bad);

        System.out.println(start.toString() + " " + end.toString());  
        System.out.println(depart + " " + arrive);  // toString implicit
        
        int duration;
        duration = start.until(end);
        System.out.println(duration + " " + depart.until(arrive));
        
        // testing minsSinceMidnight
        System.out.println(start + " " + start.minsSinceMidnight());
        System.out.println(end + " " + end.minsSinceMidnight());
        System.out.println(arrive + " " + arrive.minsSinceMidnight());
        System.out.println(depart + " " + other.minsSinceMidnight());
        System.out.println(bad + " " + bad.minsSinceMidnight());
        
        System.out.println("arrive to start is " + arrive.until(start));

        Time t1 = new TimeMil("12:00am");
        Time t2 = new TimeMil(1730);  // 5:30 pm
        Time t3 = new TimeMil(8, 45, 'p');
        
        // implicitly use toString
        System.out.println("time1 " + t1);
        System.out.println("time2 " + t2);
        System.out.println("time3 " + t3);
        
        if (t1.isMorning())
            System.out.println("rise and shine");
        if (t3.isAfternoon())
            System.out.println("afternoon");
        
        if (t2.compareTo(t3) > 0)
            System.out.println("t2 later than t3");
        else
            System.out.println("t2 <= t3");
        
        Time takeoff = new TimeMil(750);
        Time landing = new TimeMil(1005);
        System.out.println("flight time: " + takeoff.until(landing));
        
        System.out.println("t2 hour: " + t2.getHour());
        System.out.println("t2 min: " + t2.getMinutes());
        
        Time meeting = new TimeMil(1430);
        meeting.addMinutes(45);
        System.out.println("1430 + 45 " + meeting);
        System.out.println("military meeting " + meeting.militaryString());
        int d = meeting.addHours(13);
        System.out.println("meeting + 13 hours " + meeting + " & " + d + " day(s) extra");
        System.out.println("military meeting " + meeting.militaryString());

        Time problem = new TimeMil(0200);  
// we mean 2am, but 0 in front of an int means octal (base 8, not 10) in java
        System.out.println("problem time is " + problem);
    }
}