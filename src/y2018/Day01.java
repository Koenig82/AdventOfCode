package y2018;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day01 extends AdventOfCode {

	public static void main(String[] args) throws Exception {
		new Day01().run();
	}
	
	@Override
	public void part1() throws Exception {
		
		int sum = 0;
		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2018/day01Input.txt"));
		for(String line : readAllLines) {
			sum += Integer.parseInt(line);
		} 
		System.out.println("Result = "+sum);
		
		//List<Integer> collect = readAllLines.stream().map(Integer::parseInt).collect(Collectors.toList());
		//System.out.println(collect.stream().mapToInt(Integer::intValue).sum());
		
		return;
	}
	
	@Override
	public void part2() throws Exception {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2018/Day01Input.txt"));
		Set<Integer> results = new HashSet<>();
		results.add(0);
		int sum = 0;
		int numberAsInt = 0;
		
		while(true) {
			for (String numberAsString : readAllLines) {
				numberAsInt = Integer.parseInt(numberAsString);
				sum = sum + numberAsInt;
				if(!results.add(sum)) {
					System.out.println("Result = "+sum);
					return;
				}
			}
		}		
	}
}
