package y2019.day03;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import adventOfCode.AdventOfCode;
import adventOfCode.Pair;

public class Day03 extends AdventOfCode {

	public static void main(String[] args) {
		new Day03().run();
	}

	@Override
	public void part1() throws Exception {
		List<Wire> wires = getInput();
		WireGrid grid = new WireGrid(wires);
		System.out.println("Result = "+grid.getClosestIntersection());
	}

	@Override
	public void part2() throws Exception {
		List<Wire> wires = getInput();
		WireGrid grid = new WireGrid(wires);
		System.out.println("Result = "+grid.getBestIntersection());

	}

	private List<Wire> getInput() throws UnsupportedEncodingException, IOException {
		
		List<String> readAllLines = input;
		List<Wire> wires = new ArrayList<>();
		
		for(String line : readAllLines) {
			Wire wire = new Wire(line);
			wires.add(wire);
		}
		return wires;
	}
	
	private class WireGrid{
		
		List<Wire> wires;
		
		public WireGrid(List<Wire> wires) {
			this.wires = wires;			
		}
		public int getClosestIntersection() {
			Set<Pair<Integer,Integer>> wirePositions = new HashSet<>();
			TreeSet<Integer> intersectionDistances = new TreeSet<>();
			int posX;
			int posY;
			posX = 0;
			posY = 0;
			for (Instruction instruction : wires.get(0).instructions) {
				switch (instruction.dir) {
				case 'R':
					for(int i = 0; i < instruction.length; i++) {
						posX++;
						wirePositions.add(new Pair<Integer,Integer>(posX,posY));
					}
					break;
				case 'L':
					for(int i = 0; i < instruction.length; i++) {
						posX--;
						wirePositions.add(new Pair<Integer,Integer>(posX,posY));
					}
					break;
				case 'D':
					for(int i = 0; i < instruction.length; i++) {
						posY--;
						wirePositions.add(new Pair<Integer,Integer>(posX,posY));
					}
					break;
				case 'U':
					for(int i = 0; i < instruction.length; i++) {
						posY++;
						wirePositions.add(new Pair<Integer,Integer>(posX,posY));
					}
					break;

				default:
					throw new IllegalArgumentException(
							"Unexpected value: " + instruction.dir);
				}
			}
			posX = 0;
			posY = 0;
			for (Instruction instruction : wires.get(1).instructions) {
				switch (instruction.dir) {
				case 'R':
					for(int i = 0; i < instruction.length; i++) {
						posX++;
						if(!wirePositions.add(new Pair<Integer,Integer>(posX,posY))) {
							intersectionDistances.add(Math.abs(posX)+Math.abs(posY));
						}
					}
					break;
				case 'L':
					for(int i = 0; i < instruction.length; i++) {
						posX--;
						if(!wirePositions.add(new Pair<Integer,Integer>(posX,posY))) {
							intersectionDistances.add(Math.abs(posX)+Math.abs(posY));
						}
					}
					break;
				case 'D':
					for(int i = 0; i < instruction.length; i++) {
						posY--;
						if(!wirePositions.add(new Pair<Integer,Integer>(posX,posY))) {
							intersectionDistances.add(Math.abs(posX)+Math.abs(posY));
						}
					}
					break;
				case 'U':
					for(int i = 0; i < instruction.length; i++) {
						posY++;
						if(!wirePositions.add(new Pair<Integer,Integer>(posX,posY))) {
							intersectionDistances.add(Math.abs(posX)+Math.abs(posY));
						}
					}
					break;

				default:
					throw new IllegalArgumentException(
							"Unexpected value: " + instruction.dir);
				}
			}
			return intersectionDistances.first();
		}
		public int getBestIntersection() {
			HashMap<Pair<Integer,Integer>,Integer> positions = new HashMap<>();
			HashMap<Pair<Integer,Integer>,Integer> intersections = new HashMap<>();
			int posX;
			int posY;
			int steps;
			Pair<Integer,Integer> position;

			posX = 0;
			posY = 0;
			steps = 0;
			for (Instruction instruction : wires.get(0).instructions) {
				switch (instruction.dir) {
				case 'R':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posX++;
						position = new Pair<Integer,Integer>(posX,posY);
						if(!positions.containsKey(position)) {
							positions.put(position,steps);
						}
					}
					break;
				case 'L':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posX--;
						position = new Pair<Integer,Integer>(posX,posY);
						if(!positions.containsKey(position)) {
							positions.put(position,steps);
						}
					}
					break;
				case 'D':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posY--;
						position = new Pair<Integer,Integer>(posX,posY);
						if(!positions.containsKey(position)) {
							positions.put(position,steps);
						}
					}
					break;
				case 'U':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posY++;
						position = new Pair<Integer,Integer>(posX,posY);
						if(!positions.containsKey(position)) {
							positions.put(position,steps);
						}
					}
					break;

				default:
					throw new IllegalArgumentException(
							"Unexpected value: " + instruction.dir);
				}
			}
			posX = 0;
			posY = 0;
			steps = 0;
			for (Instruction instruction : wires.get(1).instructions) {
				switch (instruction.dir) {
				case 'R':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posX++;
						position = new Pair<Integer,Integer>(posX,posY);
						if(positions.containsKey(position)) {
							if(intersections.containsKey(position)) {
								intersections.put(position, intersections.get(position)+steps);
							}else {
								intersections.put(position, positions.get(position)+steps);
							}
						}
					}
					break;
				case 'L':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posX--;
						position = new Pair<Integer,Integer>(posX,posY);
						if(positions.containsKey(position)) {
							if(intersections.containsKey(position)) {
								intersections.put(position, intersections.get(position)+steps);
							}else {
								intersections.put(position, positions.get(position)+steps);
							}
						}
					}
					break;
				case 'D':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posY--;
						position = new Pair<Integer,Integer>(posX,posY);
						if(positions.containsKey(position)) {
							if(intersections.containsKey(position)) {
								intersections.put(position, intersections.get(position)+steps);
							}else {
								intersections.put(position, positions.get(position)+steps);
							}
						}
					}
					break;
				case 'U':
					for(int i = 0; i < instruction.length; i++) {
						steps++;
						posY++;
						position = new Pair<Integer,Integer>(posX,posY);
						if(positions.containsKey(position)) {
							if(intersections.containsKey(position)) {
								intersections.put(position, intersections.get(position)+steps);
							}else {
								intersections.put(position, positions.get(position)+steps);
							}
						}
					}
					break;

				default:
					throw new IllegalArgumentException(
							"Unexpected value: " + instruction.dir);
				}
			}

			int tempvalue = Integer.MAX_VALUE;
			for (Integer value : intersections.values()) {
				if(value < tempvalue) {
					tempvalue = value;
				}
			}
			return tempvalue;
		}
		
	}

	private class Wire{
		List<Instruction> instructions;

		public Wire(String intructionData) {
			instructions = new ArrayList<>();
			
			String[] substrings = intructionData.split(",");
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

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day03/Day03Input.txt");
	}

}
