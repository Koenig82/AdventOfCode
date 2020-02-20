package y2019.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import adventOfCode.AdventOfCode;

public class Day06 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day06().run();
	}

	@Override
	public void part1() throws Exception {
		
		List<String> input = getInput();
	
		HashMap<String,String> relations = new HashMap<>();
		int relationCounter = 0;
		
		for (String string : input) {
			String[] substrings = string.split("\\)");
			relations.put(substrings[1], substrings[0]);
		}
		
		for(String planet : relations.keySet()) {
			while(!relations.get(planet).equals("COM")) {
				planet = relations.get(planet);
				relationCounter++;
			}
			relationCounter++;
		}
		System.out.println("Result = " + relationCounter);
	}

	@Override
	public void part2() throws Exception {
		List<String> input = getInput();		
		HashMap<String,String> relations = new HashMap<>();
		
		for (String string : input) {
			String[] substrings = string.split("\\)");
			relations.put(substrings[1], substrings[0]);
		}
		List<String> youPath = getPathToCom("YOU", relations);
		List<String> sanPath = getPathToCom("SAN", relations);
	
		System.out.println("Result = " + getPathLengthToCommonParent(youPath, sanPath));
		
	}

	private int getPathLengthToCommonParent(List<String> youPath, List<String> sanPath) {
		Set<String> set = new HashSet<>(youPath);
		int jump = 0;
		for (String string : sanPath) {
			if(set.contains(string)) {
				for (String string2 : youPath) {
					if(string2.equals(string)) {
						return jump;
					}else {
						jump++;
					}
				}
			}else {
				jump++;
			}
		}
		return -1;
	}

	private List<String> getPathToCom(String planet,
			HashMap<String, String> relations) {
		
		
		List<String> result = new ArrayList<>();
		//result.add(planet);
		while(!relations.get(planet).equals("COM")) {
			planet = relations.get(planet);
			result.add(planet);
		}

		return result;
		
	}

	private List<String> getInput() throws IOException{

		return Files.readAllLines(Paths.get("src/y2019/day06/Day06Input.txt"));
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
