package y2019.day05;

import adventOfCode.AdventOfCode;
import y2019.IntCodeCPU;

public class Day05 extends AdventOfCode{

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram("src/y2019/day05/day05Input.txt");
		cpu.executeProgram(1);
	}

	@Override
	public void part2() throws Exception {
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram("src/y2019/day05/day05Input.txt");
		cpu.executeProgram(5);
	}
}
