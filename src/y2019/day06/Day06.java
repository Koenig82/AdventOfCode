package y2019.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day06 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day06().run();
	}

	@Override
	public void part1() throws Exception {
		Set<String> input = getInput();
		CelestialBody com = new CelestialBody("COM");
		System.out.println(input);
		
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}
	//fixa hashmap. streama ut första halvan av strängarna som key och den andra som value. kolla stream list to map
	//gå igenom hela mappen och skapa nya celestial objects. räkna upp dom medans dom skapas
	private HashMap<String> getInput() throws IOException{
		//return Files.readAllLines(Paths.get("src/y2019/day06/Day06Input.txt"));
		Set<String> input = new Set<>();
		try (Stream<String> stream = Files.lines(Paths.get("src/y2019/day06/testInput"))) {
		    stream.forEach(System.out::println);
		}
		Files.readAllLines(Paths.get("src/y2019/day06/testInput"));
		return Files.readAllLines(Paths.get("src/y2019/day06/testInput"));
	}
	

}
