package y2019.day05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	private int[] parameterModes = new int[2];
	private int[] inputBand;

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram();
		cpu.executeProgram(1);
	}

	@Override
	public void part2() throws Exception {
		IntCodeCPU cpu = new IntCodeCPU();
		cpu.loadProgram();
		cpu.executeProgram(5);
	}
}
