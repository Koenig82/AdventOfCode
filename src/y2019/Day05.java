package y2019;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;

import adventOfCode.AdventOfCode;

public class Day05 extends AdventOfCode{
	
	private ArrayDeque<Integer> input = new ArrayDeque<>();
	private int[] parameterModes = new int[2];
	private int[] machineCode;

	public static void main(String[] args) {
		new Day05().run();
	}

	@Override
	public void part1() throws Exception {
		
		getInstructions();
		addInput(1);
		parameterModes = new int[]{0,1};
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
				i = opcode2(i);
			}else if(machineCode[i] == 3) {
				i = opcode3(machineCode, i);
			}else if(machineCode[i] == 99){
				break;
			}
		}
		return machineCode[0];
	}
	
	private int opcode1(int head) {
		int[] parameters = modeSwitch(head, 1);

		machineCode[machineCode[head+3]] = parameters[0] + parameters[1];
		return head+3;
	}
	private int opcode2(int head) {
		int[] parameters = modeSwitch(head, 2);

		machineCode[machineCode[head+3]] = parameters[0] * parameters[1];
		return head+3;
	}
	private int opcode3(int[] array, int index) {
		array[array[index+1]] = array[index+1];
		return index;
	}
	private int[] modeSwitch(int head, int opCode) {
		switch (opCode) {
		case 1:
		case 2:
			//fixa att varje parameter har egen parametermode
			if(parameterModes[0] == 0) {
				return new int[]{machineCode[machineCode[head+1]],
								 machineCode[machineCode[head+2]],
								 machineCode[machineCode[head+3]]};
			}else if(parameterModes[0] == 1) {
				return new int[]{machineCode[head+1],
						 		 machineCode[head+2],
						 		 machineCode[head+3]};
			}
			break;
		case 3:

			break;
		case 4:

			break;
		default:
			throw new IllegalArgumentException("modeSwitch Error. unknown opcode: " + opCode);
		}
		return new int[] {1,1};
		
	}
	
	
	private void preCalibrateNoun(int[] array, int x) {
		array[1] = x;
	}
	private void preCalibrateVerb(int[] array, int x) {
		array[2] = x;
	}
}
