package y2018.d04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;
import java.util.TreeSet;

import adventOfCode.AdventOfCode;
import y2018.d03.Day3;

public class Day4 extends AdventOfCode{

	public static void main(String[] args) {
		new Day4().run();

	}

	@Override
	public void part1() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("src/y2018/d04/day04Input"));
		TreeSet<Event> eventlist = new TreeSet<>();
		try {
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	Calendar cal = Calendar.getInstance();
		    	cal.set(Integer.parseInt(line.substring(1, 5)),
		    			Integer.parseInt(line.substring(6, 8)),
		    			Integer.parseInt(line.substring(9, 11)),
		    			Integer.parseInt(line.substring(12, 14)),
		    			Integer.parseInt(line.substring(15, 17)));	    	
		    	String[] eventSubstring = line.substring(19).split(" ");
		    	if(eventSubstring.length > 2) {
		    		eventlist.add(new Event(cal, true, Integer.parseInt(eventSubstring[1].substring(1))));
		    	}else {
		    		if(eventSubstring[0].matches("wakes")) {
		    			eventlist.add(new Event(cal, true));
		    		}else {
		    			eventlist.add(new Event(cal, false));
		    		}
		    	}	
		    }
		} finally {
		    reader.close();
		}
		for(Event event : eventlist) {
			System.out.println("Guardid: "+event.guardId+
							   ", isAwake: "+event.isAwake+
							   ", month :"+event.timestamp.get(Calendar.MONTH)+
							   ", day: "+event.timestamp.get(Calendar.DAY_OF_MONTH)+
							   ", Hour: "+event.timestamp.get(Calendar.HOUR_OF_DAY)+
							   ", Minute: "+event.timestamp.get(Calendar.MINUTE));
			
			
		}
		
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public class Event implements Comparable<Event>{
		
		Calendar timestamp;
		boolean isAwake;
		int guardId;
		
		public Event(Calendar timestamp, boolean isAwake) {
			
			this.timestamp = timestamp;
			this.isAwake = isAwake;
			guardId = -1;
		}
		public Event(Calendar timestamp, boolean isAwake, int guardId) {
			
			this.timestamp = timestamp;
			this.isAwake = isAwake;
			this.guardId = guardId;
		}

		@Override
		public int compareTo(Event o) {
			if(timestamp.after(o.timestamp)) {
				return 1;
			}else if(timestamp.before(o.timestamp)) {
				return -1;
			}else {
				return 0;
			}
		}
	}

}
