package y2019.day05;

import adventOfCode.AdventOfCode;
import y2019.intcodeComputer.CPUControl;

public class Day05 extends AdventOfCode{

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		CPUControl cpu = new CPUControl(1);
		cpu.loadProgramAtCoreId("src/y2019/day05/day05Input.txt",0);
		cpu.addInputToCore(1, 0);
		cpu.executePrograms();
	}

	@Override
	public void part2() throws Exception {
		CPUControl cpu = new CPUControl(1);
		cpu.loadProgramAtCoreId("src/y2019/day05/day05Input.txt",0);
		cpu.addInputToCore(5, 0);
		cpu.executePrograms();
	}
}
