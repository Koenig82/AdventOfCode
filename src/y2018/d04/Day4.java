package y2018.d04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import adventOfCode.AdventOfCode;

public class Day4 extends AdventOfCode{

	public static void main(String[] args) {
		
		new Day4().run();
	}

	@Override
	public void part1() throws Exception {
		
		TreeSet<Event> eventlist = getEvents();
		HashMap<Integer,int[]> shifts = getSleepingShifts(eventlist);
		calculateData(shifts);
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
	}
	
	private void addSleepingMinutes(int[] minutes, Calendar first, Calendar second) {
		//System.out.println("addminutes, value in minutes[] on index 15 = "+minutes[15]);
		if(first.get(Calendar.HOUR_OF_DAY) != second.get(Calendar.HOUR_OF_DAY)) {
			//System.out.println("adding cross day");
			//System.out.println("first minute = "+ first.get(Calendar.MINUTE)+", second minute = "+second.get(Calendar.MINUTE));
			for(int i = first.get(Calendar.MINUTE); i < 60; i++) {
				//System.out.println("Adding a minute in array at index  = "+i);
				minutes[i]++;
			}
			for(int i = 0; i < second.get(Calendar.MINUTE); i++) {
				//System.out.println("Adding a minute in array at index  = "+i);
				minutes[i]++;
			}
		}else {
			//System.out.println("adding regular");
			//System.out.println("first minute = "+ first.get(Calendar.MINUTE)+", second minute = "+second.get(Calendar.MINUTE));
			for(int i = first.get(Calendar.MINUTE); i < second.get(Calendar.MINUTE); i++) {
				
				//System.out.println("Adding a minute in array at index  = "+i);
				minutes[i]++;
				
			}
		}
	}
	
	private TreeSet<Event> getEvents() throws NumberFormatException, IOException {
		//BufferedReader reader = new BufferedReader(new FileReader("src/y2018/d04/day04Input"));
		BufferedReader reader = new BufferedReader(new FileReader("src/y2018/d04/testinput.txt"));
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
		return eventlist;
	}
	
	private HashMap<Integer,int[]> getSleepingShifts(TreeSet<Event> eventlist){
		
		HashMap<Integer,int[]> shifts = new HashMap<>();
		Calendar latestStamp = Calendar.getInstance();
		int activeGuardId = -1;
		for(Event event : eventlist) {

			//if a new shift is starting
			if(event.guardId != -1) {

				//if the new shift is a new guard
				if(!shifts.containsKey(event.guardId)) {

					//add id and create defaultdata
					//System.out.println("*****Creating array for guard "+ event.guardId+"*****");
					int[] sleepMinutes = new int[60];
					activeGuardId = event.guardId;
					shifts.put(event.guardId, sleepMinutes);

				//if the shift is an existing guard
				}else {

					//set as active guard shift
					activeGuardId = event.guardId;
				}

			//continuing shift, add minutes asleep (event id = -1)
			}else {

				//Guard just woke up, add minutes since fall asleep timestamp for active guard
				if(event.isAwake) {
					//System.out.println("Adding minutes for guard "+ activeGuardId);
					addSleepingMinutes(shifts.get(activeGuardId), latestStamp, event.timestamp);

				//Guard just fell asleep, log the timestamp
				}else {
					latestStamp = event.timestamp;
				}
			}	
		}
		return shifts;
	}
	
	private void calculateData(HashMap<Integer,int[]> shifts) {
		
		int mostMinutesAsleep = 0;
		int tempMinutes;
		int leaderGuard = -1;
		for(Entry<Integer, int[]> entry : shifts.entrySet()) {
			tempMinutes =0;
			for(int i = 0;i<entry.getValue().length;i++) {
				tempMinutes+=entry.getValue()[i];
				//System.out.println("Guard "+entry.getKey()+" minute "+i+" value "+entry.getValue()[i]);
			}
			if(tempMinutes > mostMinutesAsleep) {
				leaderGuard = entry.getKey();
				mostMinutesAsleep = tempMinutes;
			}
			//System.out.println("Guard "+entry.getKey()+" slept a total of "+tempMinutes+" minutes");
		}

		int savedIndex = 0;
		int highestMinuteCount=0;
		for(int i = 0; i < shifts.get(leaderGuard).length; i++) {
			//System.out.println("value at index "+i+" is "+shifts.get(leaderGuard)[i]);
			if(shifts.get(leaderGuard)[i] > highestMinuteCount) {
				//System.out.println("assigning "+i+" as the leading minute");
				highestMinuteCount = shifts.get(leaderGuard)[i];
				savedIndex = i;
			}
		}
		System.out.println("guard most asleep = "+leaderGuard+" with "+mostMinutesAsleep+" minutes");
		System.out.println("The minute guard "+leaderGuard+" spent asleep the most was minute "+savedIndex);
		System.out.println("resulting number = "+(leaderGuard*savedIndex));
	}
	
 	private class Event implements Comparable<Event>{

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
