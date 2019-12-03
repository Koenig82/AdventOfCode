package y2019;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

		List<String> readAllLines = Files.readAllLines(Paths.get("src/y2019/day03Input.txt"));
		List<Wire> wires = new ArrayList<>();
		for(String line : readAllLines) {
			Wire wire = new Wire(line);
			wires.add(wire);
		}
		return wires;
	}

	private class Wire{
		List<Instruction> instructions;
		int magnitudeR;
		int magnitudeL;
		int magnitudeU;
		int magnitudeD;

		public Wire(String intructionData) {
			instructions = new ArrayList<>();
			magnitudeR = 0;
			magnitudeL = 0;
			magnitudeU = 0;
			magnitudeD = 0;
			
			String[] substrings = intructionData.split(",");
			int mag = 0;
			char dir;
			for(String substring : substrings) {
				dir = substring.charAt(0);
				switch (dir) {
				case 'R':
					mag = Integer.parseInt(substring.substring(1));
					magnitudeR += (mag-magnitudeL);
					break;
				case 'L':
					mag = Integer.parseInt(substring.substring(1));
					magnitudeL -= (mag+magnitudeR);
					break;
				case 'D':
					mag = Integer.parseInt(substring.substring(1));
					magnitudeD -= (mag+magnitudeU);
					break;
				case 'U':
					mag = Integer.parseInt(substring.substring(1));
					magnitudeU += (mag-magnitudeD);
					break;

				default:
					break;
				}
				Instruction instruction = new Instruction(dir, mag);
				System.out.println(instruction);
			}
			//instructions.add(new Instruction());
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
