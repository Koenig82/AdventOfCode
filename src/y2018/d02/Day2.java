package y2018.d02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;
import y2018.d01.Day1;

public class Day2 extends AdventOfCode{

	public static void main(String[] args) throws Exception {
		new Day2().run();
	}

	@Override
	public void work() throws Exception {
		System.out.println(part1());
	}
	
	public int part1() throws Exception {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2018/d02/Day2Input.txt"));
		HashMap<Character,Integer> counts = new HashMap<>();
		
		int twos = 0;
		int threes = 0;
		
		int result = 0;
		
		for (String word : readAllLines) {
			counts.clear();
			for (char character : word.toCharArray()) {
				counts.put(character, counts.getOrDefault(character, 0)+1);
				if(counts.get(character) == 2 && twos == 0) {
					twos++;
				}
				if(counts.get(character) == 2 && threes == 0) {
					threes++;
				}
			}
			rowresult += (twos + threes);
			twos = 0;
			threes = 0;
		}
		System.out.println(counts);
		return 0;
	}

}
