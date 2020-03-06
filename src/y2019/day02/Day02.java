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
		int corecount = 7;
		int nounCount = 0;
		int verbCount = 0;
		
		//int corecount = corecountInit % 100;
		CPUControl cpu = new CPUControl(corecount);
		for(int i = 0; i < corecount; i++) {
			cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",i);
		}
		
		for(nounCount = 0; nounCount < (100-corecount); nounCount++) {
			for(verbCount = 0; verbCount < (100-corecount); verbCount+=corecount) {
				System.out.println(verbCount);
				for(int i = 0; i < corecount; i++) {
					cpu.writeToCacheIndexAtCoreId(nounCount, 1, i);
					cpu.writeToCacheIndexAtCoreId(verbCount+i, 2, i);
				}
				//fixa nått sätt att ha dynamiskt med cores
				cpu.executePrograms();
				for(int i = 0; i < corecount; i++) {
					if(cpu.getValueFromCoreAtIndex(i, 0) == 19690720) {
						System.out.println("Result = " + ((100*nounCount) + verbCount));
						break;
					}
				}

				for(int i = 0; i < corecount; i++) {
					cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", i);
				}
			}
			System.out.println(verbCount);
			if(verbCount > (100-corecount)) {
				System.out.println("overblivna verbs: "+(100-verbCount));
				for(int i = 0; i < (100-verbCount); i++) {
					
					cpu.writeToCacheIndexAtCoreId(nounCount, 1, i);
					cpu.writeToCacheIndexAtCoreId(verbCount+i, 2, i);
					verbCount++;
					
				}
				//fixa nått sätt att ha dynamiskt med cores
				cpu.executePrograms();
				for(int i = 0; i < (100-verbCount); i++) {
					if(cpu.getValueFromCoreAtIndex(i, 0) == 19690720) {
						System.out.println("Result = " + ((100*nounCount) + verbCount));
						break;
					}
				}

				/*for(int i = 0; i < corecount; i++) {
					cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", i);
				}*/
			}
			for(int i = 0; i < corecount; i++) {
				cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt", i);
			}
		}
		/*CPUControl cpu = new CPUControl();
		cpu.loadProgramAtCoreId("src/y2019/day02/day02Input.txt",0);
		
		for(int nounCount = 0;nounCount < 100;nounCount++) {
			for(int verbCount = 0;verbCount<100;verbCount++) {
				cpu.writeToCacheAtCoreId(nounCount, 1, 0);
				cpu.writeToCacheAtCoreId(verbCount, 2, 0);
				if(cpu.executeProgram(null, false) == 19690720) {
					System.out.println("Result = "+((100*nounCount)+verbCount));
					break;
				}
				cpu.loadProgram("src/y2019/day02/day02Input.txt");
			}
			cpu.loadProgram("src/y2019/day02/day02Input.txt");
		}*/
	}
}
