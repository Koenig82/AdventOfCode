package y2019.day02;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.CPU;

public class Day02 extends AdventOfCode{

	public static void main(String[] args) {
		new Day02().run();
	}

	@Override
	public void part1() throws Exception {
		
		CPU cpu = new CPU(1);
		cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", 0);
		cpu.writeToCacheAtCoreId(12, 1, 0);
		cpu.writeToCacheAtCoreId(2, 2, 0);
		System.out.println("Result = "+cpu.executeProgram(null, false));

	}

	@Override
	public void part2() throws Exception {
		
		CPU cpu = new CPU();
		cpu.loadProgram("src/y2019/day02/day02Input.txt");
		
		for(int nounCount = 0;nounCount < 100;nounCount++) {
			for(int verbCount = 0;verbCount<100;verbCount++) {
				cpu.writeToMemory(nounCount, 1);
				cpu.writeToMemory(verbCount, 2);
				if(cpu.executeProgram(null, false) == 19690720) {
					System.out.println("Result = "+((100*nounCount)+verbCount));
					break;
				}
				cpu.loadProgram("src/y2019/day02/day02Input.txt");
			}
			cpu.loadProgram("src/y2019/day02/day02Input.txt");
		}
	}
}
