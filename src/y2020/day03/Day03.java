package y2020.day03;

import java.util.ArrayList;
import java.util.List;

import adventOfCode.AdventOfCode;
import y2020.day02.Day02;

public class Day03 extends AdventOfCode{
	
	public static void main(String[] args) {
		new Day03().run();
	}
	
	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2020/day03/Day03Input.txt");
	}

	@Override
	public void part1() throws Exception {
				
		int row = 0;
		int col = 0;
		int treeCount = 0;
		while(row < input.size()) {
			if(input.get(row).charAt(col%input.get(row).length()) == '#') {
				treeCount++;
			}
			row++;
			col+=3;
		}
		System.out.println(treeCount);
	}

	@Override
	public void part2() throws Exception {
		int[] right = {1,3,5,7,1};
		int[] down = {1,1,1,1,2};
		long value = 1;
		for (int index = 0; index < right.length; index++) {
			int row = 0;
			int col = 0;
			int treeCount = 0;
			while(row < input.size()) {
				if(input.get(row).charAt(col%input.get(row).length()) == '#') {
					treeCount++;
				}
				row+=down[index];
				col+=right[index];
			}
			value*=treeCount;
		}
		System.out.println(value);
	}
}
