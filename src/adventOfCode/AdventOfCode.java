package adventOfCode;

public abstract class AdventOfCode {
	
	public long timeStamp;
	
	public void run(){
		timeStamp = System.currentTimeMillis();
		try {
			part1();
		} catch (Exception e) {
			System.err.println("Some Error");
			e.printStackTrace();
		}
		timeStamp = System.currentTimeMillis()- timeStamp;
		System.out.println("Part 1 Solution took " + timeStamp + " milliseconds");
		
		timeStamp = System.currentTimeMillis();
		try {
			part2();
		} catch (Exception e) {
			System.err.println("Some Error");
			e.printStackTrace();
		}
		timeStamp = System.currentTimeMillis()- timeStamp;
		System.out.println("Part 2 Solution took " + timeStamp + " milliseconds");
		
	}
	
	public abstract void part1() throws Exception;
	public abstract void part2() throws Exception;
}
