package y2019.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day06 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day06().run();
	}

	@Override
	public void part1() throws Exception {
		HashSet<String> input = getInput();
		CelestialBody com = new CelestialBody("COM");
		String first = "COM";
		String body = "COM";
		for (String string : input) {
			if(string.startsWith(first)) {
				String[] substrings = string.split("\\)");
				com.inOrbit.add(new CelestialBody(substrings[1]));
				body = substrings[1];
			}
		}
		input.remove(first);
		while(!input.isEmpty()) {
			for (String string : input) {
				if(string.startsWith(body)) {
					String[] substrings = string.split("\\)");
					com.inOrbit.add(new CelestialBody(substrings[1]));
				}
			}
			input.remove(body);
			
		}
		System.out.println(input);
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}

	private HashSet<String> getInput() throws IOException{

		HashSet<String> input = new HashSet<>();
		try (Stream<String> stream = Files.lines(Paths.get("src/y2019/day06/testInput"))) {
		//try (Stream<String> stream = Files.lines(Paths.get("src/y2019/day06/Day06Input.txt"))) {
		    stream.forEach(a -> input.add(a));
		    return input;
		}
	}
}
