package y2019.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import adventOfCode.AdventOfCode;

public class Day06 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day06().run();
	}

	@Override
	public void part1() throws Exception {
		List<String> input = getInput();
		System.out.println(input);
		
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private List<String> getInput() throws IOException{
		//return Files.readAllLines(Paths.get("src/y2019/day06/Day06Input.txt"));
		return Files.readAllLines(Paths.get("src/y2019/day06/testInput"));
	}

}
