package y2020.day01;

import java.util.ArrayList;
import java.util.List;

import adventOfCode.AdventOfCode;

public class Day01 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day01().run();
	}
	
	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2020/day01/Day01Input.txt");
	}

	@Override
	public void part1() throws Exception {
		System.out.println(get2MultipliedValues());
	}

	@Override
	public void part2() throws Exception {
		System.out.println(get3MultipliedValues());
	}
	
	private int get2MultipliedValues() {
		ArrayList<Integer> entries = new ArrayList<>();
		for (String line : input) {
			entries.add(Integer.parseInt(line));
		}
		for (int i = 0; i < entries.size(); i++) {
			for(int j = 0; j < entries.size(); j++) {
				if(entries.get(i)+entries.get(j)==2020) {
					return entries.get(i)*entries.get(j);
				}
			}
		}
		return -1;
	}
	
	private int get3MultipliedValues() {
		ArrayList<Integer> entries = new ArrayList<>();
		for (String line : input) {
			entries.add(Integer.parseInt(line));
		}
		for (int i = 0; i < entries.size(); i++) {
			for(int j = 0; j < entries.size(); j++) {
				for(int k = 0; k < entries.size(); k++) {
					if(entries.get(i)+entries.get(j)+entries.get(k)==2020) {
						return entries.get(i)*entries.get(j)*entries.get(k);
					}
				}
			}
		}
		return -1;
	}

	
	

}
