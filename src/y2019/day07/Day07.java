package y2019.day07;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.CPUControl;

public class Day07 extends AdventOfCode{

	public static void main(String[] args) {
		new Day07().run();
	}

	@Override
	public void part1() throws Exception {
		//String program = "src/y2019/day07/day07Input.txt";
		String program = "src/y2019/day07/testInput";

		CPUControl cpuA = new CPUControl();
		cpuA.loadProgram(program);
		int pipe = cpuA.executeProgram(4, true);
		System.out.println(pipe);
		
		CPUControl cpuB = new CPUControl();
		cpuB.loadProgram(program);
		pipe = cpuB.executeProgram(3, true);
		System.out.println(pipe);
		
		CPUControl cpuC = new CPUControl();
		cpuC.loadProgram(program);
		pipe = cpuC.executeProgram(2, true);
		System.out.println(pipe);
		
		CPUControl cpuD = new CPUControl();
		cpuD.loadProgram(program);
		pipe = cpuD.executeProgram(1, true);
		System.out.println(pipe);
		
		CPUControl cpuE = new CPUControl();
		cpuE.loadProgram(program);
		pipe = cpuE.executeProgram(0, true);
		System.out.println(pipe);
	}

	@Override
	public void part2() throws Exception {

	}
}
