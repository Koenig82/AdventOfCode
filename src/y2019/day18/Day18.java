package y2019.day18;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import adventOfCode.AdventOfCode;

public class Day18 extends AdventOfCode{

	public static void main(String[] args) {
		new Day18().run();

	}

	@Override
	public void part1() throws Exception {
		MapState map = new MapState();
		map.traverseMaze(map.player);
		
	}

	@Override
	public void part2() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day18/day18Input.txt");
	}
}
