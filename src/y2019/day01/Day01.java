package y2019.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import adventOfCode.AdventOfCode;
import y2018.day02.Day02;

public class Day01 extends AdventOfCode{

	public static void main(String[] args) {
		new Day01().run();
	}

	@Override
	public void part1() throws Exception {
		
		List<String> input = getInput();
		int totalFuelNeeded = 0;
		for(String line : input) {
			totalFuelNeeded += ((Integer.parseInt(line) / 3) - 2);
		} 
		System.out.println("Result = "+totalFuelNeeded);	
	}

	@Override
	public void part2() throws Exception {
		
		List<String> input = getInput();
		int totalFuelNeeded = 0;
		int moduleFuel;
		for(String line : input) {
			moduleFuel = ((Integer.parseInt(line) / 3) - 2);
			totalFuelNeeded += moduleFuel;
			while(moduleFuel > 0) {
				moduleFuel = (moduleFuel  / 3) - 2;
				if(moduleFuel < 0) {
					moduleFuel = 0;
				}
				totalFuelNeeded += moduleFuel;	
			}
		} 
		System.out.println("Result = "+totalFuelNeeded);		
	}
	
	private List<String> getInput() throws IOException{
		return Files.readAllLines(Paths.get("src/y2019/day01/Day01Input.txt"));
	}
}
