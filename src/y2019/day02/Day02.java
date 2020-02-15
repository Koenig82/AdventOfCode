package y2019.day02;

import adventOfCode.AdventOfCode;
import y2019.IntCodeCPU;

public class Day02 extends AdventOfCode{

	public static void main(String[] args) {
		new Day02().run();
	}

	@Override
	public void part1() throws Exception {
		
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram("src/y2019/day02/day02Input.txt");
		cpu.writeToMemory(12, 1);
		cpu.writeToMemory(2, 2);
		System.out.println("Result = "+cpu.executeProgram(null));

	}

	@Override
	public void part2() throws Exception {
		
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram("src/y2019/day02/day02Input.txt");
		
		for(int nounCount = 0;nounCount < 100;nounCount++) {
			for(int verbCount = 0;verbCount<100;verbCount++) {
				cpu.writeToMemory(nounCount, 1);
				cpu.writeToMemory(verbCount, 2);
				cpu.executeProgram(null);
				if(cpu.memory[0] == 19690720) {
					System.out.println("Result = "+((100*nounCount)+verbCount));
					break;
				}
				cpu.loadProgram("src/y2019/day02/day02Input.txt");
			}
			cpu.loadProgram("src/y2019/day02/day02Input.txt");
		}
	}
}
