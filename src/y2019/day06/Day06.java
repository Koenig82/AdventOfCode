package y2019.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day06 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day06().run();
	}

	@Override
	public void part1() throws Exception {
		
		List<String> input = getInput();
		HashSet<String> planets = getAll(input);
		System.out.println(planets);
		HashMap<String,List<String>> relations = new HashMap<>();
		int relationCounter = 0;
		System.out.println();
		for (String planet : planets) {
			for (String relation : input) {
				if(relation.startsWith(planet)) {
					String[] substrings = relation.split("\\)");
					if(relations.containsKey(planet)) {
						relations.get(planet).add(substrings[1]);
						relationCounter++;
					}else {
						List<String> orbitlist = new ArrayList<>();
						relations.put(planet, orbitlist);
					}
				}
			}	
		}
		//System.out.println(relations);
		System.out.println("Result = " + relationCounter);
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}

	private List<String> getInput() throws IOException{
		
		return Files.readAllLines(Paths.get("src/y2019/day06/testInput"));
		//return Files.readAllLines(Paths.get("src/y2019/day06/Day06Input.txt"));
	}
	
	private HashSet<String> getAll(List<String> input){
		HashSet<String> planets = new HashSet<>();
		for (String string : input) {
			String[] substrings = string.split("\\)");
			if(!planets.contains(substrings[0])) {
				planets.add(substrings[0]);
			}
			if(!planets.contains(substrings[1])) {
				planets.add(substrings[1]);
			}
		}
		
		return planets;
	}
	
}
