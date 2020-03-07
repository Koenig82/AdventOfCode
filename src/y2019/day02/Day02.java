package y2019.day02;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.CPUControl;

public class Day02 extends AdventOfCode{

	public static void main(String[] args) {
		new Day02().run();
	}

	@Override
	public void part1() throws Exception {
		
		CPUControl cpu = new CPUControl(1);
		cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", 0);
		cpu.writeToCacheIndexAtCoreId(12, 1, 0);
		cpu.writeToCacheIndexAtCoreId(2, 2, 0);
		cpu.executePrograms();
		System.out.println("Result = "+cpu.getValueFromCoreAtIndex(0, 0));

	}

	@Override
	public void part2() throws Exception {
		int corecount = 20;
		int nounCount = 0;
		int verbCount = 0;
		
		CPUControl cpu = new CPUControl(corecount);
		for(int i = 0; i < corecount; i++) {
			cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",i);
		}
		cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",0);
		for(nounCount = 0; nounCount < 100; nounCount++) {
			for(verbCount = 0; verbCount < (100-corecount); verbCount+=corecount) {
				for(int i = 0; i < corecount; i++) {
					cpu.writeToCacheIndexAtCoreId(nounCount, 1, i);
					cpu.writeToCacheIndexAtCoreId(verbCount+i, 2, i);
				}
				cpu.executePrograms();
				for(int i = 0; i < corecount; i++) {
					if(cpu.getValueFromCoreAtIndex(i, 0) == 19690720) {
						System.out.println("Result = " + ((100*nounCount) + (verbCount+i)));
						break;
					}
				}
				for(int i = 0; i < corecount; i++) {
					cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", i);
				}
			}
			int remainingCores;
			for(remainingCores = 0; remainingCores < 100-verbCount; remainingCores++) {
				//System.out.println("writing verbcount rest:" + (verbCount+remainingCores)+ ", At nounCount:" + nounCount);
				cpu.writeToCacheIndexAtCoreId(nounCount, 1, remainingCores);
				cpu.writeToCacheIndexAtCoreId(verbCount+remainingCores, 2, remainingCores);
			}
			cpu.executeXNrOfCores(remainingCores);
			for(int i = 0; i < remainingCores; i++) {
				if(cpu.getValueFromCoreAtIndex(i, 0) == 19690720) {
					System.out.println("Result = " + ((100*nounCount) + (verbCount+remainingCores)));
					break;
				}
			}
			for(int i = 0; i < corecount; i++) {
				cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", i);
			}
		}
		/*CPUControl cpu = new CPUControl(1);
		cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",0);
		
		for(nounCount = 0;nounCount < 100;nounCount++) {
			for(verbCount = 0;verbCount<100;verbCount++) {
				cpu.writeToCacheIndexAtCoreId(nounCount, 1, 0);
				cpu.writeToCacheIndexAtCoreId(verbCount, 2, 0);
				cpu.executePrograms();
				if(cpu.getValueFromCoreAtIndex(0, 0) == 19690720) {
					System.out.println("Result = " + ((100*nounCount) + verbCount));
					break;
				}
				cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",0);
			}
			cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",0);
		}*/
	}
}
