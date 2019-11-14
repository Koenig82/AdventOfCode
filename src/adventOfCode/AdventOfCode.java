package adventOfCode;

public abstract class AdventOfCode {
	
	public long timeStamp;
	
	public void run(){
		timeStamp = System.currentTimeMillis();
		try {
			work();
		} catch (Exception e) {
			System.err.println("Some Error");
			e.printStackTrace();
		}
		timeStamp = System.currentTimeMillis()- timeStamp;
		System.out.println("Solution took " + timeStamp + " milliseconds");
		
	}
	
	public abstract void work() throws Exception;
}
