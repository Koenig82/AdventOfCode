package y2018.d01;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day1 extends AdventOfCode {

	public static void main(String[] args) throws Exception {
		new Day1().run();
	}

	@Override
	public void work() throws Exception {
		//System.out.println(part1());
		System.out.println(part2());
	}
	
	public int part1() throws Exception {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2018/d01/Day1Input.txt"));
		List<Integer> collect = readAllLines.stream().map(Integer::parseInt).collect(Collectors.toList());
		
		return collect.stream().mapToInt(Integer::intValue).sum();
	}
	
	public int part2() throws Exception {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2018/d01/Day1Input.txt"));
		Set<Integer> results = new HashSet<>();
		results.add(0);
		int sum = 0;
		int numberAsInt = 0;
		
		while(true) {
			for (String numberAsString : readAllLines) {
				numberAsInt = Integer.parseInt(numberAsString);
				sum = sum + numberAsInt;
				if(!results.add(sum)) {
					return sum;
				}
			}
		}		
	}
}
