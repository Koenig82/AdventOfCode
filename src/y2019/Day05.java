package y2019;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	private int parameterMode = 0;

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		
		int[] array = getInput();

		System.out.println("Result = "+runMachine(array));
	}

	@Override
	public void part2() throws Exception {
		
		//int[] array = getInput();
		
		
	}
	
	private int[] getInput() throws UnsupportedEncodingException, IOException {
		
		String input = new String(Files.readAllBytes(Paths.get("src/y2019/day05Input.txt")), "UTF-8");
		String[] substrings = input.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		return intArray;
	}
	
	private int runMachine(int[]array) {
		for(int i = 0;i < array.length; i++) {
			if(array[i] == 1) {
				i = opcode1(array, i, parameterMode);
			}else if(array[i] == 2) {
				i = opcode2(array, i, parameterMode);
			}else if(array[i] == 3) {
				i = opcode3(array, i, parameterMode);
			}else if(array[i] == 99){
				break;
			}
		}
		return array[0];
	}
	
	private int opcode1(int[] array, int index, int mode) {
		array[array[index+3]] = array[array[index+1]] + array[array[index+2]];
		return index+3;
	}
	
	private int opcode2(int[] array, int index, int mode) {
		array[array[index+3]] = array[array[index+1]] * array[array[index+2]];
		return index+3;
	}
	
	private int opcode3(int[] array, int index, int mode) {
		array[array[index+1]] = array[index+1];
		return index+1;
	}
	
	private void preCalibrateNoun(int[] array, int x) {
		array[1] = x;
	}
	private void preCalibrateVerb(int[] array, int x) {
		array[2] = x;
	}

}
