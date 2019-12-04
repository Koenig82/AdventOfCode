package y2019;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import adventOfCode.AdventOfCode;

public class Day03 extends AdventOfCode {

	public static void main(String[] args) {
		new Day03().run();
	}

	@Override
	public void part1() throws Exception {
		List<Wire> wires = getInput();
	
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub

	}

	private List<Wire> getInput() throws UnsupportedEncodingException, IOException {
		
		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2019/testinput"));
		//List<String> readAllLines = Files.readAllLines(Paths.get("src/y2019/day03Input.txt"));
		List<Wire> wires = new ArrayList<>();
		
		for(String line : readAllLines) {
			Wire wire = new Wire(line);
			wires.add(wire);
		}
		return wires;
	}
	
	private class WireGrid{
		Set<Position> wirePos = new TreeSet<>();

		public WireGrid(List<Wire> wires) {

			for (Wire wire : wires) {
				
			}
		}
	}
	
	private class Position{
		
	}

	private class Wire{
		List<Instruction> instructions;

		public Wire(String intructionData) {
			instructions = new ArrayList<>();
			
			String[] substrings = intructionData.split(",");
			System.out.println(substrings.length);
			for(String substring : substrings) {
				instructions.add(new Instruction(substring.charAt(0), Integer.parseInt(substring.substring(1))));
			}
		}		
	}
	
	private class Instruction{
		char dir;
		int length;
		
		public Instruction(char dir, int length) {
			
			this.dir = dir;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Instruction [dir=" + dir + ", length=" + length + "]";
		}
	}

}
