package y2019.day07;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.CPU;

public class Day07 extends AdventOfCode{

	public static void main(String[] args) {
		new Day07().run();
	}

	@Override
	public void part1() throws Exception {
		//String program = "src/y2019/day07/day07Input.txt";
		String program = "src/y2019/day07/testInput";

		CPU cpuA = new CPU();
		cpuA.loadProgram(program);
		int pipe = cpuA.executeProgram(4, true);
		System.out.println(pipe);
		
		CPU cpuB = new CPU();
		cpuB.loadProgram(program);
		pipe = cpuB.executeProgram(3, true);
		System.out.println(pipe);
		
		CPU cpuC = new CPU();
		cpuC.loadProgram(program);
		pipe = cpuC.executeProgram(2, true);
		System.out.println(pipe);
		
		CPU cpuD = new CPU();
		cpuD.loadProgram(program);
		pipe = cpuD.executeProgram(1, true);
		System.out.println(pipe);
		
		CPU cpuE = new CPU();
		cpuE.loadProgram(program);
		pipe = cpuE.executeProgram(0, true);
		System.out.println(pipe);
	}

	@Override
	public void part2() throws Exception {

	}
}
