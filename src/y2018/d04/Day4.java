package y2018.d04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import adventOfCode.AdventOfCode;

public class Day4 extends AdventOfCode{

	public static void main(String[] args) {
		
		new Day4().run();
	}
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Override
	public void part1() throws Exception {
		TreeSet<Event> eventlist = getEvents();
		HashMap<Integer,int[]> shifts = getSleepingShifts(eventlist);
		calculateData(shifts);
	}

	@Override
	public void part2() throws Exception {
		TreeSet<Event> eventlist = getEvents();
		HashMap<Integer,int[]> shifts = getSleepingShifts(eventlist);
		calculateData2(shifts);	
	}
	
	private void addSleepingMinutes(int[] minutes, LocalDateTime first, LocalDateTime second) {
		if(first.getHour() != second.getHour()) {
			for(int i = first.getMinute(); i < 60; i++) {
				minutes[i]++;
			}
			for(int i = 0; i < second.getMinute(); i++) {
				minutes[i]++;
			}
		}else {
			for(int i = first.getMinute(); i < second.getMinute(); i++) {
				minutes[i]++;
				
			}
		}
	}
	
	private TreeSet<Event> getEvents() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader("src/y2018/d04/day04Input"));
		
		TreeSet<Event> eventlist = new TreeSet<>();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				 
				LocalDateTime time = LocalDateTime.parse(line.substring(1, 17), formatter);
				
				String[] eventSubstring = line.substring(19).split(" ");
				if(eventSubstring.length > 2) {
					eventlist.add(new Event(time, true, Integer.parseInt(eventSubstring[1].substring(1))));
				}else {
					if(eventSubstring[0].matches("wakes")) {
						eventlist.add(new Event(time, true));
					}else {
						eventlist.add(new Event(time, false));
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
		LocalDateTime latestStamp = LocalDateTime.now();
		int activeGuardId = -1;
		for(Event event : eventlist) {
			//if a new shift is starting
			if(event.guardId != -1) {
				//if the new shift is a new guard
				if(!shifts.containsKey(event.guardId)) {
					//add id and create defaultdata
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
			}
			if(tempMinutes > mostMinutesAsleep) {
				leaderGuard = entry.getKey();
				mostMinutesAsleep = tempMinutes;
			}
		}

		int savedIndex = 0;
		int highestMinuteCount=0;
		for(int i = 0; i < shifts.get(leaderGuard).length; i++) {
			if(shifts.get(leaderGuard)[i] > highestMinuteCount) {
				highestMinuteCount = shifts.get(leaderGuard)[i];
				savedIndex = i;
			}
		}
		System.out.println("guard most asleep = "+leaderGuard+" with "+mostMinutesAsleep+" minutes");
		System.out.println("The minute guard "+leaderGuard+" spent asleep the most was minute "+savedIndex);
		System.out.println("resulting number = "+(leaderGuard*savedIndex));
	}
	
	private void calculateData2(HashMap<Integer,int[]> shifts) {

		int tempMinutes = 0;
		int minute = -1;;
		int leaderGuard = -1;
		for(Entry<Integer, int[]> entry : shifts.entrySet()) {

			for(int i = 0;i<entry.getValue().length;i++) {
				if(entry.getValue()[i] > tempMinutes) {
					tempMinutes = entry.getValue()[i];
					leaderGuard = entry.getKey();
					minute = i;
				}
				
			}
			
		}

		System.out.println("The minute most spent asleep by any guard was minute "+minute+
						   " by guard "+leaderGuard+" with "+tempMinutes+" minutes");
		System.out.println("Result = "+(leaderGuard*minute));
	}
	
 	private class Event implements Comparable<Event>{
 		
 		LocalDateTime timestamp;
 		
		boolean isAwake;
		int guardId;

		public Event(LocalDateTime timestamp, boolean isAwake) {

			this.timestamp = timestamp;
			this.isAwake = isAwake;
			guardId = -1;
		}
		public Event(LocalDateTime timestamp, boolean isAwake, int guardId) {

			this.timestamp = timestamp;
			this.isAwake = isAwake;
			this.guardId = guardId;
		}

		@Override
		public int compareTo(Event o) {
			return timestamp.compareTo(o.timestamp);
		}
	}
}
