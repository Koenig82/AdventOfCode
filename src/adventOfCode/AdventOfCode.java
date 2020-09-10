package adventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public abstract class AdventOfCode {
	
	private long timeStamp;
	protected List<String> input; 
	
	public void run(){
		try {
			input = Collections.unmodifiableList(readInput());
		} catch (Exception e) {
			e.printStackTrace();
		}
		timeStamp = System.nanoTime();
		try {
			part1();
		} catch (Exception e) {
			System.err.println("Some Error");
			e.printStackTrace();
		} 
		timeStamp = System.nanoTime()- timeStamp;
		System.out.println("Part 1 Solution took " + timeStamp/1000000 + " milliseconds");
		
		timeStamp = System.nanoTime();
		try {
			part2();
		} catch (Exception e) {
			System.err.println("Some Error");
			e.printStackTrace();
		}
		timeStamp = System.nanoTime()- timeStamp;
		System.out.println("Part 2 Solution took " + timeStamp/1000000 + " milliseconds");
		
	}
	
	protected List<String> readFile(String path) throws IOException{
		return Files.readAllLines(Paths.get(path));
	}
	
	public abstract List<String> readInput() throws Exception;
	public abstract void part1() throws Exception;
	public abstract void part2() throws Exception;
}
