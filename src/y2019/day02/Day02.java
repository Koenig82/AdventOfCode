package y2019.day02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.Threadpool;
import y2019.intcodeComputer.IntCPU;
import y2019.intcodeComputer.Memory;

public class Day02 extends AdventOfCode {

	public static void main(String[] args) {
		new Day02().run();
	}

	@Override
	public List<String> readInput() throws Exception {
		return readFile("src/y2019/day02/Day02Input.txt");
	}
	
	@Override
	public void part1() throws Exception {

		List<Long> program = Memory.loadProgram(input.get(0));
		IntCPU core = new IntCPU(program);
		core.writeToMemory(12, 1);
		core.writeToMemory(2, 2);
		core.run();
		System.out.println("Result = " + core.getFromMemory(0));
	}

	@Override
	public void part2() throws Exception {
		int corecount = Runtime.getRuntime().availableProcessors();

		List<Long> program = Memory.loadProgram(input.get(0));
		Threadpool cpu = new Threadpool(corecount);

		for (int nounCount = 0; nounCount < 100; nounCount++) {
			for (int verbCount = 0; verbCount < 100; verbCount++) {
				final int noun = nounCount;
				final int verb = verbCount;
				IntCPU core = new IntCPU(program);
				core.writeToMemory(noun, 1);
				core.writeToMemory(verb, 2);
				cpu.runInParallell(core, () -> {
					if (core.getFromMemory(0) == 19690720) {
						System.out.println("Result = " + ((100 * noun) + (verb)));
					}
				});

			}
		}
		cpu.waitUntilDone();
	}

}
