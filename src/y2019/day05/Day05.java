package y2019.day05;

import java.util.List;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.Threadpool;
import y2019.intcodeComputer.IntCPU;
import y2019.intcodeComputer.Memory;

public class Day05 extends AdventOfCode {

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {

		List<Long> program = Memory.loadProgram("src/y2019/day05/day05Input.txt");
		IntCPU core = new IntCPU(program);
		core.addInput(1l);
		core.run();
		while (core.hasOutput()) {
			System.out.println(core.getOutput());
		}
	}

	@Override
	public void part2() throws Exception {
		List<Long> program = Memory.loadProgram("src/y2019/day05/day05Input.txt");
		IntCPU core = new IntCPU(program);
		core.addInput(5l);
		core.run();
		while (core.hasOutput()) {
			System.out.println(core.getOutput());
		}
	}
}
