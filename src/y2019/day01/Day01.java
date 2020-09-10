package y2019.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import adventOfCode.AdventOfCode;

public class Day01 extends AdventOfCode {

	public static void main(String[] args) {
		new Day01().run();
	}

	@Override
	public void part1() throws Exception {

		int totalFuelNeeded = 0;
		for (String line : input) {
			totalFuelNeeded += ((Integer.parseInt(line) / 3) - 2);
		}
		System.out.println("Result = " + totalFuelNeeded);
	}

	@Override
	public void part2() throws Exception {

		int totalFuelNeeded = 0;
		int moduleFuel;
		for (String line : input) {
			moduleFuel = ((Integer.parseInt(line) / 3) - 2);
			totalFuelNeeded += moduleFuel;
			while (moduleFuel > 0) {
				moduleFuel = (moduleFuel / 3) - 2;
				if (moduleFuel < 0) {
					moduleFuel = 0;
				}
				totalFuelNeeded += moduleFuel;
			}
		}
		System.out.println("Result = " + totalFuelNeeded);
	}


	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day01/Day01Input.txt");
	}
}
