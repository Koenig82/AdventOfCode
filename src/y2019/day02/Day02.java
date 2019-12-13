package y2019.day02;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import adventOfCode.AdventOfCode;

public class Day02 extends AdventOfCode{

	public static void main(String[] args) {
		new Day02().run();
	}

	@Override
	public void part1() throws Exception {
		
		int[] array = getInput();
		
		preCalibrateNoun(array,12);
		preCalibrateVerb(array,2);

		System.out.println("Result = "+runMachine(array));
	}

	@Override
	public void part2() throws Exception {
		
		int[] array = getInput();
		
		for(int nounCount = 0;nounCount < 100;nounCount++) {
			for(int verbCount = 0;verbCount<100;verbCount++) {
				preCalibrateNoun(array,nounCount);
				preCalibrateVerb(array,verbCount);
				if(runMachine(array) == 19690720) {
					System.out.println("Result = "+((100*nounCount)+verbCount));
					break;
				}
				array = getInput();
			}
			array = getInput();
		}
	}
	
	private int[] getInput() throws UnsupportedEncodingException, IOException {
		
		String input = new String(Files.readAllBytes(Paths.get("src/y2019/day02/day02Input.txt")), "UTF-8");
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
				i = opcode1(array, i);
			}else if(array[i] == 2) {
				i = opcode2(array, i);
			}else if(array[i] == 99){
				break;
			}
		}
		return array[0];
	}
	
	private int opcode1(int[] array, int index) {
		array[array[index+3]] = array[array[index+1]] + array[array[index+2]];
		return index+3;
	}
	
	private int opcode2(int[] array, int index) {
		array[array[index+3]] = array[array[index+1]] * array[array[index+2]];
		return index+3;
	}
	
	private void preCalibrateNoun(int[] array, int x) {
		array[1] = x;
	}
	private void preCalibrateVerb(int[] array, int x) {
		array[2] = x;
	}
}
