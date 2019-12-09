package y2019;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	int[] parameterModes = new int[3];
	int[] machineCode;

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		
		getInstructions();
		addInput(1);
		System.out.println("Result = "+runMachine());
	}

	@Override
	public void part2() throws Exception {
		
		//int[] array = getInput();	
	}
	
	private void getInstructions() throws UnsupportedEncodingException, IOException {
		
		String instructions = new String(Files.readAllBytes(Paths.get("src/y2019/testinput")), "UTF-8");
		//String input = new String(Files.readAllBytes(Paths.get("src/y2019/day05Input.txt")), "UTF-8");
		String[] substrings = instructions.split(",");

		int[] intArray = new int[substrings.length];
		for (int i = 0; i < substrings.length; i++) {
			intArray[i] = Integer.parseInt(substrings[i]);
		}
		this.machineCode = intArray;
	}
	
	private void addInput(int input) {
		this.input.add(input);
	}
	
	private int runMachine() {
		for(int i = 0;i < machineCode.length; i++) {
			if(machineCode[i] == 1) {
				i = opcode1(i);
			}else if(machineCode[i] == 2) {
				i = opcode2(machineCode, i);
			}else if(machineCode[i] == 3) {
				i = opcode3(machineCode, i);
			}else if(machineCode[i] == 99){
				break;
			}
		}
		return machineCode[0];
	}
	
	private int opcode1(int head) {
		int[] parameters = modeSwitch(head);

		machineCode[machineCode[head+3]] = parameters[0] + machineCode[machineCode[head+2]];
		return head+3;
	}
	private int[] modeSwitch(int head) {
		
		
	}
	private int opcode2(int[] array, int index) {
		array[array[index+3]] = array[array[index+1]] * array[array[index+2]];
		return index+3;
	}
	
	private int opcode3(int[] array, int index) {
		array[array[index+1]] = array[index+1];
		return index;
	}
	
	private void preCalibrateNoun(int[] array, int x) {
		array[1] = x;
	}
	private void preCalibrateVerb(int[] array, int x) {
		array[2] = x;
	}
}
