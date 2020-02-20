package y2019.day07;

import adventOfCode.AdventOfCode;
import y2019.cpu.IntCodeCPU;

public class Day07 extends AdventOfCode{

	public static void main(String[] args) {
		new Day07().run();
	}

	@Override
	public void part1() throws Exception {
		
		IntCodeCPU cpuA = new IntCodeCPU();
		cpuA.loadProgram("src/y2019/day07/day07Input.txt");
		cpuA.loadProgram("src/y2019/day07/testInput");
		cpuA.executeProgram(4);

		IntCodeCPU cpuB = new IntCodeCPU();
		cpuB.loadProgram("src/y2019/day07/day07Input.txt");
		cpuA.loadProgram("src/y2019/day07/day07Input.txt");
		cpuB.executeProgram(3);
		
		IntCodeCPU cpuC = new IntCodeCPU();
		cpuC.loadProgram("src/y2019/day07/day07Input.txt");
		cpuC.executeProgram(2);
		
		IntCodeCPU cpuD = new IntCodeCPU();
		cpuD.loadProgram("src/y2019/day07/day07Input.txt");
		cpuD.executeProgram(1);
		
		IntCodeCPU cpuE = new IntCodeCPU();
		cpuE.loadProgram("src/y2019/day07/day07Input.txt");
		cpuE.executeProgram(0);
	}

	@Override
	public void part2() throws Exception {

	}
}
