package y2019.day07;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.CPU;

public class Day07 extends AdventOfCode{

	public static void main(String[] args) {
		new Day07().run();
	}

	@Override
	public void part1() throws Exception {
		
		CPU cpuA = new CPU();
		cpuA.loadProgram("src/y2019/day07/day07Input.txt");
		cpuA.loadProgram("src/y2019/day07/testInput");
		cpuA.executeProgram(4);

		CPU cpuB = new CPU();
		cpuB.loadProgram("src/y2019/day07/day07Input.txt");
		cpuA.loadProgram("src/y2019/day07/day07Input.txt");
		cpuB.executeProgram(3);
		
		CPU cpuC = new CPU();
		cpuC.loadProgram("src/y2019/day07/day07Input.txt");
		cpuC.executeProgram(2);
		
		CPU cpuD = new CPU();
		cpuD.loadProgram("src/y2019/day07/day07Input.txt");
		cpuD.executeProgram(1);
		
		CPU cpuE = new CPU();
		cpuE.loadProgram("src/y2019/day07/day07Input.txt");
		cpuE.executeProgram(0);
	}

	@Override
	public void part2() throws Exception {

	}
}
