package y2018.day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

import adventOfCode.AdventOfCode;

public class Day03 extends AdventOfCode {

	public static void main(String[] args) {
		new Day03().run();

	}

	@Override
	public void part1() throws Exception {
		int[][] cloth = new int[1000][1000];
		int overlapcount = 0;

		for (String line : input) {

			String[] substrings = line.split(" ");
			int id = Integer.parseInt(substrings[0].substring(1));

			String[] coordsubs = substrings[2].split(",");
			int startFromX = Integer.parseInt(coordsubs[0]);
			int startFromY = Integer.parseInt(coordsubs[1].substring(0, coordsubs[1].indexOf(":")));

			String[] sizesubs = substrings[3].split("x");
			int sizeX = Integer.parseInt(sizesubs[0]);
			int sizeY = Integer.parseInt(sizesubs[1]);

			for (int x = startFromX; x < sizeX + startFromX; x++) {
				for (int y = startFromY; y < sizeY + startFromY; y++) {
					if (cloth[x][y] == 0) {
						cloth[x][y] = id;
					} else if (cloth[x][y] != -1) {
						cloth[x][y] = -1;
						overlapcount++;
					}
				}
			}
		}

		System.out.println("Result = " + overlapcount);
	}

	@Override
	public void part2() throws Exception {
		int[][] cloth = new int[1000][1000];

		HashSet<Integer> candidates = new HashSet<>();

		for (String line : input) {

			String[] substrings = line.split(" ");

			int id = Integer.parseInt(substrings[0].substring(1));
			candidates.add(id);

			String[] coordsubs = substrings[2].split(",");
			int startFromX = Integer.parseInt(coordsubs[0]);
			int startFromY = Integer.parseInt(coordsubs[1].substring(0, coordsubs[1].indexOf(":")));

			String[] sizesubs = substrings[3].split("x");
			int sizeX = Integer.parseInt(sizesubs[0]);
			int sizeY = Integer.parseInt(sizesubs[1]);

			for (int x = startFromX; x < sizeX + startFromX; x++) {
				for (int y = startFromY; y < sizeY + startFromY; y++) {
					if (cloth[x][y] == 0) {
						cloth[x][y] = id;
					} else if (cloth[x][y] != -1) {
						candidates.remove(cloth[x][y]);
						candidates.remove(id);
						cloth[x][y] = -1;
					} else {
						candidates.remove(id);
						candidates.remove(cloth[x][y]);
					}
				}
			}
		}

		for (Integer s : candidates) {
			System.out.println("Result = " + s);
		}
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2018/day03/day03Input.txt");
	}

}
