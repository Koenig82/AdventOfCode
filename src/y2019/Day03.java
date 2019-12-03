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
		char[][] grid;

		public WireGrid(List<Wire> wires) {
			
			//find greatestmagnitude off each dir among wires to get gridsize
			//walk out each wire and mark way (special mark at intersect. get nearest)
			//perhaps intersection attribute?
			for (Wire wire : wires) {
				
			}
		}
	}

	private class Wire{
		List<Instruction> instructions;
		//exchange max to pos of each direction as a saved value to use when increasing total magnitude
		int magnitudeR;
		int magnitudeRmax;
		
		int magnitudeL;
		int magnitudeLmax;
		
		int magnitudeU;
		int magnitudeUmax;
		
		int magnitudeD;
		int magnitudeDmax;

		public Wire(String intructionData) {
			instructions = new ArrayList<>();
			
			String[] substrings = intructionData.split(",");
			int mag = 0;
			char dir;
			magnitudeR = 0;
			magnitudeL = 0;
			magnitudeU = 0;
			magnitudeD = 0;
			System.out.println(substrings.length);
			for(String substring : substrings) {
				
				dir = substring.charAt(0);
				mag = Integer.parseInt(substring.substring(1));
				switch (dir) {
				case 'R':
					if(mag > magnitudeR) {
						magnitudeRmax = mag;
					}
					magnitudeR = magnitudeR + mag - magnitudeL;
					System.out.println("Adding magnitudeR "+(mag-magnitudeL));
					break;
				case 'L':
					if(mag > magnitudeL) {
						magnitudeLmax = mag;
					}
					magnitudeL = magnitudeL + mag - magnitudeR;
					System.out.println("Adding magnitudeL "+(mag-magnitudeR));
					break;
				case 'D':
					if(mag > magnitudeD) {
						magnitudeDmax = mag;
					}
					magnitudeD = magnitudeD + mag - magnitudeU;
					System.out.println("Adding magnitudeD "+(mag-magnitudeU));
					break;
				case 'U':
					if(mag > magnitudeU) {
						magnitudeUmax = mag;
					}
					magnitudeU = magnitudeU + mag - magnitudeD;
					System.out.println("Adding magnitudeU "+(mag-magnitudeD));
					break;

				default:
					break;
				}
				instructions.add(new Instruction(dir, mag));
			}
			System.out.println("magR "+magnitudeR+" "+
							   "magL "+magnitudeL+" "+
							   "magD "+magnitudeD+" "+
							   "magU "+magnitudeU);
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
